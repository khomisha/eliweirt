/*
 * Copyright 2013-2014 Mikhail Khodonov
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * $Id$
 */

package org.homedns.mkh.eliweirt.client;

import org.homedns.mkh.dataservice.client.AbstractEntryPoint;
import org.homedns.mkh.ui.client.event.NavigationEvent;
import com.google.gwt.core.client.GWT;
import com.gwtext.client.widgets.Viewport;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Eliweirt extends AbstractEntryPoint {
	public static final Resources RESOURCES = GWT.create( Resources.class );

	private static Viewport _viewport;

	/**
	 * @see org.homedns.mkh.dataservice.client.AbstractEntryPoint#onStartApplication()
	 */
	@Override
	protected void onStartApplication( ) {
		super.onStartApplication( );
		setConstants( ( AppConstants )GWT.create( AppConstants.class ) );
		setMessages( ( AppMessages )GWT.create( AppMessages.class ) );
		setDownloadURL( GWT.getModuleBaseURL( ) + "download" );
		_viewport = new Viewport( AppMainPanel.getInstance( ) );
		NavigationEvent.fire( AppMainPanel.LOGIN_PANEL );
	}

	/**
	 * @return the view port
	 */
	public static Viewport getViewport( ) {
		return( _viewport );
	}
}
