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

import org.homedns.mkh.dataservice.client.DataBufferDesc;
import org.homedns.mkh.dataservice.client.Dialog;
import org.homedns.mkh.dataservice.client.RPCCall;
import org.homedns.mkh.dataservice.client.event.ChangePasswordEvent;
import org.homedns.mkh.dataservice.client.event.LoginEvent;
import org.homedns.mkh.dataservice.client.presenter.AccessPresenter;
import org.homedns.mkh.dataservice.shared.ChangePasswordResponse;
import org.homedns.mkh.dataservice.shared.Id;
import org.homedns.mkh.dataservice.shared.LoginResponse;
import org.homedns.mkh.dataservice.shared.LogoutResponse;
import org.homedns.mkh.dataservice.shared.Response;
import org.homedns.mkh.dataservice.shared.ChangePasswordRequest;
import org.homedns.mkh.eliweirt.shared.AppLoginRequest;
import org.homedns.mkh.eliweirt.shared.AppLoginResponse;
import org.homedns.mkh.ui.client.event.NavigationEvent;

/**
 * Application access presenter
 *
 */
public class AppAccessPresenter extends AccessPresenter {
	private Dialog _chgPassDialog;
	private String _sEntryPanel;
	
	/**
	 * @see org.homedns.mkh.dataservice.client.event.LoginEvent.LoginHandler#onLogin(org.homedns.mkh.dataservice.client.event.event.LoginEvent)
	 */
	@Override
	public void onLogin( LoginEvent event ) {
		Dialog loginDialog = event.getDialog( );
		String[] as = loginDialog.getSavingData( ).split( ":" );
		AppLoginRequest request = new AppLoginRequest( );
		request.setLogin( as[ 0 ] );
		request.setPassword( as[ 1 ] );
		request.setLocale( as[ 2 ] );
		request.setDateTimeFormat( as[ 3 ] );
		request.setID( new Id( ) );
		setRequest( request );
		RPCCall rpc = new RPCCall( );
		rpc.execute( this );
	}

	/**
	 * @see org.homedns.mkh.dataservice.client.event.ChangePasswordEvent.ChangePasswordHandler#onChangePassword(org.homedns.mkh.dataservice.client.event.ChangePasswordEvent)
	 */
	@Override
	public void onChangePassword( ChangePasswordEvent event ) {
		_chgPassDialog = event.getDialog( );
		String[] as = _chgPassDialog.getSavingData( ).split( ":" );
		ChangePasswordRequest request = new ChangePasswordRequest( );
		request.setID( getID( ) );
		request.setColName( as[ 0 ] );
		request.setPassword( as[ 1 ] );
		setRequest( request );
		RPCCall rpc = new RPCCall( );
		rpc.execute( this );
	}

	/**
	 * @see org.homedns.mkh.dataservice.client.presenter.Presenter#onResponse(org.homedns.mkh.dataservice.shared.Response)
	 */
	@Override
	public void onResponse( Response result ) {
		if( result instanceof LoginResponse ) {
			AppLoginResponse response = ( AppLoginResponse )result;
			setID( response.getID( ) );
			setResponse( response );
			loadAccess( response );
			AppMainPanel.getInstance( ).setVersion( response.getVersion( ) );
			AppMainPanel.getInstance( ).setSessionTimeout( response.getSessionTimeout( ) );
			AppMainPanel.getInstance( ).setRefreshTimeout( response.getRefreshTimeout( ) );
			NavigationEvent.fire( AppMainPanel.getInstance( ).getToken( _sEntryPanel ) );
		} else if( result instanceof ChangePasswordResponse ) {
			_chgPassDialog.refresh( result );
		} else if( result instanceof LogoutResponse ) {
			NavigationEvent.fire( AppMainPanel.LOGIN_PANEL );
			removeHandlers( );
		}		
	}

	/**
	 * @see org.homedns.mkh.dataservice.client.presenter.AccessPresenter#loadAccess()
	 */
	@Override
	protected void loadAccess( Response response ) {
		DataBufferDesc desc = getDataBufferDesc( response );
		String[][] asData = ( response.getData( ) );
		int iSobName = desc.getColumn( "sob_name" ).getColNum( );
		int iSorRightMask = desc.getColumn( "sor_right_mask" ).getColNum( );
		_sEntryPanel = asData[ 0 ][ desc.getColumn( "entry_panel" ).getColNum( ) ];
		for( String[] row : asData ) {
			getWidgetAccess( ).put( 
				row[ iSobName ], 
				Integer.valueOf( row[ iSorRightMask ] ) 
			);
		}
	}
}
