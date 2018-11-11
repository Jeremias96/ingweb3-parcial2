package ar.edu.iua.ingweb3proyecto.business.impl.dao;

import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Tarea;
import ar.edu.iua.ingweb3proyecto.model.exception.NotFoundException;

import java.io.Serializable;
import java.util.Dictionary;
import java.util.List;

public interface IGenericDAO <T, ID, D extends Serializable>{

	public List<T> findAll(D dic) throws BusinessException;
	
	public T save(T object) throws BusinessException;
	
	public T findById(ID id) throws BusinessException, NotFoundException;

	public void delete(ID id) throws BusinessException, NotFoundException;

	public T update (T object) throws BusinessException;
}
