package ar.edu.iua.ingweb3proyecto.model.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.iua.ingweb3proyecto.model.Lista;

@Repository
public interface ListaRepository extends JpaRepository <Lista, Integer>{

}
