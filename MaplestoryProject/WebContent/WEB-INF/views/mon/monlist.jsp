<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="jk" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>몬스터 정보 창</title>
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="css/footer.css" rel="stylesheet" type="text/css">
<link href="css/monlist.css" rel="stylesheet" type="text/css">
</head>
<body>
   <%@include file="/include/header.jsp"%>
   <div class="insert">
      <h2>몬스터 검색기</h2>
   </div>
   <div class="mainbox">
      <div class="main1">
         <form method="get">
            <table>
               <tr>
                  <td class="search">
                     <select name="searchField">
                        <option value="regionlist">몬스터 지역</option>
                        <option value="monname">몬스터 이름</option>
                     </select> <input type="text" name="searchWord" />
                     <input type="submit" value=" 검 색 하 기" />
                  </td>
               </tr>
            </table>
         </form>
         <table>
            <tr>
               <th>몬스터 번호</th>
               <th>몬스터 나타나는 지역</th>
               <th>몬스터 레벨</th>
               <th>몬스터 타입</th>
               <th>몬스터 이름</th>
               <th>몬스터 별 갯 수</th>
               <th>몬스터 컬랙팅 난이도</th>
            </tr>
            <c:choose>
               <c:when test="${empty MonsterLists}">
                  <tr>
                     <td>등록된 몬스터가 없습니다.</td>
                  </tr>
               </c:when>
               <c:otherwise>
                  <c:forEach items="${MonsterLists}" var="mon" varStatus="loop">
                     <tr>
                        <td class="mid"><a href="MonDetails.do?monid=${mon.monId}">${mon.monId}</a></td>
                        <td>${mon.regionList}</td>
                        <td>${mon.monLevel}</td>
                        <td class="mid">${mon.typeList}</td>
                        <td>${mon.monName}</td>
                        <td class="mid">${mon.starList}</td>
                        <td class="mid">${mon.difficultyList}</td>
                     </tr>
                  </c:forEach>
               </c:otherwise>
            </c:choose>
         </table>
         <div class="bot_menu">
            <jk:paging totalPageCount="${totalPageCount}" nowPage="${nowPage}" />
            <a href="MonInsert.do">새로운 몬스터 입력</a>
            <p>
         </div>
      </div>
   </div>
   <%@include file="/include/footer.jsp"%>
</body>
</html>