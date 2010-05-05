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

package network.services;

import im.dario.qantiqa.common.protocol.Protocol;
import im.dario.qantiqa.common.utils.QantiqaException;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import network.Overlay;
import network.Storage;

/**
 * Search service.
 * 
 * @author Dario
 */
public class SearchService extends Service {

    public SearchService(Overlay overlay) {
        super(overlay);
    }

    public Protocol.users searchUsers(String q) throws QantiqaException {
        Storage<HashSet<Long>> ix = (Storage<HashSet<Long>>) Storage.usersById
                .getIndex();

        Set<Long> results = overlay.retrieve(ix, q);

        StringTokenizer tk = new StringTokenizer(q, " _.,;");
        while (tk.hasMoreElements()) {
            String next = tk.nextToken().trim();
            if (!next.equals("")) {
                results.addAll(overlay.retrieve(ix, next));
            }
        }

        UserService usv = new UserService(overlay);
        Protocol.users.Builder builder = Protocol.users.newBuilder();
        for (Long id : results) {
            builder.addUser(usv.get(id));
        }

        return builder.build();
    }

    // TODO Implement
    public Protocol.users searchQuarks(String q) throws QantiqaException {
        return null;
    }
}
