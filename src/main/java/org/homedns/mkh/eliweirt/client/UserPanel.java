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

import java.util.ArrayList;
import java.util.List;
import org.homedns.mkh.dataservice.shared.Id;
import org.homedns.mkh.dataservice.shared.Util;
import org.homedns.mkh.ui.client.CmdTypeItem;
import org.homedns.mkh.ui.client.command.DeleteCmd;
import org.homedns.mkh.ui.client.form.GridForm;
import org.homedns.mkh.ui.client.form.GridFormConfig;
import org.homedns.mkh.ui.client.frame.SimpleContentPanel;
import org.homedns.mkh.ui.client.grid.GridConfig;
import com.gwtext.client.data.Record;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.layout.AnchorLayoutData;

/**
 * User panel
 *
 */
public class UserPanel extends SimpleContentPanel {
	private static final AppConstants CONSTANTS = ( AppConstants )Eliweirt.getConstants( );

	public UserPanel( ) {
		super( "acr_user", CONSTANTS.users( ), CONSTANTS.userList( ), CONSTANTS.userDetail( ) );
		setTag( "acr_user" );
	}

	/**
	 * @see org.homedns.mkh.ui.client.frame.SimpleContentPanel#defineGridConfig()
	 */
	@Override
	protected GridConfig defineGridConfig( ) {
		List< CmdTypeItem > items = new ArrayList< CmdTypeItem >( );
		items.add( new CmdTypeItem( CONSTANTS.del( ), DeleteCmd.class ) );
		GridConfig cfg = new GridConfig( );
		cfg.setAttribute( GridConfig.CONTEXT_MENU, items );
		cfg.setAttribute( GridConfig.FILTER, false );
		cfg.setAttribute( GridConfig.REMOTE_FILTER, false );
		return( cfg );
	}

	/**
	 * @see org.homedns.mkh.ui.client.frame.SimpleContentPanel#defineForm(org.homedns.mkh.dataservice.shared.Id, org.homedns.mkh.ui.client.form.GridFormConfig)
	 */
	@Override
	protected GridForm defineForm( Id id, GridFormConfig cfg ) {
		GridForm form = (
			new GridForm( id, cfg ) {
				@Override
				public void addFields( String[] asField ) {
					super.addFields( asField );
					TextField pass = new TextField( CONSTANTS.password( ), "pass" );
					pass.setPassword( true );
					pass.disable( );
					add( pass, new AnchorLayoutData( "95%" ) );
					TextField confirmPass = new TextField( CONSTANTS.confirmPass( ), "confirmPass" );
					confirmPass.setPassword( true );
					confirmPass.disable( );
					add( confirmPass, new AnchorLayoutData( "95%" ) );
				}

				@Override
				protected boolean beforeRecordUpdate( Record record ) {
					boolean bResult = super.beforeRecordUpdate( record );
					if( bResult ) {
						TextField pass = ( TextField )getForm( ).findField( "pass" );
						TextField confirmPass = ( TextField )getForm( ).findField( "confirmPass" );
						if( 
							pass.isDirty( ) && 
							pass.getValueAsString( ).equals( confirmPass.getValueAsString( ) ) 
						) {
							record.set( "usr_pass", pass.getValueAsString( ) );							
						} else {
							bResult = false;
							Util.signalMsg( null, Util.MSG_BOX, CONSTANTS.invalidPassInput( ) );
						}
					}
					return( bResult );
				}
				
			}
		);
		return( form );
	}
}
