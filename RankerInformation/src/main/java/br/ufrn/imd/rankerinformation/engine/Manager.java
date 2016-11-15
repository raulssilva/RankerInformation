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
		
		/*Criando Preferencias manualmente*/
		User user = new User(1, "Felipe");
		List<SourceData> listSourceData= new ArrayList<>();
		listSourceData.add(new SourceData(1, "Aventura", 10));
		listSourceData.add(new SourceData(2, "da", 0));
		listSourceData.add(new SourceData(3, "Informação", 3));
		listSourceData.add(new SourceData(4, "Software", 5));
		
		//Cria um gerenciador de ciclo de vida pra cliente com Modelo de Associação pardrão 
		ManagerCycleLife mcl = new ManagerCycleLife(user.getId());
		
		//Inicia o gerenciador SETANDO as preferencias 
		mcl.setup(user, listSourceData);
		
		//Modifica o modelo de associação
		mcl.setModelAssociation(new IntersectionModelAssociation());
		
		
		//Cria um segundo usuário (mesmo processo anterior)
		User user2 = new User(2, "Raul");
		List<SourceData> listSourceData2= new ArrayList<>();
		listSourceData2.add(new SourceData(10, "Bad", 4));
		listSourceData2.add(new SourceData(20, "uhhh", 1));
		listSourceData2.add(new SourceData(30, "legal", 2));
		listSourceData2.add(new SourceData(40, "Brasil porra, toma Argentina", 3));
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
//		SearchEngine sn = new SearchEngine() {
//			@Override
//			public void start() {
//				List<Information> news = buscar();
//				notifyAll(news);
//			}
//			
//			@Override
//			public List<Information> buscar() {
//				String noticia = "http://www3.cinemark.com.br/natal/cinemas?cinema=681";
//				Document doc = null;
//				try {
//					doc = Jsoup.connect(noticia).get();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				Element el = doc.getElementsByClass("tabs-content").get(0).getElementsByClass("active").get(0);
//				Elements elTitles = el.getElementsByClass("title");
//				
//				List<String> titles = new ArrayList<>();
//				List<String> generos = new ArrayList<>();
//				List<String> linksGeneros = new ArrayList<>();
//				
//				for(Element eli : elTitles){
//					titles.add(eli.text());
//					linksGeneros.add("http://www3.cinemark.com.br"+eli.getElementsByTag("a").get(0).attr("href"));
//				}
//				for(String linkgenero : linksGeneros){
//					String genero = null;
//					try {
//						doc = Jsoup.connect(linkgenero).get();
//						Element elsgen = doc.getElementsByClass("detail-title").get(4);
//						genero = elsgen.text().replaceAll("Gênero:", "");
//						
//					} catch (IOException e) {
//						System.err.println("[FAIL] "+ linkgenero);
//						//e.printStackTrace();
//					}
//					if(genero!=null)
//						generos.add(genero);
//				}
//				System.out.println(titles.size() +", "+generos.size() );
//				
//				List<Information> informations = new ArrayList<>();
//				if(titles.size() == generos.size()){
//					for(int i = 0; i < titles.size(); i++){
//						informations.add(new Information("Hoje", titles.get(i), generos.get(i)));
//					}
//				}
////				informations.add(new Information("28.10.2016", "Noticia1", "Informação Software"));
////				informations.add(new Information("28.10.2016", "Noticia2", "brincadeira de criança, como é bom, como é bom"));
////				informations.add(new Information("28.10.2016", "Noticia3", "Brasil 3 x 0 Argentina"));
//				return informations;
//			}
//		};
		
		
		//Adiciona um observador ao gerente de busca ()
		sn.addObserver(mcl);
		sn.addObserver(mcl2);
		sn.addObserver(mcl3);
		
		
		//Start no processo de busca
		sn.start();

	}

}
