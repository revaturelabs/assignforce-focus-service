package com.revature.assignforce.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Focus")
public class Focus {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FOCUS_ID")
	@SequenceGenerator(name = "FOCUS_ID", sequenceName = "FOCUS_ID_SEQ", allocationSize = 1)
	@Column(name = "Focus_ID")
	private int id;

	@Column(name = "Focus_Name")
	@NotNull
	@NotBlank
	@Size(min = 1, max = 128)
	private String name;

	@Column(name = "isActive")
	@NotNull
	private Boolean isActive;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Focus_SKILLS")
	@NotNull
	@NotEmpty
	@Valid
	private Set<SkillIdHolder> skills;

	public Focus() {
		super();
	}

	public Focus(int id, String name, Boolean isActive, Set<SkillIdHolder> skills) {
		super();
		this.id = id;
		this.name = name;
		this.isActive = isActive;
		this.skills = skills;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Set<SkillIdHolder> getSkills() {
		return skills;
	}

	public void setSkills(Set<SkillIdHolder> skills) {
		this.skills = skills;
	}

}
