package com.everis.d4i.tutorial.dto;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Chapter;

import java.io.Serializable;
import java.util.List;

public class SeriesAndChaptersOfActorDto implements Serializable {

    private String actorName;
    private List<String> tvShows;
    private List<String> chapters;

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public List<String> getTvShows() {
        return tvShows;
    }

    public void setTvShows(List<String> tvShows) {
        this.tvShows = tvShows;
    }

    public List<String> getChapters() {
        return chapters;
    }

    public void setChapters(List<String> chapters) {
        this.chapters = chapters;
    }
}
