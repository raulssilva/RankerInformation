package br.ufrn.imd.rankerinformation.engine;

import java.util.List;

import br.ufrn.imd.rankerinformation.model.Information;
public interface Observer {
	public void update(List<Information> informations);
}
