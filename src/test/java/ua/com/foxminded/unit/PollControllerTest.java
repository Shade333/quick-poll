package ua.com.foxminded.unit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import ua.com.foxminded.domain.Poll;
import ua.com.foxminded.repository.PollRepository;
import ua.com.foxminded.v1.controller.PollController;

@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class PollControllerTest {

    @InjectMocks
    PollController pollController;
    
    @Mock
    private PollRepository pollRepository;
    
    private MockMvc mockMvc;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(pollController).build();
    }
    
    @Test
    public void shouldGetAllPolls() throws Exception {
        when(pollRepository.findAll()).thenReturn(new ArrayList<Poll>());
        mockMvc.perform(get("/v1/polls"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
    
    @Test
    public void shouldGetPollById() throws Exception {
        when(pollRepository.findById(1L)).thenReturn(Optional.of(new Poll()));
        when(pollRepository.existsById(1L)).thenReturn(true);
        mockMvc.perform(get("/v1/polls/1"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void shouldCreatePoll() throws Exception {
        String body = "{\"question\": \"a\",\"options\": [{\"value\": \"1\"},{\"value\": \"2\"}]}";
        when(pollRepository.save(Mockito.any(Poll.class))).thenReturn(new Poll());
        mockMvc.perform(post("/v1/polls")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andExpect(status().isCreated());
    }
    
    @Test
    public void shouldUpdatePoll() throws Exception {
        String body = "{\"question\": \"a\",\"options\": [{\"value\": \"1\"},{\"value\": \"2\"}]}";
        when(pollRepository.save(Mockito.any(Poll.class))).thenReturn(new Poll());
        when(pollRepository.existsById(1L)).thenReturn(true);
        mockMvc.perform(put("/v1/polls/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andExpect(status().isOk());
    }
    
    @Test
    public void shouldDeletePoll() throws Exception {
        when(pollRepository.existsById(1L)).thenReturn(true);
        mockMvc.perform(delete("/v1/polls/1"))
        .andExpect(status().isOk());
    }
    
    @Test
    public void shouldSayNotFound() throws Exception {
        when(pollRepository.existsById(1L)).thenReturn(false);
        mockMvc.perform(get("/v1/polls/1"))
                .andExpect(status().isNotFound());
    }
}