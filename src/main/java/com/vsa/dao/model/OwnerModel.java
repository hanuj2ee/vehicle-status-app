package com.vsa.dao.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * Database layer class for Owner
 * 
 * @author Hanumant
 */
@Entity(name = "Owner")
@Table(name = "owner")
@XmlRootElement
@Getter
@Setter
public class OwnerModel implements Serializable {
	/**
	 * Serial version UID
	 */
	private static final long serialVersionUID = 2650189877724991058L;
	@Id
	@Basic(optional = false)
	@Column(name = "id")
	private long id;
	@Column(name = "name")
	private String name;
	@Column(name = "address")
	private String address;
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@XmlTransient
	private List<VehicleModel> vehicles;

}
