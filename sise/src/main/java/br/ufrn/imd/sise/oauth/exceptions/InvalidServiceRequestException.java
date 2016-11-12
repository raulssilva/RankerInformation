package br.ufrn.imd.sise.oauth.exceptions;

import javax.ws.rs.core.Response;

public class InvalidServiceRequestException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public InvalidServiceRequestException(Response response) {
		super("[" + response.getStatusInfo().getStatusCode() + "] - " + response.getStatusInfo().toString());
		// http://stackoverflow.com/questions/3776327/how-to-define-custom-exception-class-in-java-the-easiest-way
	}
	
	public InvalidServiceRequestException(Response response, String request) {
		super("[" + response.getStatusInfo().getStatusCode() + "] - " + response.getStatusInfo().toString() + ", could not access: " + request );
	}
}
