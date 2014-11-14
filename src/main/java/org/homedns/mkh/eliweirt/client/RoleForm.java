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

import org.homedns.mkh.dataservice.client.Column;
import org.homedns.mkh.dataservice.client.DataBufferDesc;
import org.homedns.mkh.dataservice.shared.Id;
import org.homedns.mkh.ui.client.WidgetDesc;
import org.homedns.mkh.ui.client.form.GridForm;
import org.homedns.mkh.ui.client.form.GridFormConfig;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.form.Field;
import com.gwtext.client.widgets.form.FieldSet;
import com.gwtext.client.widgets.layout.AnchorLayoutData;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.widgets.layout.FormLayout;

/**
 * Role form
 *
 */
public class RoleForm extends GridForm {
	private static final double COL_WIDTH = .5;

	/**
	 * {@link org.homedns.mkh.ui.client.form.GridForm}
	 */
	public RoleForm( Id id, GridFormConfig cfg ) {
		super( id, cfg );
	}

	/**
	 * @see org.homedns.mkh.ui.client.form.BoundForm#addFields(java.lang.String[])
	 */
	@Override
	public void addFields( String[] asField ) {
        FieldSet topFields = new FieldSet( );
        topFields.setBorder( false );
        FieldSet bottomFields = new FieldSet( );
        bottomFields.setBorder( false );
        bottomFields.setLayout( new ColumnLayout( ) );
        
        Field fieldRole = getField( "user_role" );
		topFields.add( fieldRole, new AnchorLayoutData( "97.5%" ) );
        Field fieldPanel = getField( "entry_panel" );
 		topFields.add( fieldPanel, new AnchorLayoutData( "97.5%" ) );
        add( topFields, new AnchorLayoutData( "98%" ) );

        bottomFields.add(
    		fillPanel( 
    			new String[] { 
    				"adminsheet", "acr_right", "acr_user", "acr_user_log", "sms_slot_floor" 
    			}
    		), 
    		new ColumnLayoutData( COL_WIDTH ) 
    	);	
        bottomFields.add( 
    		fillPanel( new String[] { "managersheet", "rep_report_type" } ), 
    		new ColumnLayoutData( COL_WIDTH ) 
		);
        add( bottomFields, new AnchorLayoutData( "98%" ) );
	}
	
	/**
	 * Returns field
	 * 
	 * @param sName
	 *            the field (column) name
	 * 
	 * @return the field
	 */
	private Field getField( String sName ) {
		WidgetDesc desc = ( WidgetDesc )getDescription( );
		DataBufferDesc dbDesc = desc.getDataBufferDesc( );
		Column col = dbDesc.getColumn( sName );
		return( desc.getCol2Field( ).get( col ) );
	}

	/**
	 * Fills panels with fields
	 * 
	 * @param asField
	 *            fields names array
	 * 
	 * @return the result panel
	 */
	private Panel fillPanel( String[] asField ) {
		Panel panel = new Panel( );
		panel.setLayout( new FormLayout( ) );
		panel.setBorder( false );
		for( String sField : asField ) {
			Field field = getField( sField );
			panel.add( field, new AnchorLayoutData( "95%" ) );
		}
		return( panel );
	}
}
