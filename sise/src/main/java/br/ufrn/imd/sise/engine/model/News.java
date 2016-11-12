package br.ufrn.imd.sise.engine.model;

public class News extends Information{
	
	private String originURL;
	private String time;
	
	@Override
	public void informationDescription() {
		// TODO Auto-generated method stub
	}

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
