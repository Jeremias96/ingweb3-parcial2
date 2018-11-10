package ar.edu.iua.ingweb3proyecto.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.iua.ingweb3proyecto.business.IListaBusiness;
import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.business.impl.util.ListaService;
import ar.edu.iua.ingweb3proyecto.model.Lista;
import ar.edu.iua.ingweb3proyecto.model.persistence.ListaRepository;

@Service
public class ListaBusiness implements IListaBusiness{

	@Autowired
	private ListaRepository listaDAO;
	//private ListaService listaService;

	@Override
	public Lista addLista(Lista lista) throws BusinessException {
		try {
			return listaDAO.save(lista);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Lista> getAll() throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
}
