/**
 * 
 */
package com.tmm.maker.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.tmm.maker.domain.enums.UnitOfMeasurement;

/**
 * A required ingredient/item to complete a project
 * @author robhinds
 */
@Entity
@Table(name = "INGREDIENT")
public class Ingredient extends PersistableObject {
	
	private static final long serialVersionUID = 1L;

	@Column
	private String description;
	
	@Column
	private String notes;
	
	@Column
	private Integer quantity;
	
	@Enumerated(EnumType.STRING)
	private UnitOfMeasurement unit;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "project")
	private Project project;
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public UnitOfMeasurement getUnit() {
		return unit;
	}

	public void setUnit(UnitOfMeasurement unit) {
		this.unit = unit;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingredient other = (Ingredient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}