package com.kamelchukov.autoshop.model.dto.person.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonResponse {
    private String firstName;
    private String lastName;
}
