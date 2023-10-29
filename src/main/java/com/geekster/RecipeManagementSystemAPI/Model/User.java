package com.geekster.RecipeManagementSystemAPI.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_table")
public class User {
    //@Id will make the column primary key;
    @Id
    // @GeneratedValue will create unique value for the tokenId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String userName;

    @Pattern(regexp = "^.+@(?![Ii][Nn][Ss][Tt][Aa][Aa][Dd][Mm][Ii][Nn]\\.[Cc][Oo][Mm]$).+$")
    @Column(unique = true)  // every value should be unique
    private String userEmail;
    @NotBlank  // this field can not be blank
    private String userPassword;
}
