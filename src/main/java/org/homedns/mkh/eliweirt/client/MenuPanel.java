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

import org.homedns.mkh.dataservice.shared.LogoutRequest;
import org.homedns.mkh.dataservice.shared.RequestFactory;
import org.homedns.mkh.ui.client.HyperLink;
import org.homedns.mkh.ui.client.command.CommandFactory;
import org.homedns.mkh.ui.client.command.ChangePassCmd;
import org.homedns.mkh.ui.client.command.ExitCmd;
import org.homedns.mkh.ui.client.command.HelpCmd;
import org.homedns.mkh.ui.client.command.NavigateCmd;
import org.homedns.mkh.ui.client.form.FormConfig;
import org.homedns.mkh.ui.client.frame.BaseDialog;
import org.homedns.mkh.ui.client.frame.MsgBox;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.widgets.layout.HorizontalLayout;

/**
 * Application main menu panel
 *
 */
public class MenuPanel extends Panel {
	private static final AppConstants CONSTANTS = ( AppConstants )Eliweirt.getConstants( );
	private static final AppMessages MESSAGES = ( AppMessages )Eliweirt.getMessages( );

	private BaseDialog _chgPass;
	private MsgBox _about;
	private HyperLink _adminItem;
	private HyperLink _managerItem;

	public MenuPanel( ) {
		setStyleName( "x-panel-header-noborder" );
		setLayout( new ColumnLayout( ) );
		setShim( false );
		setShadow( false );
		setBorder( false );
		setPaddings( 12, 10, 0, 8 );

		FormConfig config = new FormConfig( );
		config.setAttribute( FormConfig.AUTO_WIDTH, false );
		_chgPass = new BaseDialog( CONSTANTS.chgPass( ), new ChgPassForm( config ) );
		_about = createAbout( );

		Panel inner = new Panel( );
		inner.setLayout( new HorizontalLayout( 10 ) );
		inner.setBorder( false );
		_adminItem = new HyperLink( 
			CONSTANTS.admin( ),
			CommandFactory.create( NavigateCmd.class, AppMainPanel.ADMIN_DESK )
		) {
			/**
			 * @see org.homedns.mkh.ui.client.HyperLink#onItemClick()
			 */
			@Override
			protected void onItemClick( ) {
				_adminItem.setEnabled( false );
				_managerItem.setEnabled( true );
			}
		};
		inner.add( _adminItem );
		_managerItem = new HyperLink( 
			CONSTANTS.manager( ), 
			CommandFactory.create( NavigateCmd.class, AppMainPanel.MANAGER_DESK )
		) {
			/**
			 * @see org.homedns.mkh.ui.client.HyperLink#onItemClick()
			 */
			@Override
			protected void onItemClick( ) {
				_adminItem.setEnabled( true );
				_managerItem.setEnabled( false );
			}			
		};
		inner.add( _managerItem );
		HyperLink changePassItem = new HyperLink( 
			CONSTANTS.chgPass( ), 
			CommandFactory.create( ChangePassCmd.class, _chgPass )
		);
		inner.add( changePassItem );
		HyperLink aboutItem = new HyperLink( 
			CONSTANTS.about( ), 
			CommandFactory.create( HelpCmd.class, _about )
		);
		inner.add( aboutItem );
		HyperLink exitItem = new HyperLink( 
			CONSTANTS.exit( ), 
			CommandFactory.create( 
				ExitCmd.class,	
				RequestFactory.create( LogoutRequest.class )
			)
		);
		inner.add( exitItem );
		add( inner, new ColumnLayoutData( 1 ) );
	}
	
	/**
	 * Creates about message box
	 * 
	 * @return the about message box
	 */
	private MsgBox createAbout( ) {
		String sVersion = MESSAGES.version( 
			AppMainPanel.getInstance( ).getVersion( ), CONSTANTS.copyright( ) 
		);
		return( new MsgBox( CONSTANTS.about( ), sVersion ) );
	}
}
