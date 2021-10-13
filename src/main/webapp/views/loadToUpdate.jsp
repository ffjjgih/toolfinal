<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Form</title>
    <link rel="stylesheet" href="./views/assets/base.css">
    <link rel="stylesheet" href="./views/assets/grid.css">
    <link rel="stylesheet" href="./views/assets/loadToUpdate.css">
    <link rel="stylesheet" href="./views/assets/fontawesome-free-5.15.3-web/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
        integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</head>

<body>
    <div id="app">
        <header class="header">
            <div class="grid wide">
                <a href="HomeForm.html"><img class="header_img" src="./views/assets/image/Poly.png" alt="HomeForm.html"></a>
                <img class="header_icon_user" src="./views/assets/image/user.png" alt="">
                <a href="" class="header_right-btn_user">Đăng xuất</a>
            </div>
        </header>

        <div class="container">
            <div class="title">
                <h2>>> UPDATE KẾ HOẠCH THI</h2>
            </div>

            <form action="updatekht" method="post">
            	<div class="mb-3 row">
                    <p for="staticEmail" class="col-sm-4 label_text col-form-label">ID Kế hoạch thi</p>
                    <div class="col-sm-7">
                        <input type="text" value = "${detail.id }" name = "idkht" class="form-control input_control" id="inputPassword" readonly>
                    </div>
                </div>
                <div class="mb-3 row">
                    <p for="staticEmail" class="col-sm-4 label_text col-form-label">Ngày thi</p>
                    <div class="col-sm-7">
                        <input type="date" value = "${detail.ngayThi }" name = "ngay" class="form-control input_control" id="inputPassword">
                    </div>
                </div>
                <div class="mb-3 row">
                    <p for="inputPassword" class="col-sm-4 label_text col-form-label">Ca thi</p>
                    <div class="col-sm-7">
                        <input type="text" value = "${detail.caThi }" name = "caThi" class="form-control input_control" id="cathi" onchange="checkForm()">
                    </div>
                </div>
                <div class="mb-3 row">
                    <p for="staticEmail" class="col-sm-4 label_text col-form-label">Phòng thi</p>
                    <div class="col-sm-7">
                        <input type="text" value = "${detail.phongThi }" name = "phongThi" class="form-control input_control" id="inputPassword">
                    </div>
                </div>
                <div class="mb-3 row">
                    <p for="inputPassword" class="col-sm-4 label_text col-form-label">Tên môn</p>
                    <div class="col-sm-7">
                        <input type="text" value = "${detail.tenMon }" name = "tenMon" class="form-control input_control" id="inputPassword">
                    </div>
                </div>
                <div class="mb-3 row">
                    <p for="staticEmail" class="col-sm-4 label_text col-form-label">Mã môn</p>
                    <div class="col-sm-7">
                        <input type="text" value = "${detail.maMon }" name = "maMon" class="form-control input_control" id="inputPassword">
                    </div>
                </div>
                <div class="mb-3 row">
                    <p for="inputPassword" class="col-sm-4 label_text col-form-label">Loại thi</p>
                    <div class="col-sm-7">
                        <input type="text" value = "${detail.loaiThi }" name = "loaiThi" class="form-control input_control" id="inputPassword">
                    </div>
                </div>
                <div class="mb-3 row">
                    <p for="staticEmail" class="col-sm-4 label_text col-form-label">Giáo viên</p>
                    <div class="col-sm-7">
                        <input type="text" value = "${detail.giangVien }" name = "giangVien" class="form-control input_control" id="inputPassword">
                    </div>
                </div>
                <div class="mb-3 row">
                    <p for="inputPassword" class="col-sm-4 label_text col-form-label">Lớp</p>
                    <div class="col-sm-7">
                        <input type="text" value = "${detail.lop }" name = "lop" class="form-control input_control" id="inputPassword">
                    </div>
                </div>
                <input type="submit" class="btn btn-success update_btn" value = "UPDATE" >
            </form>
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
    </script>
</body>

</html>