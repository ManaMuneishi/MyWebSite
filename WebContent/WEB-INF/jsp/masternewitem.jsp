<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アイテム登録</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<br>
	<br>
	<div class="container">
		<div class="row center">
			<h5 class=" col s12 light">新規アイテム登録</h5>
			<c:if test="${validationMessage != null}">
				<P class="red-text">${validationMessage}</P>
			</c:if>
		</div>
		<div class="row">
			<div class="section"></div>
			<div class="col s6 offset-s3">
				<div class="card grey lighten-5">
					<div class="card-content">
						<form action="MasterNewItem" method="POST">
						<input type="hidden" name="itemId" value="${itemDetail.id }">

							<div class="row">
								<div class="input-field col s6">
									<input type="text" name="item_name" value="${itemDetail.name}"> <label>商品名</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<input type="text" name="item_price" value="${itemDetail.price}"> <label>値段</label>
								</div>
								<div class="input-field col s6">
									<input type="text" name="item_file" value="${itemDetail.fileName}"> <label>画像</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s12">
									<input type="text" name="item_detail" value="${itemDetail.detail}"> <label>詳細</label>
								</div>
							</div>

							<div class="row">
								<div class="col s12">
									<p class="center-align">
										<button class="btn btn-large waves-effect waves-light  col s8 offset-s2" type="submit" name="action">確認</button>
									</p>
								</div>
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