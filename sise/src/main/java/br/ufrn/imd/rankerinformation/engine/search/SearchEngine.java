package br.ufrn.imd.rankerinformation.engine.search;

import java.util.List;

import br.ufrn.imd.rankerinformation.engine.Observable;
import br.ufrn.imd.rankerinformation.engine.model.Information;

public abstract class SearchEngine extends Observable{

	public abstract List<Information> buscar(); 

	public abstract void start();

}
