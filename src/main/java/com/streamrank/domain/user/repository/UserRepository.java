package com.streamrank.domain.user.repository;

import com.streamrank.domain.user.model.User;

public interface UserRepository {
    User findByUserName(String username);
    Boolean existsByUserNameOrEmail(String userName, String email);
    User findByEmail(String email);
}
