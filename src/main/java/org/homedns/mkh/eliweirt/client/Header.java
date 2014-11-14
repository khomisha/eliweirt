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

import java.util.Date;

import org.homedns.mkh.dataservice.client.DateFormatter;
import org.homedns.mkh.ui.client.frame.DummyPanel;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Image;
import com.gwtext.client.widgets.HTMLPanel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;

/**
 * Header panel
 *
 */
public class Header extends Panel {
	private static final AppConstants CONSTANTS = ( AppConstants )Eliweirt.getConstants( );
	private static Image logo = new Image( Eliweirt.RESOURCES.logo( ) );

	public Header( ) {
		setLayout( new ColumnLayout( ) );
		setShim( false );
		setShadow( false );
		
		add( logo, new ColumnLayoutData( 0.06 ) );	
		
		HTMLPanel appName = new HTMLPanel( CONSTANTS.appTitle( ), 10, 5, 5, 5 );
		appName.setLayout( new FitLayout( ) );
		add( appName, new ColumnLayoutData( 0.1 ) );
		
		add( new DummyPanel( ), new ColumnLayoutData( 0.69 ) );
		
		DummyPanel clock = new DummyPanel( 25, 0, 0, 5 );
		clock.setLayout( new FitLayout( ) );
		clock.add( new Clock( ) );
		add( clock, new ColumnLayoutData( 0.15 ) );
	}
	
	/**
	 * Panel's clock
	 */
	private class Clock extends Label {
		public Clock( ) {
			Timer timer = new Timer( ) {
			    public void run( ) {
					Clock.this.setText( 
						DateFormatter.TIMESTAMP.getDateTimeFormat( ).format( new Date( ) ) 
					);
			    }
			};
			timer.scheduleRepeating( 1000 );
		}
	}
}
