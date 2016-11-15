package br.ufrn.imd.rankerinformation.dao;

import br.ufrn.imd.rankerinformation.user.model.SourceData;

public interface ISourceDataDAO {

	public void createSourceData(SourceData sourceData);
    
    public SourceData readSourceData(int ID_SOURCE_DATA);
    
    public void updateSourceData(int ID_SOURCEDATA, SourceData sourceData);
    
    public void deleteSourceData(int ID_SOURCEDATA);
}
