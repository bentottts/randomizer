package com.awsm.api.randomizerapp.service.impl;

import com.awsm.api.randomizerapp.exception.DuplicateCandidateException;
import com.awsm.api.randomizerapp.exception.EmptyRegistryException;
import com.awsm.api.randomizerapp.model.Candidate;
import com.awsm.api.randomizerapp.model.request.CandidateRequest;
import com.awsm.api.randomizerapp.service.CandidateService;
import com.awsm.api.randomizerapp.util.CSVUtil;
import com.awsm.api.randomizerapp.util.CandidateMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CandidateServiceImpl implements CandidateService {

    private final CSVUtil csvUtil;
    private final CandidateMapper candidateMapper;

    public CandidateServiceImpl(CSVUtil csvUtil, CandidateMapper candidateMapper) {
        this.csvUtil = csvUtil;
        this.candidateMapper = candidateMapper;
    }

    @Override
    public void saveCandidate(CandidateRequest candidateRequest) throws DuplicateCandidateException {
        if (StringUtils.isNotBlank(candidateRequest.getLastName()) && StringUtils.isNotBlank(candidateRequest.getFirstName())) {
            Candidate candidate = candidateMapper.mapToCandidate(candidateRequest);
            csvUtil.saveCandidateToCSV(candidate);
        }
    }

    @Override
    public void removeCandidate(CandidateRequest candidateRequest) {
        List<Candidate> candidates = csvUtil.getAllCandidatesFromCSV();
        // remove candidate from list then save as new
        candidates.removeIf(c -> StringUtils.equalsAnyIgnoreCase(candidateRequest.getFirstName(), c.getFirstName())
                && StringUtils.equalsAnyIgnoreCase(candidateRequest.getLastName(), c.getLastName()));
        //
        log.info("Remove {}", candidateRequest.toString());
        csvUtil.saveAfterRemoving(candidates);
    }

    @Override
    public Candidate getWinner() throws InterruptedException {
        return null;
    }

    @Override
    public List<String> getCandidateList() throws EmptyRegistryException {
        List<Candidate> candidates = csvUtil.getAllCandidatesFromCSV();
        List<String> candidateList;
        if (!Objects.isNull(candidates)) {
            candidateList = candidates.stream()
                    .map(Candidate::getCandidateName)
                    .collect(Collectors.toList());
        } else {
            throw new EmptyRegistryException("No registered candidate!");
        }
        return candidateList;
    }

    @Override
    public List<Candidate> getCandidates() {
        return csvUtil.getAllCandidatesFromCSV();
    }
}
