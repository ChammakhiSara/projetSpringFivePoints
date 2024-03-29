package com.fivePoints.firstProject.Repositries;

import com.fivePoints.firstProject.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
// First way of a custom query
    User findUserByEmail(String email);

    Optional <User> findUserByEmailAndFirstName(String email, String firstName);

    Boolean existsByEmail(String email);

//  Second way of a custom query (the most personalised way)
    @Query(value = "SELECT * FROM User WHERE email = ?1 and first_name = ?2", nativeQuery = true)
    Optional <User> findUserByEmailAndFirstNameV2(String email, String firstName);

}
