package ranker_news_information;

import br.ufrn.imd.rankerinformation.db.DBGenerator;
import br.ufrn.imd.rankerinformation.engine.ManagerCycleLife;
import br.ufrn.imd.rankerinformation.engine.filter.modelsassociation.IntersectionModelAssociation2;
import br.ufrn.imd.rankerinformation.engine.search.SearchEngine;

public class App {

	private static final String ACESS_TOKEN = "41db62f2-eca3-46db-8ca5-dfe8de87dd86";
	private static final int ID_USER = 642534;
	
    public static void main( String[] args ){
    	DBGenerator dbGenerator = new DBGenerator();
		dbGenerator.generateDataBase();
		
//		
		//Cria um gerenciador de ciclo de vida pra cliente com Modelo de Associação pardrão 
		ManagerCycleLife mcl = new ManagerCycleLife(ID_USER);
		
		//Inicia o gerenciador SETANDO as preferencias 
		mcl.setup(ACESS_TOKEN, new PrefAcademicProviderSearch());
		
		//Modifica o modelo de associação
		mcl.setModelAssociation(new IntersectionModelAssociation2());
		

		/*Implementa uma ferrmenta de busca*/
		SearchEngine sn = new NewsSearchEngine();	
		
		//Adiciona um observador ao gerente de busca ()
		sn.addObserver(mcl);
		
		//Start no processo de busca
		sn.start();
    }
}
