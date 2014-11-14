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

package org.homedns.mkh.eliweirt.shared;

import java.io.Serializable;
import org.homedns.mkh.dataservice.shared.LoginResponse;

/**
 * Application login response 
 *
 */
public class AppLoginResponse extends LoginResponse  implements Serializable {
	private static final long serialVersionUID = 5059093806515709028L;
	
	private static final int VERSION = 6012;
	private static final int SESSION_TIMEOUT = 6013;
	private static final int REFRESH_TIMEOUT = 6014;

	public AppLoginResponse( ) { }

	/**
	 * @return the application version
	 */
	public String getVersion( ) {
		return( getAttribute( String.class, VERSION ) );
	}

	/**
	 * @param sVersion the application version to set
	 */
	public void setVersion( String sVersion ) {
		setAttribute( VERSION, sVersion );
	}
	
	/**
	 * Returns session timeout
	 * 
	 * @return the session timeout
	 */
	public Integer getSessionTimeout( ) {
		return( getAttribute( Integer.class, SESSION_TIMEOUT ) );
	}
	
	/**
	 * Returns the ui refresh timeout
	 * 
	 * @return the ui refresh timeout
	 */
	public Integer getRefreshTimeout( ) {
		return( getAttribute( Integer.class, REFRESH_TIMEOUT ) );		
	}

	/**
	 * Sets session timeout
	 * 
	 * @param iTimeout the session timeout to set
	 */
	public void setSessionTimeout( Integer iTimeout ) {
		setAttribute( SESSION_TIMEOUT, iTimeout );
	}

	/**
	 * Sets ui refresh timeout
	 * 
	 * @param iTimeout the ui refresh timeout to set
	 */
	public void setRefreshTimeout( Integer iTimeout ) {
		setAttribute( REFRESH_TIMEOUT, iTimeout );
	}
}
