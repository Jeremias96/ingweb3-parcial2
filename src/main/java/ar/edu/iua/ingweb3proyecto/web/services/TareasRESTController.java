package ar.edu.iua.ingweb3proyecto.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.iua.ingweb3proyecto.business.ITareaBusiness;
import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Tarea;

@RestController
@RequestMapping(Constantes.URL_TAREAS)
public class TareasRESTController {

	@Autowired
    private ITareaBusiness tareaBusiness;
	
	@GetMapping(value = { "/", "" })
    public ResponseEntity<List<Tarea>> lista() {
		try {
			return new ResponseEntity<List<Tarea>>(tareaBusiness.getAll(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Tarea>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
