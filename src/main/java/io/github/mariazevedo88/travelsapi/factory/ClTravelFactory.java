package io.github.mariazevedo88.travelsapi.factory;

import io.github.mariazevedo88.travelsapi.model.ClonedTravel;

public interface ClTravelFactory {
    ClonedTravel createTravel (String type);
}
