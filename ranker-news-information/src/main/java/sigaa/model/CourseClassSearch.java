package sigaa.model;

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
import br.ufrn.imd.rankerinformation.util.JsonParserUtil;

public class CourseClassSearch {
	
	private static final String ACESS_DISCIPLINAS_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/matriculacomponente/discente/";//{idDiscente}/all;
	private static final String ACESS_DISCIPLINA_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/turma/usuario/";
	
	private RequestAuthorization authorization;
	private final JsonParserUtil jsonParserUtil = new JsonParserUtil();
	
	public CourseClassSearch(RequestAuthorization authorization){
		this.authorization = authorization;
	}

	public List<SourceData> consultCourseClass(int idUser) throws InvalidServiceRequestException, UnauthorizedServiceRequestException, RequestException, ParseException {

		String stringResponse = null;
		
		stringResponse = authorization.getResponse(ACESS_DISCIPLINAS_USER+idUser+"/all");

		List<SourceData> disciplinas = new ArrayList<SourceData>();

		JSONArray turmas = jsonParserUtil.extractJSONArray(stringResponse);
		
		for(Object turma : turmas){
			JSONObject turmaJASON = (JSONObject) turma;
			
			int idTurma = Integer.parseInt(turmaJASON.get("idTurma").toString()) ;
			SourceData disciplina = consultSubject(idTurma);
			disciplinas.add(disciplina);
		}
		
		return disciplinas;
	}
	
	public RequestAuthorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(RequestAuthorization authorization) {
		this.authorization = authorization;
	}

	private SourceData consultSubject(int courseClassId) throws InvalidServiceRequestException, UnauthorizedServiceRequestException, RequestException, ParseException{
		
		String stringResponse = null;
		
		stringResponse = authorization.getResponse(ACESS_DISCIPLINA_USER+courseClassId);

		JSONObject disciplina = jsonParserUtil.extractJSONObject(stringResponse);

		String nomeComponente = disciplina.get("nomeComponente").toString() ;
		int idDisciplina = Integer.parseInt(disciplina.get("idDisciplina").toString()) ;
		
		SourceData sourceData = new SourceData(idDisciplina, nomeComponente, 1);
		return sourceData;
	}
	
}
