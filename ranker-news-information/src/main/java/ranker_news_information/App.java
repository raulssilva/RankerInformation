package ranker_news_information;

import br.ufrn.imd.rankerinformation.db.DBGenerator;
import br.ufrn.imd.rankerinformation.engine.ManagerCycleLife;
import br.ufrn.imd.rankerinformation.engine.filter.modelsassociation.IntersectionModelAssociation;
import br.ufrn.imd.rankerinformation.engine.search.SearchEngine;

public class App {
	
	private static final String ACESS_TOKEN = "6d2bd6a4-8196-4f20-8b5d-8916d3d2770a";
	private static final int ID_USER = 361457;
	
    public static void main( String[] args ){
    	DBGenerator dbGenerator = new DBGenerator();
		dbGenerator.generateDataBase();
		
//		
		//Cria um gerenciador de ciclo de vida pra cliente com Modelo de Associação pardrão 
		ManagerCycleLife mcl = new ManagerCycleLife(ID_USER);
		
		//Inicia o gerenciador SETANDO as preferencias 
		mcl.setup(ACESS_TOKEN, new PrefAcademicProviderSearch());
		
		//Modifica o modelo de associação
		mcl.setModelAssociation(new IntersectionModelAssociation());
		

		/*Implementa uma ferrmenta de busca*/
		SearchEngine sn = new NewsParserHTMLSearchEngine();	
		
		//Adiciona um observador ao gerente de busca ()
		sn.addObserver(mcl);
		
		//Start no processo de busca
		sn.start();
    }
}
