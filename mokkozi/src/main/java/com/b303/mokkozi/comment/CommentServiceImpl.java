package com.b303.mokkozi.comment;

import com.b303.mokkozi.comment.request.CommentWritePostReq;
import com.b303.mokkozi.entity.Comment;
import com.b303.mokkozi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment createComment(User user, CommentWritePostReq cwpr) {

        Comment comment = new Comment();
        comment.setContent(cwpr.getContent());
        comment.setBoard(cwpr.getBoardId());
        comment.setUser(user);
        // 날짜는 엔티티에서 해줌..?
        return commentRepository.save(comment);
    }


    @Override
    public void deleteComment(Long commentId) throws NoSuchElementException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("not found"));
        commentRepository.deleteById(comment.getId());
    }

    @Override
    public Optional<List<Comment>> getCommentList(Long boardId) {
        Optional<List<Comment>> comments = commentRepository.findByBoardId(boardId);
        // 위 변수를 CommentDto로 변환하기
        return comments;
    }
}
