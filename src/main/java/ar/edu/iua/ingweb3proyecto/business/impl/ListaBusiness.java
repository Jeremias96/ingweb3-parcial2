package ar.edu.iua.ingweb3proyecto.business.impl;

import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Lista;

public interface ListaBusiness {
	Lista addLista(Lista lista) throws BusinessException;

}
