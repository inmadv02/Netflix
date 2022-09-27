package com.everis.d4i.tutorial.repositories;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import com.everis.d4i.tutorial.NetflixMain;

@DataJpaTest
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration(classes = {NetflixMain.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TvShowRepositoryTest {
}
