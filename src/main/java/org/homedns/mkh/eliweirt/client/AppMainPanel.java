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

import java.util.HashMap;
import java.util.Map;
import org.homedns.mkh.dataservice.client.event.EventBus;
import org.homedns.mkh.dataservice.client.event.MaskEvent;
import org.homedns.mkh.dataservice.client.event.UnmaskEvent;
import org.homedns.mkh.dataservice.client.event.MaskEvent.MaskHandler;
import org.homedns.mkh.dataservice.client.event.UnmaskEvent.UnmaskHandler;
import org.homedns.mkh.dataservice.shared.LogoutRequest;
import org.homedns.mkh.dataservice.shared.RequestFactory;
import org.homedns.mkh.ui.client.LoadingMask;
import org.homedns.mkh.ui.client.command.CommandFactory;
import org.homedns.mkh.ui.client.command.ExitCmd;
import org.homedns.mkh.ui.client.frame.BasePanel;
import org.homedns.mkh.ui.client.frame.BasePanel.Token;
import org.homedns.mkh.ui.client.frame.MainPanel;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;

/**
 * Application main panel
 *
 */
@SuppressWarnings( "unchecked" )
public class AppMainPanel extends MainPanel implements MaskHandler, UnmaskHandler {
	private static final AppMessages MESSAGES = ( AppMessages )Eliweirt.getMessages( );
	public static final Token LOGIN_PANEL = LoginPanel.createToken( );
	public static final Token MANAGER_DESK = ManagerDesk.createToken( );
	public static final Token ADMIN_DESK = AdminDesk.createToken( );
	
	private static AppMainPanel _instance;
	private String _sVersion;
	private LoadingMask _loadingMask;
	private Map< String, Token > _tokens = new HashMap< String, Token >( );
	
	private AppMainPanel( ) {
		super( );
		final Command logoutCmd = CommandFactory.create( 
			ExitCmd.class, 
			RequestFactory.create( LogoutRequest.class )
		);
		setLogoutTimer(  
			new Timer( ) {
			    public void run( ) {
			    	logoutCmd.execute( );
		    	}
			}
		);
		_loadingMask = new LoadingMask( );
		addHandler( EventBus.getInstance( ).addHandler( MaskEvent.TYPE, this ) );
		addHandler( EventBus.getInstance( ).addHandler( UnmaskEvent.TYPE, this ) );
		_tokens.put( LOGIN_PANEL.getName( ), LOGIN_PANEL );
		_tokens.put( MANAGER_DESK.getName( ), MANAGER_DESK );
		_tokens.put( ADMIN_DESK.getName( ), ADMIN_DESK );
	}

	/**
	 * Returns application main panel instance
	 * 
	 * @return the main panel instance
	 */
	public static AppMainPanel getInstance( ) {
		if( _instance == null ) {
			_instance = new AppMainPanel( );
		}
		return( _instance );
	}
	
	/**
	 * Returns application version
	 * 
	 * @return the application version
	 */
	public String getVersion( ) {
		return( _sVersion );
	}

	/**
	 * @see org.homedns.mkh.ui.client.frame.MainPanel#getToken(java.lang.String)
	 */
	public Token getToken( String sTokenName ) {
		Token token = _tokens.get( sTokenName );
		if( token == null ) {
			throw new IllegalArgumentException( MESSAGES.noToken( sTokenName ) );
		}
		return( token );
	}
	
	/**
	 * Sets application version 
	 * 
	 * @param sVersion the application version to set
	 */
	public void setVersion( String sVersion ) {
		_sVersion = sVersion;
	}

	/**
	 * @see org.homedns.mkh.ui.client.frame.MainPanel#showPanel(org.homedns.mkh.ui.client.frame.BasePanel)
	 */
	@Override
	protected void showPanel( BasePanel panel ) {
		super.showPanel( panel );
		Eliweirt.getViewport( ).doLayout( );
	}

	/**
	 * @see org.homedns.mkh.dataservice.client.event.UnmaskEvent.UnmaskHandler#onUnmask(org.homedns.mkh.dataservice.client.event.UnmaskEvent)
	 */
	@Override
	public void onUnmask( UnmaskEvent event ) {
		_loadingMask.mask( false );
	}

	/**
	 * @see org.homedns.mkh.dataservice.client.event.MaskEvent.MaskHandler#onMask(org.homedns.mkh.dataservice.client.event.MaskEvent)
	 */
	@Override
	public void onMask( MaskEvent event ) {
		_loadingMask.mask( true );
	}
}
