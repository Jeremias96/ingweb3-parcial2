package ar.edu.iua.ingweb3proyecto.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "listas")
public class Lista {
	
	@Id
	@GeneratedValue
	@Column(name = "id_lista", nullable = false)
	private int id;
	
	@Column(name = "nombre", length = 50)
	private String nombre;
	
	@Column(name = "sprint", length = 50)
	private String sprint;
	
	@OneToMany(mappedBy="lista")
	@JsonIgnore
	@Column(name = "lista_de_tareas")
	private List<Tarea> listaDeTareas;
	
	public Lista() {
		
	}

	public Lista(int id, String nombre, String sprint, List<Tarea> listaDeTareas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.sprint = sprint;
		this.listaDeTareas = listaDeTareas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getSprint() {
		return sprint;
	}

	public void setSprint(String sprint) {
		this.sprint = sprint;
	}

	public List<Tarea> getListaDeTareas() {
		return listaDeTareas;
	}

	public void setListaDeTareas(List<Tarea> listaDeTareas) {
		this.listaDeTareas = listaDeTareas;
	}

}
