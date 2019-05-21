package Mysql_operate;

import java.sql.SQLException;
import java.util.List;

import Enity.User;

public interface UserDao{
	
	List<User> findAllUsers() throws SQLException;
	
	int userLogin(String username,String password) throws SQLException;
	boolean userRegist(String username,String password) throws SQLException;
	boolean username(String username) throws SQLException;
    
}