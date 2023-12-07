package com.awsm.api.randomizerapp.util;

import com.awsm.api.randomizerapp.model.Candidate;
import com.awsm.api.randomizerapp.model.request.CandidateRequest;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {

    public Candidate mapToCandidate(CandidateRequest candidateRequest){
        return Candidate.builder()
                .employeeId(candidateRequest.getEmployeeId())
                .firstName(candidateRequest.getFirstName())
                .lastName(candidateRequest.getLastName())
                .build();
    }

}
