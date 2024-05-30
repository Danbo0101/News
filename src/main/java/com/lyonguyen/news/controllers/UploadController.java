package com.lyonguyen.news.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.lyonguyen.news.models.CKEditorUploadResponse;

@RestController
public class UploadController {

	@Value("${webapp.uploadfolder}")
	String FODER;

	@PostMapping("/api/upload")
	public CKEditorUploadResponse upload(@RequestParam("upload") MultipartFile file) throws IOException {
//		try {
//			Thread.sleep(200);
//		} catch (InterruptedException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		CKEditorUploadResponse a = new CKEditorUploadResponse();
		if (file.isEmpty()) {
			a.setError("No file was selected for upload.");
			a.setUploaded(0);
			
			return a;
		}

		try {
			// Get the file name
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());

			// Define the upload directory relative to the application's current directory
			String uploadDir = FODER;

			// Get the absolute path of the upload directory
			String uploadPath = System.getProperty("user.dir") + File.separator + uploadDir;

			// Create the upload directory if it doesn't exist
			Path directory = Path.of(uploadPath);
			if (!Files.exists(directory)) {
				Files.createDirectories(directory);
			}

			// Save the uploaded file to the specified location
			Path filePath = directory.resolve(fileName);
			Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			
			a.setError("File uploaded successfully.");
			a.setUploaded(1);
			a.setUrl("/"+FODER+"/"+fileName);
			a.setFileName(fileName);
			return a;
		} catch (IOException e) {
			a.setError("Failed to upload the file: " + e.getMessage());
			a.setUploaded(0);
			return a;
		}
	}

}
