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

import org.homedns.mkh.dataservice.client.presenter.DataPresenter;
import com.google.gwt.user.client.Window;

/**
 * Application data presenter
 *
 */
public class AppDataPresenter extends DataPresenter {

	public AppDataPresenter( ) {
		super( );
	}

	/**
	 * @see org.homedns.mkh.dataservice.client.presenter.DataPresenter#processReport(java.lang.String)
	 */
	@Override
	protected void processReport( String sFile ) {
		Window.open( 
			Eliweirt.getDownloadURL( ) + "?type=xls&filename=" + sFile, 
			( ( AppConstants )Eliweirt.getConstants( ) ).report( ), 
			"" 
		);			
	}
}
