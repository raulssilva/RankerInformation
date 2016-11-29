package ranker_news_information;

import br.ufrn.imd.rankerinformation.model.Information;

public class News extends Information{
	
	private String originURL;
	private String time;
	
	public String getOriginURL() {
		return originURL;
	}

	public void setOriginURL(String originURL) {
		this.originURL = originURL;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
}
