package com.everis.d4i.tutorial.repositories;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.everis.d4i.tutorial.NetflixMain;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.Season;
import com.everis.d4i.tutorial.entities.TvShow;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {NetflixMain.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ChapterRepositoryTest {

    @Autowired
    private ChapterRepository chapterRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    static TvShow tvShow;
    static Season season;
    static Chapter chapter;
    short num = 6;

    @BeforeEach
    void setUp() {
        tvShow = new TvShow();
        tvShow.setId(6L);
        tvShow.setName("Vampire Diaries");

        season = new Season();
        season.setId(5L);
        season.setNumber(num);
        season.setTvShow(tvShow);

        chapter = new Chapter();
        chapter.setId(8L);
        chapter.setSeason(season);
        chapter.setName("Chapter 1");
        testEntityManager.persist(chapter);

    }

    @Test
    public void testRepoNotNull() {
        assertThat(chapterRepository).isNotNull();
    }

    @Test
    public void findBySeasonTvShowIdAndSeasonNumber_Success() {
        List<Chapter> chapters = chapterRepository.findBySeasonTvShowIdAndSeasonNumber(6L, num);
        assertEquals(chapter.getId(), chapters.get(0).getId());

    }

}
