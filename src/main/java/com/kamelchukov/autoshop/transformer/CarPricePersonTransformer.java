package com.kamelchukov.autoshop.transformer;

import com.kamelchukov.autoshop.model.Price;
import com.kamelchukov.autoshop.model.dto.car.CarDto;
import com.kamelchukov.autoshop.model.dto.car.response.CarResponse;
import com.kamelchukov.autoshop.model.dto.person.PersonDto;

import java.util.ArrayList;
import java.util.List;

public final class CarPricePersonTransformer {
    private CarPricePersonTransformer() {
    }

    public static List<CarResponse> toCarResponseList(List<CarDto> cars, List<Price> prices, List<PersonDto> persons) {
        List<CarResponse> carResponses = new ArrayList<>();

        for (CarDto car : cars) {
            var priceCar = prices.stream()
                    .filter(price -> price.getCarId().equals(car.getId()))
                    .findFirst()
                    .orElseThrow();

            var personCar = persons.stream()
                    .filter(person -> person.getId().equals(car.getPersonId()))
                    .findFirst()
                    .orElseThrow();

            var carResponse = CarResponse.builder()
                    .id(car.getId())
                    .model(car.getModel())
                    .classCar(car.getClassCar())
                    .color(car.getColor())
                    .year(car.getYear())
                    .price(priceCar.getPrice())
                    .person(PersonTransformer.toPersonResponse(personCar))
                    .build();

            carResponses.add(carResponse);
        }
        return carResponses;
    }
}
