package ua.com.foxminded.controller;

import java.net.URI;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import ua.com.foxminded.domain.Vote;
import ua.com.foxminded.repository.VoteRepository;

@RestController
public class VoteController {

    @Inject
    private VoteRepository voteRepository;

    @RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.POST)
    @ApiOperation(value = "Casts a new vote for a given poll", notes="The newly created vote Id will be sent in the location response header", 
    response = Void.class)
    @ApiResponses(value = {@ApiResponse(code=201, message="Vote Created Successfully", response=Void.class) })
    public ResponseEntity<?> createVote(@PathVariable long pollId, @RequestBody Vote vote) {
        vote = voteRepository.save(vote);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newVoteUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(vote.getId())
                .toUri();
        responseHeaders.setLocation(newVoteUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value="/polls/{pollId}/votes", method=RequestMethod.GET)
    public ResponseEntity<Iterable<Vote>> getAllVotes(@PathVariable long pollId) {
        Iterable<Vote> votes = voteRepository.findByPoll(pollId);
        return new ResponseEntity<>(votes, HttpStatus.OK);
    }
}
