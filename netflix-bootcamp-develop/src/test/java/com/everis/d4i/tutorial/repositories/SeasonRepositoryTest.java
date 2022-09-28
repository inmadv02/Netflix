package com.everis.d4i.tutorial.repositories;

import com.everis.d4i.tutorial.NetflixMain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {NetflixMain.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SeasonRepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByTvShowId() {
    }

    @Test
    void findByTvShowIdAndNumber() {
    }
}