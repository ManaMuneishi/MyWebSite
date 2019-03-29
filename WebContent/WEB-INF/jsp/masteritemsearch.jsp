<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>TOPページ</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<br> <br>
			<h1 class="header center red-text">登録商品検索</h1>
			<div class="row center">
				<h5 class="header col s12 light">何を探しましょうか</h5>
			</div>
			<div class="row center">
				<div class="input-field col s8 offset-s2">
					<form action="MasterItemSearch" method="POST">
						<i class="material-icons prefix">search</i> <input type="text" name="search_word">
					</form>
				</div>
			</div>
			<br> <br>

		</div>
		<div class="row">
			<div class="col s8 offset-s2">
				<h5 class="right-align">
					<a href="MasterNewItem">新規アイテム登録</a>
				</h5>
			</div>
		</div>
	</div>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>