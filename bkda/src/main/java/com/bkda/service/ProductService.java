package com.bkda.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bkda.dao.MediaDAO;
import com.bkda.model.Media;

@Service
public class ProductService {
	
	// 1M
	private static final int READ_BUFF_SIZE = 1048576;
	
	@Autowired
	private MediaDAO mediaDAO;
	
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
