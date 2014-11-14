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

package org.homedns.mkh.eliweirt.server.handler;

import org.homedns.mkh.databuffer.DataBuffer;
import org.homedns.mkh.dataservice.server.Context;
import org.homedns.mkh.dataservice.server.DataBufferManager;
import org.homedns.mkh.dataservice.server.handler.LoginHandler;
import org.homedns.mkh.dataservice.shared.Id;
import org.homedns.mkh.dataservice.shared.Request;
import org.homedns.mkh.dataservice.shared.Response;
import org.homedns.mkh.eliweirt.shared.AppLoginResponse;

/**
 * Application login handler
 *
 */
public class AppLoginHandler extends LoginHandler {

	public AppLoginHandler( ) { 
	}

	/**
	 * @see org.homedns.mkh.dataservice.server.handler.LoginHandler#execute(org.homedns.mkh.dataservice.shared.Request)
	 */
	@Override
	public Response execute( Request request ) throws Exception {
		AppLoginResponse response = ( AppLoginResponse )super.execute( request );
		if( Response.SUCCESS.equals( response.getResult( ) ) ) {
			response.setVersion( org.homedns.mkh.eliweirt.server.Version.getVersion( ) );
			DataBufferManager dbm = Context.getInstance( ).getDataBufferManager( );
			Id id = new Id( );
			id.setName( "sms_slot_floor" );
			DataBuffer db = dbm.getDataBuffer( id );
			if( db.retrieve( ) > 0 && db.first( ) ) {
				response.setSessionTimeout( db.getInt( "sfl_ui_session_timeout" ) * 1000 );
				response.setRefreshTimeout( db.getInt( "sfl_ui_refresh_time" ) * 1000 );
			} else {
				response.setSessionTimeout( 0 );
				response.setRefreshTimeout( 0 );
			}
		}
		return( response );
	}
}
