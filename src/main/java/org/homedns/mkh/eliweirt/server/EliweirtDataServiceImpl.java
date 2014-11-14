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

package org.homedns.mkh.eliweirt.server;

import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import org.homedns.mkh.dataservice.server.DataServiceImpl;

@SuppressWarnings( "serial" )
public class EliweirtDataServiceImpl extends DataServiceImpl {
	private static final Logger LOG = Logger.getLogger( EliweirtDataServiceImpl.class );
	private static final String WEB_INF = "WEB-INF/classes/org/homedns/mkh/eliweirt/server/"; 
	
	public EliweirtDataServiceImpl( ) {
		// hosted mode
		//"war/WEB-INF/classes/org/homedns/mkh/eliweirt/server/eliweirt.properties"
		// web application mode
		//"WEB-INF/classes/org/homedns/mkh/eliweirt/server/eliweirt.properties"
		// production mode
		//"webapps/eliweirt-0.0.1/WEB-INF/classes/org/homedns/mkh/eliweirt/server/eliweirt.properties"
		super( );
	}

	/**
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init( ) throws ServletException {
		super.init( );
		try {
			StringBuffer sb = new StringBuffer( getServletContext( ).getRealPath( "/" ) );
			sb.delete( 0, System.getProperty( "user.dir" ).length( ) + 1 );
			sb.append( WEB_INF );
			setSrvResourcePath( sb.toString( ) );
			loadProperties( getSrvResourcePath( ) + "eliweirt.properties" );
			LOG.debug( "server resource path: " + getSrvResourcePath( ) );
			LOG.info( "DataBuffer: " + org.homedns.mkh.databuffer.Version.getVersion( ) );
			LOG.info( "Dataservice: " + org.homedns.mkh.dataservice.server.Version.getVersion( ) );
			LOG.info( "UI: " + org.homedns.mkh.ui.server.Version.getVersion( ) );
			LOG.info( "Eliweirt: " + org.homedns.mkh.eliweirt.server.Version.getVersion( ) );
		}
		catch( Exception e ) {
			ServletException se = new ServletException( );
			se.initCause( e );
			throw se;
		}
	}
}
