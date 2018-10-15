package com.bkda.controller;

import com.bkda.dto.ContentResponse;
import com.bkda.dto.ProductDTO;
import com.bkda.model.Product;
import com.bkda.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> add(
            @RequestBody ProductDTO product,
            Pageable paging,
            @RequestHeader("Authorization") String authorization) {
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
    
    @RequestMapping(path = "/search", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> search(
            @RequestBody ProductDTO product,
            Pageable paging,
            @RequestHeader("Authorization") String authorization) {
    	List<Product> result = productService.search("");
    	ContentResponse<List<Product>> response = new ContentResponse<>();
    	response.setContent(result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
