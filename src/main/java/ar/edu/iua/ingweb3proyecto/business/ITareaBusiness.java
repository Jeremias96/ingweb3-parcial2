package ar.edu.iua.ingweb3proyecto.business;

import java.util.List;

import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Tarea;
import ar.edu.iua.ingweb3proyecto.model.exception.InvalidEstimationValueException;
import ar.edu.iua.ingweb3proyecto.model.exception.InvalidListNameException;
import ar.edu.iua.ingweb3proyecto.model.exception.NotFoundException;
import ar.edu.iua.ingweb3proyecto.model.exception.NullListException;

public interface ITareaBusiness {
	//getAll
	public List<Tarea> getAll() throws BusinessException;

	//findById
	public Tarea findById(Integer id) throws BusinessException;

	//add
	public Tarea add(Tarea tarea) throws BusinessException, NullListException, InvalidListNameException;

	//delete
	public void delete(Tarea tarea) throws BusinessException, NotFoundException;

	//update
	public Tarea update(Tarea tarea) throws BusinessException, NotFoundException, InvalidEstimationValueException;
}
