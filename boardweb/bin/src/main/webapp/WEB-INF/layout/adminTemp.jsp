<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!-- http://localhost:8080/boardweb/myapp.admin.tiles => 지금 이 페이지로.. -->
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Admin Page</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="static/css/adminstyles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">      
        <!-- 헤더 잘라냄 -->
        <tiles:insertAttribute name="header" />
        
        <div id="layoutSidenav">
        
        	<!-- 메뉴 잘라냄 -->   
			<tiles:insertAttribute name="menu" />
			
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                    
                        <!-- 바디 잘라냄 (일부러 div 안쪽만. 그래야 레이아웃이 유지됨) -->   
						<tiles:insertAttribute name="body" />
                    </div>
                    
                </main>
				<!-- 푸터 잘라냄 -->
				<tiles:insertAttribute name="footer" />
            </div>
        </div>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="static/js/adminscripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <!-- 
        <script src="static/assets/demo/chart-area-demo.js"></script>
        <script src="static/assets/demo/chart-bar-demo.js"></script>
         -->
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <!-- 
        <script src="static/js/datatables-simple-demo.js"></script> 
        -->
        
    </body>
</html>

