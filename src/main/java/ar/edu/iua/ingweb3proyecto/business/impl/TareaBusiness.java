package ar.edu.iua.ingweb3proyecto.business.impl;

import java.util.List;

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
	public Tarea addTarea(Tarea tarea) {
		// TODO Auto-generated method stub
		return null;
	}

}
