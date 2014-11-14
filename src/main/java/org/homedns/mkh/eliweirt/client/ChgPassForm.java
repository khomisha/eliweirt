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

import org.homedns.mkh.dataservice.client.Dialog;
import org.homedns.mkh.dataservice.client.event.ChangePasswordEvent;
import org.homedns.mkh.dataservice.shared.Response;
import org.homedns.mkh.dataservice.shared.Util;
import org.homedns.mkh.ui.client.form.BaseForm;
import org.homedns.mkh.ui.client.form.FormConfig;

import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.TextField;

/**
 * Change password form
 *
 */
@SuppressWarnings( "unchecked" )
public class ChgPassForm extends BaseForm implements Dialog {
	private static final AppConstants CONSTANTS = ( AppConstants )Eliweirt.getConstants( );
	
	private String _sData;

	/**
	 * @param cfg
	 *            the form configuration object
	 */
	public ChgPassForm( FormConfig cfg ) {
		super( cfg );
		config( );
		int iFieldLen = ( Integer )cfg.getAttribute( FormConfig.FIELD_LEN );
		final TextField pass = new TextField( CONSTANTS.password( ), "pass", iFieldLen );
		pass.setPassword( true );
		add( pass );
		
		final TextField confirmPass = new TextField( CONSTANTS.confirmPass( ), "confirmPass", iFieldLen );
		confirmPass.setPassword( true );
		add( confirmPass );
		
		Button ok = new Button( 
			CONSTANTS.ok( ), 
			new ButtonListenerAdapter( ) {
				public void onClick( Button button, EventObject e ) {
					String sPass = pass.getValueAsString( );
					if( 
						pass.isDirty( ) && 
						sPass.equals( confirmPass.getValueAsString( ) ) 
					) {
						_sData = "usr_pass:" + sPass;
						ChangePasswordEvent.fire( ChgPassForm.this );
					} else {
						Util.signalMsg( null, Util.MSG_BOX, CONSTANTS.invalidPassInput( ) );
					}
				}
			}
		);
		addButton( ok );
		Button cancel = new Button( 
			CONSTANTS.cancel( ), 
			new ButtonListenerAdapter( ) {
				public void onClick( Button button, EventObject e ) {
					ChgPassForm.this.hide( );
				}
			}
		);
		addButton( cancel );
	}

	/**
	 * @see org.homedns.mkh.dataservice.client.EditAccessView#getSavingData()
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
		hide( );
	}
}
