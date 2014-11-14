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
import org.homedns.mkh.ui.client.command.CommandFactory;
import org.homedns.mkh.ui.client.command.UpdateCmd;
import org.homedns.mkh.ui.client.frame.Tab;
import org.homedns.mkh.ui.client.grid.GridConfig;
import org.homedns.mkh.ui.client.grid.PropertyGrid;
import org.homedns.mkh.ui.client.grid.PropertyGridImpl;
import com.google.gwt.user.client.Command;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;

/**
 * System parameters panel
 *
 */
public class SysParametersPanel extends Tab {
	private static final AppConstants CONSTANTS = ( AppConstants )Eliweirt.getConstants( );

	public SysParametersPanel( ) {
		super( CONSTANTS.sysParams( ) );
		setLayout( new FitLayout( ) );
		Id id = new Id( );
		id.setName( "sms_slot_floor" );
		final PropertyGrid gridSysParams = new PropertyGrid( 
			id, 
			new PropertyGridImpl( new GridConfig( ) ) 
		);
		final Command cmd = CommandFactory.create( 
			UpdateCmd.class, gridSysParams 
		);
		final Button btnSave = new Button( 
			CONSTANTS.save( ), 				
			new ButtonListenerAdapter( ) {
				public void onClick( Button button, EventObject e ) {
					cmd.execute( );
				}
			}
		);
		final Button btnCancel = new Button( 
			CONSTANTS.cancel( ), 				
			new ButtonListenerAdapter( ) {
				public void onClick( Button button, EventObject e ) {
					gridSysParams.getView( ).refresh( );
				}
			}
		);
		gridSysParams.setAfterInitCommand(
			new Command( ) {
				public void execute( ) {
					SysParametersPanel.this.add( gridSysParams );
					SysParametersPanel.this.addButton( btnSave );
					SysParametersPanel.this.addButton( btnCancel );
					SysParametersPanel.this.doLayout( );
				}
			}
		);
		setTag( "sms_slot_floor" );
	}
}
