package edu.iot.app.view;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

// BeanNameViewResolver 가 이걸 찾아서 연결시켜준다/ forwarding이므로 model 객체 공유 가능
@Component("fileView") // 뷰 이름 생성 
public class FileView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String path = (String) model.get("path");
		String type = (String) model.get("type");
		
		File file = new File(path);
		
		// 응답 헤더 설정
		response.setContentType(type);
		response.setContentLength((int) file.length());	// file의 길이만큼 읽어들인다
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		// FileUtil.copy(path, response.getOutputStream());
		FileUtils.copyFile(file, response.getOutputStream());
	}

}
