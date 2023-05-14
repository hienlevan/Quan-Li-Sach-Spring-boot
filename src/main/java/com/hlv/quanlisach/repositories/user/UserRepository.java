package com.hlv.quanlisach.repositories.user;

import com.hlv.quanlisach.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByEmail(String email);

    @Query(value = "{'email': ?0}")
    Optional<User> getUser(String email);

    @Query(value = "{'email': ?0}", exists = true)
    boolean checkEmail(String email);

    boolean existsByEmail(String email);

    Optional<User> findById(String id);

    @Query(value = "{'trangThai': true}")
    List<User> getAlls();

    @Query(value = "{$or: [ {'name': ?0}, {'email': ?0}]}",
            sort = "{'trangThai': -1, 'name': 1}")
    Page<User> filter(String search, Pageable pageable);

}
