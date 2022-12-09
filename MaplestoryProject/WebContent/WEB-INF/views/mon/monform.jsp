<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>몬스터 입력 페이지</title>
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="css/footer.css" rel="stylesheet" type="text/css">
<link href="css/monform.css" rel="stylesheet" type="text/css">
</head>
<body>
   <%@include file="/include/header.jsp"%>
   <div class="insert">
      <h2>몬스터 정보 입력 양식</h2>
   </div>
   <form action="MonInsert.do" method="post">
      <div class="mainbox">
            <div class="main1">
               <h3>새로운 몬스터의 정보를 입력하세요.</h3>
               <table>
               <tr>
                     <th>성질</th>            
                     <th>정보</th>            
                  </tr>
                  <tr>
                     <td class="category">몬스터 아이디</td>
                     <td><input type="number" name="monId"></td>
                  </tr>
                  <tr>
                     <td class="category">몬스터 서식지</td>
                     <td><select name="regionList">
                           <c:forEach var="rg" items="${rList}">
                              <option value="${rg.region}">${rg.region}</option>
                           </c:forEach>
                     </select></td>
                  </tr>
                  <tr>
                     <td class="category">몬스터 레벨</td>
                     <td><input type="number" name="monLevel"></td>
                  </tr>
                  <tr>
                     <td class="category">몬스터 타입</td>
                     <td><select name="typeList">
                           <c:forEach var="tp" items="${tList}">
                              <option value="${tp.type}">${tp.type}</option>
                           </c:forEach>
                     </select></td>
                  </tr>
                  <tr>
                     <td class="category">몬스터 이름</td>
                     <td><input type="text" name="monName"></td>
                  </tr>
                  <tr>
                     <td class="category">몬스터 별 갯수</td>
                     <td><select name="starList">
                           <c:forEach var="st" items="${sList}">
                              <option value="${st.star}">${st.star}</option>
                           </c:forEach>
                     </select></td>
                  </tr>
                  <tr>
                     <td class="category">몬스터 난이도</td>
                     <td><select name="difficultyList">
                           <c:forEach var="df" items="${dList}">
                              <option value="${df.difficulty}">${df.difficulty}</option>
                           </c:forEach>
                     </select></td>
                  </tr>
                  <tr>
                     <td colspan="2" class="button">
                        <input type="submit" value=" 저 장 "> 
                        <input type="reset" value=" 취 소 ">
                     </td>
                  </tr>
               </table>
               <a href="MonList.do">돌아가기</a>
            </div>
         </div>
   </form>
   <%@include file="/include/footer.jsp"%>
</body>
</html>