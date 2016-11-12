package br.ufrn.imd.rankerinformation.engine;

import br.ufrn.imd.rankerinformation.db.DBGenerator;
import br.ufrn.imd.rankerinformation.engine.search.NewsParserHTMLSearchEngine;
import br.ufrn.imd.rankerinformation.engine.search.SearchEngine;

public class Manager {

	private static final String ACESS_TOKEN = "6d2bd6a4-8196-4f20-8b5d-8916d3d2770a";
	private static final int ID_USER = 361457;
	
	public static void main(String[] args) {
		
		//Gera tabelas necessárias
		DBGenerator dbGenerator = new DBGenerator();
		dbGenerator.generateDataBase();
		
		//Cria um gerenciador de ciclo de vida pra cliente (simulando a conexão com a aplicação cliente)
		ManagerCycleLife mcl = new ManagerCycleLife(ACESS_TOKEN, ID_USER);
		
		//Cria um gerente de busca de noticias
		SearchEngine sn = new NewsParserHTMLSearchEngine();
		
		//Adiciona um observador ao gerente de busca
		sn.addObserver(mcl);
		
		//Start no processo de busca
		sn.start();

	}

}
