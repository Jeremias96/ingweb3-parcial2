package ar.edu.iua.ingweb3proyecto.web.services;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constantes.URL_TEST)
public class TestRestController extends BaseRestController{

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<String> onlyAdmin() {
		return new ResponseEntity<String>("Servicio admin", HttpStatus.OK);
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<String> onlyUser() {
		return new ResponseEntity<String>("Servicio user", HttpStatus.OK);
	}
	
	@GetMapping("/adminoruser")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	public ResponseEntity<String> adminOrUser() {
		return new ResponseEntity<String>("Servicio admin or user", HttpStatus.OK);
	}
	
	@PreAuthorize("#username == authentication.principal.username")
	@GetMapping("/misroles")
	public ResponseEntity<String> getMyRoles(@RequestParam("username")  String username) {
		return new ResponseEntity<String>(getUserPrincipal().getAuthorities().toString(), HttpStatus.OK);
	}

	@GetMapping("/variable")
	public ResponseEntity<String> onlyAdminProgramado(HttpServletRequest request) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			return new ResponseEntity<String>("Servicio dinámico para ADMIN", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Servicio dinámico para USER", HttpStatus.OK);
		}
	}

	@GetMapping("/secured")
	//@Secured no soporta Expression Language (SpEL).
	//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#expressions
	@Secured({ "ROLE_VIEWER", "ROLE_EDITOR" }) //El usuario debe tener al menos un rol
	public ResponseEntity<String> secured() {
		return new ResponseEntity<String>("Servicio con @Secured", HttpStatus.OK);
	}
	
	@GetMapping("/rolesalowed")
	//@RolesAllowed idem @Secured
	//https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#expressions
	@RolesAllowed({ "ROLE_VIEWER", "ROLE_EDITOR" }) //El usuario debe tener al menos un rol
	public ResponseEntity<String> rolesAllowed() {
		return new ResponseEntity<String>("Servicio con @RolesAllowed", HttpStatus.OK);
	}
	
}
