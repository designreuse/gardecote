package com.gardecote.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="qUsern", schema="dbo", catalog="GCM11" )
// Define named queries here

public class User implements Serializable{
    @Id
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;
    private boolean tokenExpired;
    @ManyToMany
    @JoinTable(
            name = "quser_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "email"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "name"))
    private Collection<Role> roles;
}