package br.ufrn.imd.sise.user.search;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import br.ufrn.imd.sise.oauth.RequestAuthorization;
import br.ufrn.imd.sise.oauth.exceptions.InvalidServiceRequestException;
import br.ufrn.imd.sise.oauth.exceptions.RequestException;
import br.ufrn.imd.sise.oauth.exceptions.UnauthorizedServiceRequestException;
import br.ufrn.imd.sise.user.model.CourseClass;
import br.ufrn.imd.sise.user.model.Subject;
import br.ufrn.imd.sise.user.search.exceptions.ConsultCourseClassException;
import br.ufrn.imd.sise.user.search.exceptions.CourseClassIdException;

public class CourseClassSearch {
	
	private static final String ACESS_DISCIPLINAS_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/matriculacomponente/discente/";//{idDiscente}/all;
	private static final String ACESS_DISCIPLINA_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/turma/usuario/";
	
	private RequestAuthorization authorization;
	private final JsonParserUtil jsonParserUtil = new JsonParserUtil();
	
	public CourseClassSearch(RequestAuthorization authorization){
		this.authorization = authorization;
	}

	public List<CourseClass> consultCourseClass(int idUser) throws UnauthorizedServiceRequestException, RequestException, ConsultCourseClassException, ParseException, CourseClassIdException{

		String stringResponse = null;
		
		try {
			stringResponse = authorization.getResponse(ACESS_DISCIPLINAS_USER+idUser+"/all");
		} catch (InvalidServiceRequestException e1) {
			throw new ConsultCourseClassException(ACESS_DISCIPLINAS_USER+idUser+"/all", idUser);
		} 
		
		List<CourseClass> coursesClass = new ArrayList<CourseClass>();

		JSONArray turmas = jsonParserUtil.extractJSONArray(stringResponse);
		
		for(Object turma : turmas){
			JSONObject turmaJASON = (JSONObject) turma;
			
			int idTurma = Integer.parseInt(turmaJASON.get("idTurma").toString()) ;
			String description = turmaJASON.get("idTurma").toString();
			Subject subject = consultSubject(idTurma);
			
			CourseClass courseClass = new CourseClass(idTurma, subject, description);
			
			coursesClass.add(courseClass);
		}
		
		return coursesClass;
	}
	
	public RequestAuthorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(RequestAuthorization authorization) {
		this.authorization = authorization;
	}

	private Subject consultSubject(int courseClassId) throws UnauthorizedServiceRequestException, RequestException, ParseException, CourseClassIdException{
		
		String stringResponse = null;
		
		try {
			stringResponse = authorization.getResponse(ACESS_DISCIPLINA_USER+courseClassId);
		} catch (InvalidServiceRequestException e1) {
			throw new CourseClassIdException(ACESS_DISCIPLINA_USER, courseClassId);
		} 
		
		JSONObject disciplina = jsonParserUtil.extractJSONObject(stringResponse);

		String nomeComponente = disciplina.get("nomeComponente").toString() ;
		String codigoComponente = disciplina.get("codigoComponente").toString() ;
		int idDisciplina = Integer.parseInt(disciplina.get("idDisciplina").toString()) ;
		
		Subject susbject = new Subject(idDisciplina, nomeComponente, codigoComponente);

		return susbject;
	}
	
}
