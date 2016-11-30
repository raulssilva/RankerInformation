package ranker_books_information;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.ufrn.imd.rankerinformation.engine.search.SearchEngine;
import br.ufrn.imd.rankerinformation.model.Information;

public class SaraivaBooksParserHTMLSearchEngine extends SearchEngine{
	
	@Override
	public void start() {
		List<Information> news = buscar();
		notifyAll(news);
	}


	public List<Information> buscar() {
		long start = System.currentTimeMillis();

    	String amazon = "https://www.amazon.com.br/gp/new-releases/books/ref=zg_bsnr_unv_b_1_7872854011_1";
    	System.out.println("[SEARCHING_BOOKS] https://www.amazon.com.br/gp/new-releases/books/ref=zg_bsnr_unv_b_1_7872854011_1");
    	
		List<String> links = new ArrayList<String>();
    	List<String> titlescategory = new ArrayList<String>();

    	Document doc = null;
		try {
			doc = Jsoup.connect(amazon).get();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements els = doc.getElementById("zg_browseRoot").getElementsByTag("a");
		
		for(Element el : els){
			titlescategory.add(el.text());
			links.add(el.attr("href"));
		}
		
		System.out.println("[SEARCHING_BOOKS] getting "+titlescategory.size()+" category books with "+links.size()+" Links");
		List<Elements> elements = new ArrayList<Elements>();
		int contLinkCorrect = 0;
		for(String link : links){
			try {
				System.out.print("[SEARCHING_BOOKS In] "+link);
				Elements booksArray = Jsoup.connect(link).get().getElementsByClass("zg_itemWrapper");
				elements.add(booksArray);
				System.out.println(" OK");
				contLinkCorrect++;
			} catch (IOException e) {
				System.out.println(" Error");
				titlescategory.remove(contLinkCorrect);
			}
		}
		
		
		List<Information> books = new ArrayList<Information>();
		
		int cont = 0;
		for(Elements eles : elements){
			for(Element element : eles){
				String title = element.getElementsByTag("span").first().attr("title");
				System.out.println(title);
//				String data = element.getElementsByClass("zg_releaseDate").first().text();
//				System.out.println(data);
				books.add(new Information("today", title, titlescategory.get(cont), cont));
			}
			cont++;
		}

		return books;
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
