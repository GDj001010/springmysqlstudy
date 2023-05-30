package com.gdu.app11.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UploadService {
	
	public void getUploadList(HttpServletRequest request, Model model);
	public int addUpload(MultipartHttpServletRequest multipartRequest);	// 첨부에선 HttpServletRequest가 아닌 MultipartHttpServletRequest를 사용한다.
	public void getUploadByNo(int uploadNo, Model model);
	// ajax처럼 처리 ResponseEntity
	public ResponseEntity<byte[]> display(int attachNo);
	public ResponseEntity<Resource> download(int attachNo, String userAgent);
	public ResponseEntity<Resource> downloadAll(int uploadNo);
	public int removeUpload(int uploadNo);
	public int updateUpload(MultipartHttpServletRequest multipartRequest);
}
