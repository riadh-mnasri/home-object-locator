package com.riadh.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "T_PLACE")
public class Place implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "OBJECT_ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "OBJECT_NAME")
    @Size(min = 1, max = 30)
    @NotNull
    private String objectName;
    
    @Column(name = "PLACE_NAME")
    @Size(min = 1, max = 30)
    @NotNull
    private String placeName;

    @Column(name = "PLACE_DESCRIPTION")
    @Size(min = 1, max = 200)
    @NotNull
    private String placeDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceDescription() {
		return placeDescription;
	}

	public void setPlaceDescription(String placeDescription) {
		this.placeDescription = placeDescription;
	}

	@Override
    public String toString() {
        return "Place [object_id=" + id + ", objet_name=" + objectName + ", place_name=" + placeName + ", place_description=" + placeDescription + "]";
    }
}
