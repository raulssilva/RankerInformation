package br.ufrn.imd.rankerinformation.engine.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import br.ufrn.imd.rankerinformation.engine.model.Information;

public class MoviesSearchEngine extends SearchEngine{

	
	@Override
	public void start() {
		List<Information> news = buscar();
		notifyAll(news);
	}
	
	@Override
	public List<Information> buscar() {
		long start = System.currentTimeMillis();
		
		String filme = "http://www3.cinemark.com.br/natal/cinemas?cinema=681";
    	System.out.println("[SEARCHING_MOVIES] http://www3.cinemark.com.br/natal/cinemas?cinema=681");
    	
    	List<String> links = new ArrayList<String>();
    	List<String> genres = new ArrayList<String>();
    	List<String> titles = new ArrayList<String>();
    	
    	List<String> contents = new ArrayList<String>();
    	
    	Document doc = null;
		try {
			doc = Jsoup.connect(filme).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		Elements doc1 = doc.getElementsByClass("listagem_noticia"); //Pega Lista de Notícia
		
		
		return null;
	}

	

}
