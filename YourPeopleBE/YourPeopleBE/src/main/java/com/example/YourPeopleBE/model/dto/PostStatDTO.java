package com.example.YourPeopleBE.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostStatDTO {

    Integer reactionsNum;
    Integer commentsNum;

    public PostStatDTO(PostStatDTO postStatDTO) {
        this.reactionsNum = postStatDTO.reactionsNum;
        this.commentsNum = postStatDTO.commentsNum;
    }
}
