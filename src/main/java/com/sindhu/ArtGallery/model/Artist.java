package com.sindhu.ArtGallery.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "artist")
public class Artist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "artist_id")
	private int id;
	
	@Column(name = "artist")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "artist")
	private List<Art> pieces;
	
	
}
