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

import org.homedns.mkh.ui.client.frame.BasePanel;
import org.homedns.mkh.ui.client.frame.Content;
import org.homedns.mkh.ui.client.frame.PanelBuilder;
import org.homedns.mkh.ui.client.frame.ViewAccessHandler;

/**
 * Admin's desk
 *
 */
@SuppressWarnings( "unchecked" )
public class AdminDesk extends BasePanel {
	
	private AdminDesk( ) {
		setDefaults( new ViewAccessHandler( ) );
		DeskConfig cfg = new DeskConfig( );
		ContentPanel content = ( ContentPanel )cfg.getChilds( ).get( 2 ).getPanel( );
		content.setTag( "AdminSheet" );
		addContent( ( Content )content );
		PanelBuilder.build( this, cfg );
	}
	
	/**
	* Create customized token instance, which implements panel
	* creation on-demand
	*
	* @return the panel token
	*/
	public static Token createToken( ) {
		Token token = new Token( "AdminSheet" ) {
			public BasePanel createPanel( ) {
				return( new AdminDesk( ) );
			}
		};
		return( token );
	}

	/**
	 * Adds content to the wrapper panel
	 * 
	 * @param content
	 *            the wrapper panel
	 */
	private void addContent( Content content ) {
		content.add( new UserPanel( ) );
		content.add( new RolePanel( ) );
		content.add( new UserLogPanel( ) );
		content.add( new SysParametersPanel( ) );
	}
}
