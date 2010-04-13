/*******************************************************************************
 * Qantiqa : Decentralized microblogging platform
 * Copyright (C) 2010 Dario (i@dario.im) 
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 ******************************************************************************/

package network;

import im.dario.qantiqa.common.higgs.HiggsWS;
import im.dario.qantiqa.common.protocol.Protocol;
import im.dario.qantiqa.common.protocol.Protocol.AuthResult;
import im.dario.qantiqa.common.protocol.Protocol.authentication;
import im.dario.qantiqa.common.utils.AsyncResult;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import network.utils.QastContent;
import network.utils.QastListener;
import rice.p2p.commonapi.NodeHandle;
import rice.pastry.socket.SocketNodeHandle;

import com.google.protobuf.Message;
import com.google.protobuf.Message.Builder;

import easypastry.cast.CastHandler;
import easypastry.core.PastryConnection;
import easypastry.core.PastryKernel;

/**
 * Qantiqa overlay
 * 
 * Handles all the request done by peers to other peers and gluons.
 * 
 * @author Dario
 */
public class Overlay {

    private final PastryConnection conn;

    private final CastHandler cast;

    /**
     * Reference to our bootstrap gluon.
     */
    private final AsyncResult<NodeHandle> gluon;

    public static Overlay init(String configPath) {
        if (configPath == null) {
            throw new RuntimeException("Invalid config path");
        }

        Overlay ov = null;
        String easyPastryConfigPath = configPath + "/easypastry-config.xml";

        // Get the current gluon list and try to connect.
        for (String gluon : HiggsWS.gluons().getGluonList()) {
            String[] data = gluon.split(":");
            try {
                modifyConfig(easyPastryConfigPath, data[0], data[1]);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            try {
                ov = new Overlay(easyPastryConfigPath, data[0], data[1]);
            } catch (IOException e) {
                ov = null;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (ov != null) {
                break;
            }
        }

        if (ov == null) {
            throw new RuntimeException("Unable to join Qantiqa overlay");
        }

        return ov;
    }

    /**
     * Debugging method used by qa-gluon-mock to simulate the gluon
     * initialization.
     * 
     * @param configPath
     *            easypastry-config.xml path
     * @return
     */
    public static Overlay initGluon(String configPath) {
        Overlay overlay = new Overlay(configPath);

        final CastHandler cast = PastryKernel.getCastHandler();

        return overlay;
    }

    /**
     * Private constructor.
     * 
     * @param configPath
     *            easypastry-config.xml path
     * @param host
     *            Bootstrap gluon IP
     * @param sPort
     *            Bootstrap gluon port
     * @throws IOException
     * @throws Exception
     */
    private Overlay(String configPath, final String host, String sPort)
            throws IOException, Exception {
        this(configPath);

        /*
         * This is a dumb listener just used to get the NodeHandler of our
         * bootstrap gluon.
         */
        final Integer port = Integer.valueOf(sPort);
        cast.addDeliverListener("gluon", new QastListener() {
            @Override
            public void hostUpdate(rice.p2p.commonapi.NodeHandle nh,
                    boolean joined) {
                if (joined) {
                    if (nh instanceof SocketNodeHandle) {
                        SocketNodeHandle snh = (SocketNodeHandle) nh;
                        InetSocketAddress address = snh.getInetSocketAddress();
                        if (address.getAddress().getHostAddress().equals(host)) {
                            if (address.getPort() == port) {
                                // This is our gluon.
                                gluon.set(nh);
                            }
                        }
                    }
                }
            }
        });

        // TODO Create a protocol listeners registry.
        // Register authentication subject/topic.
        cast.addDeliverListener(Protocol.authentication.class.getSimpleName(),
                new QastListener() {

                    @Override
                    public void hostUpdate(NodeHandle nh, boolean joined) {
                        if (joined) {
                            System.out.println(nh + " joined.");
                        }
                    }

                    @Override
                    public void contentDelivery(QastContent qc) {
                        NodeHandle nh = qc.getSource();

                        try {
                            Protocol.authentication auth = qc
                                    .getMessage(Protocol.authentication.class);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // TODO check authentication

                        Protocol.authentication_response.Builder rs = Protocol.authentication_response
                                .newBuilder();
                        rs.setResult(AuthResult.NOT_VALID);

                        sendToPeer(nh, rs);
                    }
                });

        this.conn.bootNode();
    }

    /**
     * 
     * @param configPath
     *            easypastry-config.xml path
     * @return
     */
    private Overlay(String configPath) {
        try {
            PastryKernel.init(configPath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.conn = PastryKernel.getPastryConnection();
        this.cast = PastryKernel.getCastHandler();

        this.gluon = new AsyncResult<NodeHandle>();
        this.gluon.set(conn.getNode().getLocalNodeHandle());
    }

    /**
     * Debugging method to boot qa-gluon-mock node.
     */
    public void bootGluon() {
        try {
            this.conn.bootNode();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Auxiliary method used to modify the easypastry-config.xml because there
     * is no way to do this programmatically, so this is a workaround.
     * 
     * @param easyPastryConfigPath
     * @param host
     *            Bootstrap gluon IP
     * @param port
     *            Bootstrap gluon port
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InvalidPropertiesFormatException
     */
    private static void modifyConfig(String easyPastryConfigPath, String host,
            String port) throws FileNotFoundException, IOException,
            InvalidPropertiesFormatException {
        FileInputStream fis = new FileInputStream(easyPastryConfigPath);
        Properties prop = new Properties();
        prop.loadFromXML(fis);
        fis.close();

        prop.setProperty("host", host);
        prop.setProperty("port", port);

        FileOutputStream fos = new FileOutputStream(easyPastryConfigPath);
        prop.storeToXML(fos, null);
        fos.close();
    }

    /**
     * Auxiliary method to send a direct protobuf message to our bootstrap
     * gluon.
     * 
     * This registers a handle for the corresponding answer from gluon (kind of
     * client/server interaction over Pastry).
     * 
     * TODO Find a way to destroy this registered handle after receiving the
     * result.
     * 
     * @param builder
     * @param result
     *            Async result handler
     * @param messageClass
     *            Class of the message built by the builder
     */
    public void sendToGluon(Builder builder, final AsyncResult<?> result,
            final Class<? extends Message> messageClass) {
        String subject = builder.getDescriptorForType().getName();
        sendToPeer(this.gluon.get(), builder);

        cast.addDeliverListener(subject + "_response", new QastListener() {
            @Override
            public void contentDelivery(QastContent qc) {
                try {
                    Message msg = qc.getMessage(messageClass);
                    Method m = msg.getClass().getMethod("getResult");

                    result.set(m.invoke(msg));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Sends a direct protobuf message to a peer/gluon.
     * 
     * @param nh
     * @param builder
     */
    private void sendToPeer(NodeHandle nh, Builder builder) {
        Message msg = builder.build();
        String subject = msg.getDescriptorForType().getName();

        cast.sendDirect(nh, new QastContent(subject, msg));
    }
}
