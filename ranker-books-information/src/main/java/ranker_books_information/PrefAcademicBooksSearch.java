package ranker_books_information;

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

public class PrefAcademicBooksSearch implements PreferencesProviderSearch{
	
	private static final String ACESS_HISTORY_BIBL_USER = "https://api.ufrn.br/biblioteca-services/services/consulta/biblioteca/historico/emprestimo";

	private static final String ACESS_PERFIL_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/perfilusuario/";
	private static final String ACESS_VINCULO_USER = "https://apitestes.info.ufrn.br/ensino-services/services/consulta/listavinculos/usuario";
	
	private final JsonParserUtil jsonParserUtil;
	
	public PrefAcademicBooksSearch(){
		jsonParserUtil = new JsonParserUtil();
	}

	public List<SourceData> consultDataSource(RequestAuthorization authorization, User user) {
		
		List<SourceData> emprestimos = null;
		
		try {
			String stringResponse = authorization.getResponse(ACESS_HISTORY_BIBL_USER);
			
			JSONArray emprestimosArray = jsonParserUtil.extractJSONArray(stringResponse);
			
			emprestimos = new ArrayList<SourceData>();
			
			for(Object emprestimo : emprestimosArray){
				JSONObject emprestimoJASON = (JSONObject) emprestimo;
				
				int id = Integer.parseInt(emprestimoJASON.get("numeroChamada").toString()) ;
				String titulo = emprestimoJASON.get("titulo").toString();
				SourceData livro;
				livro = new SourceData(id, titulo, 1);
				emprestimos.add(livro);
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

		return emprestimos;
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

}