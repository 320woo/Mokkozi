package com.b303.mokkozi.comment;

import com.b303.mokkozi.entity.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Override
    List<Comment> findAll(Sort sort);
}
