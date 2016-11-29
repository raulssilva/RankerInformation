package ranker_news_information;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.ufrn.imd.rankerinformation.engine.search.SearchEngine;
import br.ufrn.imd.rankerinformation.model.Information;

public class NewsParserHTMLSearchEngine extends SearchEngine{
	
	@Override
	public void start() {
		List<Information> news = buscar();
		notifyAll(news);
	}


	public List<Information> buscar() {
		long start = System.currentTimeMillis();

    	String noticia = "https://sistemas.ufrn.br/portal/PT/noticia#";
    	System.out.println("[SEARCHING_NEWS] https://sistemas.ufrn.br/portal/PT/noticia#");
    	
		List<String> links = new ArrayList<String>();
    	List<String> dates = new ArrayList<String>();
    	List<String> titles = new ArrayList<String>();
    	
    	List<String> times = new ArrayList<String>();
    	List<String> contents = new ArrayList<String>();

    	Document doc = null;
		try {
			doc = Jsoup.connect(noticia).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Elements doc1 = doc.getElementsByClass("listagem_noticia"); //Pega Lista de Notícia
    	
    	Elements elements = doc1.get(0).getElementsByTag("a"); //Pega Lista de Notícia

    	for (Element element : elements) {
    		links.add(element.attr("href").toString());//Pega O link da notícia
    		dates.add(element.getElementsByTag("strong").get(0).text()); //Pega a data
    		titles.add(element.text().substring(13).trim()); //Pega O título
		}
    	
    	for (String link : links) {
    		Document acessNotice = null;
			try {
				acessNotice = Jsoup.connect(link).timeout(3000).get();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		Element notice = acessNotice.getElementsByClass("altura_minima").get(0);
    		
    		times.add(notice.getElementsByClass("data").get(0).text().substring(15).trim()); //Pega a hora
    		Element noticeWithHTML = notice.getElementsByTag("div").get(1); //Pega a noticia suja com html
    		contents.add(noticeWithHTML.toString());
    	}
    	
    	long elapsed = System.currentTimeMillis() - start;
    	System.out.println();
    	System.out.println("[SEARCHING_NEWS] time " + elapsed/1000.0 + " segundos");
    	ArrayList<Information> listNews = new ArrayList<Information>(); 
    	
    	for (int i = 0; i < links.size(); i++) {
    		News news = new News();
    		news.setTitle(titles.get(i));
    		news.setContent(extractDataFromHTML(contents.get(i)));
    		news.setOriginURL(links.get(i));
    		news.setDate(dates.get(i));
    		news.setTime(times.get(i));
			listNews.add(news);
		}

		return listNews;
	}
	
	
	private String extractDataFromHTML(String content){
		String contentCleared = "";
		int flag = 0;
		for(int i = 0; i < content.length(); i++){
			if(content.charAt(i) == '<'){
				flag = 1;
			}
			
			if(flag == 0){
				if(content.charAt(i) != '\n'){
					contentCleared += content.charAt(i);					
				}
			}
			
			if(content.charAt(i) == '>'){
				flag = 0;
			}
		}
		return contentCleared.trim();
	}

	
}
