package ar.edu.iua.ingweb3proyecto.web.services;

import ar.edu.iua.ingweb3proyecto.model.exception.AlreadyUsedListNameException;
import ar.edu.iua.ingweb3proyecto.model.exception.InvalidListNameException;
import ar.edu.iua.ingweb3proyecto.model.exception.NullListException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.iua.ingweb3proyecto.business.IListaBusiness;
import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Lista;

import java.util.List;

@RestController
@RequestMapping(Constantes.URL_LISTAS)
public class ListasRESTController {

	@Autowired
    private IListaBusiness listaBusiness;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping(value = { "/", "" })
	public ResponseEntity<List<Lista>> lista() {
		try {
			log.info("Listas obtenidas");
			return new ResponseEntity<List<Lista>>(listaBusiness.getAll(), HttpStatus.OK);
		} catch (BusinessException e) {
			log.error("ListasRESTController.lista() - BUSINESS EXCEPTION - HTTP Status: " + HttpStatus.INTERNAL_SERVER_ERROR + " (INTERNAL SERVER ERROR)");
			return new ResponseEntity<List<Lista>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping(value = {"", "/"})
    public ResponseEntity<Lista> addLista(@RequestBody Lista lista) {
    	try {
			Lista l = listaBusiness.add(lista);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", "/productos/" + lista.getId());
			log.info("Lista ;" + l.getNombre() + "; guardada con ID " + l.getId());
			return new ResponseEntity<Lista>(l, responseHeaders, HttpStatus.CREATED);
		} catch (NullListException e) {
			log.error("ListasRESTController.addLista() - NULL LIST EXCEPTION - HTTP Status: " + HttpStatus.NOT_ACCEPTABLE + " (NOT ACCEPTABLE)");
			return new ResponseEntity<Lista>(HttpStatus.NOT_ACCEPTABLE);
    	} catch (AlreadyUsedListNameException e) {
			log.error("ListasRESTController.addLista() - ALREADY USED LIST NAME EXCEPTION - HTTP Status: " + HttpStatus.NOT_ACCEPTABLE + " (NOT ACCEPTABLE)");
			return new ResponseEntity<Lista>(HttpStatus.NOT_ACCEPTABLE);
		} catch (InvalidListNameException e) {
			log.error("ListasRESTController.addLista() - INVALID LIST NAME EXCEPTION - HTTP Status: " + HttpStatus.NOT_ACCEPTABLE + " (NOT ACCEPTABLE)");
    		return new ResponseEntity<Lista>(HttpStatus.NOT_ACCEPTABLE);
		} catch (BusinessException e) {
			log.error("ListasRESTController.addLista() - BUSINESS EXCEPTION - HTTP Status: " + HttpStatus.INTERNAL_SERVER_ERROR + " (INTERNAL SERVER ERROR)");
			return new ResponseEntity<Lista>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }


}
