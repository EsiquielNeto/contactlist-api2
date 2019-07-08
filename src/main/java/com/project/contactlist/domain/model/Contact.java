package com.project.contactlist.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "contact")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @Email
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min = 11, max = 11)
    @Column(name = "telephone")
    private String telephone;

    @Column(name = "twitter")
    private String twitter;

    @Column(name = "skype")
    private String skype;

    @Column(name = "photo")
    private String photo;
}
