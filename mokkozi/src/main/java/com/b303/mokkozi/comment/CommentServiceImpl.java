package com.b303.mokkozi.comment;

import com.b303.mokkozi.entity.Comment;
import com.b303.mokkozi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment createComment(User user, CommentWritePostReq cwpr) {

        Comment comment = new Comment();
        comment.setContent(cwpr.getContent());
        comment.setUser(user);
        comment.setBoard(cwpr.boardId);
        return commentRepository.save(comment);
    }


    @Override
    public void deleteComment(Long commentId) throws NoSuchElementException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("not found"));
        commentRepository.deleteById(comment.getId());
    }

    @Override
    public Comment getCommentList(Long boardId) {
        return null;
    }
}
