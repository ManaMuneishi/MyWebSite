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
							<div class="row">
								<div class="col s12">
									<p class="center-align">
									<a class="btn btn-primary" href="MasterItemSearch">アイテム管理</a>
									</p>
								</div>
							</div>
							<div class="row">
								<div class="col s12">
									<p class="center-align">
									<a class="btn btn-primary" href="">ユーザ管理</a>//ここあとでたす
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