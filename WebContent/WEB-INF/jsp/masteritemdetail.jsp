<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="section no-pad-bot" id="index-banner">
		<div class="container">
			<br> <br>
			<div class="row center">
				<div class="col s4">
					<c:if test="${searchWord != null}">
						<a
							href="MasterItemSearchResult?search_word=${searchWord}&page_num=${pageNum}"
							class="btn waves-effect waves-light">検索結果へ </a>
					</c:if>
				</div>
				<div class="col s4">
					<h5 class=" col s12 light">商品詳細</h5>
				</div>
				<div class="col s4">
					<div class="row">
						<div class="col s12">
							<div class="col s6 center-align">
								<a href="MasterItemDelete?itemId=${item.id }"
									class="btn waves-effect waves-light">削除</a>
							</div>
							<div class="col s6 center-align">
								<a href="MasterItemUpdate?itemId=${item.id }"
									class="btn waves-effect waves-light">更新</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<br> <br>
			<div class="row">
				<div class="col s6">
					<div class="card">
						<div class="card-image">
							<!-- ここらあたりが写真 -->
							<img src="img/${item.fileName}">
							<!-- ここが写真の名前 -->
						</div>
					</div>
				</div>
				<div class="col s6">
					<h4>${item.name}</h4>
					<!-- 商品の名前 -->
					<h5>${item.price}円</h5>
					<!-- 値段 -->
					<p>${item.detail}</p>
					<!-- ここを商品名→詳細に変更しました -->
				</div>
			</div>
		</div>
		<jsp:include page="/baselayout/footer.jsp" />
	</div>
</body>
</html>