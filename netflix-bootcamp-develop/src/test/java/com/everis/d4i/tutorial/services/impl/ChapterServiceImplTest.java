package com.everis.d4i.tutorial.services.impl;

import java.time.Year;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.everis.d4i.tutorial.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.Season;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.repositories.ChapterRepository;
import com.everis.d4i.tutorial.repositories.SeasonRepository;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ChapterServiceImplTest {

    @Mock
    private ChapterRepository chapterRepository;
    @InjectMocks
    private ChapterServiceImpl chapterService;
    @Mock
    ModelMapper modelMapper;

    static Chapter chapter;
    static TvShow tvShow;
    static Season season;

    @BeforeEach
    void setUp() throws Exception {

        tvShow = new TvShow();
        tvShow.setId(6L);
        tvShow.setName("Vampire Diaries");
        tvShow.setRecommendedAge((byte) 16);
        tvShow.setYear(Year.of(2020));

        season = new Season();
        season.setId(5L);
        season.setName("Season 4");
        season.setNumber((short) 4);
        season.setTvShow(tvShow);

        chapter = new Chapter();
        chapter.setId(8L);
        chapter.setSeason(season);
        chapter.setName("Chapter 1");
        chapter.setDuration((short) 45);

    }

    @Test
    public void editChapter_Success() throws NetflixException {
        String editedChapterName = "One";

        Mockito.when(chapterRepository.getById(Mockito.anyLong())).thenReturn(chapter);
        chapter.setName(editedChapterName);
        assertEquals(chapterService.editChapter(8L, editedChapterName), modelMapper.map(chapter, ChapterRest.class));

    }

    @Test
    public void editChapter_thenThrowsNetflixException() {
        String editedChapterName = "One";
        Mockito.when(chapterRepository.getById(56L)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> chapterService.editChapter(56L, editedChapterName));

    }


}
