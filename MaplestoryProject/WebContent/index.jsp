<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Gowun+Dodum&display=swap"
	rel="stylesheet">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Black+Han+Sans&family=Gowun+Dodum&family=Kanit:wght@500&display=swap"
	rel="stylesheet">

<title>몬스터 컬랙션 관리</title>
<style>
* {
	font-family: 'Black Han Sans', sans-serif;
	font-family: 'Gowun Dodum', sans-serif;
	font-family: 'Kanit', sans-serif;
}

h1 {
	text-align: center;
	margin-top: 30px;
	font-size: 58px;
	color: darkorange;
	margin-bottom: -2rem;
}

.search-bar {
	width: 500px;
}

.myButton {
	box-shadow: inset 0px 1px 0px 0px #fce2c1;
	background: linear-gradient(to bottom, #ffc477 5%, #fb9e25 100%);
	background-color: #ffc477;
	border-radius: 6px;
	border: 1px solid #eeb44f;
	display: inline-block;
	cursor: pointer;
	color: #ffffff;
	font-family: 'Black Han Sans', sans-serif;
	font-family: 'Gowun Dodum', sans-serif;
	font-size: 18px;
	font-weight: bold;
	padding: 15px 184px;
	text-decoration: none;
	text-shadow: 0px 1px 0px #cc9f52;
}

.myButton:hover {
	background: linear-gradient(to bottom, #fb9e25 5%, #ffc477 100%);
	background-color: #fb9e25;
}

.myButton:active {
	position: relative;
	top: 1px;
}

.profile {
	margin: 0;
	position: absolute;
	top: 30%;
	left: 50%;
	transform: translate(-50%, -50%);
}

img {
	margin-top: 400px;
}
</style>
</head>
<body>
	<div class="profile" align="center">
		<img src="/css/images/1.jpg">
		<h1>
			<span>Monster Book Management</span>
		</h1>
		<form action="https://www.google.com/search" method="GET">
		<div class="mx-auto mt-5 search-bar input-group mb-3">
			<a href="MonList.do" class="myButton">몬스터 목록 관리</a>
		</div>
	</div>

</form>
</body>
</html>