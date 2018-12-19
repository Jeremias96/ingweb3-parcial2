package ar.edu.iua.ingweb3proyecto.web.services;

import java.util.List;

//import ar.edu.iua.ingweb3proyecto.model.Lista;
import ar.edu.iua.ingweb3proyecto.business.exception.InvalidSortException;
import ar.edu.iua.ingweb3proyecto.model.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.iua.ingweb3proyecto.business.ITareaBusiness;
import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Tarea;

import javax.servlet.http.HttpServletRequest;

//import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping(Constantes.URL_TAREAS)
public class TareasRESTController {

	@Autowired
    private ITareaBusiness tareaBusiness;

    private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value = { "/", "" })
    public ResponseEntity<List<Tarea>> lista(
            @RequestParam(required = false, value = "q", defaultValue = "*") String q,
            @RequestParam(required = false, value = "sort", defaultValue = "*") String sort) {

		try {
            if (!q.equals("*") && sort.equals("*")) {
                //getByLista
                log.info("Tareas de la lista '" + q + "' obtenidas");
                return new ResponseEntity<List<Tarea>>(tareaBusiness.getByLista(q), HttpStatus.OK);
            } else if (q.equals("*") && !sort.equals("*")) {
                //getAllSorted
                log.info("Todas las tareas obtenidas, ordenadas por '" + sort + "'");
                return new ResponseEntity<List<Tarea>>(tareaBusiness.getAllSorted(sort), HttpStatus.OK);
            } else if (!q.equals("*") && !sort.equals("*")) {
                //getByListaSorted
                log.info("Tareas de la lista '" + q + "' obtenidas, ordenadas por '" + sort + "'");
                return new ResponseEntity<List<Tarea>>(tareaBusiness.getByListaSorted(q, sort), HttpStatus.OK);
            } else {
                //getAll
                log.info("Todas las tareas obtenidas");
                return new ResponseEntity<List<Tarea>>(tareaBusiness.getAll(), HttpStatus.OK);
            }
		} catch (InvalidSortException e){
            log.error("TareasRESTController.lista() - INVALID SORT EXCEPTION - HTTP Status: " + HttpStatus.NOT_ACCEPTABLE + " (NOT ACCEPTABLE)");
            return new ResponseEntity<List<Tarea>>(HttpStatus.NOT_ACCEPTABLE);
		} catch (BusinessException e) {
            log.error("TareasRESTController.lista() - BUSINESS EXCEPTION - HTTP Status: " + HttpStatus.INTERNAL_SERVER_ERROR + " (INTERNAL SERVER ERROR)");
			return new ResponseEntity<List<Tarea>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @PostMapping(value = { "/", "" })
    public ResponseEntity<Tarea> add(@RequestBody Tarea tarea ) {
        try {
            Tarea t = tareaBusiness.add(tarea);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("location", "/tareas" + t.getId());
            log.info("Tarea " + t.getNombre() + " guardada con ID " + t.getId() + " en la lista " + t.getLista().getNombre());
            return new ResponseEntity<Tarea>(t, responseHeaders, HttpStatus.CREATED);
        } catch (InvalidListNameException e) {
            log.error("TareasRESTController.add() - INVALID LIST NAME EXCEPTION - HTTP Status: " + HttpStatus.NOT_ACCEPTABLE + " (NOT ACCEPTABLE)");
            return new ResponseEntity<Tarea>(HttpStatus.NOT_ACCEPTABLE);
        } catch (NullListException e){
            log.error("TareasRESTController.add() - NULL LIST EXCEPTION - HTTP Status: " + HttpStatus.NOT_FOUND + " (NOT FOUND)");
            return new ResponseEntity<Tarea>(HttpStatus.NOT_FOUND);
        } catch (NotFoundException e){
            log.error("TareasRESTController.add() - NOT FOUND EXCEPTION - HTTP Status: " + HttpStatus.NOT_FOUND + " (NOT FOUND)");
            return new ResponseEntity<Tarea>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            log.error("TareasRESTController.add() - BUSINESS EXCEPTION - HTTP Status: " + HttpStatus.INTERNAL_SERVER_ERROR + " (INTERNAL SERVER ERROR)");
            return new ResponseEntity<Tarea>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Tarea> delete(@PathVariable("id") int id) {
	    try {
            Tarea t = new Tarea();
            t.setId(id);
            tareaBusiness.delete(t);
            log.info("Tarea con ID " + t.getId() + " eliminada");
            return new ResponseEntity<Tarea>(HttpStatus.OK);
        } catch (NotFoundException e) {
            log.error("TareasRESTController.delete() - NOT FOUND EXCEPTION - HTTP Status: " + HttpStatus.NOT_FOUND + " (NOT FOUND)");
            return new ResponseEntity<Tarea>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            log.error("TareasRESTController.delete() - BUSINESS EXCEPTION - HTTP Status: " + HttpStatus.INTERNAL_SERVER_ERROR + " (INTERNAL SERVER ERROR)");
            return new ResponseEntity<Tarea>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = {"/{id}"})
    public ResponseEntity<Tarea> update(@PathVariable("id") int id, @RequestBody Tarea tarea, HttpServletRequest request) {
        try {
            tarea.setId(id);
            Tarea t = new Tarea();
            if (request.isUserInRole("ROLE_ADMIN")) {
                t = tareaBusiness.updateAdmin(tarea);
            } else if (request.isUserInRole("ROLE_USER")) {
                t = tareaBusiness.updateUser(tarea);
            }
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("location", "/tareas" + t.getId());
            log.info("Tarea '" + t.getNombre() + "' con ID "  + t.getId() + " movida a la lista '" + t.getLista().getNombre() + "'");
            return new ResponseEntity<Tarea>(t, responseHeaders, HttpStatus.OK);
        } catch (NullListException e) {
            log.error("TareasRESTController.update() - NULL LIST EXCEPTION - HTTP Status: " + HttpStatus.NOT_FOUND + " (NOT FOUND)");
            return new ResponseEntity<Tarea>(HttpStatus.NOT_FOUND);
        } catch (NotFoundException e) {
            log.error("TareasRESTController.update() - NOT FOUND EXCEPTION - HTTP Status: " + HttpStatus.NOT_FOUND + " (NOT FOUND)");
            return new ResponseEntity<Tarea>(HttpStatus.NOT_FOUND);
        } catch (InvalidEstimationValueException e) {
            log.error("TareasRESTController.update() - INVALID ESTIMATION VALUE EXCEPTION - HTTP Status: " + HttpStatus.NOT_ACCEPTABLE + " (NOT ACCEPTABLE)");
            return new ResponseEntity<Tarea>(HttpStatus.NOT_ACCEPTABLE);
        } catch (InvalidDestinationListException e) {
            log.error("TareasRESTController.update() - INVALID DESTINATION LIST EXCEPTION - HTTP Status: " + HttpStatus.NOT_ACCEPTABLE + " (NOT ACCEPTABLE)");
            return new ResponseEntity<Tarea>(HttpStatus.NOT_ACCEPTABLE);
        } catch (BusinessException e) {
            log.error("TareasRESTController.update() - BUSINESS EXCEPTION - HTTP Status: " + HttpStatus.INTERNAL_SERVER_ERROR + " (INTERNAL SERVER ERROR)");
            return new ResponseEntity<Tarea>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
}
