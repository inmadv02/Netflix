package com.everis.d4i.tutorial.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Data
@Table(name = "CATEGORIES")
public class Category implements Serializable {

	private static final long serialVersionUID = 180802329613616000L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinTable(name = "TVSHOW_CATEGORIES", joinColumns = {
			@JoinColumn(name = "TVSHOW_ID")},
			inverseJoinColumns = {
					@JoinColumn(name = "CATEGORY_ID")
			}
	)
	private List<TvShow> tvShows;




}
