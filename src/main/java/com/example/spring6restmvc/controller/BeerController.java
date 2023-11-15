package com.example.spring6restmvc.controller;

import com.example.spring6restmvc.model.Beer;
import com.example.spring6restmvc.service.BeerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/beer")
public class BeerController {

    private final BeerService beerService;

    @PatchMapping("{beerId}")
    public ResponseEntity patchById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer){
        Beer patchedBeer = beerService.patchById(beerId, beer);
        log.debug("Patch by ID in controller was called with beer: " + patchedBeer.toString());
        return new ResponseEntity(patchedBeer, HttpStatus.CREATED );
    }

    @DeleteMapping("{beerId}")
    public ResponseEntity deleteById(@PathVariable("beerId") UUID beerId){
        log.debug("Delete by ID in controller was called ! ");
        beerService.deleteById(beerId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PutMapping("{beerId}")
    public ResponseEntity updateById(@RequestBody Beer beer,@PathVariable("beerId") UUID beerId){
        Beer updatedBeer = beerService.updateById(beerId, beer);
        log.debug("Update by ID in controller was called with beer: " + updatedBeer.toString());
        return new ResponseEntity(updatedBeer, HttpStatus.CREATED );
    }
    @PostMapping
    public ResponseEntity<Beer> handlePost(@RequestBody Beer beer){
        log.debug("Handle Post in controller was called with beer: " + beer.toString());
        Beer savedBeer = beerService.saveNewBeer(beer);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/beer/" + savedBeer.getId().toString());
        return new ResponseEntity(savedBeer, headers, HttpStatus.CREATED);
    }
    @GetMapping("{beerId}")
    public Beer getBeerById(@PathVariable("beerId") UUID beerId){
        log.debug("Get Beer ID in controller was called");
        return beerService.getBeerById(beerId);
    }
    @GetMapping
    public List<Beer> listBeers(){
        log.debug("List Beers in controller was called !");
        return beerService.listBeers();
    }
}
