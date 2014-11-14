/*
* Copyright 2011 Mikhail Khodonov
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
* $Id: Login.java 95 2012-04-05 19:50:25Z khomisha $
*/

package org.homedns.mkh.eliweirt.client;

import org.homedns.mkh.dataservice.shared.Util;
import org.homedns.mkh.ui.client.form.FormConfig;
import org.homedns.mkh.ui.client.frame.BasePanel;
import org.homedns.mkh.ui.client.frame.DummyPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.gwtext.client.widgets.Container;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.AnchorLayout;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.RowLayout;
import com.gwtext.client.widgets.layout.RowLayoutData;
import com.gwtext.client.widgets.event.PanelListenerAdapter;

/**
* Login form container
*/
@SuppressWarnings( "unchecked" )
public class LoginPanel extends BasePanel {
	private static final AppConstants CONSTANTS = ( AppConstants )Eliweirt.getConstants( );

	public LoginPanel( ) {
		setHeaderAsText( false );
		setLayout( new RowLayout( ) );
		
		Panel localePanel = new Panel( );
		localePanel.setLayout( new ColumnLayout( ) );
		localePanel.setBorder( false );
		
		localePanel.add( new DummyPanel( ), new ColumnLayoutData( 0.01 ) );

		Panel flagsPanel = new Panel( );
		flagsPanel.setLayout( new HorizontalLayout( 5 ) );
		
		Image imgRu = new Image( Eliweirt.RESOURCES.rf( ) );
		imgRu.addStyleName( "x-image-onmouseover" );
		imgRu.addClickHandler(
			new ClickHandler( ) {
				public void onClick( ClickEvent event ) {
					onChangeLocale( "ru" );
				}
			}
		);
		flagsPanel.add( imgRu );
		
		Image imgUs = new Image( Eliweirt.RESOURCES.usa( ) );
		imgUs.addStyleName( "x-image-onmouseover" );
		imgUs.addClickHandler(
			new ClickHandler( ) {
				public void onClick( ClickEvent event ) {
					onChangeLocale( "en" );
				}
			}
		);
		flagsPanel.add( imgUs );
		localePanel.add( flagsPanel, new ColumnLayoutData( 0.20 ) );
		add( localePanel, new RowLayoutData( "8%" ) );

		
		Panel formPanel = new Panel( );
		formPanel.setLayout( new AnchorLayout( ) );
		formPanel.setBorder( false );
		
		FormConfig cfg = new FormConfig( );
		cfg.setAttribute( FormConfig.TITLE, CONSTANTS.login( ) );
		cfg.setAttribute( FormConfig.AUTO_WIDTH, false );
		final LoginForm form = new LoginForm( cfg );
		addListener(
			new PanelListenerAdapter( ) {
				public void onAfterLayout( Container self ) {
					form.getEl( ).center( );
					form.getFields( )[ 0 ].focus( );
				}
			}
		);
		formPanel.add( form );
		add( formPanel, new RowLayoutData( "92%" ) );
	}

	/**
	* Create customized token instance, which implements panel
	* creation on-demand
	*
	* @return the panel token
	*/
	public static Token createToken( ) {
		Token token = new Token( "LoginPanel" ) {
			public BasePanel createPanel( ) {
				return( new LoginPanel( ) );
			}
		};
		return( token );
	}

	/**
	 * On change locale: sets new locale, replaces the current URL with a new
	 * one which is appropriate to new locale
	 * 
	 * @param sLocaleName
	 *            the locale name
	 */
	private void onChangeLocale( String sLocaleName ) {
		String sURL = Util.getBaseUrl( );
		Window.Location.replace( 
			Util.DEFAULT_LOCALE.equals( sLocaleName ) ? sURL : sURL + "?locale=" + sLocaleName 
		);		
	}
}
