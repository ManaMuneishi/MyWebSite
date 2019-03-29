<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
						<div class="col-sm-4">
							<div>${itemDetail.name}を本当に削除してよろしいでしょうか。</div>
							<div class="container">
								<div class="row">
									<div class="col-sm-5">
										<button type="button" class="btn btn-primary btn-block"
											onclick="history.back()">キャンセル</button>
									</div>
									<form action="MasterItemDeleteConf" method="post">
										<div class="col-sm-2"></div>
										<div class="col-sm-5">
											<button type="submit" class="btn btn-danger btn-block">OK</button>
										</div>
										<input type="hidden" name="itemId" value="${itemDetail.id}">
									</form>
								</div>
							</div>
						</div>
						</form>
						<div class="row">
							<div class="col s8 offset-s2">
								<p class="right-align">
								<div>
									<button type="button" class="btn btn-primary"
										onclick="history.back()">戻る</button>
								</div>
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