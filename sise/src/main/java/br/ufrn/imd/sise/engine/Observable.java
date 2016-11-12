package br.ufrn.imd.sise.engine;

import java.util.ArrayList;
import java.util.List;

import br.ufrn.imd.sise.engine.model.Information;

public class Observable {
	
	private List<Observer> observers;
	
	public Observable(){
		observers = new ArrayList<Observer>();
	}
	
	public void addObserver(Observer observer){
		observers.add(observer);
	}
	
	public void notifyAll(List<Information> informations){
		for (Observer observer : observers) {
			observer.update(informations);
		}
	}

}
