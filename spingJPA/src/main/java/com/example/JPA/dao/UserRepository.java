package com.example.JPA.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.JPA.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	//Custom Binder ///Derive Query Methods:
	public List<User> findByName(String name);
	
	public List<User>findByNameAndCity(String name,String city);
	
	
	/*
	 * findByNameStartingWith(String prefix);
	 *  findByNameEndWith(String suffix);
	 * findByNameContaining(String words);
	 * 
	 * findByNameLike(String likepattern);
	 *  findByAgeLessThan(int age);
	 * findByAgeIn(Collection<Integer> ages); 
	 * findByNameOrderByName(String name);
	 */

	
	//Custom Query
	@Query("select u from User u")
	public List<User> getAllUser();
	
	//ParaMeterised Query
	@Query("select u from User u WHERE u.name=:n and  u.city=:c")
    public List<User> getUserByName(@Param("n") String name,@Param("c") String city);
	
   //Native Query
	@Query(value="select * from User", nativeQuery=true)
	public List<User> getUsers();
	
}
