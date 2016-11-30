package ranker_books_information;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.rankerinformation.db.DBGenerator;
import br.ufrn.imd.rankerinformation.engine.ManagerCycleLife;
import br.ufrn.imd.rankerinformation.engine.search.SearchEngine;
import br.ufrn.imd.rankerinformation.user.model.SourceData;
import br.ufrn.imd.rankerinformation.user.model.User;

public class App {

//	private static final String ACESS_TOKEN = "41db62f2-eca3-46db-8ca5-dfe8de87dd86";
//	private static final int ID_USER = 642534;
	
    public static void main( String[] args ){
    	DBGenerator dbGenerator = new DBGenerator();
		dbGenerator.generateDataBase();
		
		User raul = new User(1, "Raul");
		List<SourceData> raulListSourceData= new ArrayList<SourceData>();
		raulListSourceData.add(new SourceData(1, "Arte, Cinema e Fotografia", 10));
		raulListSourceData.add(new SourceData(2, "Ciências Tecnológicas", 0));
		raulListSourceData.add(new SourceData(3, "Computação, Informática e Mídias Digitais", 3));
		raulListSourceData.add(new SourceData(4, "Fantasia, Horror e Ficção Científica", 5));
		raulListSourceData.add(new SourceData(5, "HQs, Mangás e Graphic Novels", 5));
		
		//Cria um gerenciador de ciclo de vida pra cliente com Modelo de Associação pardrão 
		//ManagerCycleLife mcl = new ManagerCycleLife(ID_USER);
		ManagerCycleLife raulMCL = new ManagerCycleLife(raul.getId());
		
		//Inicia o gerenciador SETANDO as preferencias 
		//mcl.setup(ACESS_TOKEN, new PrefAcademicBooksSearch());
		raulMCL.setup(raul, raulListSourceData);
		
		//Modifica o modelo de associação
		// TODO Jackson, colocar seu modelo de associação aqui
		raulMCL.setModelAssociation(new CategoryModelAssociation());
		
		/*Implementa uma ferrmenta de busca*/
		SearchEngine sn = new BooksSearchEngine();	
		
		//Adiciona um observador ao gerente de busca ()
		sn.addObserver(raulMCL);
		
		//Start no processo de busca
		sn.start();
    }
}
