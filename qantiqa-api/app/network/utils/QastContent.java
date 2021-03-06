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

package network.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import rice.p2p.commonapi.NodeHandle;

import com.google.protobuf.Message;

import easypastry.cast.CastContent;
import easypastry.sample.AppCastContent;

/**
 * {@link AppCastContent} class wrapper for Protobuf handling.
 * 
 * It uses the {@link AppCastContent} fields because extending from CastContent
 * causes a weird exception with Java serialization.
 * 
 * @author Dario
 */
public class QastContent {

	private static final Logger log = Logger.getLogger(QastContent.class);

	private final CastContent acc;

	public QastContent(CastContent acc) {
		this.acc = acc;
	}

	/**
	 * Parses a message from a String for a given class.
	 * 
	 * @param sMsg
	 *            Message in serialized form
	 * @param klass
	 *            Message class
	 * @return Message deserialized or null if there is an exception.
	 */
	@SuppressWarnings("unchecked")
	public <V extends Message> V getMessage(Class<V> klass) {
		V msg;

		if (klass == null) {
			msg = null;
		} else {
			Field field;
			try {
				field = AppCastContent.class.getDeclaredField("txt");
				field.setAccessible(true);

				Method m = klass.getDeclaredMethod("parseFrom",
						new Class<?>[] { byte[].class });
				msg = (V) m.invoke(null, ((String) field.get(acc)).getBytes());
			} catch (Exception e) {
				log.error("ERR", e);
				msg = null;
			}
		}

		return msg;
	}

	public NodeHandle getSource() {
		return acc.getSource();
	}
}
