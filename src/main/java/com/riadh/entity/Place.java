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
    @Column(name = "PLACE_ID")
    @GeneratedValue
    private Integer id;

    @Column(name = "PLACE_NAME")
    @Size(min = 1, max = 30)
    @NotNull
    private String name;

    @Column(name = "PLACE_DESCRIPTION")
    @Size(min = 1, max = 200)
    @NotNull
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Place [id=" + id + ", name=" + name + ", description=" + description + "]";
    }
}
