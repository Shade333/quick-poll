package ua.com.foxminded.repository;

import org.springframework.data.repository.CrudRepository;

import ua.com.foxminded.domain.Poll;

public interface PollRepository extends CrudRepository<Poll, Long> {
}