package com.awsm.api.randomizerapp.model;

import lombok.*;

import java.util.Objects;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Candidate {

    private String firstName;
    private String lastName;
    private String employeeId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return Objects.equals(firstName, candidate.firstName) && Objects.equals(lastName, candidate.lastName) && Objects.equals(employeeId, candidate.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, employeeId);
    }

    public String getCandidateName() {
        return firstName + " " + lastName;
    }
}
