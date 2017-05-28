/**
 * Mar 29, 2017 9:39:46 PM Messages.java Created by Tristan Treboz
 */
package com.ttr.math.function.msg;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Tristan Treboz
 *
 */
public class Messages {
	private static final String			BUNDLE_NAME		= "com.ttr.math.function.msg.messages";	//$NON-NLS-1$

	private static final ResourceBundle	RESOURCE_BUNDLE	= ResourceBundle.getBundle(BUNDLE_NAME);

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (final MissingResourceException e) {
			return '!' + key + '!';
		}
	}

	private Messages() {
	}
}
