package ar.edu.iua.ingweb3proyecto.business;

import java.util.List;

import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Tarea;
import ar.edu.iua.ingweb3proyecto.model.exception.NotFoundException;

public interface ITareaBusiness {
	//getAll
	public List<Tarea> getAll() throws BusinessException;
	//search
	//searchByAlgo
	//getOne
	//add
	public Tarea add(Tarea tarea) throws BusinessException;
	//update
	//delete
	public void delete(Tarea tarea) throws BusinessException, NotFoundException;
}
