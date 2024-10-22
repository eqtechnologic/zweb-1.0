/* ServletLabelLocator.java

	Purpose:
		
	Description:
		
	History:
		Sat Apr  8 19:51:08     2006, Created by tomyeh

Copyright (C) 2006 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under LGPL Version 3.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.web.util.resource;

import java.util.Locale;
import java.io.IOException;
import java.net.URL;
import jakarta.servlet.ServletContext;

import org.zkoss.lang.Library;
import org.zkoss.util.resource.LabelLocator;

/**
 * Used by Lables to load labels from a servlet context.
 *
 * @author tomyeh
 */
public class ServletLabelLocator implements LabelLocator {
	private final ServletContext _ctx;
	public ServletLabelLocator(ServletContext ctx) {
		if (ctx == null)
			throw new IllegalArgumentException("null");
		_ctx = ctx;
	}

	//-- LabelLocator --//
	public URL locate(Locale locale) throws IOException {
		init();
		return _ctx.getResource(
			locale == null ? PREFIX + SUFFIX: PREFIX + '_' + locale + SUFFIX);
	}
	private static final void init() {
		if (PREFIX == null) {
			String s = Library.getProperty("org.zkoss.util.label.web.location", 
				"/WEB-INF/i3-label.properties");
			int j = s.lastIndexOf('.');
			PREFIX = j >= 0 ? s.substring(0, j): s;
			SUFFIX = j >= 0 ? s.substring(j): "";
		}
	}
	private static String PREFIX, SUFFIX;

	//-- Object --//
	public int hashCode() {
		return _ctx.hashCode();
	}
	public boolean equals(Object o) {
		return o instanceof ServletLabelLocator
			&& ((ServletLabelLocator)o)._ctx.equals(_ctx);
	}
}
