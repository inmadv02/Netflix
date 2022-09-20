package com.everis.d4i.tutorial.services;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ActorsWorkRest;

import java.util.List;

public interface ActorService {

    List<ActorRest> getActors() throws NetflixException;

    ActorRest getActorById(Long id) throws NetflixException;

    ActorRest createNewActor(ActorRest actorRest) throws NetflixException;

    ActorRest editActor(Long id, ActorRest editedActorRest) throws NetflixException;

    void deleteActorById(Long id) throws NetflixException;

    ActorsWorkRest getSeriesAndChaptersByActorId(Long id) throws NetflixException;

}
