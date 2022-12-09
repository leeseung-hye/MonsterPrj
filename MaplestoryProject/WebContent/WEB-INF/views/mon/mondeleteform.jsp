<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제 페이지</title>
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="css/footer.css" rel="stylesheet" type="text/css">
<link href="css/mondeleteform.css" rel="stylesheet" type="text/css">
</head>
<body>
   <%@include file="/include/header.jsp"%>
   <div class="insert">
      <h2>몬스터 삭제</h2>
   </div>
   <div>
      <div class="mainbox">
         <div class="mainImg">
            <form action="MonDelete.do" method="post">
               <input type="hidden" name="monid" class="monid"
                  value="${param.monid}">
               <h2>삭제할 몬스터의 이름을 입력하세요.</h2>
               <br> 
               <input type="text" name="monname" class="input" id="search-input">
               <input type="submit" value="삭 제">
            </form>
         </div>
      </div>
   </div>

   <%@include file="/include/footer.jsp"%>
</body>
</html>