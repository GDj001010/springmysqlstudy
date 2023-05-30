package com.gdu.app13.util;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Matcher;

import org.springframework.stereotype.Component;



@Component
public class MyFileUtil {

	// 매일 서버 안에 저장할 폴더를 새로 만든다 
	// 경로 구분자 
	private String sep = Matcher.quoteReplacement(File.separator);	// separator 자바가 경로 구분할 때 사용
	
	// String path 만들기
	public String getPath() {
		LocalDate now = LocalDate.now();
		// 02는 2자리 d는 정수 %는 기본값
		// 루트/storage/2023/05/08
		return "/storage" + sep + now.getYear() + sep + String.format("%02d", now.getMonthValue()) + sep + String.format("%02d", now.getDayOfMonth());
	}
	
	// String summernoteImagePath 만들기
	public String getSummernoteImagePath() {
	  return "/storage" + sep + "summernote";
	}
	
	// String filesystemName 만들기
	public String getFilesystemName(String originName) {
		
		// 원래 첨부 파일의 확장자 꺼내기
		String extName = "";
		
		// 확장자에 마침표(.)가 포함된 예외 상황 처리
		if(originName.endsWith("tar.gz")) {
			extName = "tar.gz";
		} else {
			// String.split(정규식)
			// 정규식에서 마침표(.)는 모든 문자를 의미하므로 이스케이프 처리하거나 문자 클래스로 처리한다.
			// 이스케이프 처리 : \.
			// 문자 클래스 처리 : [.]
			String[] array = originName.split("\\.");
			extName = array[array.length - 1];
		}
		
		// 결과 반환
		// UUID.extName
		// replace  모든 "-" 을 ""(빈문자열)로 바꿔라
		// UUID란 네트워크상에서 중복되지 않은 ID를 만들기 위한 값.
		return UUID.randomUUID().toString().replace("-", "") + "." + extName ;
	}

	// String tempPath 만들기
	public String getTempPath() {
		
		// 임시 파일이 저장될 경로는 어딘가.
		return "/storage" + sep + "temp";
		
	}
	
	// String tempfileName (임시파일 이름) zip 파일
	public String getTempfileName() {
		return UUID.randomUUID().toString().replace("-", "") + ".zip";
	}
	
	// String yesterdayPath 만들기
	public String getYesterdayPath() {
		LocalDate date = LocalDate.now();
		date.minusDays(1);	// 1일 전
		return "/storage" + sep + date.getYear() + sep + String.format("%02d", date.getMonthValue()) + sep + String.format("%02d", date.getDayOfMonth());
	}
	
	
	
	
	
	
	
	
	
	
}

