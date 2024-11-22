package com.rest.api.repository;

import com.rest.api.entity.Comment;
import com.rest.api.utils.response.CommentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAll();

    @Query(value = "SELECT c FROM Comment c")
    List<Comment> findAllComments();

    @Query("SELECT c FROM Comment c WHERE c.id=?1")
    Comment findByCommentId(Long commentId);

    @Query("SELECT c FROM Comment c WHERE c.name LIKE %?1%")
    List<Comment> findWithLike(String name);

    @Query("SELECT c FROM Comment c WHERE c.name LIKE %?1%")
    List<Comment> findWithLikeJPA(String name);



    @Query(value = "SELECT c.* FROM comment c", nativeQuery = true)
    List<Comment> findAllWithNativeQuery();

    @Query(value = "SELECT c.* FROM comment c WHERE c.comment_id=?1", nativeQuery = true)
    Comment findByIdWithNativeQuery(Long id);


    @Query(value = "SELECT c.* FROM comment WHERE c.comment_id= ?1", nativeQuery = true)
    Comment findByIdWithJPA(Long id);


    @Query(value = "SELECT c.comment_id as commentId, c.name as commentName, " +
            "c.email as commentEmail, c.body as commentBody FROM comment c", nativeQuery = true)
    List<CommentProjection> findAllWithProjection();

}
