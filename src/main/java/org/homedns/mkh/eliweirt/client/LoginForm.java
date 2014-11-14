/*
* Copyright 2011-2014 Mikhail Khodonov
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
* $Id: LoginForm.java 99 2012-10-11 16:28:06Z khomisha $
*/

package org.homedns.mkh.eliweirt.client;

import org.homedns.mkh.dataservice.client.DateFormatter;
import org.homedns.mkh.dataservice.client.Dialog;
import org.homedns.mkh.dataservice.client.event.LoginEvent;
import org.homedns.mkh.dataservice.client.presenter.AccessPresenter;
import org.homedns.mkh.dataservice.client.presenter.PresenterFactory;
import org.homedns.mkh.dataservice.shared.Response;
import org.homedns.mkh.ui.client.form.BaseForm;
import org.homedns.mkh.ui.client.form.FormConfig;
import com.google.gwt.i18n.client.LocaleInfo;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.TextField;

/**
* Login form, should be add to the login panel
*/
@SuppressWarnings("unchecked")
public class LoginForm extends BaseForm implements Dialog {
	private static final AppConstants CONSTANTS = ( AppConstants )Eliweirt.getConstants( );
	
	private String _sData;

	/**
	 * @param cfg
	 *            the form configuration object
	 */
	public LoginForm( FormConfig cfg ) {
		super( cfg );
		config( );
		int iFieldLen = ( Integer )cfg.getAttribute( FormConfig.FIELD_LEN );
		final TextField login = new TextField( CONSTANTS.login( ), "login", iFieldLen );
		add( login );

		final TextField pass = new TextField( CONSTANTS.password( ), "pass", iFieldLen );
		pass.setPassword( true );
		add( pass );
		
		PresenterFactory.create( AccessPresenter.class, null );

		Button ok = new Button( 
			CONSTANTS.ok( ), 
			new ButtonListenerAdapter( ) {
				public void onClick( Button button, EventObject e ) {
					String sLogin = login.getValueAsString( );
					String sPass = pass.getValueAsString( );
					if( sLogin.length( ) > 0 && sPass.length( ) > 0 ) {
						_sData = (
							sLogin + ":" + 
							sPass + ":" + 
							LocaleInfo.getCurrentLocale( ).getLocaleName( ) + ":" +
							DateFormatter.TIMESTAMP.getDateTimeFormat( ).getPattern( )
						);
						LoginEvent.fire( LoginForm.this );
					}					
				}
			}
		);
		addButton( ok );
	}
	
	/**
	 * @see org.homedns.mkh.dataservice.client.Dialog#getSavingData()
	 */
	@Override
	public String getSavingData( ) {
		return( _sData );
	}

	/**
	 * @see org.homedns.mkh.dataservice.client.Dialog#refresh(org.homedns.mkh.dataservice.shared.Response)
	 */
	@Override
	public void refresh( Response data ) {
	}
}
