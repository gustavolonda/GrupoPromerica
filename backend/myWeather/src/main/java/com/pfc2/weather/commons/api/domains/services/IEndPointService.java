package com.pfc2.weather.commons.api.domains.services;

import java.util.List;

public interface IEndPointService  < T, ID> {
	
	List<T> saveAll(List<T> entityList);
	
	 T save(T entity);

	 T update(T entity);
	
	 T delete(ID id);
	
	 T getById(ID id);
	
	 List<T> getAll();
	 
	
	 
}