package com.example.YourPeopleBE.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReactionTypeDTO {

    private String reaction;

    public ReactionTypeDTO(String reaction) {
        this.reaction = reaction;
    }
}
