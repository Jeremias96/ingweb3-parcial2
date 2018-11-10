package ar.edu.iua.ingweb3proyecto.business.impl;

import java.util.List;

import ar.edu.iua.ingweb3proyecto.model.exception.AlreadyUsedListNameException;
import ar.edu.iua.ingweb3proyecto.model.exception.InvalidListNameException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.iua.ingweb3proyecto.business.IListaBusiness;
import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Lista;
import ar.edu.iua.ingweb3proyecto.model.persistence.ListaRepository;

@Service
public class ListaBusiness implements IListaBusiness{

	@Autowired
	private ListaRepository listaDAO;

	@Override
	public Lista add(Lista lista) throws BusinessException, InvalidListNameException, AlreadyUsedListNameException {
        String nombre = lista.getNombre();
        if(!(nombre.equalsIgnoreCase("backlog") ||
                nombre.equalsIgnoreCase("todo") ||
                nombre.equalsIgnoreCase("in progress") ||
                nombre.equalsIgnoreCase("waiting") ||
                nombre.equalsIgnoreCase("done"))) {
            throw new InvalidListNameException();
        }

        List<Lista> listaActual = getAll();

        for( Lista l : listaActual){
            if(l.getNombre().equalsIgnoreCase(nombre)){
                throw new AlreadyUsedListNameException();
            }
        }

        try {
			return listaDAO.save(lista);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public List<Lista> getAll() throws BusinessException {
        try {
            return listaDAO.findAll();
        } catch (Exception e) {
            throw new BusinessException(e);
        }
	}
}
