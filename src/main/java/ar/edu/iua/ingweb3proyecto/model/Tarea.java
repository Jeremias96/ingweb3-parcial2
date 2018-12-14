package ar.edu.iua.ingweb3proyecto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tareas")
public class Tarea {
	
	@Id
	@GeneratedValue
	@Column(name = "id_tarea", nullable = false)
	private int id;
	
	@Column(name = "nombre", length = 50)
	private String nombre;
	
	@Column(name = "fecha_creacion")
	private Date fechacreacion;
	
	@Column(name = "fecha_modificacion")
	private Date fechamodificacion;

    @Column(name = "prioridad", length = 10)
	private String prioridad;
	
	@ManyToOne
    @JoinColumn(name="id_lista", nullable=true)
	//@Column(name = "lista")
	private Lista lista;
	
	@Column(name = "estimacion")
	private int estimacion;

	public Tarea() {
		
	}

	public Tarea(int id, String nombre, Date fechacreacion, Date fechamodificacion, String prioridad, Lista lista, int estimacion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fechacreacion = fechacreacion;
		this.fechamodificacion = fechamodificacion;
		this.prioridad = prioridad;
		this.lista = lista;
		this.estimacion = estimacion;
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

	public long getFechacreacion() {
		if (fechacreacion != null)
			return fechacreacion.getTime();
		else
			return 0;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public long getFechamodificacion() {
		if (fechamodificacion != null)
			return fechamodificacion.getTime();
		else
			return 0;
	}

	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

	public Lista getLista() {
		return lista;
	}

	public void setLista(Lista lista) {
		this.lista = lista;
	}

	public int getEstimacion() {
		return estimacion;
	}

	public void setEstimacion(int estimacion) {
		this.estimacion = estimacion;
	}


	public String toString(){
        return "Tarea: " + this.id + "|" + this.nombre + "|" + this.fechacreacion + "|" + this.fechamodificacion + "|" + this.prioridad + "|" + this.lista + "|" + this.estimacion;
    }
}
