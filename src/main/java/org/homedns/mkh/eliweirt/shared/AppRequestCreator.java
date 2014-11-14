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

import org.homedns.mkh.dataservice.shared.LoginRequest;
import org.homedns.mkh.dataservice.shared.Request;
import org.homedns.mkh.dataservice.shared.BaseRequestCreator;

/**
 * Application request creator
 *
 */
public class AppRequestCreator extends BaseRequestCreator {

	/**
	 * @see org.homedns.mkh.dataservice.shared.BaseRequestCreator#instantiate(java.lang.Class)
	 */
	@Override
	public Request instantiate( Class< ? > type ) {
		Request request = super.instantiate( type );
		if( type == LoginRequest.class ) {
			request = new AppLoginRequest( );
		}
		return( request );
	}
}
