package com.bkda.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bkda.common.Constants;
import com.bkda.dao.MediaDAO;
import com.bkda.dto.ErrorContent;
import com.bkda.dto.MediaDTO;
import com.bkda.dto.UploadFileResult;
import com.bkda.model.Media;
import com.bkda.model.User;

@Service
public class MediaService {
	
	private Logger log = LoggerFactory.getLogger(MediaService.class);
	
	// Maximum file size is 4 MB
	private static final long MAX_FILE_SIZE = 4194304;
	
	@Value("${storage.mediaFolderPath}")
	private String mediaFolderPath;
	
	@Autowired
	private MediaDAO mediaDAO;
	
	@Autowired
    private UserService userService;
	
	public MediaDTO getProductImage(long imageId) throws IOException, FileNotFoundException {
		FileInputStream fis = null;
		MediaDTO mediaDto = new MediaDTO();
		Media media = mediaDAO.findOne(imageId);
		File file = new File(mediaFolderPath + "/" + media.getFilename());
		int size = (int)file.length();
		byte[] content = new byte[size];
		fis = new FileInputStream(file);
		try {
			fis.read(content, 0, size);
			mediaDto.setContent(content);
			mediaDto.setMedia(media);
			return mediaDto;	
		} finally {
			fis.close();
		}
	}
	
	public UploadFileResult addProductImages(MultipartFile[] files, long userId, long productId) {
		
		List<ErrorContent> errors = new ArrayList<>();
		UploadFileResult result = new UploadFileResult();
		if(files.length > 5) {
			ErrorContent error = new ErrorContent(Constants.ERROR_EXCEED_MAXIMUM_NUMBER_OF_FILE_UPLOADED, "You can just upload five files concurrently");
			errors.add(error);
			result.setErrors(errors);
			result.setNumOfError(files.length);
			result.setNumOfSuccess(0);
			return result;
		}
		
		int numberOfSuccess = 0;
		User owner = userService.findUserById(userId);
		Date now = Date.from(Instant.now(Clock.systemUTC()));
		for(MultipartFile f: files) {
			String originalName = f.getOriginalFilename();
			long fileSize = f.getSize();
			
			if(fileSize > MAX_FILE_SIZE) {
				ErrorContent error = new ErrorContent(Constants.ERROR_EXCEED_FILE_SIZE, "File " + originalName + " too large(" + fileSize + " bytes), maximum allowed size is 4 MB");
				errors.add(error);
				continue;
			}
			
			FileOutputStream fos = null;
			try {
				byte[] content = f.getBytes();
				String extension = originalName.substring(originalName.lastIndexOf("."));
				String savedFileName = userId + "_" + now.getTime() + "." + extension;
				String savedFullFilename = mediaFolderPath + "/" + savedFileName;
				fos = new FileOutputStream(savedFullFilename);
				fos.write(content);
				Media media = new Media();
				media.setFilename(savedFileName);
				media.setType(f.getContentType());
				media.setExtension(extension);
				media.setUploadedTime(now);
				media.setUser(owner);
				media.setSize(fileSize);
				mediaDAO.save(media);
				numberOfSuccess++;
			} catch(IOException e) {
				e.printStackTrace();
				ErrorContent error = new ErrorContent(Constants.ERROR_STORE_FILE, e.getMessage());
				errors.add(error);
			} finally {
				try {
					if(fos != null)
						fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}
		
		result.setNumOfSuccess(numberOfSuccess);
		result.setNumOfError(errors.size());
		result.setErrors(errors);
		return result;
	}
	
}
