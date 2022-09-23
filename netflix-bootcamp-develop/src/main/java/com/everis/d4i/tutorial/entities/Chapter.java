package com.everis.d4i.tutorial.entities;

import lombok.*;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CHAPTERS")
public class Chapter implements Serializable {

	private static final long serialVersionUID = 8725949484031409482L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NUMBER")
	private short number;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DURATION")
	private short duration;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SEASON_ID", nullable = false)
	private Season season;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "CHAPTER_ACTOR", joinColumns = {
			@JoinColumn(name = "CHAPTER_ID")},
			inverseJoinColumns = {
					@JoinColumn(name = "ACTOR_ID")
			}
	)
	private List<Actor> actors;


}
