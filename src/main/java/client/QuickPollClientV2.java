package client;

import java.net.URI;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ua.com.foxminded.domain.Poll;

public class QuickPollClientV2 {

    private static final String QUICK_POLL_URI_V2 = "http://localhost:8080/v2/polls";

    private RestTemplate restTemplate = new RestTemplate();

    public Poll getPollById(Long pollId) {
        return restTemplate.getForObject(QUICK_POLL_URI_V2 + "/{pollId}", Poll.class, pollId);
    }

    public PageWrapper<Poll> getAllPolls(int page, int size) {
        ParameterizedTypeReference<PageWrapper<Poll>> responseType = 
                new ParameterizedTypeReference<PageWrapper<Poll>>() {};
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(QUICK_POLL_URI_V2)
                .queryParam("page", page)
                .queryParam("size", size);
        ResponseEntity<PageWrapper<Poll>> responseEntity = 
                restTemplate.exchange(builder.build().toUri(), HttpMethod.GET, null, responseType);
        PageWrapper<Poll> allPolls = responseEntity.getBody();
        return allPolls;
    }

    public URI createPoll(Poll poll) {
        return restTemplate.postForLocation(QUICK_POLL_URI_V2, poll);
    }

    public void updatePoll(Poll poll) {
        restTemplate.put(QUICK_POLL_URI_V2 + "/{pollId}", poll, poll.getId());
    }

    public void deletePoll(Long pollId) {
        restTemplate.delete(QUICK_POLL_URI_V2 + "/{pollId}", pollId);
    }
}