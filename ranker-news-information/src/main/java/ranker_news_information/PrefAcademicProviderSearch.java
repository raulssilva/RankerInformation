package ranker_news_information;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import br.ufrn.imd.rankerinformation.oauth.RequestAuthorization;
import br.ufrn.imd.rankerinformation.oauth.exceptions.InvalidServiceRequestException;
import br.ufrn.imd.rankerinformation.oauth.exceptions.RequestException;
import br.ufrn.imd.rankerinformation.oauth.exceptions.UnauthorizedServiceRequestException;
import br.ufrn.imd.rankerinformation.user.model.SourceData;
import br.ufrn.imd.rankerinformation.user.model.User;
import br.ufrn.imd.rankerinformation.user.search.PreferencesProviderSearch;
import br.ufrn.imd.rankerinformation.util.JsonParserUtil;

public class PrefAcademicProviderSearch implements PreferencesProviderSearch{
	
	private static final String ACESS_DISCIPLINAS_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/matriculacomponente/discente/";//{idDiscente}/all;
	private static final String ACESS_DISCIPLINA_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/turma/usuario/";
	
	private static final String ACESS_PERFIL_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/perfilusuario/";
	private static final String ACESS_VINCULO_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/listavinculos/usuario";
	
	private final JsonParserUtil jsonParserUtil;
	
	public PrefAcademicProviderSearch(){
		jsonParserUtil = new JsonParserUtil();
	}

	public List<SourceData> consultDataSource(RequestAuthorization authorization, User user) {
		
		List<SourceData> disciplinas = null;
		
		try {
			String stringResponse = authorization.getResponse(ACESS_DISCIPLINAS_USER+user.getId()+"/all");
			
			JSONArray turmas = jsonParserUtil.extractJSONArray(stringResponse);
			
			disciplinas = new ArrayList<SourceData>();
			
			ArrayList<Integer> testidDisciplina = new ArrayList<Integer>();
			for(Object turma : turmas){
				JSONObject turmaJASON = (JSONObject) turma;
				
				int idTurma = Integer.parseInt(turmaJASON.get("idTurma").toString()) ;
				SourceData disciplina;

				disciplina = consultSubject(idTurma, authorization);
				
				if(!testidDisciplina.contains(disciplina.getId())){
					disciplinas.add(disciplina);
					testidDisciplina.add(disciplina.getId());
				}
				
			}
			
		} catch (InvalidServiceRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnauthorizedServiceRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return disciplinas;
	}

	public User consultUser(RequestAuthorization authorization) {

		User user = null;
		
		try {
			
			JSONArray discentes;
			String stringResponse = null;
			stringResponse = authorization.getResponse(ACESS_VINCULO_USER);
			discentes = jsonParserUtil.extractJSONArray(stringResponse, "discentes");
			JSONObject discente =  (JSONObject) discentes.get(0);
			int idUsuario = Integer.parseInt(discente.get("idUsuario").toString()) ;
			int idDiscente = Integer.parseInt(discente.get("id").toString()) ;
			String name = colsultProfileName(idUsuario, authorization);
			user = new User(idDiscente, name);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidServiceRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnauthorizedServiceRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}
	
	private String colsultProfileName(int idUser, RequestAuthorization authorization) throws ParseException, InvalidServiceRequestException, UnauthorizedServiceRequestException, RequestException{
		String stringResponse = authorization.getResponse(ACESS_PERFIL_USER+idUser);
		String name = jsonParserUtil.extractStringJSONObject(stringResponse, "nome");
		return name;
	}
	
	private SourceData consultSubject(int courseClassId, RequestAuthorization authorization) throws InvalidServiceRequestException, UnauthorizedServiceRequestException, RequestException, ParseException{
		String stringResponse = null;
		stringResponse = authorization.getResponse(ACESS_DISCIPLINA_USER+courseClassId);
		JSONObject disciplina = jsonParserUtil.extractJSONObject(stringResponse);
		String nomeComponente = disciplina.get("nomeComponente").toString() ;
		int idDisciplina = Integer.parseInt(disciplina.get("idDisciplina").toString()) ;
		SourceData sourceData = new SourceData(idDisciplina, nomeComponente, 1);
		return sourceData;
	}

}