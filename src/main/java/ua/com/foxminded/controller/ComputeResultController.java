package ua.com.foxminded.controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import ua.com.foxminded.domain.Vote;
import ua.com.foxminded.dto.OptionCount;
import ua.com.foxminded.dto.VoteResult;
import ua.com.foxminded.repository.VoteRepository;

@RestController
public class ComputeResultController {

    @Inject
    private VoteRepository voteRepository;

    @RequestMapping(value = "/computeresults", method = RequestMethod.GET)
    @ApiOperation(value = "Compute the results of a given poll", response = VoteResult.class)
    public ResponseEntity<?> computeResults(@RequestParam long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPoll(pollId);
        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<Long, OptionCount>();
        for(Vote v : allVotes) {
            totalVotes ++;
            OptionCount optionCount = tempMap.get(v.getOption().getId());
            if(optionCount == null) {
                    optionCount = new OptionCount();
                    optionCount.setOptionId(v.getOption().getId());
                    tempMap.put(v.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount()+1);
        }
        voteResult.setTotalVotes(totalVotes);
        voteResult.setResults(tempMap.values());
        return new ResponseEntity<>(voteResult, HttpStatus.OK);
    }
}