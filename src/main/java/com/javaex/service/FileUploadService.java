package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	public String restore(MultipartFile file) {
		// 파일 정보 수집
		String saveDir = "D:\\javaStudy\\upload";
		String orgName = file.getOriginalFilename(); // 원래 파일 이름
		String exName = orgName.substring(orgName.lastIndexOf(".")); // 확장자
		String saveName = UUID.randomUUID().toString() + exName; // 지정한 파일 이름
		String filePath = saveDir + "\\" + saveName; // 파일위치(패스)
		long fileSize = -file.getSize(); // 사이즈

		// 파일 카피
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(saveDir + "/" + saveName);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			bos.write(fileData);

			if (bos != null) {
				bos.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return saveName;
	}
}
