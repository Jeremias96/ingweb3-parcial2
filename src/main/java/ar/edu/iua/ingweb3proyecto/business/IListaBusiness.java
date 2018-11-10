package ar.edu.iua.ingweb3proyecto.business;

import java.util.List;

import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Lista;
import ar.edu.iua.ingweb3proyecto.model.exception.AlreadyUsedListNameException;
import ar.edu.iua.ingweb3proyecto.model.exception.InvalidListNameException;

public interface IListaBusiness {
	
	//getAll
	public List<Lista> getAll() throws BusinessException;

	//add
	Lista add(Lista lista) throws BusinessException, InvalidListNameException, AlreadyUsedListNameException;

}
