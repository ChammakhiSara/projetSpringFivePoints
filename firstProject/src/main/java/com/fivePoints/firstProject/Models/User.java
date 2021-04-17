package com.fivePoints.firstProject.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
     private int id;

    @Column(name = "firstName")
     private String firstName;

    @Column(name = "lastName")
     private String lastName;

    @Column(name = "email")
     private String email;

    @Column(name = "password")
     private String password;

    @Column(name = "photo")
     private String photo;

    @JsonIgnore
    @OneToMany(mappedBy="user")
    private List<Post> post;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userDetail_id")
    private UserDetail userDetail;
}
