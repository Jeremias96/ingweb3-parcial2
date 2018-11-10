package ar.edu.iua.ingweb3proyecto.business.impl.util;

import java.util.List;

import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.exception.NotFoundException;
import org.springframework.stereotype.Service;

import ar.edu.iua.ingweb3proyecto.business.impl.dao.FactoryDAO;
import ar.edu.iua.ingweb3proyecto.model.Tarea;

@Service
public class TareaService {

	public List<Tarea> findAll() throws BusinessException{
		List<Tarea> tareas = FactoryDAO.getInstance().getTareasDAO().findAll();
		return tareas;
	}

    public Tarea findById(Integer id) throws BusinessException, NotFoundException {
        return (Tarea) FactoryDAO.getInstance().getTareasDAO().findById(id);
    }

	public Tarea save(Tarea tarea) throws BusinessException{
        Tarea t = (Tarea) FactoryDAO.getInstance().getTareasDAO().save(tarea);
        return t;
    }

	public void delete(Integer id) throws BusinessException, NotFoundException {
		FactoryDAO.getInstance().getTareasDAO().delete(id);
	}

    public Tarea update(Tarea tarea) throws BusinessException, NotFoundException {
        return (Tarea) FactoryDAO.getInstance().getTareasDAO().update(tarea);
    }
}
