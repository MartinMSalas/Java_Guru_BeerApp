package com.example.spring6restmvc.controller;

import com.example.spring6restmvc.model.Beer;
import com.example.spring6restmvc.service.BeerService;
import com.example.spring6restmvc.service.BeerServiceImpl;




import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;




import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

// expect



//@SpringBootTest
@Slf4j
@WebMvcTest(BeerController.class)
class BeerControllerTest {
    //@Autowired
    //BeerController beerController;

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    BeerService beerService;


    BeerServiceImpl beerServiceImpl = new BeerServiceImpl();

    @BeforeEach
    void setUp() {
        beerServiceImpl = new BeerServiceImpl();
    }
    @Test
    void testCreateNewBeer() throws Exception {

        Beer beer = beerServiceImpl.listBeers().get(0);
        beer.setVersion(null);
        beer.setId(null);

        given(beerService.saveNewBeer(any(Beer.class))).willReturn(beerServiceImpl.listBeers().get(1));

        mockMvc.perform(post("/beer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beer)))
                        .andExpect(status().isCreated())
                        .andExpect(header().exists("Location"));




    }
    @Test
    void testListBeers() throws Exception {
        given(beerService.listBeers()).willReturn(beerServiceImpl.listBeers());

        mockMvc.perform(get("/beer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", org.hamcrest.Matchers.is(3)));
    }
    @Test
    void getBeerById() throws Exception {
        Beer testBeer = beerServiceImpl.listBeers().get(0);
        log.debug("Test Beer: " + testBeer.toString());
        given(beerService.getBeerById(testBeer.getId())).willReturn(testBeer);
        //System.out.println(beerController.getBeerById(UUID.randomUUID()));
        mockMvc.perform(get("/beer/" + testBeer.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", org.hamcrest.Matchers.is(testBeer.getId().toString())))
                .andExpect(jsonPath("$.beerName", org.hamcrest.Matchers.is(testBeer.getBeerName())));


    }
}