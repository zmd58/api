package com.api.todo.dao;

import com.techelevator.auctions.model.User;

import java.util.Optional;

public interface UserDao {

    Optional<User> findByUsername(String username);

}
