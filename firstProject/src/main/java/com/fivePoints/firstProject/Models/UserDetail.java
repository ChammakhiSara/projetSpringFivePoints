package com.fivePoints.firstProject.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userDetail")
public class UserDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "age")
    private String age;

    @Column(name = "dateNaissance")
    private String dateNaissance;

    @Column(name = "githubLink")
    private String githubLink;

    @Column(name = "linkedinLink")
    private String linkedinLink;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
