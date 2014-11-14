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

import org.homedns.mkh.dataservice.client.presenter.AccessPresenter;
import org.homedns.mkh.dataservice.client.presenter.DataPresenter;
import org.homedns.mkh.dataservice.client.presenter.Presenter;
import org.homedns.mkh.dataservice.client.presenter.PresenterFactory.PresenterCreator;

/**
 * Application presenter creator
 *
 */
public class AppPresenterCreator implements PresenterCreator {

	/**
	 * @see org.homedns.mkh.dataservice.client.Creator#instantiate(java.lang.Class)
	 */
	@Override
	public Presenter instantiate( Class< ? > type ) {
		if( type == AccessPresenter.class ) {
			return( new AppAccessPresenter( ) );			
		} else if( type == DataPresenter.class ) {
			return( new AppDataPresenter( ) );
		} else {
			throw new IllegalArgumentException( type.getName( ) );
		}
	}
}
