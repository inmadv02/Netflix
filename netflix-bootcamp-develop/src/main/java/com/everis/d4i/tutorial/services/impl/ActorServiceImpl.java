package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.json.ActorsWorkRest;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.repositories.ChapterRepository;
import com.everis.d4i.tutorial.services.ActorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ChapterRepository chapterRepository;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public List<ActorRest> getActors() throws NetflixException {
            return actorRepository.findAll()
                                        .stream()
                                        .map(actor -> modelMapper.map(actor, ActorRest.class))
                                        .collect(Collectors.toList());
    }

    @Override
    public ActorRest getActorById(Long id) throws NetflixException {
        try {
            return modelMapper.map(actorRepository.getOne(id), ActorRest.class);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }

    @Override
    public ActorRest createNewActor(ActorRest actorRest) throws NetflixException {
        Actor newActor = new Actor();
        List<TvShow> tvShowsList = actorRest.getTvShowRests()
                                                .stream()
                                                .map(tv -> modelMapper.map(tv, TvShow.class))
                                                .collect(Collectors.toList());

        newActor.setName(actorRest.getName());
        //tvShowsList.stream().map(tvShow -> tvShow.getActors().add(newActor));

        newActor = actorRepository.save(newActor);
        return modelMapper.map(newActor, ActorRest.class);
    }

    @Override
    public ActorRest editActor(Long id, ActorRest editedActorRest) throws NetflixException {
        try {
            actorRepository.getOne(id).setName(editedActorRest.getName());
            actorRepository.save(actorRepository.getOne(id));
            return modelMapper.map(actorRepository.getOne(id), ActorRest.class);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }

    @Override
    public void deleteActorById(Long id) throws NetflixException {
        try{
            actorRepository.deleteById(id);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }

    @Override
    public ActorsWorkRest getSeriesAndChaptersByActorId(Long actorId) throws NetflixException {
        ActorsWorkRest actorsWorkRest = new ActorsWorkRest();

        try {
            List<Chapter> chapters = chapterRepository.fetchAllChaptersOfActor(actorId);
            List<TvShow> tvShows = chapters.stream()
                                            .map(c -> c.getSeason().getTvShow())
                                            .collect(Collectors.toList());

            actorsWorkRest.setTvShowRests(tvShows
                                            .stream()
                                            .map(tv -> modelMapper.map(tv, TvShowRest.class))
                                            .collect(Collectors.toList()));
            actorsWorkRest.setChapterRests(chapters
                                            .stream()
                                            .map(ch -> modelMapper.map(ch, ChapterRest.class))
                                            .collect(Collectors.toList()));

            return actorsWorkRest;
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }


}
