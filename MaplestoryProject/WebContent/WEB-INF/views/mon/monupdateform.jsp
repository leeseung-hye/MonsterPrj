<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>몬스터 정보 수정 페이지</title>
<link href="css/header.css" rel="stylesheet" type="text/css">
<link href="css/footer.css" rel="stylesheet" type="text/css">
<link href="css/monupdateform.css" rel="stylesheet" type="text/css">
</head>
<body>
   <%@include file="/include/header.jsp"%>
   <div class="insert">
      <h2>몬스터 정보 입력 양식</h2>
   </div>
   <form action="MonUpdate.do" method="post">
      <div class="mainbox">
         <div class="middle">
            <div class="main1">
               <h3>몬스터의 변경할 정보를 입력하세요.</h3>
               <table>
               <tr>
                     <th>성질</th>            
                     <th>정보</th>            
                  </tr>
                  <tr>
                     <td class="category">몬스터아이디</td>
                     <td><input type="number" name="monId" value="${mon.monId}" readonly></td>
                  </tr>
                  <tr>
                     <td class="category">몬스터명</td>
                     <td><input type="text" name="monName" value="${mon.monName}"></td>
                  </tr>
                  <tr>
                     <td class="category">레벨</td>
                     <td><input type="number" name="monLevel"
                        value="${mon.monLevel}"></td>
                  </tr>
                  <tr>
                     <td class="category">나타나는 지역</td>
                     <td><select name="regionList">
                           <c:forEach var="rg" items="${rList}">
                              <option value="${rg.region}"
                                 <c:if test="${mon.regionList==rg.region}">selected
                                 </c:if>>
                              ${rg.region}</option>
                           </c:forEach>
                     </select></td>
                  </tr>
                  <tr>
                     <td class="category">몬스터 타입</td>
                     <td><select name="typeList">
                           <c:forEach var="tp" items="${tList}">
                              <option value="${tp.type}"
                                 <c:if test="${mon.typeList==tp.type}">selected
                        </c:if>>${tp.type}</option>
                           </c:forEach>
                     </select></td>
                  </tr>
                  <tr>
                     <td class="category">몬스터 난이도</td>
                     <td><select name="starList">
                           <c:forEach var="st" items="${sList}">
                              <option value="${st.star}"
                                 <c:if test="${mon.starList==st.star}">selected
                        </c:if>>${st.star}</option>
                           </c:forEach>
                     </select></td>
                  </tr>
                  <tr>
                     <td class="category">체감 난이도</td>
                     <td><select name="difficultyList">
                           <c:forEach var="df" items="${dList}">
                              <option value="${df.difficulty}"
                                 <c:if test="${mon.difficultyList==df.difficulty}">selected
                        </c:if>>${df.difficulty}</option>
                           </c:forEach>
                     </select></td>
                  </tr>
                  <tr>
                     <td colspan=2 class="button">
                     <input type="submit" value="저 장"> 
                     <input type="reset" value="취 소"></td>
                  </tr>
               </table>
            </div>
         </div>
      </div>
   </form>
   <%@include file="/include/footer.jsp"%>
</body>
</html>