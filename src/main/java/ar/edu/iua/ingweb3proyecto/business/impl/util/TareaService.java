package ar.edu.iua.ingweb3proyecto.business.impl.util;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.iua.ingweb3proyecto.business.impl.dao.FactoryDAO;
import ar.edu.iua.ingweb3proyecto.model.Tarea;

@Service
public class TareaService {

	public List<Tarea> findAll() {
		List<Tarea> tareas = FactoryDAO.getInstance().getTareasDAO().findAll();
		return tareas;
	}

	public Tarea save(Tarea tarea){
		Tarea t = (Tarea) FactoryDAO.getInstance().getTareasDAO().save(tarea);
		return t;
	}

	public Tarea findById(Integer id){
		Tarea t = (Tarea) FactoryDAO.getInstance().getTareasDAO().findById(id);
		return t;
	}

	public void delete(Integer id){
		FactoryDAO.getInstance().getTareasDAO().delete(id);
	}

}
