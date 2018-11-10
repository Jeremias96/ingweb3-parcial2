package ar.edu.iua.ingweb3proyecto.business.impl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import ar.edu.iua.ingweb3proyecto.model.Lista;
import ar.edu.iua.ingweb3proyecto.model.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.iua.ingweb3proyecto.business.ITareaBusiness;
import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.business.impl.util.TareaService;
import ar.edu.iua.ingweb3proyecto.model.Tarea;

@Service
public class TareaBusiness implements ITareaBusiness{

	@Autowired
	private TareaService tareaService;
	
	@Override
	public List<Tarea> getAll() throws BusinessException {
		try {
			return tareaService.findAll();
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public Tarea add(Tarea tarea) throws BusinessException {
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
		} catch (Exception e){
			throw new BusinessException(e);
		}
	}

}
