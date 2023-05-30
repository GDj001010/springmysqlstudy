package com.gdu.app11.batch;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.app11.domain.AttachDTO;
import com.gdu.app11.mapper.UploadMapper;
import com.gdu.app11.util.MyFileUtil;

import lombok.AllArgsConstructor;

@EnableScheduling
@Component
@AllArgsConstructor
public class RemoveWrongfileScheduler {

	// field
	private MyFileUtil myFileUtil;
	private UploadMapper uploadMapper;
	
	@Scheduled(cron="0 0 2 1/1 * ?") // www.cronmaker.com 매일 새벽 2시 정보에서 마지막 필드 *를 지워준다.
	public void execute() {
		
		// 어제 업로드 된 첨부 파일들의 정보 (DB에서 가져오기)
		List<AttachDTO> attachList = uploadMapper.getAttachListInYersterday();
		
		// List<AttachDTO> -> List<Path>로 변환하기 (Path : 경로 + 파일명)
		List<Path> pathList = new ArrayList<Path>();;
		if(attachList != null && attachList.isEmpty() == false) {
			for(AttachDTO attachDTO : attachList) {
				pathList.add(new File(attachDTO.getPath(), attachDTO.getFilesystemName()).toPath());	// Path 만드는 법 : File객체.toPath();
				if(attachDTO.getHasThumbnail() == 1) {
					pathList.add(new File(attachDTO.getPath(), "s_" + attachDTO.getFilesystemName()).toPath());
				}
			}
		}
		
		// 어제 업로드 된 경로
		String yesterdayPath = myFileUtil.getYesterdayPath();
		
		// 어제 업로드 된 파일 목록(HDD에서 확인) 중에서 DB에는 정보가 없는 파일 목록
		File dir = new File(yesterdayPath);
		File[] wrongFiles = dir.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {	// accept : 허용 > true를 반환하면 File[] wrongFiles에 포함된다. 매개변수 File dir, String name은 HDD에 저장된 파일을 의미한다.
				// pathList 			 :  DB에 있는 목록이다.		- 이미 Path
				// File dir, String name :  HDD에 있는 파일이다.	- File.toPath() 처리로 Path로 변경 후 둘을 비교한다.
				return pathList.contains(new File(dir, name).toPath()) == false;
			}
		});
		
		// File[] wrongFiles 모두 삭제
		if(wrongFiles != null && wrongFiles.length != 0) {
			for(File wrongFile : wrongFiles) {
				wrongFile.delete();
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
