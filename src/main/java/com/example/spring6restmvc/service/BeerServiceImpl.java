package com.example.spring6restmvc.service;

import com.example.spring6restmvc.model.Beer;
import com.example.spring6restmvc.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {
    private Map<UUID,Beer> beerMap;

    public BeerServiceImpl() {
        this.beerMap = new HashMap<>();

        Beer beer1 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123123123123")
                .price(new BigDecimal("12.95"))
                .quantityOnHand(122)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        Beer beer2 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("Crank")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123123123123")
                .price(new BigDecimal("11.99"))
                .quantityOnHand(392)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        Beer beer3 = Beer.builder()
                .id(UUID.randomUUID())
                .version(1)
                .beerName("No Hammers On The Bar")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123123123123")
                .price(new BigDecimal("24.55"))
                .quantityOnHand(200)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        beerMap.put(beer1.getId(),beer1);
        beerMap.put(beer2.getId(),beer2);
        beerMap.put(beer3.getId(),beer3);


    }

    @Override
    public Beer getBeerById(UUID beerId) {
        log.debug("Get Beer ID in service was called");
        if(beerMap.containsKey(beerId)){
            return beerMap.get(beerId);
        }return null;
/*

        return Beer.builder()
                .id(beerId)
                .version(1)
                .beerName("Galaxy Cat")
                .beerStyle(BeerStyle.PALE_ALE)
                .upc("123123123123")
                .price(new BigDecimal("12.95"))
                .quantityOnHand(200)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

         */
    }

    @Override
    public List<Beer> listBeers() {
        log.debug("List Beers in service was called");

        return new ArrayList<>(beerMap.values());
    }

    @Override
    public Beer saveNewBeer(Beer beer) {
        Beer beer4 = Beer.builder()
                .id(beer.getId())
                .version(beer.getVersion())
                .beerName(beer.getBeerName())
                .beerStyle(beer.getBeerStyle())
                .upc(beer.getUpc())
                .quantityOnHand(beer.getQuantityOnHand())
                .price(beer.getPrice())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        beerMap.put(beer4.getId(),beer4);

        return beer4;
    }

    @Override
    public Beer updateById(UUID beerId, Beer beer) {

        beerMap.put(beerId,beer);

        return beerMap.get(beerId);
    }

    @Override
    public void deleteById(UUID beerId) {
        beerMap.remove(beerId);
    }

    @Override
    public Beer patchById(UUID beerId, Beer beer) {
        Beer beer5 = beerMap.get(beerId);
        if(beer.getBeerName()!=null){
            beer5.setBeerName(beer.getBeerName());
        }
        if(beer.getBeerStyle()!=null){
            beer5.setBeerStyle(beer.getBeerStyle());
        }
        if(beer.getUpc()!=null){
            beer5.setUpc(beer.getUpc());
        }
        if(beer.getPrice()!=null){
            beer5.setPrice(beer.getPrice());
        }
        if(beer.getQuantityOnHand()!=null){
            beer5.setQuantityOnHand(beer.getQuantityOnHand());
        }
        beerMap.put(beerId,beer5);

        return null;
    }
}
