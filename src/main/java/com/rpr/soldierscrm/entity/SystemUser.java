package com.rpr.soldierscrm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private String userName;
    private String fullName;
    @JsonIgnore
    private String password;
    @ManyToMany(fetch = EAGER)
    private Collection<SystemRole> roles = new ArrayList<>();
}
