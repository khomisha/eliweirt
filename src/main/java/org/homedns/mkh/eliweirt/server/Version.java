/*
* Copyright 2012-2014 Mikhail Khodonov
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

import java.io.IOException;
import java.util.Properties;
import javax.servlet.ServletContext;
import org.apache.log4j.Logger;
import org.homedns.mkh.dataservice.server.Context;

/**
 * Package version stamp
 *
 */
public class Version {
	private static final Logger LOG = Logger.getLogger( Version.class );
	
	private Version( ) {
		//this prevents even the native class from
		//calling this constructor as well :
		throw new AssertionError( );
	}

	public static String getVersion( ) {
		String sVersion = Version.class.getPackage( ).getImplementationVersion( );
		if( sVersion == null ) {
		    Properties props = new Properties( );
		    try {
		    	ServletContext context = Context.getInstance( ).getServletContext( );
		    	props.load( context.getResourceAsStream( "/META-INF/MANIFEST.MF" ) );
		    	sVersion = props.getProperty( "Implementation-Version" );
		    } catch( IOException e ) {
		    	LOG.error( e.toString( ) );
		    }
		}		
		return( sVersion );
	}
}
