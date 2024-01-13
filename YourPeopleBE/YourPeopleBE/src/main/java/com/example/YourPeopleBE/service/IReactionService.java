package com.example.YourPeopleBE.service;

import com.example.YourPeopleBE.model.dto.ReactionDTO;
import com.example.YourPeopleBE.model.entity.Reaction;

import java.util.List;

public interface IReactionService {

    Reaction createReaction(ReactionDTO reactionDTO);
    List<Reaction> findReactionsOnCom(Long commId);
    List<Reaction> findReactionsOnPost(Long postId);
    List<Reaction> findReactionsOfUser(String username);
}
