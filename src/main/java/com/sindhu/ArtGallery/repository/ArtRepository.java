package com.sindhu.ArtGallery.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sindhu.ArtGallery.model.Art;

@Repository
public interface ArtRepository extends CrudRepository<Art, Integer> {
	
	List<Art> findByArtist(String name);

}
