package br.ufrn.imd.rankerinformation.oauth;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import br.ufrn.imd.rankerinformation.oauth.exceptions.InvalidServiceRequestException;
import br.ufrn.imd.rankerinformation.oauth.exceptions.RequestException;
import br.ufrn.imd.rankerinformation.oauth.exceptions.UnauthorizedServiceRequestException;

public class RequestAuthorization {

	private String acess_token;

	public RequestAuthorization(){

	}
	
	public RequestAuthorization(String acess_token){
		this.acess_token = acess_token;
	}
	
	public String getResponse(String request) throws InvalidServiceRequestException, UnauthorizedServiceRequestException, RequestException{

		Client client = ClientBuilder.newClient();
		
		Response response = client.target(request)
				  .request().header("Authorization", "Bearer "+ acess_token).method("GET");

		System.out.println("[LOG REQUEST] "+request +" "+response.getStatusInfo());
		
		if(response.getStatusInfo().equals(Response.Status.NOT_FOUND)){
			throw new InvalidServiceRequestException(response, request);
		}else if(response.getStatusInfo().equals(Response.Status.UNAUTHORIZED)){
			throw new UnauthorizedServiceRequestException(response);
		}else if(! response.getStatusInfo().equals(Response.Status.OK)){
			throw new RequestException(response);
		}

		String stringResponse = response.readEntity(String.class);
		
		return stringResponse;
	}

	public String getAcessToken() {
		return acess_token;
	}

	public void setAcessToken(String acess_token) {
		this.acess_token = acess_token;
	}

}
