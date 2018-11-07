package ar.edu.iua.ingweb3proyecto.web.services;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ar.edu.iua.ingweb3proyecto.web.services.Constantes;
import ar.edu.iua.ingweb3proyecto.business.IListaBusiness;
import ar.edu.iua.ingweb3proyecto.business.exception.BusinessException;
import ar.edu.iua.ingweb3proyecto.model.Lista;

@RestController
@RequestMapping(Constantes.URL_LISTAS)
public class ListasRESTController {

	@Autowired
    private IListaBusiness listaBusiness;

    @PostMapping(value = {"", "/"})
    public ResponseEntity<Lista> addLista(@RequestBody Lista Lista, UriComponentsBuilder uriComponentsBuilder, HttpServletRequest request) {
//        try {
            Lista listaCreated = listaBusiness.addLista(Lista);

            URI locationURI = uriComponentsBuilder
                            .path(Constantes.URL_LISTAS + "/{id}")
                            .buildAndExpand(listaCreated.getId())
                            .toUri();

            return ResponseEntity.created(locationURI).body(listaCreated);
//        } catch
            //excepciones

    }
}
