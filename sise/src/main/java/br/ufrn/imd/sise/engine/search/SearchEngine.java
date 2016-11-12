package br.ufrn.imd.sise.engine.search;

import java.util.List;

import br.ufrn.imd.sise.engine.Observable;
import br.ufrn.imd.sise.engine.model.Information;

public abstract class SearchEngine extends Observable{

	public abstract List<Information> buscar(); 

	public abstract void start();

}
