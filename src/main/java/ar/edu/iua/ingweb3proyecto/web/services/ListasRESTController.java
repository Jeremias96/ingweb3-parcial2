package ar.edu.iua.ingweb3proyecto.web.services;

import ar.edu.iua.ingweb3proyecto.model.exception.AlreadyUsedListNameException;
import ar.edu.iua.ingweb3proyecto.model.exception.InvalidListNameException;
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

	@GetMapping(value = { "/", "" })
	public ResponseEntity<List<Lista>> lista() {
		try {
			return new ResponseEntity<List<Lista>>(listaBusiness.getAll(), HttpStatus.OK);
		} catch (BusinessException e) {
			return new ResponseEntity<List<Lista>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@PostMapping(value = {"", "/"})
    public ResponseEntity<Lista> addLista(@RequestBody Lista lista) {
    	try {
			Lista l = listaBusiness.add(lista);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", "/productos/" + lista.getId());
			return new ResponseEntity<Lista>(l, responseHeaders, HttpStatus.CREATED);
		} catch (AlreadyUsedListNameException e) {
			return new ResponseEntity<Lista>(HttpStatus.NOT_ACCEPTABLE);
		} catch (InvalidListNameException e) {
    		return new ResponseEntity<Lista>(HttpStatus.NOT_ACCEPTABLE);
		} catch (BusinessException e) {
			return new ResponseEntity<Lista>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }


}
