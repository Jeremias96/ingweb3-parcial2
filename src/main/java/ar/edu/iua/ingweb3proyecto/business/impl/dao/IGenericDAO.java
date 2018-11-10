package ar.edu.iua.ingweb3proyecto.business.impl.dao;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO <T, ID extends Serializable>{

	public List<T> findAll();
	
	public T save(T object);
	
	public T findById(ID id);

	public void delete(ID id);
	
	//public void update (T object);
	
}
