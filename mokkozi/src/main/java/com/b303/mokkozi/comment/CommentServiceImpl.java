package com.b303.mokkozi.comment;

import com.b303.mokkozi.board.BoardRepository;
import com.b303.mokkozi.board.BoardService;
import com.b303.mokkozi.comment.request.CommentWritePostReq;
import com.b303.mokkozi.entity.Board;
import com.b303.mokkozi.entity.Comment;
import com.b303.mokkozi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment createComment(User user, CommentWritePostReq cwpr) {

        Comment comment = new Comment();
        comment.setContent(cwpr.getContent());
        comment.setBoard(cwpr.getBoardId());
        comment.setUser(user);
        return commentRepository.save(comment);
    }


    @Override
    public void deleteComment(Long commentId) throws NoSuchElementException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new NoSuchElementException("not found"));
        commentRepository.deleteById(comment.getId());
    }

    @Override
    public Comment getCommentList(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException("not found"));
        List<Comment> comments = board.getComments();
        // 댓글 목록 가져오는 부분..모르곘다

        return comments;
    }
}
