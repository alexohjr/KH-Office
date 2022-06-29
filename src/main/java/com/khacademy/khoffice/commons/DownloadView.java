package com.khacademy.khoffice.commons;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

@Component
public class DownloadView extends AbstractView {

	public DownloadView() {
		setContentType("application/download; charset=utf-8"); // 빈으로 생성될 때 자동호출됨
	}

	@Override
	protected void renderMergedOutputModel(
		Map<String, Object> model, HttpServletRequest request,
		HttpServletResponse response
	) throws Exception {
		File file = (File) model.get("downloadFile"); // 모델에서 이름으로 모델객체 꺼내오기

		response.setContentType(getContentType());
		response.setContentLength((int) file.length());

		String userAgent = request.getHeader("User-Agent");

		// 다운로드받는 파일이름 깨지지 않도록 처리
		boolean ie = userAgent.indexOf("MSIE") > -1; // ie 인 경우만 true
		String fileName = null;
		if (ie) {
			fileName = URLEncoder.encode(file.getName(), "utf-8");
		} else {
			fileName = new String(file.getName().getBytes("utf-8"), "iso-8859-1");
		}
		
		// 원본 파일명으로 만들기
		int index = fileName.indexOf("_"); 
		String downloadFileName = fileName.substring(index+1);
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + downloadFileName + "\";"); // 무조건 특정 파일이	름으로 다운받게  만들기
		response.setHeader("Content-Transfer-Encoding", "binary");
		OutputStream out = response.getOutputStream();

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException ex) {
				}
			}
		}
		out.flush(); // 남은 데이터를 전송하고 close 하기
	}
}






