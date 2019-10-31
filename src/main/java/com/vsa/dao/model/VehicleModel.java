package com.vsa.dao.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;

/**
 * Database layer class for vehicle
 * 
 * @author Hanumant
 */
@Entity(name = "Vehicle")
@Table(name = "vehicle")
@XmlRootElement
@Getter
@Setter
public class VehicleModel implements Serializable {

	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 4293561998362301906L;
	@Id
	@Basic(optional = false)
	@Column(name = "vehicleId")
	private String vehicleId;
	@Column(name = "regNumber")
	private String regNumber;
	@JoinColumn(name = "owner", referencedColumnName = "id")
	@ManyToOne(fetch = FetchType.EAGER)
	private OwnerModel owner;
	@Column(name = "status")
	private String status;
	@Column(name = "imagePath")
	private String imagePath;

}
