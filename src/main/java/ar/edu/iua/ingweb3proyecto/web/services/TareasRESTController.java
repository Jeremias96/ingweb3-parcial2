package ar.edu.iua.ingweb3proyecto.web.services;

import java.util.List;

import ar.edu.iua.ingweb3proyecto.model.Lista;
import ar.edu.iua.ingweb3proyecto.model.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.iua.ingweb3proyecto.business.ITareaBusiness;
import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Tarea;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(Constantes.URL_TAREAS)
public class TareasRESTController {

	@Autowired
    private ITareaBusiness tareaBusiness;
	
	@GetMapping(value = { "/", "" })
    public ResponseEntity<List<Tarea>> lista(
            @RequestParam(required = false, value = "q", defaultValue = "*") String q,
            @RequestParam(required = false, value = "sort", defaultValue = "*") String sort) {

		try {
            if (!q.equals("*") && sort.equals("*")) {
                //getByLista
                return new ResponseEntity<List<Tarea>>(tareaBusiness.getByLista(q), HttpStatus.OK);
            } else if (q.equals("*") && !sort.equals("*")) {
                //getAllSorted
                return new ResponseEntity<List<Tarea>>(tareaBusiness.getAllSorted(sort), HttpStatus.OK);
            } else if (!q.equals("*") && !sort.equals("*")) {
                //getByListaSorted
                return new ResponseEntity<List<Tarea>>(tareaBusiness.getByListaSorted(q, sort), HttpStatus.OK);
            } else {
                //getAll
                return new ResponseEntity<List<Tarea>>(tareaBusiness.getAll(), HttpStatus.OK);
            }
		} catch (BusinessException e) {
			return new ResponseEntity<List<Tarea>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping(value = { "/", "" })
    public ResponseEntity<Tarea> add(@RequestBody Tarea tarea ) {
        try {
            Tarea t = tareaBusiness.add(tarea);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("location", "/tareas" + t.getId());
            return new ResponseEntity<Tarea>(t, responseHeaders, HttpStatus.CREATED);
        } catch (InvalidListNameException e) {
            return new ResponseEntity<Tarea>(HttpStatus.NOT_ACCEPTABLE);
        } catch (NullListException e){
            return new ResponseEntity<Tarea>(HttpStatus.NOT_FOUND);
        } catch (NotFoundException e){
            return new ResponseEntity<Tarea>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            return new ResponseEntity<Tarea>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Tarea> delete(@PathVariable("id") int id) {
	    try {
            Tarea t = new Tarea();
            t.setId(id);
            tareaBusiness.delete(t);
            return new ResponseEntity<Tarea>(HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<Tarea>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            return new ResponseEntity<Tarea>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = {"/{id}"})
    public ResponseEntity<Tarea> update(@PathVariable("id") int id, @RequestBody Tarea tarea ) {
        try {
            tarea.setId(id);
            Tarea t = tareaBusiness.update(tarea);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("location", "/tareas" + t.getId());
            return new ResponseEntity<Tarea>(t, responseHeaders, HttpStatus.OK);
        } catch (NullListException e) {
            return new ResponseEntity<Tarea>(HttpStatus.NOT_FOUND);
        } catch (NotFoundException e) {
            return new ResponseEntity<Tarea>(HttpStatus.NOT_FOUND);
        } catch (InvalidEstimationValueException e) {
            return new ResponseEntity<Tarea>(HttpStatus.NOT_ACCEPTABLE);
        } catch (InvalidDestinationListException e) {
            return new ResponseEntity<Tarea>(HttpStatus.NOT_ACCEPTABLE);
        } catch (BusinessException e) {
            return new ResponseEntity<Tarea>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
}
