package com.awsm.api.randomizerapp.model.request;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CandidateRequest {

    private String firstName;
    private String lastName;
    private String employeeId;

}
