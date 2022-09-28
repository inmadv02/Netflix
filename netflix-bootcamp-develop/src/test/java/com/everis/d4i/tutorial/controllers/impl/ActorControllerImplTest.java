package com.everis.d4i.tutorial.controllers.impl;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.impl.ActorServiceImpl;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static sun.plugin2.util.PojoUtil.toJson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.main.allow-bean-definition-overriding=true"})
class ActorControllerImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ActorControllerImpl actorController;

    @MockBean
    private ActorServiceImpl actorService;

    @MockBean
    private ModelMapper modelMapper;

    static Actor actor, actor2;
    static ActorRest actor3;
    static List<Actor> actors;
    static List<ActorRest> actorRests;
    static NetflixResponse<List<ActorRest>> netflixResponse;
    static NetflixResponse<ActorRest> createActorRestNetflixResponse, getActorByIdNetflixResponse;


    @BeforeEach
    void setUp() {
        actors = new ArrayList<>();
        actor = Actor.builder()
                        .id(8L)
                        .name("Angelina Jolie")
                        .build();

        actor2 = Actor.builder()
                        .id(9L)
                        .name("Jennifer Aniston")
                        .build();

        actor3 = ActorRest.builder()
                            .id(10L)
                            .name("Jim Parsons")
                            .build();

        actors.addAll(Arrays.asList(actor, actor2));

        actorRests = actors.stream().map(a -> modelMapper.map(a, ActorRest.class)).collect(Collectors.toList());

        netflixResponse = new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK, actorRests);
        createActorRestNetflixResponse = new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK, actor3);
        getActorByIdNetflixResponse = new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK, modelMapper.map(actor, ActorRest.class));
    }

    @Test
    void getActors_Success() throws Exception {
        Mockito.when(actorService.getActors()).thenReturn(actorRests);

        mockMvc.perform(get(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_ACTOR)
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(netflixResponse)))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    void getActorById_Success() throws Exception {

        ActorRest actorRest = modelMapper.map(actor, ActorRest.class);

        Mockito.when(actorService.getActorById(8L)).thenReturn(actorRest);

        mockMvc.perform(get(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_ACTOR + RestConstants.RESOURCE_ID, 8L)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().json(objectMapper.writeValueAsString(getActorByIdNetflixResponse)))
                .andExpect(status().isOk()).andDo(print());

        assertEquals(getActorByIdNetflixResponse.getData(), actorRest);
    }

    @Test
    void createNewActor_Success() throws Exception {

        Mockito.when(actorService.createNewActor(actor3)).thenReturn(actor3);

        mockMvc.perform(post(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_ACTOR)
                        .param(RestConstants.PARAMETER_ACTOR, String.valueOf(actor3))
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(toJson(actor3)))
                .andExpect(status().isOk()).andDo(print());


    }

}