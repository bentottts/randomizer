package com.awsm.api.randomizerapp.service;



import com.awsm.api.randomizerapp.exception.DuplicateCandidateException;
import com.awsm.api.randomizerapp.exception.EmptyRegistryException;
import com.awsm.api.randomizerapp.model.Candidate;
import com.awsm.api.randomizerapp.model.request.CandidateRequest;

import java.util.List;

public interface CandidateService {

    void saveCandidate(CandidateRequest candidateRequest) throws DuplicateCandidateException;

    void removeCandidate(CandidateRequest candidateRequest);

    Candidate getWinner() throws InterruptedException;

    List<String> getCandidateList() throws EmptyRegistryException;

    List<Candidate> getCandidates();
}
