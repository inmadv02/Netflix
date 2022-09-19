package com.everis.d4i.tutorial.json;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorRest implements Serializable {

    private Long id;

    private String name;

    private List<TvShowRest> tvShows;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TvShowRest> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<TvShowRest> tvShows) {
        this.tvShows = tvShows;
    }
}
