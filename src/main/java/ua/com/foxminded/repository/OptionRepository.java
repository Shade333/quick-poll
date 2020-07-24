package ua.com.foxminded.repository;

import org.springframework.data.repository.CrudRepository;

import ua.com.foxminded.domain.Option;

public interface OptionRepository extends CrudRepository<Option, Long> {
}