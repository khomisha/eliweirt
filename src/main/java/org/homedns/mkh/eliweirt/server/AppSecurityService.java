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

import javax.security.auth.login.LoginException;
import org.homedns.mkh.databuffer.SessionParameters;
import org.homedns.mkh.dataservice.server.Context;
import org.homedns.mkh.dataservice.server.SecurityService;

/**
 * Application security service
 *
 */
public class AppSecurityService extends SecurityService {

	/**
	 * @see org.homedns.mkh.dataservice.server.SecurityService#commit()
	 */
	@Override
	public boolean commit( ) throws LoginException {
		boolean bCommit = super.commit( );
		if( bCommit ) {
			AppSessionParameters sp = new AppSessionParameters( );
			// Postgresql specific implementation
			sp.setParameter( SessionParameters.CURRENT_LOGIN, "custom_var.current_login" );
			sp.setParameterValue( SessionParameters.CURRENT_LOGIN, getLogin( ) );
			Context.getInstance( ).getDataBufferManager( ).getTransObject( ).setSessionParameters( sp );
		}
		return( bCommit );
	}
}
