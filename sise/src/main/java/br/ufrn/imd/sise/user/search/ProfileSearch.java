package br.ufrn.imd.sise.user.search;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import br.ufrn.imd.sise.oauth.RequestAuthorization;
import br.ufrn.imd.sise.oauth.exceptions.InvalidServiceRequestException;
import br.ufrn.imd.sise.oauth.exceptions.RequestException;
import br.ufrn.imd.sise.oauth.exceptions.UnauthorizedServiceRequestException;
import br.ufrn.imd.sise.user.model.Course;
import br.ufrn.imd.sise.user.model.User;
import br.ufrn.imd.sise.user.search.exceptions.AcessVinculoUserException;
import br.ufrn.imd.sise.user.search.exceptions.InvalidIdUserException;

public class ProfileSearch {
	
	private static final String ACESS_PERFIL_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/perfilusuario/";
	private static final String ACESS_VINCULO_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/listavinculos/usuario";
	
	private RequestAuthorization authorization;
	private final JsonParserUtil jsonParserUtil = new JsonParserUtil();
	
	public ProfileSearch(RequestAuthorization authorization){
		this.authorization = authorization;
	}
	
	public Course colsultCourse() throws UnauthorizedServiceRequestException, RequestException, AcessVinculoUserException, ParseException{
		
		String stringResponse = null;
		
		try {
			stringResponse = authorization.getResponse(ACESS_VINCULO_USER);
		} catch (InvalidServiceRequestException e1) {
			throw new AcessVinculoUserException(ACESS_VINCULO_USER);
		} 
		
		Course course = new Course();
		
		JSONArray discentes = jsonParserUtil.extractJSONArray(stringResponse, "discentes");
		
		//TODO INSERIR EXCEÇÃO CASO NÃO ENCONTRE VINCULO 
		//AQUI PODE ACONTECER ERRO CASO NÃO HAJA VINCULOS 
		JSONObject discente =  (JSONObject) discentes.get(0);

		String curso = discente.get("curso").toString();
		
		course.setName(curso);
		
		return course;
	}
	
	public User consultUser() throws UnauthorizedServiceRequestException, RequestException, InvalidIdUserException, AcessVinculoUserException, ParseException{

		String stringResponse = null;
		
		try {
			stringResponse = authorization.getResponse(ACESS_VINCULO_USER);
		} catch (InvalidServiceRequestException e1) {
			throw new AcessVinculoUserException(ACESS_VINCULO_USER);
		} 

		JSONArray discentes = jsonParserUtil.extractJSONArray(stringResponse, "discentes");
		JSONObject discente =  (JSONObject) discentes.get(0);

		int idUsuario = Integer.parseInt(discente.get("idUsuario").toString()) ;
		int idDiscente = Integer.parseInt(discente.get("id").toString()) ;
		int matricula = Integer.parseInt(discente.get("matricula").toString());
		String name = colsultProfileName(idUsuario);
		
		User user = new User(name, idUsuario, idDiscente, matricula);

		return user;	
	}

	public RequestAuthorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(RequestAuthorization authorization) {
		this.authorization = authorization;
	}
	
	private String colsultProfileName(int idUser) throws InvalidIdUserException, UnauthorizedServiceRequestException, RequestException, ParseException{

		String stringResponse;
		
		try {
			stringResponse = authorization.getResponse(ACESS_PERFIL_USER+idUser);
		} catch (InvalidServiceRequestException e1) {
			throw new InvalidIdUserException(ACESS_PERFIL_USER, idUser);
		} 

		String name = jsonParserUtil.extractStringJSONObject(stringResponse, "nome");

		return name;
	}
	
}
