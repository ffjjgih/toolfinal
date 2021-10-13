<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sắp xếp lịch thi</title>
<link rel="stylesheet" href="./views/assets/upLoadDiem.css">
<link rel="stylesheet" href="./views/assets/base.css">
<link rel="stylesheet" href="./views/assets/grid.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>

<body>
	<div id="app">
		<header class="header">
			<div class="grid wide">
				<a href="/Toolpdt/Home"><img class="header_img"
					src="./views/assets/image/Poly.png" alt=""></a> <img
					class="header_icon_user" src="./views/assets/image/user.png" alt="">
				<button class="header_right-btn_user">Đăng xuất</button>
			</div>
		</header>
		<div class="container">
			<div class="row">
				<div class="col"></div>
				<c:if test="${error!=null }">
					<h3 style="color: red">${error}</h3>
				</c:if>
				<div class="col container_middle">
					<h1 class="h1 container_header">UPLOAD FILE ĐIỂM</h1>
					<%
					try {
						int data = Integer.parseInt(session.getAttribute("value").toString());
						if ((data == 1) || (data == 2) || (data == 3)) {
							out.print("<div class=\"container_alert\">\r\n"
							+ "							<div class=\"alert alert-danger container_alert-popup\"\r\n"
							+ "								role=\"alert\">Xin mời chọn file</div>\r\n"
							+ "						</div>");
						} else if (data == 5) {
							out.print("<div class=\"container_alert\">\r\n"
							+ "							<div class=\"alert alert-danger container_alert-popup\"\r\n"
							+ "								role=\"alert\">Không phải định dạng file điểm Online, hãy thử lại</div>\r\n"
							+ "						</div>");
						} else if (data == 6) {
							out.print("<div class=\"container_alert\">\r\n"
							+ "							<div class=\"alert alert-danger container_alert-popup\"\r\n"
							+ "								role=\"alert\">Không phải định dạng file điểm danh, hãy thử lại</div>\r\n"
							+ "						</div>");
						} else if (data == 7) {
							out.print("<div class=\"container_alert\">\r\n"
							+ "							<div class=\"alert alert-danger container_alert-popup\"\r\n"
							+ "								role=\"alert\">Không phải định dạng file điểm Quiz, hãy thử lại</div>\r\n"
							+ "						</div>");
						} else if (data == 8) {
							out.print("<div class=\"container_alert\">\r\n"
							+ "							<div class=\"alert alert-danger container_alert-popup\"\r\n"
							+ "								role=\"alert\">Lớp và Môn học không tồn tại trong kế hoạch thi kì này</div>\r\n"
							+ "						</div>");
						} else if (data == 9) {
							out.print("<div class=\"container_alert\">\r\n"
							+ "							<div class=\"alert alert-danger container_alert-popup\"\r\n"
							+ "								role=\"alert\">File không hợp lệ</div>\r\n"
							+ "						</div>");
						}
					} catch (Exception e) {
					}
					%>

					<form action="/Toolpdt/Readlsistmark" enctype="multipart/form-data"
						method="post">
						<div class="container_body">
							<div class="container_body-button">
								<input type="file" id="namefile" name="namefile" class="btnFile">
							</div>
							<div class="container_body-cbb">
								<div class="rdo-group ">
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="Category"
											id="mark_quiz" value="option1" checked> <label
											class="form-check-label" for="Category">ĐIỂM QUIZ</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="Category"
											id="mark_online" value="option2"> <label
											class="form-check-label" for="Category">ĐIỂM ONLINE</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio" name="Category"
											id="attendance" value="option3"> <label
											class="form-check-label" for="Category">ĐIỂM DANH</label>
									</div>
								</div>
							</div>
							<div class="formDownLoad">
								<p class="textDownload">Lựa chọn định dạng file muốn tải:</p>
								<div class="rdo-group_download" id="modalCBB">
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio"
											name="downloadFile" id="inlineRadio1" value="fileExcel"
											checked> <label class="form-check-label"
											for="inlineRadio1">DOWNLOAD FILE EXCEL</label>
									</div>
									<div class="form-check form-check-inline">
										<input class="form-check-input" type="radio"
											name="downloadFile" id="inlineRadio2" value="fileWord">
										<label class="form-check-label" for="inlineRadio2">DOWNLOAD
											FILE WORD</label>
									</div>
								</div>
							</div>
							<div class="container_body-submit">
								<button class="btn_submit" type="submit" onclick = "GFG_Fun()">IT</button>
							</div>
						</div>
					</form>

				</div>
				<div class="col"></div>
				<div class="container_footer">
					<div class="container_footer-download">
						<a href="/Toolpdt/Uploadkht?id=${idkihoc }"><img
							src="./views/assets/image/241315670_374048297772326_2602951087702346768_n.png"
							alt=""></a>
					</div>
				</div>

			</div>

		</div>
	</div>
<script>
        function checkForm() {
            var cathi = document.getElementById('cathi').value;
            if (isNaN(cathi)) {
                document.getElementById('cathi').style.borderColor = "red";
                alert("Value input Ca thi not a number, Please try again!");
            } else {
                if (cathi <=6 && cathi >=1) {
                    document.getElementById('cathi').style.borderColor = "green";
                } else {
                    document.getElementById('cathi').style.borderColor = "red";
                    alert("Value input Ca thi must between 1 to 6, Please try again!");
                }
            }
        }
        function fileValidation() {
			var fileInput = document.getElementById('UploadFileInput');
			var filePath = fileInput.value;
			var allowedExtendsion = /(\.xls|\.xlsx)$/i;
			// kiểm tra định dạng
			if (!allowedExtendsion.exec(filePath)) {
				alert('Vui lòng chỉ upload file Excel!');
				fileInput.value = '';
				return false;
			}
		}
		var file = document.getElementById("namefile");
		function GFG_Fun() {
			if (file.files.length == 0) {
				alert('Bạn chưa upload file!');
			} else {
			}
		}
    </script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	
</body>

</html>