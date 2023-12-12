package com.manage.user.repository;

import com.manage.user.model.User;
import com.manage.user.util.UserRepositoryTestHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This class contains unit tests for the UserRepository class.
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    /**
     * Unit test for the findByEmail method of UserRepository.
     */
    @Test
    void shouldFindByEmail() {
        User dummyUser = UserRepositoryTestHelper.createDummyUser();
        entityManager.persist(dummyUser);

        Optional<User> foundUser = userRepository.findByEmail(dummyUser.getEmail());

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo(dummyUser.getEmail());
    }

    /**
     * Unit test for the findByEmail method of UserRepository for a non-existent email.
     */
    @Test
    void shouldReturnEmptyForNonExistentEmail() {
        Optional<User> foundUser = userRepository.findByEmail("nonexistent@example.com");

        assertThat(foundUser).isNotPresent();
    }
}