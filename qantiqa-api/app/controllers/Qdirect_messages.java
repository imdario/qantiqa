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

package controllers;

import static constants.Format.ATOM;
import static constants.Format.JSON;
import static constants.Format.RSS;
import static constants.Format.XML;
import static constants.HttpMethod.GET;
import im.dario.qantiqa.common.protocol.Protocol;
import annotations.Formats;
import annotations.Methods;
import annotations.RequiresAuthentication;

/**
 * REST API methods for direct messages.
 * 
 * @author Dario
 */
public class Qdirect_messages extends QController {

	/**
	 * From Twitter official doc {@linkplain http
	 * ://dev.twitter.com/doc/get/direct_messages}
	 * 
	 * Returns a list of the 20 most recent direct messages sent to the
	 * authenticating user. The XML and JSON versions include detailed
	 * information about the sending and recipient users.
	 * 
	 * @param count
	 *            Specifies the number of records to retrieve. May not be
	 *            greater than 200.
	 */
	@Methods( { GET })
	@Formats( { XML, JSON, RSS, ATOM })
	@RequiresAuthentication
	public static void index(Integer count) {
		renderProtobuf(Protocol.direct_messages.newBuilder().build());
	}

	/**
	 * From Twitter official doc {@linkplain http
	 * ://dev.twitter.com/doc/get/direct_messages/sent}
	 * 
	 * Returns a list of the 20 most recent direct messages sent by the
	 * authenticating user. The XML and JSON versions include detailed
	 * information about the sending and recipient users
	 * 
	 * @param count
	 *            Specifies the number of records to retrieve. May not be
	 *            greater than 200.
	 */
	@Methods( { GET })
	@Formats( { XML, JSON, RSS, ATOM })
	@RequiresAuthentication
	public static void sent(Integer count) {
		renderProtobuf(Protocol.direct_messages.newBuilder().build());
	}
}
