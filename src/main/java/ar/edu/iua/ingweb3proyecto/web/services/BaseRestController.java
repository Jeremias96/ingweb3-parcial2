package ar.edu.iua.ingweb3proyecto.web.services;

import org.jooq.tools.json.JSONArray;
import org.jooq.tools.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import ar.edu.iua.ingweb3proyecto.model.User;

public class BaseRestController {

	protected User getUserPrincipal() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		return user;
	}

	@SuppressWarnings("unchecked")
	protected JSONObject userToJson(User u) {
		JSONObject o = new JSONObject();
		o.put("username", u.getUsername());		
		o.put("name", u.getName());
		o.put("code", 0);
		JSONArray r = new JSONArray();
		for (GrantedAuthority g : u.getAuthorities()) {
			r.add(g.getAuthority());
		}
		o.put("roles", r);
		return o;
	}

}
