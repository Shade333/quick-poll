package ua.com.foxminded.repository;

import org.springframework.data.repository.CrudRepository;

import ua.com.foxminded.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);
}