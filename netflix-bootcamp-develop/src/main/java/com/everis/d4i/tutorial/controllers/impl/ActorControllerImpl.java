package com.everis.d4i.tutorial.controllers.impl;

import com.everis.d4i.tutorial.controllers.ActorController;
import com.everis.d4i.tutorial.dto.SeriesAndChaptersOfActorDto;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ActorRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;
import com.everis.d4i.tutorial.services.impl.ActorServiceImpl;
import com.everis.d4i.tutorial.utils.constants.CommonConstants;
import com.everis.d4i.tutorial.utils.constants.RestConstants;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(RestConstants.APPLICATION_NAME + RestConstants.API_VERSION_1 + RestConstants.RESOURCE_ACTOR)
public class ActorControllerImpl implements ActorController {

    @Autowired
    private ActorServiceImpl actorService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<List<ActorRest>> getActors() throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.getActors());
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorRest> getActorById(@PathVariable Long id) throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.getActorById(id));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorRest> createNewActor(
            @ApiParam(value = RestConstants.PARAMETER_ACTOR, required = true) @RequestBody @Valid final ActorRest actorRest)
            throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.createNewActor(actorRest));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<ActorRest> editActor(
            @PathVariable Long id,
            @ApiParam(value = RestConstants.PARAMETER_ACTOR, required = true) @RequestBody @Valid final ActorRest actorRest) throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.editActor(id, actorRest));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = RestConstants.RESOURCE_ID, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<?> deleteActorById(@PathVariable Long id) throws NetflixException {
        actorService.deleteActorById(id);
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstants.RESOURCE_ACTOR_SEASON_CHAPTERS, produces = MediaType.APPLICATION_JSON_VALUE)
    public NetflixResponse<SeriesAndChaptersOfActorDto> getSeriesAndChaptersByActorId(@PathVariable Long id) throws NetflixException {
        return new NetflixResponse<>(CommonConstants.SUCCESS, String.valueOf(HttpStatus.OK), CommonConstants.OK,
                actorService.getSeriesAndChaptersByActorId(id));
    }
}
