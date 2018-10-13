package com.bkda.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkda.dao.MediaDAO;
import com.bkda.dao.ProductDAO;
import com.bkda.dto.ProductDTO;
import com.bkda.exception.BKDAServiceException;
import com.bkda.model.Media;
import com.bkda.model.Product;
import com.bkda.dto.ErrorContent;

@Service
public class ProductService {
	
	// 1M
	private static final int READ_BUFF_SIZE = 1048576;
	
	@Autowired
	private MediaDAO mediaDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	public Product addProduct(ProductDTO productDto) {
		List<ErrorContent> errors = new ArrayList<>();
		
		if(StringUtils.isBlank(productDto.getName())) {
			errors.add(new ErrorContent(1700, "Product's name missing"));
		}
		
		if(errors.size() > 0) {
			throw new BKDAServiceException(1000, "Bad request");
		}
		
		Product product = new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product = productDAO.save(product);
		return product;
	}
	
	public byte[] getProductImage(long imageId) throws IOException, FileNotFoundException {
		FileInputStream fis = null;
		Media media = mediaDAO.findOne(imageId);
		File file = new File(media.getFilename());
		int size = (int)file.length();
		byte[] content = new byte[size];
		fis = new FileInputStream(media.getFilename());
		try {
			fis.read(content, 0, size);
			return content;	
		} finally {
			fis.close();
		}
	}
}
