package com.everis.d4i.tutorial.entities;

import java.io.Serializable;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "TV_SHOWS")
public class TvShow implements Serializable {

	private static final long serialVersionUID = 4916713904971425156L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SHORT_DESC", nullable = true)
	private String shortDescription;

	@Column(name = "LONG_DESC", nullable = true)
	private String longDescription;

	@Column(name = "YEAR")
	private Year year;

	@Column(name = "RECOMMENDED_AGE")
	private byte recommendedAge;

	@ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY, mappedBy = "tvShows")
	private List<Category> category;

	@Column(name = "ADVERTISING", nullable = true)
	private String advertising;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Season> seasons;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TVSHOW_ACTOR", joinColumns = {
			@JoinColumn(name = "TVSHOW_ID")},
			inverseJoinColumns = {
					@JoinColumn(name = "ACTOR_ID")
			}
	)
	private List<Actor> actors;

	@Column(name = "PRIZES", nullable = true)
	private String[] prizes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public byte getRecommendedAge() {
		return recommendedAge;
	}

	public void setRecommendedAge(byte recommendedAge) {
		this.recommendedAge = recommendedAge;
	}

	public String getAdvertising() {
		return advertising;
	}

	public void setAdvertising(String advertising) {
		this.advertising = advertising;
	}

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}

	public List<Category> getCategories() {
		return category;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public void setCategories(List<Category> category) {
		this.category = category;
	}

	public String[] getPrizes() {
		return prizes;
	}

	public void setPrizes(String[] prizes) {
		this.prizes = prizes;
	}


	//// HELPERS /////

	public void addToCategory(List<Category> categories) {
		this.category.addAll(categories);
		categories.forEach(c -> c.getTvShows().add(this));
	}
}
