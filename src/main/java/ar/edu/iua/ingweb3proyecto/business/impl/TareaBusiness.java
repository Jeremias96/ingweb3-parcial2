package ar.edu.iua.ingweb3proyecto.business.impl;

import java.sql.Date;
import java.util.*;

import ar.edu.iua.ingweb3proyecto.business.exception.InvalidSortException;
import ar.edu.iua.ingweb3proyecto.model.Lista;
import ar.edu.iua.ingweb3proyecto.model.exception.*;
import ar.edu.iua.ingweb3proyecto.model.persistence.ListaRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.iua.ingweb3proyecto.business.ITareaBusiness;
import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.business.impl.util.TareaService;
import ar.edu.iua.ingweb3proyecto.model.Tarea;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

@Service
public class TareaBusiness implements ITareaBusiness{

	@Autowired
	private TareaService tareaService;

	@Autowired
	private ListaRepository listaDAO;
	
	@Override
	public List<Tarea> getAll() throws BusinessException {
		try {
            HashMap<String, String> parameters = new HashMap<String, String>();
			return tareaService.findAll(parameters);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

    @Override
    public List<Tarea> getByLista(String q) throws BusinessException {
        try {
            HashMap<String, String> parameters = new HashMap<String, String>();
            parameters.put("q", q);
            return tareaService.findAll(parameters);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public List<Tarea> getAllSorted(String sort) throws BusinessException, InvalidSortException {
        try {
            HashMap<String, String> parameters = new HashMap<String, String>();
            parameters.put("sort", sort);
            return tareaService.findAll(parameters);
		} catch (InvalidSortException e) {
			throw new InvalidSortException(e);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

    @Override
    public List<Tarea> getByListaSorted(String q, String sort) throws BusinessException, InvalidSortException {
        try {
            HashMap<String, String> parameters = new HashMap<String, String>();
            parameters.put("q", q);
            parameters.put("sort", sort);
            return tareaService.findAll(parameters);
		} catch (InvalidSortException e) {
			throw new InvalidSortException(e);
        } catch (Exception e) {
            throw new BusinessException(e);
        }
    }

	@Override
	public Tarea findById(Integer id) throws BusinessException, NotFoundException {
		try {
			return tareaService.findById(id);
		} catch (NotFoundException e) {
            throw new NotFoundException(e);
        } catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Tarea add(Tarea tarea) throws BusinessException, NullListException, InvalidListNameException, NotFoundException {

		Lista lista = null;

		try {
			lista = listaDAO.getOne(tarea.getLista().getId());
		} catch (EntityNotFoundException e) {
			throw new NotFoundException();
		} catch (NullPointerException e){
			throw new NullListException();
		}

		if (!lista.getNombre().equalsIgnoreCase("backlog")) {
			throw new InvalidListNameException();
		}

		try {
			return tareaService.save(tarea);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public void delete(Tarea tarea) throws BusinessException, NotFoundException {
		try {
			tareaService.delete(tarea.getId());
		} catch (NotFoundException e) {
            throw new NotFoundException(e);
		} catch (BusinessException e){
			throw new BusinessException(e);
		}
	}

	@Override
	public Tarea update(Tarea tarea) throws BusinessException, NotFoundException, InvalidEstimationValueException, NullListException, InvalidDestinationListException {

        Tarea tareaOrigen = null;
        String listaDestino = null;

        try {
            listaDestino = listaDAO.getOne(tarea.getLista().getId()).getNombre().toLowerCase();
        } catch (EntityNotFoundException e) {
            throw new NotFoundException();
        } catch (NullPointerException e){
            throw new NullListException();
        }

        try {
            tareaOrigen = findById(tarea.getId());
        } catch (NotFoundException e) {
            throw new NotFoundException(e);
        } catch (BusinessException e) {
            throw new BusinessException(e);
        }


		if (tareaOrigen.getEstimacion() <= 0){
			throw new InvalidEstimationValueException();
		}

		String listaOrigen = tareaOrigen.getLista().getNombre().toLowerCase();

        HashMap<String, String[]> origenDestino = new HashMap<String, String[]>();
        origenDestino.put("backlog", new String[]{"todo"});
        origenDestino.put("todo", new String[]{"in progress", "waiting", "done"});
        origenDestino.put("in progress", new String[]{"waiting", "todo", "done"});
        origenDestino.put("waiting", new String[]{"in progress", "todo", "done"});
        origenDestino.put("done", new String[]{});

        if (!Arrays.asList(origenDestino.get(listaOrigen)).contains(listaDestino)){
            System.out.println("Agarrate este try catch");
            throw new InvalidDestinationListException();
        }

		try {
			return tareaService.update(tarea);
		}catch (NotFoundException e){
			throw new NotFoundException(e);
		}catch (BusinessException e){
			throw new BusinessException(e);
		}
	}
}
