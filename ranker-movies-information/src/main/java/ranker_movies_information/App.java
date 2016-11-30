package ranker_movies_information;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.rankerinformation.db.DBGenerator;
import br.ufrn.imd.rankerinformation.engine.ManagerCycleLife;
import br.ufrn.imd.rankerinformation.engine.search.SearchEngine;
import br.ufrn.imd.rankerinformation.user.model.SourceData;
import br.ufrn.imd.rankerinformation.user.model.User;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {

		DBGenerator dbGenerator = new DBGenerator();
		dbGenerator.generateDataBase();
		
		/*Criando Preferencias manualmente*/
		User user = new User(1, "Felipe");
		List<SourceData> listSourceData= new ArrayList<SourceData>();
		listSourceData.add(new SourceData(1, "Aventura", 10));
		listSourceData.add(new SourceData(2, "da", 0));
		listSourceData.add(new SourceData(3, "Informação", 3));
		listSourceData.add(new SourceData(4, "Software", 5));
		
		//Cria um gerenciador de ciclo de vida pra cliente com Modelo de Associação pardrão 
		ManagerCycleLife mcl = new ManagerCycleLife(user.getId());
		
		//Inicia o gerenciador SETANDO as preferencias 
		mcl.setup(user, listSourceData);
		
		//Modifica o modelo de associação
		mcl.setModelAssociation(new GenreModelAssociation());
		
		//Cria um segundo usuário (mesmo processo anterior)
		User user2 = new User(2, "Raul");
		List<SourceData> listSourceData2= new ArrayList<SourceData>();
		listSourceData2.add(new SourceData(10, "Bad", 4));
		listSourceData2.add(new SourceData(20, "uhhh", 1));
		listSourceData2.add(new SourceData(30, "legal", 2));
		listSourceData2.add(new SourceData(40, "Brasil porra, toma Argentina", 3));
		ManagerCycleLife mcl2 = new ManagerCycleLife(user2.getId());
		mcl2.setup(user2, listSourceData2);
		mcl2.setModelAssociation(new GenreModelAssociation());
		
		//Cria um segundo usuário (mesmo processo anterior)
		User user3 = new User(3, "Jackson");
		List<SourceData> listSourceData3= new ArrayList<SourceData>();
		listSourceData3.add(new SourceData(11, "Aventura", 5));
		listSourceData3.add(new SourceData(22, "Infantil", 3));
		listSourceData3.add(new SourceData(33, "Ação", 2));
		listSourceData3.add(new SourceData(44, "Drama", 4));
		ManagerCycleLife mcl3 = new ManagerCycleLife(user3.getId());
		mcl3.setup(user3, listSourceData3);
		mcl3.setModelAssociation(new GenreModelAssociation());
		
		
		/*Implementa uma ferrmenta de busca*/
		SearchEngine sn = new MoviesSearchEngine();	
		
		//Adiciona um observador ao gerente de busca ()
		sn.addObserver(mcl);
		sn.addObserver(mcl2);
		sn.addObserver(mcl3);
		
		
		//Start no processo de busca
		sn.start();

	}
}
