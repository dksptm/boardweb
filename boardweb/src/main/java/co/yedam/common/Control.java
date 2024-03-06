package co.yedam.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// interface: 코드X. 메소드만 담고있음.
public interface Control {
	
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

}
