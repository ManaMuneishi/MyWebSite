<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アイテム登録/入力内容確認</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<br>
	<br>
	<div class="container">
		<div class="row center">
			<h5 class=" col s12 light">入力内容確認</h5>
		</div>
		<div class="row">
			<div class="section"></div>
			<div class="col s6 offset-s3">
				<div class="card grey lighten-5">
					<div class="card-content">
						<form action="MasterItemUpdateConf" method="POST">
							<div class="row">
								<div class="input-field col s10 offset-s1"><!-- ここいる！！！！！！！ -->>
									<input type="text" name="item_id" value="${itemDetail.id}" readonly> <label>アイテムID</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s10 offset-s1">
									<input type="text" name="item_name" value="${itemDetail.name}" readonly> <label>商品名</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s10 offset-s1">
									<input type="text" name="item_price" value="${itemDetail.price}" readonly> <label>値段</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s10 offset-s1">
									<input type="text" name="item_file" value="${itemDetail.fileName}" readonly> <label>画像ファイル</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s10 offset-s1">
									<input type="text" name="item_detail" value="${itemDetail.detail}" readonly> <label>詳細</label>
								</div>
							</div>
							<div class="row">
								<div class="col s12">
									<p class="center-align">上記内容で登録してよろしいでしょうか?</p>
								</div>
							</div>
							<div class="row">
								<div class="col s6 center-align">
									<button class="btn  waves-effect waves-light" type="submit" name="confirm_button" value="cancel">修正</button>
								</div>//ここserにて分岐必要
								<div class="col s6 center-align">
									<button class="btn  waves-effect waves-light" type="submit" name="confirm_button" value="regist">登録</button>
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