package ar.edu.iua.ingweb3proyecto.business;

import java.util.List;

import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Lista;

public interface IListaBusiness {
	
	//getAll
	public List<Lista> getAll() throws BusinessException;
	//search
	//searchByAlgo
	//getOne
	//add
	Lista addLista(Lista lista) throws BusinessException;
	//update
	//delete

}
