package com.sindhu.ArtGallery.controller;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.servlet.http.HttpServletResponse;
import javax.swing.text.AbstractDocument.Content;

import org.apache.commons.io.IOUtils;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sindhu.ArtGallery.model.Art;
import com.sindhu.ArtGallery.service.ArtService;


@Controller
public class MainController {
	
	@Autowired
	private ArtService artService;
	
	@GetMapping("/")
	public String indexPage(Model model) {
		
		
		model.addAttribute("art_pieces", artService.getAllPieces());
		return "index";
	}
	
	@ModelAttribute("art")
	public Art fruitBinding() {
		return new Art();
	}
	
	@GetMapping("/showArtPage")
	public String showArtPage(Model model) {
		model.addAttribute("art_list", artService.getAllPieces());
		return "gallery";
	}
	@GetMapping("/addArtPage")
	public String showAddArtPage() {
		return "art_add";
	}
	
	@PostMapping("/addArt")
	public String addArt(
			@RequestParam("file") MultipartFile file, 
			@ModelAttribute("art") Art art) throws IOException {
		
			artService.createArt(file, art);
			
		return "redirect:/?art_added";
	}
	
	  @GetMapping("/showEditArt") 
	  public String showUpdateArt(@RequestParam("art_id") int art_id, Model model) {
		  model.addAttribute("art", artService.getArtById(art_id));
		  return "art_update";
	  }
	 
	
	@PostMapping("/updateArt")
	public String updateArt(@RequestParam("art_id") int art_id, @ModelAttribute("art") Art at) {
		Art art = artService.getArtById(art_id);
		art.setName(at.getName());
		art.setArtist(at.getArtist());
		art.setDescription(at.getDescription());
		art.setPrice(at.getPrice());
		art.setTitle(at.getTitle());
		
		artService.updateArt(art);
		
		return "redirect:/?art_updated";
	}
	
	@GetMapping("/artistPage")
	public String showArtistPage(@RequestParam("art_id") int art_id, Model model) {
		String artist = artService.getArtById(art_id).getArtist();
		model.addAttribute("art_artist", artService.getArtByArtist(artist));
		model.addAttribute("artist", artist);
		return "artist_page";
	}
	
	@GetMapping("/deleteArt")
	public String deleteArt(@RequestParam("art_id") int art_id) {
		artService.deleteArt(art_id);
		return "redirect:/?art_deleted";
	}
	
	@GetMapping(value = "/buyArtPage", produces = "image/jpeg")
	public String showBuyArtPage(@RequestParam("art_id") int art_id, Model model) {
		model.addAttribute("art", artService.getArtById(art_id));
		return "art_buy";
	}
	@GetMapping(value = "/buyArt", produces = MediaType.IMAGE_JPEG_VALUE)
	public ResponseEntity<FileSystemResource> buyArt(@RequestParam("art_id") int art_id, HttpServletResponse response) throws IOException {
		
		Art art = artService.getArtById(art_id);
		byte[] decoded_art = Base64.getMimeDecoder().decode(art.getImage());
		
		java.io.File file = new java.io.File(art.getTitle());
		
		HttpHeaders header = new HttpHeaders();
	    header.setContentType(MediaType.parseMediaType(art.getType()));
	    header.setContentLength(decoded_art.length);
	    header.setContentDispositionFormData("attachment", art.getTitle()+".jpg");
		
	    return new ResponseEntity<FileSystemResource>( 
	    		new FileSystemResource(file), header, HttpStatus.OK);
				
			
	}
}
