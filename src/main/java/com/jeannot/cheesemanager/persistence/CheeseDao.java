package com.jeannot.cheesemanager.persistence;

import java.util.List;

import com.jeannot.cheesemanager.openapi.model.Cheese;

/**
 * Cheese DAO
 *
 */
public interface CheeseDao {
	
	Cheese create(Cheese cheese);
	Cheese get(String id);
	List<Cheese> getAll();
	Cheese update(String id, Cheese cheese);
	void delete(String id);

}
