package com.manage.user.util;

import com.manage.user.model.Phone;
import com.manage.user.model.User;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class UserRepositoryTestHelper {

    public static User createDummyUser() {
        User user = new User();
        user.setName("Test User");
        user.setEmail("testuser@example.com");
        user.setPassword("Password123!");
        user.setToken("dummyToken");
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setActive(true);
        user.setPhones(createDummyPhones(user));

        return user;
    }

    private static Set<Phone> createDummyPhones(User user) {
        Set<Phone> phones = new HashSet<>();
        Phone phone = new Phone();
        phone.setNumber("1234567890");
        phone.setCityCode("1");
        phone.setCountryCode("57");
        phone.setUser(user);
        phones.add(phone);

        return phones;
    }
}
