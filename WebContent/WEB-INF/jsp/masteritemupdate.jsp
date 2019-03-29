<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アイテム情報</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<br>
	<br>
	<div class="container">
		<div class="row center">
			<h5 class=" col s12 light">更新アイテム</h5>
		</div>
		<div class="row">
			<div class="col s12">
				<div class="card grey lighten-5">
					<div class="card-content">
						<form action="MasterItemUpdate" method="POST">
							<br> <br>
							<input type="hidden" name="itemId" value="${itemDetail.id }">
							<div class="row">
								<div class="input-field col s6">
									<input type="text" name="item_name" value="${itemDetail.name}">
									<label>商品名</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<input type="text" name="item_price" value="${itemDetail.price}">
									<label>値段</label>
								</div>
								<div class="input-field col s6">
									<input type="text" name="item_file" value="${itemDetail.fileName}">
									<label>画像</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s12">
									<input type="text" name="item_detail" value="${itemDetail.detail}">
									<label>詳細</label>
								</div>
							</div>
							<div class="col s12">
								<button class="btn  waves-effect waves-light  col s4 offset-s4"
									type="submit" name="action">更新するー</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/baselayout/footer.jsp" />
</body>
</html>