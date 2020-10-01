package ua.com.foxminded.unit;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.ArrayList;

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

import ua.com.foxminded.domain.Vote;
import ua.com.foxminded.repository.VoteRepository;
import ua.com.foxminded.v1.controller.VoteController;

@ContextConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class VoteControllerTest {

    @InjectMocks
    VoteController voteController;
    
    @Mock
    private VoteRepository voteRepository;
    
    private MockMvc mockMvc;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(voteController).build();
    }
    
    @Test
    public void shouldGetAllVotes() throws Exception {
        when(voteRepository.findByPoll(1L)).thenReturn(new ArrayList<Vote>());
        mockMvc.perform(get("/v1/polls/1/votes"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
    
    @Test
    public void shouldCreateVote() throws Exception {
        String body = "{\"option\": {\"value\": \"1\"}}";
        when(voteRepository.save(Mockito.any(Vote.class))).thenReturn(new Vote());
        mockMvc.perform(post("/v1/polls/1/votes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(body))
                .andExpect(status().isCreated());
    }
}
