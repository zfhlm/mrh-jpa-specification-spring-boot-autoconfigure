package org.lushen.mrh.boot.data.jpa.specification.service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 测试实体类
 * 
 * @author hlm
 */
@Entity(name="t_hello")
public class HelloEntity {

	@Id
	@Column(name="id", length=36, nullable=false)
	private String id;

	@Column(name="username", length=50, nullable=false)
	private String username;

	@Column(name="gender", length=11, nullable=false)
	private Integer gender;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", gender=");
		builder.append(gender);
		builder.append("]");
		return builder.toString();
	}

}
