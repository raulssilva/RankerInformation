package br.ufrn.imd.rankerinformation.engine;

import java.util.List;


import org.json.simple.parser.ParseException;

import br.ufrn.imd.rankerinformation.dao.PrefferencesDAO;
import br.ufrn.imd.rankerinformation.dao.UserDAO;
import br.ufrn.imd.rankerinformation.engine.filter.Analyzer;
import br.ufrn.imd.rankerinformation.engine.filter.IntersectionModelAssociation;
import br.ufrn.imd.rankerinformation.engine.model.Information;
import br.ufrn.imd.rankerinformation.oauth.RequestAuthorization;
import br.ufrn.imd.rankerinformation.oauth.exceptions.RequestException;
import br.ufrn.imd.rankerinformation.oauth.exceptions.UnauthorizedServiceRequestException;
import br.ufrn.imd.rankerinformation.user.model.Prefferences;
import br.ufrn.imd.rankerinformation.user.model.User;
import br.ufrn.imd.rankerinformation.user.search.PrefferencesSearch;
import br.ufrn.imd.rankerinformation.user.search.exceptions.ServiceAPIException;

public class ManagerCycleLife implements Observer {
	
	private String acess_token;
	private int iduserCycleLife;
	private Prefferences prefferences;
	
	public ManagerCycleLife(String acess_token, int iduserCycleLife){
		this.acess_token = acess_token;
		this.iduserCycleLife = iduserCycleLife;
		setup();
	}
	
	public void setup(){
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.readUser(iduserCycleLife);
		if(user == null || user.getId() == 0){
			//Busca na API do SIGAA
			try {
				//Cria o mecanismo de busca de preferencia do cliente passando a autorização correspondente
				RequestAuthorization authorization = new RequestAuthorization(this.acess_token);
				PrefferencesSearch pSearchEngine = new PrefferencesSearch(authorization);
				prefferences = pSearchEngine.searchPrefferences();
				System.out.println("[SETUP] Dados buscado na API Ddo SIGAA");
			} catch (UnauthorizedServiceRequestException | RequestException | ServiceAPIException | ParseException e) {
				e.printStackTrace();
			}
		}else{
			//Busca no banco
			PrefferencesDAO prefferencesDAO = new PrefferencesDAO();
			this.prefferences = prefferencesDAO.readPrefferencesByIdUser(iduserCycleLife); 
			System.out.println("----------------------");
			System.out.println("[SETUP] Dados buscado no Banco de dados");
			System.out.println("[SETUP] "+prefferences.toString());
			System.out.println("----------------------");
		}

	}

	@Override
	public void update(List<Information> informations) {
		Analyzer analyzer = new Analyzer();
		@SuppressWarnings("unused")
		List<Information> analyzedList = analyzer.analyze(informations, prefferences, new IntersectionModelAssociation());
//		List<Information> analyzedList = analyzer.analyze(informations, prefferences, GoogleModelAssociation.getInstance());
		
		//TODO SALVA analyzedList NO BANCO
		
	}

	

}
