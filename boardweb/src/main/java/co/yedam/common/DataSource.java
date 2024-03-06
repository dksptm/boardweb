package co.yedam.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataSource {
	// DAO클래스를 대신하는 것.
	
	//sqlSession ==> sql을 실행시켜주는것.
	
	// 쿼리를 실행할 세션(SqlSession)을 만들어주는 팩토리를 리턴하는 메소드.
	public static SqlSessionFactory getInstance() {
//		String resource = "org/mybatis/example/mybatis-config.xml";
		String resource = "config/mybatis-config.xml";
		//자바로해야하지만 xml을 사용해서 태그로 간편하게 하기위함 -> my~~xml
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		return sqlSessionFactory;
	}

}
