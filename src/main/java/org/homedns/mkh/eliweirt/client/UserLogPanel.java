/*
 * Copyright 2014 Mikhail Khodonov
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

import org.homedns.mkh.dataservice.shared.Id;
import org.homedns.mkh.ui.client.frame.Tab;
import org.homedns.mkh.ui.client.grid.Grid;
import org.homedns.mkh.ui.client.grid.GridConfig;
import org.homedns.mkh.ui.client.grid.GridImpl;
import com.google.gwt.user.client.Command;
import com.gwtext.client.widgets.layout.FitLayout;

/**
 * User's log panel
 *
 */
public class UserLogPanel extends Tab {
	private static final AppConstants CONSTANTS = ( AppConstants )Eliweirt.getConstants( );

	public UserLogPanel( ) {
		super( CONSTANTS.userLog( ) );
		setLayout( new FitLayout( ) );
		Id id = new Id( );
		id.setName( "acr_user_log" );
		final Grid userLogGrid = new Grid( id, new GridImpl( new GridConfig( ) ) );
		userLogGrid.setAfterInitCommand(
			new Command( ) {
				public void execute( ) {
					UserLogPanel.this.add( userLogGrid );
					UserLogPanel.this.doLayout( );
				}
			}
		);
		setTag( "acr_user_log" );
	}
}
