package com.everis.d4i.tutorial.services.impl;

import com.everis.d4i.tutorial.dto.SeriesAndChaptersOfActorDto;
import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.repositories.ActorRepository;
import com.everis.d4i.tutorial.services.ActorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

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
        newActor.setName(actorRest.getName());
        try {
            actorRepository.save(newActor);
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
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
    public SeriesAndChaptersOfActorDto getSeriesAndChaptersByActorId(Long id) throws NetflixException {
        try {
            Optional<Actor> actorOptional = actorRepository.findById(id);
            List<String> chapters = actorOptional.get().getChapters()
                                                                .stream()
                                                                .map(Chapter::getName)
                                                                .collect(Collectors.toList());
            List<String> tvShows = actorOptional.get().getTvShows()
                                                                .stream()
                                                                .map(TvShow::getName)
                                                                .collect(Collectors.toList());
            SeriesAndChaptersOfActorDto dto = new SeriesAndChaptersOfActorDto();
            dto.setActorName(actorOptional.get().getName());
            dto.setChapters(chapters);
            dto.setTvShows(tvShows);
            return dto;
        } catch (EntityNotFoundException entityNotFoundException) {
            throw new NotFoundException(entityNotFoundException.getMessage());
        }
    }
}
