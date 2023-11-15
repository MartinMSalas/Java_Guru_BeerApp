package com.example.spring6restmvc.service;

import com.example.spring6restmvc.model.Beer;

import java.util.List;
import java.util.UUID;

public interface BeerService  {
    Beer getBeerById(UUID beerId);

    List<Beer> listBeers();

    Beer saveNewBeer(Beer beer);

    Beer updateById(UUID beerId, Beer beer);

    void deleteById(UUID beerId);

    Beer patchById(UUID beerId, Beer beer);
}
