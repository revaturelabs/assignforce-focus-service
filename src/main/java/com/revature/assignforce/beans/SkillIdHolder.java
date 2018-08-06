package com.revature.assignforce.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "SKILLIDHOLDER")
public class SkillIdHolder {

	@Id
	@Column(name = "SKILL_ID")
	@NotNull
	private Integer skillID;

	public SkillIdHolder() {
		super();
	}

	public SkillIdHolder(int skillID) {
		super();
		this.skillID = skillID;
	}

	public int getSkillId() {
		return skillID;
	}

	public void setSkillId(int skillID) {
		this.skillID = skillID;
	}

}