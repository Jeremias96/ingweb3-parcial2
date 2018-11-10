package ar.edu.iua.ingweb3proyecto.web.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.iua.ingweb3proyecto.business.IListaBusiness;
import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Lista;

@RestController
@RequestMapping(Constantes.URL_LISTAS)
public class ListasRESTController {

	@Autowired
    private IListaBusiness listaBusiness;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Lista> addLista(@RequestBody Lista lista) {
    	try {
			Lista l = listaBusiness.addLista(lista);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.set("location", "/productos/" + lista.getId());
			return new ResponseEntity<Lista>(l,responseHeaders, HttpStatus.CREATED);
		} catch (BusinessException e) {
			return new ResponseEntity<Lista>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
}
