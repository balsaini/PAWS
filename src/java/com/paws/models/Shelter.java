package com.paws.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "shelters")
@Access(AccessType.PROPERTY)
public class Shelter {

    private int id;
    private String name;
    private Date opened;

    public Shelter(String name) {
        this.name = name;
        this.opened = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}

    @Basic
    @Column(name = "name", nullable = false, length = 45)
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Basic
    @Column(name = "opened", nullable = false, length = 45)
    public Date getOpened() {return opened;}
    public void setOpened(Date opened) {this.opened = opened;}

}
