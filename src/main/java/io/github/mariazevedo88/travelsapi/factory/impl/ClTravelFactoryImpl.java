package io.github.mariazevedo88.travelsapi.factory.impl;

import io.github.mariazevedo88.travelsapi.enumeration.ClTravelTypeEnum;
import io.github.mariazevedo88.travelsapi.factory.ClTravelFactory;
import io.github.mariazevedo88.travelsapi.model.ClonedTravel;

public class ClTravelFactoryImpl implements ClTravelFactory {

    @Override
    public ClonedTravel createTravel(String type) {
        
        if (ClTravelTypeEnum.ONE_WAY.getValue().equals(type)) {
            return new ClonedTravel(ClTravelTypeEnum.ONE_WAY);
        } else if ( ClTravelTypeEnum.MULTI_CITY.getValue().equals(type)) {
            return new ClonedTravel(ClTravelTypeEnum.MULTI_CITY);
        }

        return new ClonedTravel(ClTravelTypeEnum.RETURN);
    }
    
}
