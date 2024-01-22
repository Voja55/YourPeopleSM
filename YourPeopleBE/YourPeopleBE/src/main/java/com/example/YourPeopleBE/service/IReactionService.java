package com.example.YourPeopleBE.service;

import com.example.YourPeopleBE.model.dto.ReactionDTO;
import com.example.YourPeopleBE.model.entity.Comment;
import com.example.YourPeopleBE.model.entity.Post;
import com.example.YourPeopleBE.model.entity.Reaction;

import java.util.List;

public interface IReactionService {

    Reaction createReaction(ReactionDTO reactionDTO, String where);

    Long reactionsCountOnPost(Post post);
    Long reactionsCountOnComment(Comment comment);

    List<Reaction> findReactionsOnCom(Long commId);
    List<Reaction> findReactionsOnPost(Long postId);
    List<Reaction> findReactionsOfUser(String username);
}
