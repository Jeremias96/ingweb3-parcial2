package ar.edu.iua.ingweb3proyecto.business;

import java.util.List;

import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Tarea;
import ar.edu.iua.ingweb3proyecto.model.exception.*;

public interface ITareaBusiness {
	//getAll
	public List<Tarea> getAll() throws BusinessException;

	//getByLista
	public List<Tarea> getByLista(String q) throws BusinessException;

	//getAllSorted
	public List<Tarea> getAllSorted(String sort) throws BusinessException;

	//getByListaSorted
	public List<Tarea> getByListaSorted(String q, String sort) throws BusinessException;

	//findById
	public Tarea findById(Integer id) throws BusinessException, NotFoundException;

	//add
	public Tarea add(Tarea tarea) throws BusinessException, NullListException, InvalidListNameException, NotFoundException;

	//delete
	public void delete(Tarea tarea) throws BusinessException, NotFoundException;

	//update
	public Tarea update(Tarea tarea) throws BusinessException, NotFoundException, InvalidEstimationValueException, NullListException, InvalidDestinationListException;
}
