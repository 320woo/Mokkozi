package com.b303.mokkozi.comment;

import com.b303.mokkozi.entity.Comment;
import com.b303.mokkozi.entity.User;

import java.util.NoSuchElementException;

public interface CommentService {
    Comment createComment(User user, CommentWritePostReq cwpr);

    void deleteComment(Long commentId) throws NoSuchElementException;

    Comment getCommentList(Long boardId);
}
