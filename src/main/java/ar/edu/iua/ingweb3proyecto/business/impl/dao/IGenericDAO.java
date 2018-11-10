package ar.edu.iua.ingweb3proyecto.business.impl.dao;

import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;

import java.io.Serializable;
import java.util.List;

public interface IGenericDAO <T, ID extends Serializable>{

	public List<T> findAll() throws BusinessException;
	
	public T save(T object) throws BusinessException;
	
	public T findById(ID id) throws BusinessException;

	public void delete(ID id) throws BusinessException;

	public T update (T object) throws BusinessException;
}
