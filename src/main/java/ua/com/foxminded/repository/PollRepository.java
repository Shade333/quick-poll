package ua.com.foxminded.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import ua.com.foxminded.domain.Poll;

public interface PollRepository extends PagingAndSortingRepository<Poll, Long> {
}