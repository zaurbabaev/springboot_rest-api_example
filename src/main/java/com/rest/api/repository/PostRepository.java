package com.rest.api.repository;

import com.rest.api.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAll(Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %?1%")
    Page<Post> findByTitle(String title, Pageable pageable);


}
