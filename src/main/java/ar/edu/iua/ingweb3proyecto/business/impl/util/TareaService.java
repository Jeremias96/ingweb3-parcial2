package ar.edu.iua.ingweb3proyecto.business.impl.util;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.iua.ingweb3proyecto.business.impl.dao.FactoryDAO;
import ar.edu.iua.ingweb3proyecto.model.Tarea;

@Service
public class TareaService {

	public List<Tarea> findAll() {
		List<Tarea> tareas = FactoryDAO.getInstance().getBillingsDAO().findAll();		
		return tareas;
	}

}
