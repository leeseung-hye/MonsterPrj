<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세 정보 페이지</title>
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="css/footer.css" rel="stylesheet" type="text/css">
<link href="css/mondetails.css" rel="stylesheet" type="text/css">
</head>
<body>
   <%@include file="/include/header.jsp"%>
   <div class="insert">
      <h3>몬스터 상세 정보</h3>
   </div>
   <div class="mainbox">
      <div class="main1">
         <h3>선택하신 몬스터의 현재 정보</h3>
         <c:if test="${not empty mon}">
            <table>
               <tr>
                  <th>성질</th>            
                  <th>정보</th>   
                  <th>모습</th>         
               </tr>
               <tr>
                  <td>몬스터아이디 </td>
                  <td>${mon.monId}</td>
                  <td rowspan="7"><img src="/css/images/mon=${mon.monId}.png"></td>                  
               </tr>
               <tr>
                  <td>몬스터명 </td>
                  <td>${mon.monName}</td>                  
               </tr>
               <tr>
                  <td>레벨 </td>
                  <td>${mon.monLevel}</td>                  
               </tr>
               <tr>
                  <td>나타나는 지역 </td>
                  <td>${mon.regionList}</td>                  
               </tr>
               <tr>
                  <td>몬스터 유형 </td>
                  <td>${mon.typeList}</td>                  
               </tr>
               <tr>
                  <td>몬스터 난이도</td>
                  <td>${mon.starList}</td>                  
               </tr>
               <tr>
                  <td>체감 난이도 </td>
                  <td>${mon.difficultyList}</td>                  
               </tr>
            </table>
            <div class="button">
            <a href="MonUpdate.do?monid=${mon.monId}">몬스터 정보 수정</a>
            <a href="MonDelete.do?monid=${mon.monId}">몬스터 삭제</a>
            <a href="MonList.do">돌아가기</a>
            </div>
         </c:if>
         <c:if test="${empty mon}">
            <!--  객체가 null일 경우 보여져야 할 내용 -->
            몬스터 정보가 없습니다.
         </c:if>
      </div>
   </div>
   <%@include file="/include/footer.jsp"%>   
</body>
</html>