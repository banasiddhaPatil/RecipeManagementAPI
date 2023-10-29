package com.geekster.RecipeManagementSystemAPI.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comment_table")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;
    private String commentBody;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY) // hide this in json but not in database table column
    private LocalDateTime commentCreationTimeStamp;

    @ManyToOne
    @JoinColumn(name = "fk_user-id")
    User commenter;

    @ManyToOne
    @JoinColumn(name = "fk_recipeId")
    Recipe recipe;
}
