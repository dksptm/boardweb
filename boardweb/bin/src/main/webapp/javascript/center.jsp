<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공공데이터포털 API활용하기</title>
</head>
<body>
  <br>
  <label for="keyword">시도</label>
  <input type="text" name="" id="keyword">
  <button id="searchBtn">조회</button>

  <label for="searchSido">시도별 조회</label>
  <select name="" id="searchSido">
    <option value="all" selected>전체</option>
    <!-- <option value="" selected> 원래 value로 해야. 요즘엔그냥 이너텍스트로. selectd는 처음 브라우저열었을때</option> -->
  </select>

  <button id="registerData">데이터생성</button>
  
  <br><br>
  <div id="show">
    <table border="1">
      <thead id="title">
        <tr>
          <th>센터id</th>
          <th>센터명</th>
          <th>연락처</th>
          <th>지역</th>
        </tr>
      </thead>
      <tbody id="list">
      </tbody>
    </table>
  </div>
  <script src="center.js"></script>

</body>
</html>