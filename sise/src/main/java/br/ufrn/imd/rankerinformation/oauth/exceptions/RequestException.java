package br.ufrn.imd.rankerinformation.oauth.exceptions;

import javax.ws.rs.core.Response;

public class RequestException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public RequestException(Response response) {
		
	}
}
