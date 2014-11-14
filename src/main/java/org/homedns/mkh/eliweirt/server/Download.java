/*
* Copyright 2011-2014 Mikhail Khodonov
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

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.homedns.mkh.dataservice.server.DownloadService;

/**
 * Files download service implementation
 *
 */
public class Download extends DownloadService {
	private static final long serialVersionUID = 4161666211133823182L;

	/**
	 * @see org.homedns.mkh.dataservice.server.DownloadService#setResponseParams(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void setResponseParams( 
		HttpServletRequest request, 
		HttpServletResponse response 
	) throws IOException {
		if( "xls".equals( request.getParameter( "type" ) ) ) {
			response.setContentType( "application/vnd.ms-excel" );
		} else {
			response.setContentType( "application/octet-stream" );
		}
		String sFile = request.getParameter( "filename" );
		response.setHeader( "Content-Disposition", "attachment; filename=\"" + sFile + "\"" );
		response.setBufferSize( getBuffSize( ) );
		
		File file = new File( sFile );
		response.setContentLength( ( int )file.length( ) );
		setInputStream( new BufferedInputStream( new FileInputStream( file ), getBuffSize( ) ) );
	}
}
