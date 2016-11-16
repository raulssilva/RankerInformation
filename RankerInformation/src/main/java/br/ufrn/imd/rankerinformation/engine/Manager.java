package br.ufrn.imd.rankerinformation.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.ufrn.imd.rankerinformation.db.DBGenerator;
import br.ufrn.imd.rankerinformation.engine.filter.GenreMatchingModelAssociation;
import br.ufrn.imd.rankerinformation.engine.filter.IntersectionModelAssociation;
import br.ufrn.imd.rankerinformation.engine.filter.IntersectionModelAssociation2;
import br.ufrn.imd.rankerinformation.engine.search.MoviesSearchEngine;
import br.ufrn.imd.rankerinformation.engine.search.SearchEngine;
import br.ufrn.imd.rankerinformation.model.Information;
import br.ufrn.imd.rankerinformation.user.model.SourceData;
import br.ufrn.imd.rankerinformation.user.model.User;

public class Manager {

	public static void main(String[] args) {

		DBGenerator dbGenerator = new DBGenerator();
		dbGenerator.generateDataBase();
		
		//Cria um segundo usuário (mesmo processo anterior)
		User user2 = new User(2, "Raul");
		List<SourceData> listSourceData2= new ArrayList<>();
		listSourceData2.add(new SourceData(10, "ação", 10));
		listSourceData2.add(new SourceData(20, "comedia", 1));
		listSourceData2.add(new SourceData(30, "infantil", 2));
		ManagerCycleLife mcl2 = new ManagerCycleLife(user2.getId());
		mcl2.setup(user2, listSourceData2);
		mcl2.setModelAssociation(new IntersectionModelAssociation2());
		
		//Cria um segundo usuário (mesmo processo anterior)
		User user3 = new User(3, "Jackson");
		List<SourceData> listSourceData3= new ArrayList<>();
		listSourceData3.add(new SourceData(11, "Aventura", 5));
		listSourceData3.add(new SourceData(22, "Infantil", 3));
		listSourceData3.add(new SourceData(33, "Ação", 2));
		listSourceData3.add(new SourceData(44, "Drama", 4));
		ManagerCycleLife mcl3 = new ManagerCycleLife(user3.getId());
		mcl3.setup(user3, listSourceData3);
		mcl3.setModelAssociation(new GenreMatchingModelAssociation());
		
		/*Implementa uma ferrmenta de busca*/
		SearchEngine sn = new MoviesSearchEngine();
		
		//Adiciona um observador ao gerente de busca ()
		sn.addObserver(mcl2);
		sn.addObserver(mcl3);
		
		//Start no processo de busca
		sn.start();

	}
}
