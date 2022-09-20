package com.everis.d4i.tutorial.controllers;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ActorsWorkRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

import java.util.List;

public interface ActorController {

    NetflixResponse<List<ActorRest>> getActors() throws NetflixException;

    NetflixResponse<ActorRest> getActorById(Long id) throws NetflixException;

    NetflixResponse<ActorRest> createNewActor(ActorRest actorRest) throws NetflixException;

    NetflixResponse<ActorRest> editActor(Long id, ActorRest editedActorRest) throws NetflixException;

    NetflixResponse<?> deleteActorById(Long id) throws NetflixException;

    NetflixResponse<ActorsWorkRest> getSeriesAndChaptersByActorId(Long id) throws NetflixException;


}
