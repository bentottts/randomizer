package com.awsm.api.randomizerapp.service.impl;

import com.awsm.api.randomizerapp.exception.DuplicateCandidateException;
import com.awsm.api.randomizerapp.exception.EmptyRegistryException;
import com.awsm.api.randomizerapp.model.Candidate;
import com.awsm.api.randomizerapp.model.request.CandidateRequest;
import com.awsm.api.randomizerapp.service.CandidateService;
import com.awsm.api.randomizerapp.util.CSVUtil;
import com.awsm.api.randomizerapp.util.CandidateMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CandidateServiceImplV02 implements CandidateService {

    private final CSVUtil csvUtil;
    private final CandidateMapper candidateMapper;

    public CandidateServiceImplV02(CSVUtil csvUtil, CandidateMapper candidateMapper) {
        this.csvUtil = csvUtil;
        this.candidateMapper = candidateMapper;
    }

    @Override
    public void saveCandidate(CandidateRequest candidateRequest) throws DuplicateCandidateException {
        Candidate candidate = candidateMapper.mapToCandidate(candidateRequest);

        csvUtil.saveCandidateToCSV(candidate);
    }

    @Override
    public void removeCandidate(CandidateRequest candidateRequest) {
        List<Candidate> candidates = csvUtil.getAllCandidatesFromCSV();
        // remove candidate from list then save as new
        candidates.remove(candidateMapper.mapToCandidate(candidateRequest));

        csvUtil.saveAfterRemoving(candidates);
    }

    @Override
    public Candidate getWinner() throws InterruptedException {
        return null;
    }

    @Override
    public List<String> getCandidateList() throws EmptyRegistryException {
        List<Candidate> candidates = csvUtil.getAllCandidatesFromCSV();
        List<String> candidateList = new ArrayList<>();
        if(!Objects.isNull(candidates)){
           candidateList = candidates.stream()
                    .map(Candidate::getCandidateName)
                    .collect(Collectors.toList());
        }else {
            throw new EmptyRegistryException("No registered candidate!");
        }
        return candidateList;
    }

    @Override
    public List<Candidate> getCandidates() {
       return csvUtil.getAllCandidatesFromCSV();
    }
}
