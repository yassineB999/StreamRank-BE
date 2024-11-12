package com.streamrank.infrastructure.persistence;

import com.streamrank.domain.user.model.User;
import com.streamrank.domain.user.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {
}