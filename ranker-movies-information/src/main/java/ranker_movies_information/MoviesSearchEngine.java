package ranker_movies_information;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.ufrn.imd.rankerinformation.engine.search.SearchEngine;
import br.ufrn.imd.rankerinformation.model.Information;

public class MoviesSearchEngine extends SearchEngine{

	
	@Override
	public void start() {
		List<Information> news = buscar();
		notifyAll(news);
	}
	
	@Override
	public List<Information> buscar() {
		String noticia = "http://www3.cinemark.com.br/natal/cinemas?cinema=681";
		Document doc = null;
		try {
			doc = Jsoup.connect(noticia).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Element el = doc.getElementsByClass("tabs-content").get(0).getElementsByClass("active").get(0);
		Elements elTitles = el.getElementsByClass("title");
		
		List<String> titles = new ArrayList<String>();
		List<String> generos = new ArrayList<String>();
		List<String> linksGeneros = new ArrayList<String>();
		
		for(Element eli : elTitles){
			titles.add(eli.text());
			linksGeneros.add("http://www3.cinemark.com.br"+eli.getElementsByTag("a").get(0).attr("href"));
		}
		for(String linkgenero : linksGeneros){
			String genero = null;
			try {
				doc = Jsoup.connect(linkgenero).get();
				Element elsgen = doc.getElementsByClass("detail-title").get(4);
				genero = elsgen.text().replaceAll("Gênero:", "");
				
			} catch (IOException e) {
				System.err.println("[FAIL] "+ linkgenero);
				//e.printStackTrace();
			}
			if(genero!=null)
				generos.add(genero);
		}
		System.out.println(titles.size() +", "+generos.size() );
		
		List<Information> informations = new ArrayList<Information>();
		if(titles.size() == generos.size()){
			for(int i = 0; i < titles.size(); i++){
				informations.add(new Information("Hoje", titles.get(i), generos.get(i)));
			}
		}
		return informations;
	}

	

}
