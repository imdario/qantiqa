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
import static constants.HttpMethod.GET;
import network.services.SearchService;
import annotations.Formats;
import annotations.Methods;

/**
 * REST API methods for searching.
 * 
 * @author Dario
 */
public class Qsearch extends QController {

	/**
	 * From Twitter official doc {@linkplain http
	 * ://apiwiki.twitter.com/Twitter-Search-API-Method:-search}
	 * 
	 * Returns tweets that match a specified query.
	 * 
	 * @param q
	 *            The text to search for.
	 */
	@Methods( { GET })
	@Formats( { ATOM, JSON })
	public static void index(String q) {
		SearchService ssv = new SearchService(getOverlay());

		try {
			renderProtobuf(ssv.searchQuarks(q));
		} catch (Exception e) {
			renderError(e);
		}
	}
}
