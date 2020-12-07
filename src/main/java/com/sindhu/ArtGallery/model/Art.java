package com.sindhu.ArtGallery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "art_pieces")
public class Art {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "art_id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Lob
	@Column(name = "art")
	private String art_image;
	
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "artist_id"))
	private Artist artist;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "description")
	private String desription;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDesription() {
		return desription;
	}

	public void setDesription(String desription) {
		this.desription = desription;
	}

	public int getId() {
		return id;
	}
	
	
}
