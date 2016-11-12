package br.ufrn.imd.sise.engine;

import java.util.List;

import br.ufrn.imd.sise.engine.model.Information;
public interface Observer {
	public void update(List<Information> informations);
}
