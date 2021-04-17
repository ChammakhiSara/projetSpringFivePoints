package com.fivePoints.firstProject.Repositries;

import com.fivePoints.firstProject.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepository extends JpaRepository<Post, Integer> {
}
