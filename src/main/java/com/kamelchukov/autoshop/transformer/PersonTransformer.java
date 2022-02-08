package com.kamelchukov.autoshop.transformer;

import com.kamelchukov.autoshop.model.dto.person.PersonDto;
import com.kamelchukov.autoshop.model.dto.person.response.PersonResponse;

public final class PersonTransformer {
    private PersonTransformer() {
    }

    public static PersonResponse toPersonResponse(PersonDto personDto) {
        return PersonResponse.builder()
                .firstName(personDto.getFirstName())
                .lastName(personDto.getLastName())
                .build();
    }
}
