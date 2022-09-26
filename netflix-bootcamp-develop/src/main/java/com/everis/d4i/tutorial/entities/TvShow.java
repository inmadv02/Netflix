package com.everis.d4i.tutorial.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;
import java.util.List;
import java.util.Objects;


@Entity
@Data
@EqualsAndHashCode
@NoArgsConstructor
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

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "tvShows")
	private List<Category> category;

	@Column(name = "ADVERTISING", nullable = true)
	private String advertising;

	@OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Season> seasons;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinTable(name = "TVSHOW_AWARDS", joinColumns = {
			@JoinColumn(name = "TVSHOW_ID")},
			inverseJoinColumns = {
					@JoinColumn(name = "AWARD_ID")
			}
	)
	private List<Award> awards;

	public TvShow(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	//// HELPERS /////

	public void addToCategory(List<Category> categories) {
		this.category.addAll(categories);
		categories.forEach(c -> c.getTvShows().add(this));
	}

}
