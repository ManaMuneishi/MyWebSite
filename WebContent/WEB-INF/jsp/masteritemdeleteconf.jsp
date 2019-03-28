<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<jsp:include page="/baselayout/head.html" />
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<div class="row">
			<div class="section"></div>
			<div class="col s6 offset-s3">
				<div class="card grey lighten-5">
					<div class="card-content">
						<form action="LoginResult" method="POST">
							 <div class="col-sm-4">
			この辺りかえる	<form>
					<form class="dropdown-menu p-4">
  						<div class="form-group">
   							<label for="exampleDropdownFormEmail2">itemID:</label>
   							<input class="form-control input-sm" type="email" class="form-control" id="exampleDropdownFormEmail2">

   							<div>を本当に削除してよろしいでしょうか。
 							</div>
 							 <div class="container">
 								<div class="row">

									<div class="col-sm-5">
										<button type="button" class="btn btn-primary btn-block" onclick="history.back()">キャンセル</button>
									</div>
									<div class="col-sm-2">
									</div>
									<div class="col-sm-5">
										<button type="button" class="btn btn-danger btn-block">OK</button>
									</div>

								</div>
							</div>
						</div>
 					</form>
							<div class="row">
								<div class="col s8 offset-s2">
									<p class="right-align">
										<a href="itemRegist">戻る</a> ここかえた!
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