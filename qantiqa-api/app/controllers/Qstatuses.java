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
import static constants.HttpMethod.DELETE;
import static constants.HttpMethod.GET;
import static constants.HttpMethod.POST;
import static constants.HttpMethod.PUT;
import im.dario.qantiqa.common.protocol.Protocol;

import java.util.Set;

import network.services.QuarkService;
import network.services.RelationshipService;
import network.services.SearchService;
import annotations.Formats;
import annotations.Methods;
import annotations.RequiresAuthentication;

/**
 * REST API methods for statuses/tweets/quarks.
 * 
 * @author Dario
 */
public class Qstatuses extends QController {

	/**
	 * From Twitter official doc {@linkplain http
	 * ://dev.twitter.com/doc/get/statuses/home_timeline}
	 * 
	 * Returns the 20 most recent statuses, including retweets, posted by the
	 * authenticating user and that user's friends. This is the equivalent of
	 * /timeline/home on the Web.
	 * 
	 * @param count
	 *            Specifies the number of records to retrieve. May not be
	 *            greater than 200.
	 * @param since_id
	 *            Returns results with an ID greater than (that is, more recent
	 *            than) the specified ID.
	 */
	@Methods( { GET })
	@Formats( { XML, JSON, ATOM })
	@RequiresAuthentication
	public static void home_timeline(Integer count, Long since_id) {
		RelationshipService rsv = new RelationshipService(getOverlay());
		Protocol.user request = getRequestUser();

		Set<Long> users = rsv.following(request);
		users.add(request.getId());

		QuarkService qsv = new QuarkService(getOverlay());
		renderProtobuf(qsv.timelines(users, count, since_id));
	}

	/**
	 * From Twitter official doc {@linkplain http
	 * ://dev.twitter.com/doc/get/statuses/mentions}
	 * 
	 * Returns the 20 most recent mentions (status containing @username) for the
	 * authenticating user.
	 * 
	 * @param count
	 *            Specifies the number of records to retrieve. May not be
	 *            greater than 200.
	 */
	@Methods( { GET })
	@Formats( { XML, JSON, RSS, ATOM })
	@RequiresAuthentication
	public static void mentions(Integer count) {
		SearchService ssv = new SearchService(getOverlay());

		try {
			renderProtobuf(ssv.searchQuarks("@" + request.user));
		} catch (Exception e) {
			renderError(e);
		}
	}

	/**
	 * From Twitter official doc {@linkplain http
	 * ://dev.twitter.com/doc/get/statuses/user_timeline}
	 * 
	 * Returns the 20 most recent statuses posted from the authenticating user.
	 * It's also possible to request another user's timeline via the id
	 * parameter.
	 * 
	 * This is the equivalent of the Web / page for your own user, or the
	 * profile page for a third party.
	 * 
	 * @param id
	 *            Specifies the ID or screen name of the user for whom to return
	 *            results for.
	 * @param count
	 *            Specifies the number of records to retrieve. May not be
	 *            greater than 200.
	 */
	@Methods( { GET })
	@Formats( { XML, JSON, RSS, ATOM })
	public static void user_timeline(String id, Integer count) {
		if (id == null) {
			id = request.user;
		}

		Protocol.user user = getUser(id, "target");

		QuarkService qsv = new QuarkService(getOverlay());
		renderProtobuf(qsv.timeline(user.getId(), count));
	}

	/**
	 * From Twitter official doc {@linkplain http
	 * ://dev.twitter.com/doc/get/statuses/friends_timeline}
	 * 
	 * Returns the 20 most recent statuses posted by the authenticating user and
	 * that user's friends. This is the equivalent of /timeline/home on the Web.
	 * 
	 * @param count
	 *            Specifies the number of records to retrieve. May not be
	 *            greater than 200.
	 * @param since_id
	 *            Returns results with an ID greater than (that is, more recent
	 *            than) the specified ID.
	 */
	@Methods( { GET })
	@Formats( { XML, JSON, RSS, ATOM })
	public static void friends_timeline(Integer count, Long since_id) {
		RelationshipService rsv = new RelationshipService(getOverlay());

		Protocol.user request = getRequestUser();
		Set<Long> users = rsv.following(request);

		QuarkService qsv = new QuarkService(getOverlay());
		renderProtobuf(qsv.timelines(users, count, since_id));
	}

	@Methods( { GET })
	@Formats( { XML, JSON, RSS, ATOM })
	public static void replies(String id, Integer count) {
		renderProtobuf(Protocol.statuses.newBuilder().build());
	}

	/**
	 * From Twitter official doc {@linkplain http
	 * ://dev.twitter.com/doc/get/statuses/retweeted_by_me}
	 * 
	 * Returns the 20 most recent retweets posted by the authenticating user.
	 * 
	 * @param count
	 *            Specifies the number of records to retrieve. May not be
	 *            greater than 200.
	 */
	@Methods( { GET })
	@Formats( { XML, JSON, ATOM })
	@RequiresAuthentication
	public static void retweeted_by_me(Integer count) {
		QuarkService qsv = new QuarkService(getOverlay());

		try {
			qsv.requarks(request.user);
		} catch (Exception e) {
			renderError(e);
		}
	}

	/**
	 * From Twitter official doc {@linkplain http
	 * ://dev.twitter.com/doc/get/statuses/show}
	 * 
	 * Returns a single status, specified by the id parameter below. The
	 * status's author will be returned inline.
	 * 
	 * @param id
	 *            The numerical ID of the desired status.
	 */
	@Methods( { GET })
	@Formats( { XML, JSON })
	public static void show(Long id) {
		QuarkService qsv = new QuarkService(getOverlay());

		try {
			renderProtobuf(appendUser(qsv.show(id), id));
		} catch (Exception e) {
			renderError(e);
		}
	}

	/**
	 * From Twitter official doc {@linkplain http
	 * ://dev.twitter.com/doc/post/statuses/update}
	 * 
	 * Updates the authenticating user's status.
	 * 
	 * A status update with text identical to the authenticating user's text
	 * identical to the authenticating user's current status will be ignored to
	 * prevent duplicates.
	 * 
	 * @param status
	 *            The text of your status update, up to 140 characters. URL
	 *            encode as necessary.
	 * @param in_reply_to_status_id
	 *            The ID of an existing status that the update is in reply to.
	 * @param source
	 *            App ID which status is created from.
	 */
	@Methods( { POST })
	@Formats( { XML, JSON })
	@RequiresAuthentication
	public static void update(String status, Long in_reply_to_status_id,
			String source) {
		QuarkService qsv = new QuarkService(getOverlay());

		try {
			renderProtobuf(qsv.update(getRequestUser(), status,
					in_reply_to_status_id, source));
		} catch (Exception e) {
			renderError(e);
		}
	}

	/**
	 * From Twitter official doc {@linkplain http
	 * ://dev.twitter.com/doc/post/statuses/destroy}
	 * 
	 * Destroys the status specified by the required ID parameter.
	 * 
	 * Usage note: The authenticating user must be the author of the specified
	 * status.
	 * 
	 * @param id
	 *            The numerical ID of the desired status.
	 */
	@Methods( { POST, DELETE })
	@Formats( { XML, JSON })
	@RequiresAuthentication
	public static void destroy(Long id) {
		QuarkService qsv = new QuarkService(getOverlay());

		try {
			renderProtobuf(appendUser(qsv.destroy(id), id));
		} catch (Exception e) {
			renderError(e);
		}
	}

	/**
	 * From Twitter official doc {@linkplain http
	 * ://dev.twitter.com/doc/post/statuses/retweet/:id}
	 * 
	 * Retweets a tweet. Returns the original tweet with retweet details
	 * embedded.
	 * 
	 * @param id
	 *            The numerical ID of the desired status.
	 * @param source
	 *            App ID which status is created from.
	 */
	@Methods( { POST, PUT })
	@Formats( { XML, JSON })
	@RequiresAuthentication
	public static void retweet(Long id, String source) {
		QuarkService qsv = new QuarkService(getOverlay());

		try {
			Protocol.status requark = qsv.requark(getRequestUser(), id, source);

			renderProtobuf(requark);
		} catch (Exception e) {
			renderRetweetError(e);
		}
	}
}
