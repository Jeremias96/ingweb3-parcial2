package ar.edu.iua.ingweb3proyecto.business.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import ar.edu.iua.ingweb3proyecto.model.Lista;
import ar.edu.iua.ingweb3proyecto.model.exception.InvalidEstimationValueException;
import ar.edu.iua.ingweb3proyecto.model.exception.InvalidListNameException;
import ar.edu.iua.ingweb3proyecto.model.exception.NotFoundException;
import ar.edu.iua.ingweb3proyecto.model.exception.NullListException;
import ar.edu.iua.ingweb3proyecto.model.persistence.ListaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.iua.ingweb3proyecto.business.ITareaBusiness;
import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.business.impl.util.TareaService;
import ar.edu.iua.ingweb3proyecto.model.Tarea;

import javax.persistence.EntityNotFoundException;

@Service
public class TareaBusiness implements ITareaBusiness{

	@Autowired
	private TareaService tareaService;

	@Autowired
	private ListaRepository listaDAO;
	
	@Override
	public List<Tarea> getAll() throws BusinessException {
		try {
			return tareaService.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Tarea findById(Integer id) throws BusinessException {
		try {
			return tareaService.findById(id);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Tarea add(Tarea tarea) throws BusinessException, NullListException, InvalidListNameException {

		Lista lista = null;

		try {
			lista = listaDAO.getOne(tarea.getLista().getId());
		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException();
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
		}catch (NotFoundException e){
			throw new NotFoundException(e);
		}catch (BusinessException e){
			throw new BusinessException(e);
		}
	}

	@Override
	public Tarea update(Tarea tarea) throws BusinessException, NotFoundException , InvalidEstimationValueException{

		String listaDestino = listaDAO.getOne(tarea.getLista().getId()).getNombre();

		Tarea tareaOrigen = findById(tarea.getId());

		if (tareaOrigen.getEstimacion() <= 0){
			throw new InvalidEstimationValueException();
		}
		tareaOrigen.getLista().getNombre();

		try {
			return tareaService.update(tarea);
		}catch (NotFoundException e){
			throw new NotFoundException(e);
		}catch (BusinessException e){
			throw new BusinessException(e);
		}
	}

}
