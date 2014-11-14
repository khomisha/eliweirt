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

import org.homedns.mkh.ui.client.frame.DummyPanel;
import com.gwtext.client.widgets.HTMLPanel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;

/**
 * Footer panel
 *
 */
public class Footer extends Panel {
	private static final AppConstants CONSTANTS = ( AppConstants )Eliweirt.getConstants( );

	public Footer( ) {
		setLayout( new ColumnLayout( ) );
		setShim( false );
		setShadow( false );
		
		add( new DummyPanel( ), new ColumnLayoutData( 0.85 ) );

		HTMLPanel copyright = new HTMLPanel( CONSTANTS.copyright( ), 10, 5, 5, 5 );
		copyright.setLayout( new FitLayout( ) );
		add( copyright, new ColumnLayoutData( 0.15 ) );
	}
}
