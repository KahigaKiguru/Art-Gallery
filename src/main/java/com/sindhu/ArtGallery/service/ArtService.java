package com.sindhu.ArtGallery.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sindhu.ArtGallery.model.Art;
import com.sindhu.ArtGallery.repository.ArtRepository;

@Service
public class ArtService {
	
	@Autowired
	private ArtRepository artRepository;
	
//	create, update, delete, get art pieces
	
	public Art createArt(MultipartFile file, Art art) throws IOException {
		
		art.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		art.setType(file.getContentType());
		
		return artRepository.save(art);
	}
	
	public void updateArt(Art art) {
		artRepository.save(art);
	}
	
	public void deleteArt(int art_id) {
		artRepository.deleteById(art_id);
	}
	
	public Iterable<Art> getAllPieces(){
		return artRepository.findAll();
	}
	
	public List<Art> getArtByArtist(String artist){
		return artRepository.findByArtist(artist);
	}
	
	public Art getArtById(int art_id) {
		return artRepository.findById(art_id).get();
	}

}
