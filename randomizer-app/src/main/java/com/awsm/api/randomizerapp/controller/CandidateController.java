package com.awsm.api.randomizerapp.controller;

import com.awsm.api.randomizerapp.exception.DuplicateCandidateException;
import com.awsm.api.randomizerapp.model.request.CandidateRequest;
import com.awsm.api.randomizerapp.service.CandidateService;
import com.awsm.api.randomizerapp.service.impl.CandidateServiceImplV02;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/api")
public class CandidateController {

    private final CandidateService candidateService;


    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/save-candidate")
    ResponseEntity<Object> saveCandidate(@RequestBody CandidateRequest candidateRequest) throws DuplicateCandidateException {

        // save candidate
//        candidateService.saveCandidate(candidateRequest);
        candidateService.saveCandidate(candidateRequest);
        log.info(candidateRequest.toString());

        return new ResponseEntity<>(candidateRequest,HttpStatus.CREATED);
    }

    @PostMapping("/remove-candidate")
    ResponseEntity<Object> removeCandidateFromList(@RequestBody CandidateRequest candidateRequest){
        candidateService.removeCandidate(candidateRequest);

        return new ResponseEntity<>(candidateRequest,HttpStatus.OK);
    }
}
