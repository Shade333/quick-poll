package ua.com.foxminded.controller;

import java.net.URI;
import java.util.Optional;

import javax.inject.Inject;
import javax.validation.Valid;

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
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import ua.com.foxminded.domain.Poll;
import ua.com.foxminded.dto.error.ErrorDetail;
import ua.com.foxminded.exception.ResourceNotFoundException;
import ua.com.foxminded.repository.PollRepository;

@RestController
public class PollController {

    @Inject
    private PollRepository pollRepository;

    protected void verifyPoll(long pollId) throws ResourceNotFoundException {
        if (!pollRepository.existsById(pollId)) {
            throw new ResourceNotFoundException("Poll with id " + pollId + " not found");
        }
    }

    @RequestMapping(method=RequestMethod.GET, value="/polls")
    @ApiOperation(value = "Retrieves all the polls", response=Poll.class, responseContainer="List")
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> allPolls = pollRepository.findAll();
        return new ResponseEntity<>(allPolls, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST, value="/polls")
    @ApiOperation(value = "Creates a new Poll", notes="The newly created poll Id will be sent in the location response header", response = Void.class)
    @ApiResponses(value = {@ApiResponse(code=201, message="Poll Created Successfully", response=Void.class), 
            @ApiResponse(code=500, message="Error creating Poll", response=ErrorDetail.class) } )
    public ResponseEntity<Void> createPoll(@Valid @RequestBody Poll poll) {
        poll = pollRepository.save(poll);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
    @ApiOperation(value = "Retrieves given Poll", response=Poll.class)
    @ApiResponses(value = {@ApiResponse(code=200, message="", response=Poll.class),  
            @ApiResponse(code=404, message="Unable to find Poll", response=ErrorDetail.class) } )
    public ResponseEntity<Poll> getPoll(@PathVariable long pollId) {
        verifyPoll(pollId);
        Optional<Poll> poll = pollRepository.findById(pollId);
        return new ResponseEntity<>(poll.get(), HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.PUT)
    @ApiOperation(value = "Updates given Poll", response=Void.class)
    @ApiResponses(value = {@ApiResponse(code=200, message="", response=Void.class),  
            @ApiResponse(code=404, message="Unable to find Poll", response=ErrorDetail.class) } )
    public ResponseEntity<?> updatePoll(@RequestBody Poll poll, @PathVariable long pollId) {
        verifyPoll(pollId);
        poll.setId(pollId);
        poll = pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK); 
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.DELETE)
    @ApiOperation(value = "Deletes given Poll", response=Void.class)
    @ApiResponses(value = {@ApiResponse(code=200, message="", response=Void.class),  
            @ApiResponse(code=404, message="Unable to find Poll", response=ErrorDetail.class) } )
    public ResponseEntity<?> deletePoll(@PathVariable long pollId) {
        verifyPoll(pollId);
        pollRepository.deleteById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}