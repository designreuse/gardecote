package com.gardecote.entities;

import netscape.security.Privilege;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="qRolen", schema="dbo", catalog="GCM11" )
public class Role implements Serializable {

    @Id
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

}