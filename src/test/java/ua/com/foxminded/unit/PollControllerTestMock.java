package ua.com.foxminded.unit;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import ua.com.foxminded.domain.Poll;
import ua.com.foxminded.repository.PollRepository;
import ua.com.foxminded.v1.controller.PollController;

public class PollControllerTestMock {

    @Mock
    private PollRepository pollRepository;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void shouldGetAllPolls() {
        PollController pollController = new PollController();
        ReflectionTestUtils.setField(pollController, "pollRepository", pollRepository);
        when(pollRepository.findAll()).thenReturn(new ArrayList<Poll>());
        ResponseEntity<Iterable<Poll>> allPollsEntity = pollController.getAllPolls();
        verify(pollRepository, times(1)).findAll();
        assertEquals(HttpStatus.OK, allPollsEntity.getStatusCode());
        assertEquals(0, Lists.newArrayList(allPollsEntity.getBody()).size());
    }
}