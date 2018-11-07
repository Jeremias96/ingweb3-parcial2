package ar.edu.iua.ingweb3proyecto.business;

import java.util.List;

import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Tarea;

public interface ITareaBusiness {
	//getAll
	public List<Tarea> getAll() throws BusinessException;
	//search
	//searchByAlgo
	//getOne
	//add
	Tarea addTarea(Tarea tarea);
	//update
	//delete
}
