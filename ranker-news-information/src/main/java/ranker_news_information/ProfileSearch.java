package ranker_news_information;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import br.ufrn.imd.rankerinformation.oauth.RequestAuthorization;
import br.ufrn.imd.rankerinformation.oauth.exceptions.InvalidServiceRequestException;
import br.ufrn.imd.rankerinformation.oauth.exceptions.RequestException;
import br.ufrn.imd.rankerinformation.oauth.exceptions.UnauthorizedServiceRequestException;
import br.ufrn.imd.rankerinformation.util.JsonParserUtil;
import sigaa.model.Course;
import sigaa.model.User;

public class ProfileSearch {
	
	private static final String ACESS_PERFIL_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/perfilusuario/";
	private static final String ACESS_VINCULO_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/listavinculos/usuario";
	
	private RequestAuthorization authorization;
	private final JsonParserUtil jsonParserUtil = new JsonParserUtil();
	
	public ProfileSearch(RequestAuthorization authorization){
		this.authorization = authorization;
	}
	
	public Course colsultCourse() throws InvalidServiceRequestException, UnauthorizedServiceRequestException, RequestException, ParseException {
		
		String stringResponse = null;
		

		stringResponse = authorization.getResponse(ACESS_VINCULO_USER);

		
		Course course = new Course();
		
		JSONArray discentes = jsonParserUtil.extractJSONArray(stringResponse, "discentes");
		
		//TODO INSERIR EXCEÇÃO CASO NÃO ENCONTRE VINCULO 
		//AQUI PODE ACONTECER ERRO CASO NÃO HAJA VINCULOS 
		JSONObject discente =  (JSONObject) discentes.get(0);

		String curso = discente.get("curso").toString();
		
		course.setName(curso);
		
		return course;
	}
	
	public User consultUser() throws InvalidServiceRequestException, UnauthorizedServiceRequestException, RequestException, ParseException {

		String stringResponse = null;
		
		stringResponse = authorization.getResponse(ACESS_VINCULO_USER);


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
	
	private String colsultProfileName(int idUser) throws InvalidServiceRequestException, UnauthorizedServiceRequestException, RequestException, ParseException {

		String stringResponse;
		
		stringResponse = authorization.getResponse(ACESS_PERFIL_USER+idUser);


		String name = jsonParserUtil.extractStringJSONObject(stringResponse, "nome");

		return name;
	}
	
}
