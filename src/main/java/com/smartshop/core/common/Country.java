package com.smartshop.core.common;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.smartshop.domain.common.BusinessDomain;

/**
 * A Country.
 */
@Entity
@Table(name = "country")
public class Country extends BusinessDomain<Long, Country> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "iso_code")
	private String isoCode;

	@Column(name = "name")
	private String name;

	private String descriptions;

	@OneToMany(mappedBy = "country")
	private Set<Zone> zones = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country() {
	}

	public Country(String isoCode) {
		this.setIsoCode(isoCode);
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public Country isoCode(String isoCode) {
		this.isoCode = isoCode;
		return this;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public Set<Zone> getZones() {
		return zones;
	}

	public Country zones(Set<Zone> zones) {
		this.zones = zones;
		return this;
	}

	public Country addZones(Zone zone) {
		this.zones.add(zone);
		zone.setCountry(this);
		return this;
	}

	public Country removeZones(Zone zone) {
		this.zones.remove(zone);
		zone.setCountry(null);
		return this;
	}

	public void setZones(Set<Zone> zones) {
		this.zones = zones;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

}
