package com.manage.user.repository;

import com.manage.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * This interface serves as the repository for the User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Retrieves a user based on the email.
     *
     * @param email the email of the user
     * @return an Optional containing the user associated with the email
     */
    Optional<User> findByEmail(String email);

}