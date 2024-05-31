package com.bookMall.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;


@Controller
public class FileDownloadController {
	private static final String CURR_IMAGE_REPO_PATH = "C:\\shopping\\file_repo";
	private static final Logger log = LoggerFactory.getLogger(FileDownloadController.class);

	@RequestMapping("/download")
	protected void download(@RequestParam("goodsFileName") String fileName, @RequestParam("goodsId") String goods_id,
							HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath=CURR_IMAGE_REPO_PATH+"\\"+goods_id+"\\"+fileName;
		File image=new File(filePath);


		response.setHeader("Cache-Control","no-cache");
		String encodedFileName = URLEncoder.encode(fileName, "UTF-8");
		response.addHeader("Content-disposition", "attachment; fileName="+encodedFileName);
		FileInputStream in=new FileInputStream(image);
		byte[] buffer=new byte[1024*8];
		while(true){
			int count=in.read(buffer); //버퍼에 읽어들인 문자개수
			if(count==-1)  //버퍼의 마지막에 도달했는지 체크
				break;
			out.write(buffer,0,count);
		}
		in.close();
		out.close();
	}

	@RequestMapping("/thumbnails.do")
	protected void thumbnails(@RequestParam("goodsFileName") String fileName, @RequestParam("goodsId") String goods_id,
							  HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath = CURR_IMAGE_REPO_PATH + "\\" + goods_id + "\\" + fileName;
		File image = new File(filePath);
		log.info("CURR_IMAGE_REPO_PATH : " + CURR_IMAGE_REPO_PATH);
		log.info("image : " + image);

		if (image.exists()) {
			Thumbnails.of(image).size(121, 154).outputFormat("png").toOutputStream(out);
		}
		byte[] buffer = new byte[1024 * 8];
		out.write(buffer);
		out.close();
	}
}
