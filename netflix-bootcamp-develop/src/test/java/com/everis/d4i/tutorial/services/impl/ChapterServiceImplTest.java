package com.everis.d4i.tutorial.services.impl;
import com.everis.d4i.tutorial.entities.Chapter;
import com.everis.d4i.tutorial.entities.Season;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.ChapterRest;
import com.everis.d4i.tutorial.repositories.ChapterRepository;
import com.everis.d4i.tutorial.repositories.SeasonRepository;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import javax.persistence.EntityNotFoundException;
import java.time.Year;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ChapterServiceImplTest {

    @Mock
    private ChapterRepository chapterRepository;

    @Mock
    private SeasonRepository seasonRepository;

    @Mock
    private TvShowRepository tvShowRepository;

    @InjectMocks
    private ChapterServiceImpl chapterService;

    @Mock
    ModelMapper modelMapper;

    static Chapter chapter;
    static TvShow tvShow;
    static Season season;

    static final String ENTITY_NOT_FOUND = "It doesn't exist an entity with that ID";

    @BeforeEach
    void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);

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
    public void isChapterEmpty(){
        when(chapterRepository.findById(8L)).thenReturn(Optional.of(chapter));
        assertEquals(chapterRepository.getOne(8L), chapter);
    }

    @Test
    public void editChapter_Success() throws NetflixException {
        String editedChapterName = "One";

        when(chapterRepository.getOne(anyLong())).thenReturn(chapter);
        chapter.setName(editedChapterName);
        when(chapterService.editChapter(anyLong(), anyString()))
                            .thenReturn(modelMapper.map(chapter, ChapterRest.class));
        assertEquals(chapterService.editChapter(8L, editedChapterName), modelMapper.map(chapter, ChapterRest.class));

    }

    @Test
    public void editChapter_thenThrowsNetflixException() throws NetflixException {
        String editedChapterName = "One";

        when(chapterRepository.getOne(56L)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> chapterService.editChapter(56L, editedChapterName));

    }

    @Test
    public void getChapterByTvShowIdAndSeasonNumberAndChapterNumber_Success(){

    }

}
