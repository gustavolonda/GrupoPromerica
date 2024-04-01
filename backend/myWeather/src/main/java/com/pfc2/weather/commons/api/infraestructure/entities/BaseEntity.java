package com.pfc2.weather.commons.api.infraestructure.entities;
import java.util.Date;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.pfc2.weather.commons.api.applications.model.StatusDomain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {
	@CreationTimestamp
	@Column(name = "create_date" , columnDefinition = "TIMESTAMP")
	private Date createDate;

	@UpdateTimestamp
	@Column(name = "modify_date" , columnDefinition = "TIMESTAMP")
	private Date modifyDate;
	@Column(columnDefinition = "varchar(2)")
	private String status = StatusDomain.ACTIVE.getValue();

}
