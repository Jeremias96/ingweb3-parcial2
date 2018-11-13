package ar.edu.iua.ingweb3proyecto.business.impl.util;

import java.util.HashMap;
import java.util.List;

import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.business.exception.InvalidSortException;
import ar.edu.iua.ingweb3proyecto.model.exception.NotFoundException;
import org.springframework.stereotype.Service;

import ar.edu.iua.ingweb3proyecto.business.impl.dao.FactoryDAO;
import ar.edu.iua.ingweb3proyecto.model.Tarea;

@Service
public class TareaService {

	public List<Tarea> findAll(HashMap map) throws BusinessException, InvalidSortException {
        return FactoryDAO.getInstance().getTareasDAO().findAll(map);
	}

    public Tarea findById(Integer id) throws BusinessException, NotFoundException {
        return (Tarea) FactoryDAO.getInstance().getTareasDAO().findById(id);
    }

	public Tarea save(Tarea tarea) throws BusinessException{
        return (Tarea) FactoryDAO.getInstance().getTareasDAO().save(tarea);
    }

	public void delete(Integer id) throws BusinessException, NotFoundException {
		FactoryDAO.getInstance().getTareasDAO().delete(id);
	}

    public Tarea update(Tarea tarea) throws BusinessException, NotFoundException {
        return (Tarea) FactoryDAO.getInstance().getTareasDAO().update(tarea);
    }
}
