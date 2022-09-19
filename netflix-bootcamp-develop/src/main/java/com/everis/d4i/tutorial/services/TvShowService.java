package com.everis.d4i.tutorial.services;

import java.util.List;

import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;

public interface TvShowService {

	List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException;

	TvShowRest getTvShowById(Long id) throws NetflixException;

	TvShowRest editTvShow(Long id, String tvShowNewName) throws NetflixException;

	TvShowRest addCategory(Long id, List<Category> categoryList) throws NetflixException;

	void deleteTvShowById(Long id) throws NetflixException;

	String[] getTvShowPrizes(Long id) throws NetflixException;

}
