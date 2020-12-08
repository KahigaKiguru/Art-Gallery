package com.sindhu.ArtGallery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "art_pieces")
public class Art {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "art_id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "artist")
	private String artist;
	
	@Lob
	@Column(name = "image")
	private String image;
	
	@Column(name = "price")
	private double price;
	
	@Column(name = "art_type")
	private String type;
	
	@Column(name = "description")
	private String description;

	public String getName() {
		return title;
	}

	public void setName(String name) {
		this.title = name;
	}
	
	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDesription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
