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

import java.util.ArrayList;
import java.util.List;
import org.homedns.mkh.dataservice.shared.Id;
import org.homedns.mkh.ui.client.CmdTypeItem;
import org.homedns.mkh.ui.client.command.DeleteCmd;
import org.homedns.mkh.ui.client.form.GridForm;
import org.homedns.mkh.ui.client.form.GridFormConfig;
import org.homedns.mkh.ui.client.frame.SimpleContentPanel;
import org.homedns.mkh.ui.client.grid.GridConfig;

/**
 * Role panel
 *
 */
public class RolePanel extends SimpleContentPanel {
	private static final AppConstants CONSTANTS = ( AppConstants )Eliweirt.getConstants( );

	public RolePanel( ) {
		super( "acr_right", CONSTANTS.roles( ), CONSTANTS.roleList( ), CONSTANTS.roleDetail( ) );
		setTag( "acr_right" );
	}

	/**
	 * @see org.homedns.mkh.ui.client.frame.SimpleContentPanel#defineGridConfig()
	 */
	@Override
	protected GridConfig defineGridConfig( ) {
		List< CmdTypeItem > items = new ArrayList< CmdTypeItem >( );
		items.add( new CmdTypeItem( CONSTANTS.del( ), DeleteCmd.class ) );
		GridConfig cfg = new GridConfig( );
		cfg.setAttribute( GridConfig.CONTEXT_MENU, items );
		cfg.setAttribute( GridConfig.FILTER, false );
		cfg.setAttribute( GridConfig.REMOTE_FILTER, false );
		return( cfg );
	}

	/**
	 * @see org.homedns.mkh.ui.client.frame.SimpleContentPanel#defineForm(org.homedns.mkh.dataservice.shared.Id, org.homedns.mkh.ui.client.form.GridFormConfig)
	 */
	@Override
	protected GridForm defineForm( Id id, GridFormConfig cfg ) {
		return( new RoleForm( id, cfg ) );
	}
}
