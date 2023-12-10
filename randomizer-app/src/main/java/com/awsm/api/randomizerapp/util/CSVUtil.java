package com.awsm.api.randomizerapp.util;

import com.awsm.api.randomizerapp.exception.DuplicateCandidateException;
import com.awsm.api.randomizerapp.model.Candidate;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class CSVUtil {

    @Value("${app.csv.path}")
    private String path;

    @Value("${app.csv.file-name}")
    private String fileName;

    @Value("${app.csv.header}")
    private String header;

    private final String STRING_DELIMITER = ",";


    public void saveCandidateToCSV(Candidate candidate) throws DuplicateCandidateException {
        List<Candidate> candidates = readCSVFile(true);

        //check if candidate exists
        if (isCandidateAlreadyRegistered(candidates, candidate)) {
            throw new DuplicateCandidateException("The candidate is already registered!");
        }
        candidates.add(candidate);
        writeCSVFile(candidates);
    }

    public List<Candidate> getAllCandidatesFromCSV(){
        List<Candidate> candidates = readCSVFile(false);
        if(!Objects.isNull(candidates)){
            candidates.remove(0);
        }
        return candidates;
    }

    public void saveAfterRemoving(List<Candidate> candidates){
        // save the updated list
        writeCSVFile(candidates);
    }

    private String pathBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        return String.valueOf(stringBuilder.append(path).append(fileName));
    }

    private boolean isFileExists(String filePath) {
        Path path = Paths.get(filePath);
        return Files.exists(path);
    }

    private boolean isFileEmpty() throws IOException, CsvValidationException {
        FileReader filereader = new FileReader(pathBuilder());

        // create csvReader object passing
        // file reader as a parameter
        CSVReader csvReader = new CSVReader(filereader);
        return csvReader.readNext().length == 0;
    }

    private boolean isCandidateAlreadyRegistered(List<Candidate> candidates, Candidate candidate) {

        return candidates.contains(candidate);
    }

    private List<Candidate> readCSVFile(boolean skipHeader) {
        List<Candidate> candidates = new ArrayList<>();
        try {
            if (!isFileExists(pathBuilder())) {
                return candidates;
            }

            FileReader filereader = new FileReader(pathBuilder());
            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            if(skipHeader){
                //to skip the header
                String[] headers = csvReader.readNext();
            }
            while ((nextRecord = csvReader.readNext()) != null) {
                candidates.add(Candidate.builder()
                        .employeeId(StringUtils.isBlank(nextRecord[0]) ? "" : nextRecord[0])
                        .firstName(StringUtils.isBlank(nextRecord[1]) ? "" : nextRecord[1])
                        .lastName(StringUtils.isBlank(nextRecord[2]) ? "" : nextRecord[2])
                        .build());
            }
            log.info("Read Candidates: {}", candidates);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            log.error("Error reading the CSV : {}", e.toString());
        }
        return candidates;
    }

    private void writeCSVFile(List<Candidate> candidates) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter(pathBuilder()))) {

            // add the header
            String[] header = this.header.split(STRING_DELIMITER);
            csvWriter.writeNext(header);

            List<String[]> datas = new ArrayList<>();

            for (Candidate candidate : candidates) {
                //add the candidate/s to the file
                String[] data = {candidate.getEmployeeId(), candidate.getFirstName(), candidate.getLastName()};
                datas.add(data);
            }
            csvWriter.writeAll(datas);

            log.info("Successful");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
