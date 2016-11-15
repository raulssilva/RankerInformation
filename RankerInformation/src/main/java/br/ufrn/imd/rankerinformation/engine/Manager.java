package br.ufrn.imd.rankerinformation.engine;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.rankerinformation.db.DBGenerator;
import br.ufrn.imd.rankerinformation.engine.model.Information;
import br.ufrn.imd.rankerinformation.engine.search.SearchEngine;
import br.ufrn.imd.rankerinformation.user.model.SourceData;
import br.ufrn.imd.rankerinformation.user.model.User;

public class Manager {

	public static void main(String[] args) {

		DBGenerator dbGenerator = new DBGenerator();
		dbGenerator.generateDataBase();
		
		/*Criando Preferencias manualmente*/
		User user = new User(1, "Felipe");
		List<SourceData> listSourceData= new ArrayList<>();
		listSourceData.add(new SourceData(1, "Tecnologia", 4));
		listSourceData.add(new SourceData(2, "da", 0));
		listSourceData.add(new SourceData(3, "Informação", 3));
		listSourceData.add(new SourceData(4, "Software", 5));
		//Cria um gerenciador de ciclo de vida pra cliente 
		ManagerCycleLife mcl = new ManagerCycleLife(user.getId());
		//Inicia o gerenciador SETANDO as preferencias 
		mcl.setup(user, listSourceData);
		
		
//		User user2 = new User(2, "Jão");
//		List<SourceData> listSourceData2= new ArrayList<>();
//		listSourceData2.add(new SourceData(10, "aASS", 4));
//		listSourceData2.add(new SourceData(20, "bABB", 1));
//		listSourceData2.add(new SourceData(30, "ASSAc", 2));
//		listSourceData2.add(new SourceData(40, "ASASd", 3));
//		ManagerCycleLife mcl2 = new ManagerCycleLife(user.getId());
//		mcl2.setup(user2, listSourceData2);
		
		
		/*Isso aqui deve ser criado em uma nova classe*/
		SearchEngine sn = new SearchEngine() {
			@Override
			public void start() {
				List<Information> news = buscar();
				notifyAll(news);
			}
			
			@Override
			public List<Information> buscar() {
				List<Information> informations = new ArrayList<>();
				informations.add(new Information("28.10.2016", "Noticia1", "Informação Software"));
				informations.add(new Information("28.10.2016", "Noticia2", "brincadeira de criança, como é bom, como é bom"));
				informations.add(new Information("28.10.2016", "Noticia3", "Brasil 3 x 0 Argentina"));
				return informations;
			}
		};
		
		//Adiciona um observador ao gerente de busca
		sn.addObserver(mcl);
//		sn.addObserver(mcl2);
		
		//Start no processo de busca
		sn.start();

	}

}
