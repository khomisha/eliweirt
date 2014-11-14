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

import org.homedns.mkh.ui.client.frame.ChildContainer;
import org.homedns.mkh.ui.client.frame.PanelConfig;
import com.gwtext.client.widgets.layout.RowLayout;
import com.gwtext.client.widgets.layout.RowLayoutData;

/**
 * Application panel configuration object
 *
 */
public class DeskConfig extends PanelConfig {

	public DeskConfig( ) {
		super( new RowLayout( ) );
		add( new ChildContainer( new Header( ), new RowLayoutData( "8%" ) ) );
		add( new ChildContainer( new MenuPanel( ), new RowLayoutData( "6%" ) ) );
		add( new ChildContainer( new ContentPanel( ) ) );
		add( new ChildContainer( new Footer( ), new RowLayoutData( "5%" ) ) );
	}
}
