package io.github.mariazevedo88.travelsapi.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import io.github.mariazevedo88.travelsapi.enumeration.ClTravelTypeEnum;
import io.github.mariazevedo88.travelsapi.factory.ClTravelFactory;
import io.github.mariazevedo88.travelsapi.factory.impl.ClTravelFactoryImpl;
import io.github.mariazevedo88.travelsapi.model.ClonedTravel;

@Service
public class ClonedTravelService {
    
    private ClTravelFactory factory;

    private List<ClonedTravel> travels;

    public void createFactory() {
        if (factory == null) {
            factory = new ClTravelFactoryImpl();
        }
    }

    public void createTravelList() {
        if (travels == null) {
            travels = new ArrayList<>();
        }
    }

    public boolean isJSONValid(String jsonInString) {
        try {
            return new ObjectMapper().readTree(jsonInString) != null;
        } catch (IOException e) {
            return false;
        }
    }

    private long parseId(JSONObject travel) {
        return Long.valueOf((int) travel.get("id"));
    }

    private BigDecimal parseAmount (JSONObject travel) {
        return new BigDecimal((String) travel.get("amount"));
    }

    private LocalDateTime parseStartDate(JSONObject travel) {
        String startDate = (String) travel.get("startDate");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return ZonedDateTime.parse(startDate, formatter.withZone(ZoneId.of("UTC"))).toLocalDateTime();
    }

    private LocalDateTime parseEndDate(JSONObject travel) {
        String endDate = (String) travel.get("endDate");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        return ZonedDateTime.parse(endDate, formatter.withZone(ZoneId.of("UTC"))).toLocalDateTime();
    }

    private void setTravelValues(JSONObject jsonTravel, ClonedTravel travel) {
        String orderNumber = (String) jsonTravel.get("orderNumber");
        String type = (String) jsonTravel.get("type");

        travel.setOrderNumber(orderNumber != null? orderNumber : travel.getOrderNumber());
        travel.setAmount(jsonTravel.get("amount") != null ? parseAmount(jsonTravel) : travel.getAmount());
        travel.setStartDate(jsonTravel.get("startDate") != null ? parseStartDate(jsonTravel): travel.getStartDate());
        travel.setEndDate(jsonTravel.get("endDate") != null ? parseEndDate(jsonTravel) :travel.getEndDate());
        travel.setType(type != null? ClTravelTypeEnum.getEnum(type) : travel.getType());
    }

    public boolean isStartDateGreaterThanEndDate(ClonedTravel travel) {
        if (travel.getEndDate() == null) return false;
        return travel.getStartDate().isAfter(travel.getEndDate());
    }

    public ClonedTravel create(JSONObject jsonTravel) {

        createFactory();

        ClonedTravel travel = factory.createTravel((String) jsonTravel.get("type"));
        travel.setId(parseId(jsonTravel));
        setTravelValues(jsonTravel, travel);

        return travel;
    }

    public ClonedTravel update (ClonedTravel travel, JSONObject jsonTravel) {
        setTravelValues(jsonTravel, travel);
        return travel;
    }

    public void add (ClonedTravel travel) {
        createTravelList();
        travels.add(travel);
    }

    public List<ClonedTravel> find() {
        createTravelList();
        return travels;
    }

    public ClonedTravel findById(long id) {
        List<ClonedTravel> list = travels.stream().filter(t -> id == t.getId()).collect(Collectors.toList());

        if (list != null && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public void delete() {
        travels.clear();
    }
}
