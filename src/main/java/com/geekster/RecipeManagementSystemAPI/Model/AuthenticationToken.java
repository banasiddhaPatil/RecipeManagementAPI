package com.geekster.RecipeManagementSystemAPI.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authentication_table")
public class AuthenticationToken {
    //@Id will make the column primary key;
    @Id
    // @GeneratedValue will create unique value for the tokenId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationTime;
    @OneToOne
    @JoinColumn(name = "fk_user_id")
    User user;

    //created a parameterised constructor which will be taking user as an argument;
    public AuthenticationToken(User user){
        this.user = user;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationTime = LocalDateTime.now();
    }
}
