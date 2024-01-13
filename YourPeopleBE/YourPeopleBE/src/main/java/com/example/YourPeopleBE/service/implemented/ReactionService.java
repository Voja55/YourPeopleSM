package com.example.YourPeopleBE.service.implemented;

import com.example.YourPeopleBE.model.dto.ReactionDTO;
import com.example.YourPeopleBE.model.entity.Comment;
import com.example.YourPeopleBE.model.entity.Post;
import com.example.YourPeopleBE.model.entity.Reaction;
import com.example.YourPeopleBE.model.entity.User;
import com.example.YourPeopleBE.repositories.ReactionRepo;
import com.example.YourPeopleBE.service.IReactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReactionService implements IReactionService {

    final ReactionRepo reactionRepo;
    final PostService postService;
    final CommentService commentService;
    final UserService userService;

    public ReactionService(ReactionRepo reactionRepo, PostService postService, CommentService commentService, UserService userService) {
        this.reactionRepo = reactionRepo;
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @Override
    public Reaction createReaction(ReactionDTO reactionDTO) {
        Reaction newReact = new Reaction();
        newReact.setReactionType(reactionDTO.getReactionType());
        newReact.setReactedBy(reactionDTO.getReactedBy());
        newReact.setReactedOnComment(reactionDTO.getReactedOnComment());
        newReact.setReactedOnPost(reactionDTO.getReactedOnPost());

        newReact = reactionRepo.save(newReact);
        return newReact;
    }

    @Override
    public Long reactionsCountOnPost(Post post){

        return null;
    }

    @Override
    public Long reactionsCountOnComment(Comment comment){

        return null;
    }

    @Override
    public List<Reaction> findReactionsOnCom(Long commId) {
        Comment comment = commentService.findCommentById(commId);
        List<Reaction> reactions = reactionRepo.findAllByReactedOnComment(comment);
        return reactions;
    }

    @Override
    public List<Reaction> findReactionsOnPost(Long postId) {
        Post post = postService.findPostById(postId);
        List<Reaction> reactions = reactionRepo.findAllByReactedOnPost(post);
        return reactions;
    }

    @Override
    public List<Reaction> findReactionsOfUser(String username) {

        return null;
    }
}
