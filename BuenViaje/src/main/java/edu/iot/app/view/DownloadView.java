package edu.iot.app.view;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

// BeanNameViewResolver 가 이걸 찾아서 연결시켜준다/ forwarding이므로 model 객체 공유 가능
@Component("download") // 뷰 이름 생성 
public class DownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String path = (String) model.get("path");
		String type = (String) model.get("type");
		String fileName = (String) model.get("fileName");
		fileName = URLEncoder.encode(fileName, "utf-8"); // 이건 chrome에 맞춘거
														// request의 agent로 브라우저 속성알수있음
		
		File file = new File(path);
		
		// 응답 헤더 설정
		response.setContentType(type);
		response.setContentLength((int) file.length());	// file의 길이만큼 읽어들인다
		// attachment; 는 이 파일이 첨부파일임을 의미
		// 넘겨진 fileName은 저장시 디폴트로 갖게되는 파일 이름이 됨(아니면, 대화상자에 기본적으로 쓰여있는  이름)
		response.setHeader("Content-Disposition", 
								"attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		// FileUtil.copy(path, response.getOutputStream());
		FileUtils.copyFile(file, response.getOutputStream());
	}

}
