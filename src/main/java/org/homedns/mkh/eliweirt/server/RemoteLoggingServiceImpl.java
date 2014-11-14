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

package org.homedns.mkh.eliweirt.server;

import javax.servlet.ServletException;
import org.apache.log4j.Logger;
import org.homedns.mkh.dataservice.server.AbstractRemoteLoggingService;
import com.google.gwt.logging.server.StackTraceDeobfuscator;

/**
 * Remote logging service implementation
 *
 */
@SuppressWarnings( "serial" )
public class RemoteLoggingServiceImpl extends AbstractRemoteLoggingService {
	private static final Logger LOG = Logger.getLogger( RemoteLoggingServiceImpl.class );
	private static final String SYMBOL_MAP_PATH = "WEB-INF/deploy/Eliweirt/symbolMaps/"; 

	/**
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init( ) throws ServletException {
		super.init( );
		try {
			StringBuffer sb = new StringBuffer( getServletContext( ).getRealPath( "/" ) );
			sb.delete( 0, System.getProperty( "user.dir" ).length( ) + 1 );
			sb.append( SYMBOL_MAP_PATH );
			setDeobfuscator( new StackTraceDeobfuscator( sb.toString( ), true ) );
			LOG.debug( "symbol maps path: " + sb.toString( ) );
		}
		catch( Exception e ) {
			ServletException se = new ServletException( );
			se.initCause( e );
			throw se;
		}
	}
}
