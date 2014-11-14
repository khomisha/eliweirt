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

import org.homedns.mkh.dataservice.client.Type;
import org.homedns.mkh.dataservice.shared.Data;
import org.homedns.mkh.dataservice.shared.Id;
import org.homedns.mkh.dataservice.shared.ReportRequest;
import org.homedns.mkh.dataservice.shared.Request;
import org.homedns.mkh.ui.client.command.CommandFactory;
import org.homedns.mkh.ui.client.command.ReportCmd;
import org.homedns.mkh.ui.client.frame.AccordionPanel;
import org.homedns.mkh.ui.client.grid.CellEditorGrid;
import org.homedns.mkh.ui.client.grid.EditorGridImpl;
import org.homedns.mkh.ui.client.grid.Grid;
import org.homedns.mkh.ui.client.grid.GridConfig;
import org.homedns.mkh.ui.client.grid.GridImpl;
import com.google.gwt.user.client.Command;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.grid.RowSelectionModel;
import com.gwtext.client.widgets.grid.event.GridCellListenerAdapter;
import com.gwtext.client.widgets.grid.event.RowSelectionListenerAdapter;
import com.gwtext.client.widgets.layout.FitLayout;

/**
 * Report panel
 *
 */
public class ReportPanel extends AccordionPanel {
	private static final AppConstants CONSTANTS = ( AppConstants )Eliweirt.getConstants( );

	private String _sReportDataBuffer;
	private CellEditorGrid _reportParams;
	private String _sFilterValue;
	
	/**
	 * @param sTitle the panel title
	 */
	public ReportPanel( String sTitle ) {
		super( sTitle, CONSTANTS.reportList( ), CONSTANTS.reportParams( ) );
		final Grid reportType = getReportTypeGrid( );
		_reportParams = getReportParamGrid( );
		// binds report parameters grid to the report type grid: on row select
		// in report type grid content of the report parameters grid is filtered
		reportType.getSelectionModel( ).addListener(
			new RowSelectionListenerAdapter( ) {
				public void onRowSelect( RowSelectionModel sm, int rowIndex, Record record ) {
					_sFilterValue = record.getAsString( "rpt_id" );
					_reportParams.getStore( ).filter( "rpt_id", _sFilterValue, false );
					_sReportDataBuffer = record.getAsString( "rpt_databuffer" );
				}
			}
		);
		getListPanel( ).setLayout( new FitLayout( ) );
		reportType.setAfterInitCommand(
			new Command( ) {
				public void execute( ) {
					getListPanel( ).add( reportType );
					addPanels( );
				}
			}
		);

		final Panel editPanel = getEditPanel( );
		editPanel.setLayout( new FitLayout( ) );
		// execution button, on click invokes report creation 
		final Command cmd = CommandFactory.create( 
			ReportCmd.class, 
			_reportParams 
		);
		final Button btnExec = new Button( 
			CONSTANTS.execute( ), 				
			new ButtonListenerAdapter( ) {
				public void onClick( Button button, EventObject e ) {
					cmd.execute( );
				}
			}
		);
		// clear button, clears report parameters entered data
		final Button btnClear = new Button( 
			CONSTANTS.clear( ), 				
			new ButtonListenerAdapter( ) {
				public void onClick( Button button, EventObject e ) {
					Store store = _reportParams.getStore( );
					store.reload( );
					store.filter( "rpt_id", _sFilterValue, false );
				}
			}
		);
		_reportParams.setAfterInitCommand(
			new Command( ) {
				public void execute( ) {
					_reportParams.getColumnModel( ).setEditable( 3, false );
					editPanel.add( _reportParams );
					editPanel.addButton( btnExec );
					editPanel.addButton( btnClear );
					addPanels( );
				}
			}
		);
		setTag( "rep_report_type" );
	}
	
	/**
	 * @return report type grid
	 */
	private Grid getReportTypeGrid( ) {
		Id id = new Id( );
		id.setName( "rep_report_type" );
		GridConfig cfg = new GridConfig( );
		cfg.setAttribute( GridConfig.FILTER, false );
		cfg.setAttribute( GridConfig.REMOTE_FILTER, false );
		Grid reportType = new Grid( id, new GridImpl( cfg ) );
		reportType.addGridListener( new ListWidgetListenerAdapter( ) );
		return( reportType );
	}
	
	/**
	 * @return report parameters editable grid
	 */
	private CellEditorGrid getReportParamGrid( ) {
		Id id = new Id( );
		id.setName( "rep_parameter" );
		GridConfig cfg = new GridConfig( );
		cfg.setAttribute( GridConfig.FILTER, false );
		cfg.setAttribute( GridConfig.REMOTE_FILTER, false );
		final ReportParams reportParams = new ReportParams( id, new EditorGridImpl( cfg ) ); 
		reportParams.addGridCellListener( 
			new GridCellListenerAdapter( ) {
	            public void onCellClick( GridPanel grid, int rowIndex, int colIndex, EventObject e ) {
	            	// defines editable column and sets editor
	            	// in data buffer description must be column 'value'
	                if( grid.getColumnModel( ).getDataIndex( colIndex ).equals( "value" ) ) {
	                	String sType = getDataType( rowIndex );
	                	reportParams.setEditor( colIndex, reportParams.getCellEditor( sType ) );
	                }
	            }
	        }
		);
		return( reportParams );	
	}
	
	private class ReportParams extends CellEditorGrid {

		/**
		 * {@link org.homedns.mkh.ui.client.grid.CellEditorGrid}
		 */
		public ReportParams( Id id, EditorGridImpl impl ) {
			super( id, impl );
		}
		
		/**
		 * @see org.homedns.mkh.dataservice.client.view.View#onSend(org.homedns.mkh.dataservice.shared.Request)
		 */
		@Override
		public void onSend( Request request ) {
			if( request instanceof ReportRequest ) {
				ReportRequest rr = ( ReportRequest )request;
				Data args = new Data( );
				Store store = getStore( );
				for( Record rec : store.getRecords( ) ) {
					Type type = Enum.valueOf( Type.class, getDataType( store.indexOf( rec ) ) );
					if( type == Type.STRING ) {
						args.addValue( rec.getAsString( "value" ) );
					} else if( type == Type.BOOLEAN ) {
						args.addValue( rec.getAsBoolean( "value" ) );
					} else if( type == Type.DOUBLE ) {
						args.addValue( rec.getAsDouble( "value" ) );
					} else if( type == Type.FLOAT ) {
						args.addValue( rec.getAsFloat( "value" ) );
					} else if( type == Type.INT ) {
						args.addValue( rec.getAsInteger( "value" ) );
					} else if( type == Type.TIMESTAMP ) {
						args.addValue( rec.getAsDate( "value" ) );						
					}
				}	
				rr.setArgs( args );
				rr.setDataBufferName( _sReportDataBuffer );
			}
		}
	}
	
	private String getDataType( int iRow ) {
		return( _reportParams.getStore( ).getAt( iRow ).getAsString( "rpm_type" ) );
	}
}
