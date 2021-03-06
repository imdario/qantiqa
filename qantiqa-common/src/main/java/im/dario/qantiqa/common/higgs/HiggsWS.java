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

package im.dario.qantiqa.common.higgs;

import im.dario.qantiqa.common.protocol.Protocol;
import im.dario.qantiqa.common.protocol.Protocol.AuthResult;
import im.dario.qantiqa.common.protocol.format.QantiqaFormat;
import im.dario.qantiqa.common.utils.QantiqaException;

import java.util.HashMap;
import java.util.Map;

import play.Play;
import play.libs.WS.HttpResponse;

import com.google.protobuf.Message.Builder;

/**
 * Higgs webservice client.
 * 
 * Allows to interact with Higgs public API for gluons. It depends on Play
 * because our webapps depend too.
 * 
 * @author Dario
 */
public class HiggsWS {

	private static Map<String, Protocol.authentication> cachedAuths = new HashMap<String, Protocol.authentication>();
	private static Map<String, Protocol.authentication_response> cachedAuthResponses = new HashMap<String, Protocol.authentication_response>();

	private HiggsWS() {
	}

	/**
	 * Validates a gluon against our official list.
	 * 
	 * @see {@link HiggsWS#gluons()}
	 * 
	 * @param port
	 *            Port published by the gluon.
	 * @param secret
	 *            Secret used to validate the gluon against the list.
	 * @return Validation result message
	 * @throws QantiqaException
	 */
	public static Protocol.validation validate(Integer port, String secret)
			throws QantiqaException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("secret", play.libs.Codec.hexMD5(secret));
		params.put("port", port);

		return get("validate", params, Protocol.validation.newBuilder())
				.build();
	}

	/**
	 * Get the official gluon (supernode) list from Higgs.
	 * 
	 * @return Gluon list message
	 * @throws QantiqaException
	 */
	public static Protocol.gluons gluons() throws QantiqaException {
		return get("gluons", null, Protocol.gluons.newBuilder()).build();
	}

	/**
	 * Authenticate given user credentials auth.
	 * 
	 * @param auth
	 * @return Authentication response (AuthResult)
	 * @throws QantiqaException
	 */
	public static Protocol.authentication_response authenticate(
			Protocol.authentication auth) {
		Protocol.authentication_response response;
		Protocol.authentication cached = cachedAuths.get(auth.getUsername());

		if (auth.equals(cached)) {
			response = cachedAuthResponses.get(auth.getUsername());
		} else {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("password", auth.getPassword());
			params.put("username", auth.getUsername());

			try {
				response = get("authenticate", params,
						Protocol.authentication_response.newBuilder()).build();
			} catch (QantiqaException e) {
				e.printStackTrace();
				response = Protocol.authentication_response.newBuilder()
						.setResult(AuthResult.ERROR).build();
			}

			if (response.getResult().equals(AuthResult.VALID)) {
				cachedAuths.put(auth.getUsername(), auth);
				cachedAuthResponses.put(auth.getUsername(), response);
			}
		}

		return response;
	}

	/**
	 * 
	 * @param userId
	 * @param userAddress
	 * @param sessionId
	 * @return
	 * @throws QantiqaException
	 */
	public static Protocol.validation verify_session(Long userId,
			String userAddress, String sessionId) throws QantiqaException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user_id", userId);
		params.put("user_address", userAddress);
		params.put("session_id", sessionId);

		return get("verify_session", params, Protocol.validation.newBuilder())
				.build();
	}

	/**
	 * Auxiliary method to retrieve data in Protobuf format from Higgs.
	 * 
	 * @param action
	 * @param params
	 * @param builder
	 * @return
	 * @throws QantiqaException
	 */
	private static <V extends Builder> V get(String action,
			Map<String, Object> params, V builder) throws QantiqaException {
		try {
			HttpResponse rs = play.libs.WS.url(getHiggsURL() + "/" + action)
					.params(params).get();
			QantiqaFormat.merge(rs.getStream(), builder);

			return builder;
		} catch (Exception e) {
			throw new QantiqaException(e);
		}
	}

	/**
	 * Auxiliary method to get the Higgs URL from Play configuration.
	 * 
	 * @return Higgs webservice URL
	 */
	private static String getHiggsURL() {
		return Play.configuration.getProperty("qantiqa.higgs.url");
	}
}
