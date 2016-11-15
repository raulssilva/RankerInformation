package br.ufrn.imd.rankerinformation.dao;

import br.ufrn.imd.rankerinformation.user.model.User;

public interface IUserDAO {
	
	public void createUser(User user);
    
    public User readUser(int ID_USER);
    
    public void updateUser(int ID_USER, User user);
    
    public void deleteUser(int ID_USER);

}
