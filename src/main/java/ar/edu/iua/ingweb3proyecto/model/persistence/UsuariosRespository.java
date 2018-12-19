package ar.edu.iua.ingweb3proyecto.model.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.edu.iua.ingweb3proyecto.model.User;

@Repository
public interface UsuariosRespository extends JpaRepository<User, Long> {

	public List<User> findByUsernameOrEmail(String username, String email);
	
	@Transactional
	@Modifying
	@Query(value="UPDATE users SET password=? WHERE username=? OR email=?",nativeQuery=true)
	public int setPassword(String password, String username, String email);
}
