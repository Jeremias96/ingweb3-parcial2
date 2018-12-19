package ar.edu.iua.ingweb3proyecto.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class Role implements Serializable,Comparable<Role> {

	private static final long serialVersionUID = -7502573796951736094L;

	@Column(nullable = false, length = 100)
	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(unique = true, nullable = false)
	private String name;

	@Override
	public boolean equals(Object obj) {
		return ((Role) obj).getId() == getId() || ((Role) obj).getName().equals(getName());
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return getId();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Role: [%d] %s", getId(), getName());
	}

	@Override
	public int compareTo(Role o) {
		return getName().compareTo(o.getName());
	}
}
