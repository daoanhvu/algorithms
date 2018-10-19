package com.bkda.controller;

import com.bkda.dto.MediaDTO;
import com.bkda.dto.ProductDTO;
import com.bkda.dto.UploadFileResult;
import com.bkda.dto.response.ContentResponse;
import com.bkda.model.Product;
import com.bkda.service.MediaService;
import com.bkda.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/medias")
public class MediaController {
	
	@Autowired
	private MediaService mediaService;
	
	@RequestMapping(path = "/upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ContentResponse<UploadFileResult>> uploadProductImage(
    		@RequestParam("userId") Long userId,
    		@RequestParam("productId") Long productId, 
    		@RequestParam("files") MultipartFile[] files) throws FileNotFoundException, IOException {
		UploadFileResult result = mediaService.addProductImages(files, userId, productId);
		ContentResponse<UploadFileResult> response = new ContentResponse<>(result);
		
    	return new ResponseEntity<>(response, HttpStatus.OK);
    }
	
	@RequestMapping(path = "/{mediaId}", method = RequestMethod.GET)
	@ResponseBody
    public HttpEntity<byte[]> getImage(@PathVariable("mediaId") long mediaId) throws FileNotFoundException, IOException {
    	MediaDTO mediaDto  = mediaService.getProductImage(mediaId);
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(new MediaType(mediaDto.getMedia().getType()));
    	headers.setContentLength(mediaDto.getMedia().getSize());
    	return new HttpEntity<byte[]>(mediaDto.getContent(), headers);
    }
	
}
