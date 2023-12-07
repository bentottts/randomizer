package com.awsm.api.randomizerapp.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CandidateServiceImpl {
//
//    private final CSVWriterUtil csvWriterUtil;
//
//    List<Candidate> candidateList = new ArrayList<>();
//
//
//    private final CandidateMapper candidateMapper;
//
//    public CandidateServiceImpl(CSVWriterUtil csvWriterUtil, CandidateMapper candidateMapper) {
//        this.csvWriterUtil = csvWriterUtil;
//        this.candidateMapper = candidateMapper;
//    }
//
//    @Override
//    public void saveCandidate(CandidateRequest candidateRequest) {
//        // save to java memory
//
//        Candidate candidate = candidateMapper.mapToCandidate(candidateRequest);
//
//        csvWriterUtil.saveCandidateToCSV(candidate);
//
//        candidateList.add(candidate);
//        //
//        log.info(candidateList.toString());
//    }
//
//    @Override
//    public void removeCandidate(String empId) {
//        // remove from master list
//        candidateList.remove(empId);
//        //
//        log.info(candidateList.toString());
//    }
//
//    @Override
//    public Candidate getWinner() throws InterruptedException {
//
////        candidateMap.put("19-123",new Candidate("paul", "dungca", "19-123"));
////        candidateMap.put("19-124",new Candidate("reyven", "dungca", "19-124"));
////        candidateMap.put("19-125",new Candidate("gene", "dungca", "19-124"));
////        candidateMap.put("19-126",new Candidate("carlo", "dungca", "19-124"));
////        candidateMap.put("19-127",new Candidate("son", "dungca", "19-124"));
////        candidateMap.put("19-128",new Candidate("ben", "dungca", "19-124"));
////        candidateMap.put("19-132",new Candidate("til", "dungca", "19-124"));
////        candidateMap.put("19-129",new Candidate("amor", "dungca", "19-124"));
////        candidateMap.put("19-120",new Candidate("el", "dungca", "19-124"));
////        candidateMap.put("19-131",new Candidate("ar", "dungca", "19-124"));
////
////        List<Candidate> candidateList = new ArrayList<>(candidateMap.values());
////        // Cryptographically strong than Random()
////        SecureRandom secureRandom = new SecureRandom();
//
//        // drum roll for n seconds
////        Thread.sleep(3000);
//
//        // get random Candidate
////        return candidateList.get(secureRandom.nextInt(candidateList.size()));
//
//    return null;
//
//    }
//
//    @Override
//    public List<Candidate> getCandidateList() {
//
//        // return list of candidates
////        return new ArrayList<>(candidateMap.values());
//
//    return null;
//    }

}
