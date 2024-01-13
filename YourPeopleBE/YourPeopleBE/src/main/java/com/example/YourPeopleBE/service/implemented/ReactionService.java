package com.example.YourPeopleBE.service.implemented;

import com.example.YourPeopleBE.model.dto.ReactionDTO;
import com.example.YourPeopleBE.model.entity.Reaction;
import com.example.YourPeopleBE.service.IReactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService implements IReactionService {
    @Override
    public Reaction createReaction(ReactionDTO reactionDTO) {
        return null;
    }

    @Override
    public List<Reaction> findReactionsOnCom(Long commId) {
        return null;
    }

    @Override
    public List<Reaction> findReactionsOnPost(Long postId) {
        return null;
    }

    @Override
    public List<Reaction> findReactionsOfUser(String username) {
        return null;
    }
}
