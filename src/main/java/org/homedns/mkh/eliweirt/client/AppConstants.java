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

import org.homedns.mkh.ui.client.UIConstants;

/**
 * Interface to represent the constants contained in resource bundle:
 * AppConstants.properties.
 */
public interface AppConstants extends UIConstants {
	/**
	* Translated "<font size=6>[Application name]".
	*
	* @return translated "<font size=6>[Application name]"
	*/
	@DefaultStringValue( "<font size=6'>Eliweirt" )
	@Key( "appTitle" )
	String appTitle( );

	/**
	* Translated "<font size=1>Copyright MKH 2014".
	*
	* @return translated "<font size=1>Copyright MKH 2014"
	*/
	@DefaultStringValue( "<font size=1>Copyright MKH 2014" )
	@Key( "copyright" )
	String copyright( );

	/**
	* Translated "Admin".
	*
	* @return translated "Admin"
	*/
	@DefaultStringValue( "Admin" )
	@Key( "admin" )
	String admin( );
	
	/**
	* Translated "Manager".
	*
	* @return translated "Manager"
	*/
	@DefaultStringValue( "Manager" )
	@Key( "manager" )
	String manager( );

	/**
	* Translated "Reports".
	*
	* @return translated "Reports"
	*/
	@DefaultStringValue( "Reports" )
	@Key( "reports" )
	String reports( );

	/**
	* Translated "Reports List".
	*
	* @return translated "Reports List"
	*/
	@DefaultStringValue( "Reports List" )
	@Key( "reportList" )
	String reportList( );

	/**
	* Translated "Report Parameters".
	*
	* @return translated "Report Parameters"
	*/
	@DefaultStringValue( "Report Parameters" )
	@Key( "reportParams" )
	String reportParams( );

	/**
	* Translated "Execute".
	*
	* @return translated "Execute"
	*/
	@DefaultStringValue( "Execute" )
	@Key( "execute" )
	String execute( );

	/**
	* Translated "Users".
	*
	* @return translated "Users"
	*/
	@DefaultStringValue( "Users" )
	@Key( "users" )
	String users( );

	/**
	* Translated "Roles".
	*
	* @return translated "Roles"
	*/
	@DefaultStringValue( "Roles" )
	@Key( "roles" )
	String roles( );

	/**
	* Translated "System Parameters".
	*
	* @return translated "System Parameters"
	*/
	@DefaultStringValue( "System Parameters" )
	@Key( "sysParams" )
	String sysParams( );

	/**
	* Translated "Users list".
	*
	* @return translated "Users list"
	*/
	@DefaultStringValue( "Users list" )
	@Key( "userList" )
	String userList( );

	/**
	* Translated "User details".
	*
	* @return translated "User details"
	*/
	@DefaultStringValue( "User details" )
	@Key( "userDetail" )
	String userDetail( );

	/**
	* Translated "Roles list".
	*
	* @return translated "Roles list"
	*/
	@DefaultStringValue( "Roles list" )
	@Key( "roleList" )
	String roleList( );

	/**
	* Translated "Role details".
	*
	* @return translated "Role details"
	*/
	@DefaultStringValue( "Role details" )
	@Key( "roleDetail" )
	String roleDetail( );

	/**
	* Translated "User's Log".
	*
	* @return translated "User's Log"
	*/
	@DefaultStringValue( "User's Log" )
	@Key( "userLog" )
	String userLog( );
}
