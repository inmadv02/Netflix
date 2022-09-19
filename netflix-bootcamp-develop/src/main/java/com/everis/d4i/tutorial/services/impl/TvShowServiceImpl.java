package com.everis.d4i.tutorial.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import com.everis.d4i.tutorial.entities.Category;
import com.everis.d4i.tutorial.entities.TvShow;
import com.everis.d4i.tutorial.json.CategoryRest;
import com.everis.d4i.tutorial.repositories.CategoryRepository;
import com.everis.d4i.tutorial.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.d4i.tutorial.exceptions.NetflixException;
import com.everis.d4i.tutorial.exceptions.NotFoundException;
import com.everis.d4i.tutorial.json.TvShowRest;
import com.everis.d4i.tutorial.repositories.TvShowRepository;
import com.everis.d4i.tutorial.services.TvShowService;

@Service
public class TvShowServiceImpl implements TvShowService {

	@Autowired
	private TvShowRepository tvShowRepository;

	@Autowired
	private CategoryService categoryService;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public List<TvShowRest> getTvShowsByCategory(Long categoryId) throws NetflixException {
		return tvShowRepository.findByCategoryId(categoryId).stream()
				.map(tvShow -> modelMapper.map(tvShow, TvShowRest.class)).collect(Collectors.toList());
	}

	@Override
	public TvShowRest getTvShowById(Long id) throws NetflixException {
		try {
			return modelMapper.map(tvShowRepository.getOne(id), TvShowRest.class);
		} catch (EntityNotFoundException entityNotFoundException) {
			throw new NotFoundException(entityNotFoundException.getMessage());
		}
	}

	@Override
	public TvShowRest editTvShow(Long id, String tvShowNewName) throws NetflixException {
		try {
			TvShow editedTvShow = tvShowRepository.getOne(id);
			editedTvShow.setName(tvShowNewName);
			tvShowRepository.save(editedTvShow);
			return modelMapper.map(editedTvShow, TvShowRest.class);
		} catch (EntityNotFoundException entityNotFoundException){
			throw new NotFoundException(entityNotFoundException.getMessage());
		}
	}

	@Override
	public TvShowRest addCategory(Long id, List<Category> categoryList) throws NetflixException {
		try {
			tvShowRepository.getOne(id).addToCategory(categoryList);
			categoryService.saveCategories(categoryList);
			tvShowRepository.save(tvShowRepository.getOne(id));
			return modelMapper.map(tvShowRepository.getOne(id), TvShowRest.class);
		} catch (EntityNotFoundException entityNotFoundException){
			throw new NotFoundException(entityNotFoundException.getMessage());
		}
	}

	@Override
	public void deleteTvShowById(Long id) throws NetflixException {
		try {
			tvShowRepository.deleteById(id);
		} catch (EntityNotFoundException entityNotFoundException){
			throw new NotFoundException(entityNotFoundException.getMessage());
		}
	}

	@Override
	public String[] getTvShowPrizes(Long id) throws NetflixException {
		try {
			return tvShowRepository.getOne(id).getPrizes();
		} catch (EntityNotFoundException entityNotFoundException){
			throw new NotFoundException(entityNotFoundException.getMessage());
		}
	}

}
