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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;
import org.homedns.mkh.dataservice.server.AbstractCheckupService;
import org.homedns.mkh.dataservice.server.Context;

/**
 * Application checkup service
 *
 */
public class AppCheckupServcie extends AbstractCheckupService {
	private static final Logger LOG = Logger.getLogger( AppCheckupServcie.class );

	/**
	 * @see org.homedns.mkh.dataservice.server.AbstractCheckupService#close()
	 */
	@Override
	public void close( ) {
		Connection conn = null;
		try {
			conn = getTransObject( ).getConnection( );
			Statement stmt = conn.createStatement( );
			String sIP = Context.getInstance( ).getRequestIp( );
			String sSQL = "";
			String sLoginCount = String.valueOf( getLoginCount( ) );
			if( isExist( sIP ) ) {
				sSQL = (
					"update logon_attempt_log set lal_attempt_num = " + 
					sLoginCount + " where lal_remote_ip = '" + sIP + "'"
				);
			} else {
				sSQL = (
					"insert into logon_attempt_log(lal_remote_ip, lal_attempt_num) values('" + 
					sIP + "'," + sLoginCount + ")"
				);
			}
			stmt.executeUpdate( sSQL );
		}
		catch( SQLException e ) {
			LOG.error( e );			
		}
		finally {
			try {
				if( conn != null ) {
					conn.close( );
				}
			} catch( SQLException e ) {
				LOG.error( e );			
			}
		}
	}

	/**
	 * @see org.homedns.mkh.dataservice.server.AbstractCheckupService#retrieveLoginCount()
	 */
	@Override
	protected int retrieveLoginCount( ) throws SQLException {
		int iLoginCount = 0;
		Connection conn = null;
		try {
			conn = getTransObject( ).getConnection( );
			String sIP = Context.getInstance( ).getRequestIp( );
			Statement stmt = conn.createStatement( 
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY 
			);
			ResultSet rs = stmt.executeQuery( 
				"SELECT lal_attempt_num FROM logon_attempt_log WHERE lal_remote_ip = '" + sIP + "'" 
			);
			if( rs.first( ) ) {
				iLoginCount = rs.getInt( 1 );
			}			
		}
		finally {
			if( conn != null ) {
				conn.close( );
			}
		}
		return( iLoginCount );
	}
	
	/**
	 * Returns true if record for specified IP exits in logon attempts log
	 * otherwise false
	 * 
	 * @param sIP
	 *            the request client IP
	 * 
	 * @return true or false
	 * 
	 * @throws SQLException
	 */
	private boolean isExist( String sIP ) throws SQLException {
		int iCount = 0;
		Connection conn = null;
		try {
			conn = getTransObject( ).getConnection( );
			Statement stmt = conn.createStatement( 
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY 
			);
			ResultSet rs = stmt.executeQuery( 
				"SELECT count(*) FROM logon_attempt_log WHERE lal_remote_ip = '" + sIP + "'" 
			);
			if( rs.first( ) ) {
				iCount = rs.getInt( 1 );
			}			
		}
		finally {
			if( conn != null ) {
				conn.close( );
			}
		}
		return( iCount > 0 );
	}
}
