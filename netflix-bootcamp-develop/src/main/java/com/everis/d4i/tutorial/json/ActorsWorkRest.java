package com.everis.d4i.tutorial.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActorsWorkRest implements Serializable {

    private List<TvShowRest> tvShowRests;
    private List<ChapterRest> chapterRests;
}
