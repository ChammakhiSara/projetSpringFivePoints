package com.fivePoints.firstProject.Repositries;

import com.fivePoints.firstProject.Models.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetail, Integer> {
}
