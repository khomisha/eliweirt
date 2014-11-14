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

package org.homedns.mkh.eliweirt.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.homedns.mkh.databuffer.SessionParameters;
import org.homedns.mkh.dataservice.server.AbstractSessionParameters;

/**
 * Application session parameters object
 *
 */
public class AppSessionParameters extends AbstractSessionParameters {

	/**
	 * @see org.homedns.mkh.dataservice.server.AbstractSessionParameters#set2Session(java.sql.Connection)
	 */
	@Override
	public void set2Session( Connection conn ) throws SQLException {
		// Postgresql specific implementation
		PreparedStatement stmt = conn.prepareStatement( "select set_config(?, ?, false)" );
		// Here may be verification that the given parameter is already set.
		// This is only if jdbc driver supports getClientInfo
		//if( conn.getClientInfo( sKey ).equals( get( sKey ) ) ) {
		//	continue;
		//}
		stmt.setString( 1, getParameter( SessionParameters.CURRENT_LOGIN ) );
		stmt.setString( 2, getParameterValue( SessionParameters.CURRENT_LOGIN ) );
		stmt.execute( );
	}
}
