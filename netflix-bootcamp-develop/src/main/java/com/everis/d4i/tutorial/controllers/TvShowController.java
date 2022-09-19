package com.everis.d4i.tutorial.controllers;

import java.util.List;

import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.responses.NetflixResponse;

public interface TvShowController {

	NetflixResponse<List<TvShowRest>> getTvShowsByCategory(Long categoryId) throws NetflixException;

	NetflixResponse<TvShowRest> getTvShowById(Long id) throws NetflixException;

	NetflixResponse<TvShowRest> editTvShow(Long id, String newName) throws NetflixException;

	NetflixResponse<TvShowRest> addCategory(Long id, List<Category> categoryList) throws NetflixException;

	NetflixResponse<?> deleteTvShowById(Long id) throws NetflixException;

	NetflixResponse<String[]> getTvShowPrizes(Long id) throws NetflixException;

}
