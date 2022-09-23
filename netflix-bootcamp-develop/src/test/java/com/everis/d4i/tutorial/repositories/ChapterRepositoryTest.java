package com.everis.d4i.tutorial.repositories;

import com.everis.d4i.tutorial.entities.Actor;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.Season;
import com.everis.d4i.tutorial.entities.TvShow;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChapterRepositoryTest {

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    static TvShow tvShow;
    static Season season;
    static Chapter chapter;

    @BeforeEach
    void initData(){

        tvShow = TvShow.builder()
                                .id(1L)
                                .build();

        season = Season.builder()
                                .number((short) 2)
                                .build();
        season.setTvShow(tvShow);
        testEntityManager.persist(tvShow);
        testEntityManager.persist(season);

        chapter =  Chapter.builder()
                            .name("Chapter 4")
                            .duration((short) 43)
                            .season(season)
                            .build();

        testEntityManager.persist(chapter);
    }

    @Test
    public void findBySeasonTvShowIdAndSeasonNumber_Success(){
        List<Chapter> chapters = chapterRepository
                                            .findBySeasonTvShowIdAndSeasonNumber(1L, (short) 2);
        assertEquals(chapter, chapters.get(1));

    }

}
