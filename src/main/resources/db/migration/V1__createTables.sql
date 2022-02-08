CREATE TABLE dealership
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    city VARCHAR(30) NOT NULL
);

CREATE TABLE price
(
    id            BIGSERIAL PRIMARY KEY,
    dealership_id BIGINT REFERENCES dealership (id),
    car_id        BIGINT REFERENCES catalog.car (id),
    price         INT NOT NULL
);

