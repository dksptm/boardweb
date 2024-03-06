package co.yedam.common;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.yedam.reply.service.ReplyService;
import co.yedam.reply.service.ReplyServiceImpl;

public class RegisterCenter implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 요청정보의 스트림 읽어와서 바이트로.. 해서 객체타입으로 변경해야.
		// byte stream => 객체.(id,centerName,...) => 객체배열[]
		// 라이브러리를 사용해야 한다!(스피링코어, 잭슨코어)
		ServletInputStream sis = req.getInputStream();
		String json = StreamUtils.copyToString(sis, StandardCharsets.UTF_8); // byte->문자열로.(StreamUtils.copyToString: 스프링코어라이브러리)
		//System.out.println(json);
		
		// 문자열을 객체로 잭슨데이터바이든. 객체Center 클래스부터 생성.
		// mapper가 문자열을 객체로 변경해주는. 그런데 일단 배열임.
		ObjectMapper mapper = new ObjectMapper();
		Center[] list = mapper.readValue(json, Center[].class); // Center[] 는 Center배열이라서.
		ReplyService svc = new ReplyServiceImpl();
		
		int cnt = svc.addCenter(list);
		System.out.println(cnt);
		
		resp.getWriter().print(cnt);
		
	}

}
