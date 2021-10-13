<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sắp xếp lịch thi</title>
    <link rel="stylesheet" href="./views/assets/CDLT.css">
    <link rel="stylesheet" href="./views/assets/base.css">
    <link rel="stylesheet" href="./views/assets/grid.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
        integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>

<body>
    <div id="app">
        <header class="header">
            <div class="grid wide">
                <a href="/Toolpdt/Home"><img class="header_img" src="./views/assets/image/Poly.png" alt=""></a>
                <img class="header_icon_user" src="./views/assets/image/user.png" alt="">
                <button class="header_right-btn_user">Đăng xuất</button>
            </div>
        </header>
        <div class="container">
            <div class="row">
                <div class="col">
                </div>
                <div class="col container_middle">
                    <h1 class="h1 container_header">
                        UPLOAD FILE KẾ HOẠCH THI
                    </h1>
                     <%
                     try{
                    	int data = Integer.parseInt(session.getAttribute("value").toString());
                    	if((data == 3)){
                    		out.print("<div class=\"container_alert\">\r\n"
                    				+ "                        <div class=\"alert alert-danger container_alert-popup\" role=\"alert\">\r\n"
                    				+ "                            Opps, Chúng tôi chưa lấy được file của bạn rồi, Hãy thử lại!\r\n"
                    				+ "                          </div>\r\n"
                    				+ "                    </div>");
                    	} else if (data == 4){
                    		out.print("<div class=\"container_alert\">\r\n"
                    				+ "                        <div class=\"alert alert-danger container_alert-popup\" role=\"alert\">\r\n"
                    				+ "                            Opps, Đây không phải định dạng file Kế hoạch thi rồi, Hãy thử lại!\r\n"
                    				+ "                          </div>\r\n"
                    				+ "                    </div>");
                    	}
                     }catch(Exception e){
                     }
                    %>
                    <form action="/Toolpdt/Uploadkht" enctype="multipart/form-data" method="post" >
                        <div class="container_body">
                            <div class="container_body-button">
                                <input type="file" id="namfile" name="namefile" class="btnFile"></input>
                            </div>
                            <div class="container_body-cbb">
                               
                            </div> 
                            <div class="container_body-submit">
                                <button class="btn_submit" onclick = "GFG_Fun()">SUBMIT</button>
                            </div>
                        </div>
                    </form>

                </div>
                <div class="col">
                </div>
                <form action="">
                    <div class="container_footer">
                        <div class="container_footer-download">
                            <a href=""><img src="./assets/image/241315670_374048297772326_2602951087702346768_n.png"
                                    alt=""></a>
                        </div>
                    </div>
                </form>

            </div>

        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script>
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
		
		var file = document.getElementById("namfile");
        function GFG_Fun() {
            if(file.files.length == 0 ){
                alert('Bạn chưa upload file!');
            } else {
            }
        }
	</script>
</body>

</html>