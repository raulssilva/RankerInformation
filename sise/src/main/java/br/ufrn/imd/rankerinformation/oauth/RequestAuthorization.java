package br.ufrn.imd.rankerinformation.oauth;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import br.ufrn.imd.rankerinformation.oauth.exceptions.InvalidServiceRequestException;
import br.ufrn.imd.rankerinformation.oauth.exceptions.RequestException;
import br.ufrn.imd.rankerinformation.oauth.exceptions.UnauthorizedServiceRequestException;

public class RequestAuthorization {
	
	//TODO TOKEN TEMPORÁRIO PARA TESTES (REMOVER DEPOIS)
	private String ACESS_TOKEN;
	private String acess_token;
	
	public RequestAuthorization(){
		this.acess_token = ACESS_TOKEN;
	}
	
	public RequestAuthorization(String acess_token){
		this.acess_token = acess_token;
	}
	
	public String getResponse(String request) throws InvalidServiceRequestException, UnauthorizedServiceRequestException, RequestException{

			Client client = ClientBuilder.newClient();
			
			Response response = client.target(request)
					  .request().header("Authorization", "Bearer "+ acess_token).method("GET");

			//TODO - Remover depois o log da API
			System.out.println("[API_CONSULT] "+request +" "+response.getStatusInfo());
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
	
//	private static final String CLIENT_TARGET = "https://apitestes.info.ufrn.br/authz-server/oauth/token";
//	private static final String CLIENT_ID = "sesi-id";
//	private static final String CLIENT_SECRET = "segredo";
//	private static final String GRANT_TYPE = "client_credentials";
	
//	public void authorization(){
//	Client client = ClientBuilder.newClient();
//	Response response = client.target(CLIENT_TARGET)
//			.queryParam("client_id", CLIENT_ID)
//			.queryParam("client_secret", CLIENT_SECRET)
//			.queryParam("grant_type", GRANT_TYPE)
//	  .request().method("POST");
//	//POST http://apitestes.info.ufrn.br/authz-server/oauth/token?client_id=AppId&client_secret=AppSecret&grant_type=client_credentials
//	System.out.println("status: " + response.getStatus());
//	System.out.println("headers: " + response.getHeaders());
//	String stringResponse = response.readEntity(String.class);
//	System.out.println("body:" + stringResponse);
//	System.out.println("--------------------------------------");
//	//-----------------------------------------------------------------------
//	
//	//body:{"access_token":"0c9951b2-97e3-4131-a8a0-b96ea1f5d65b","token_type":"bearer","expires_in":7650041,"scope":"read"}	
//	
//	String access_token =  "";
//	String token_type =  "";
//	//Integer expires_in = "";
//	String scope= "";
//	
//	JSONParser parser = new JSONParser();
//	try {
//		Object obj = parser.parse(stringResponse);
//		
//		JSONObject jsonObject = (JSONObject) obj;
//
//		access_token = jsonObject.get("access_token").toString();
//		token_type = jsonObject.get("token_type").toString();
//		Integer expires_in = Integer.parseInt(jsonObject.get("expires_in").toString()) ;
//		scope = jsonObject.get("scope").toString();
//		
//		System.out.println(access_token);
//		System.out.println(token_type);
//		System.out.println(expires_in);
//		System.out.println(scope);
//
//	} catch (ParseException e) {
//		e.printStackTrace();
//	}
//	
//	System.out.println("--------------------------------------");
//	
//	//https://api.ufrn.br/portal-services/services/noticias/lista/SIGAA
//	Client client2 = ClientBuilder.newClient();
//	Response response2 = client2.target("https://apitestes.info.ufrn.br/bolsas-services/services/consulta/oportunidadebolsa/monitoria")
//	//Response response2 = client.target("https://apitestes.info.ufrn.br/portal-services/services/noticias/lista/SIGAA")
//	  .request()
//	  .header("Authorization", token_type+" "+access_token)
//	  //.header("Accept", "application/json")
//	  .method("GET");
//	//GET http://apitestes.info.ufrn.br/telefone-services/services/consulta/telefone/ccet 
//	//Authorization: Bearer 111
//	System.out.println("status: " + response2.getStatus());
//	System.out.println("headers: " + response2.getHeaders());
//	String stringResponse2 = response2.readEntity(String.class);
//	System.out.println("body:" + stringResponse2);
//	System.out.println("--------------------------------------");
//}

}
