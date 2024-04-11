package com.lcwd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lcwd.helper.FileUploadHelper;

@RestController
public class FileUploadController {

	@Autowired
	private FileUploadHelper fileUploadHelper;

	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadfile(@RequestParam ("file") MultipartFile file){
				System.out.println(file.getOriginalFilename());
				System.out.println(file.getSize());
				System.out.println(file.getContentType());
				System.out.println(file.getName());

		try {

			if(file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must content file");
			}
			if(!file.getContentType().equals("text/plain")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only txt content type are allowed");
			}

			// file upload ...
			boolean f =	fileUploadHelper.uploadFile(file);
			if(f) {
			//	return ResponseEntity.ok("File is successfully upload");
				
				// ServletUriComponentsBuilder.fromCurrentContextPath() ==> its url localhost:8080
			return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/image").path(file.getOriginalFilename()).toUriString());
			}

		} catch (Exception e) {

		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong...");
	}
}
