<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Kế hoạch thi</title>
    <link rel="stylesheet" href="./views/assets/base.css">
    <link rel="stylesheet" href="./views/assets/grid.css">
    <link rel="stylesheet" href="./views/assets/KHT.css">
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
                <a href="/Toolpdt/Home"><img class="header_img" src="./views/assets/image/Poly.png" alt=""></a>
                <img class="header_icon_user" src="./views/assets/image/user.png" alt="">
                <button class="header_right-btn_user">Đăng xuất</button>
            </div>
        </header>

        <body>
            <div class="container">
            <c:if test="${not empty lst}">
					<div class="alert alert-primary container_alert" role="alert">${suc}</div>
				</c:if>
				<c:if test="${empty lst}">
					<div class="alert alert-danger container_alert" role="alert">${error}</div>
				</c:if>
                <ul class="container_button">
                    <li>
                        <div class="grid wide">
                            <button class="button_add js-btn_add">THÊM</button>
                        </div>
                    </li>
                </ul>
                <form action="searchkht" method="post">
                    <ul class="container_button">
                        <li>
                            <div class="container_search">
                                <i class="container_search-icon fas fa-search"></i>
                                <input type="text" placeholder="VD IT16308" name = "txtSearch" class="container_search-input">
                            </div>
                        </li>
                        <li>
                            <div class="grid wide">
                                <button type="submit" class="button_find">TÌM KIẾM</button>
                            </div>
                        </li>
                    </ul>
                </form>
                <!-- table -->
                <div class="grid wide">
                    <table class="table table_font table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th>Ngày thi</th>
                                <th>Ca thi</th>
                                <th>Phòng thi</th>
                                <th>Tên môn</th>
                                <th>Mã môn</th>
                                <th>Loại thi</th>
                                <th>Giáo viên</th>
                                <th>Lớp</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${lst}" var="item">
                            <tr>
                                <td>${item.ngayThi }</td>
                                <td>${item.caThi }</td>
                                <td>${item.phongThi }</td>
                                <td>${item.tenMon }</td>
                                <td>${item.maMon }</td>
                                <td>${item.loaiThi }</td>
                                <td>${item.giangVien }</td>
                                <td>${item.lop }</td>
                                <td>
                                    <a type = "submit" href="updatekht?index=${item.id}&&id=${idkihoc}" class="button_update">SỬA</a>
                                    <a type = "submit" href="deletekht?index=${item.id}&&id=${idkihoc}" class="button_del">XÓA</a>
                                </td>
                            </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </body>
        <div class="modal_update js-modal_update ">
			<div class="modal_container_update js-modal_container_update">
				<form action="Updatekihoc" method="post">
					<div class="modal-header">
						<h3 class="modal-title">
							Thêm kế hoạch thi <input class="idkh" name="idkh" value="${id}"
								readonly>
						</h3>
						<i class="model-header_exit fas fa-times js-btn-close"></i>
					</div>

					<div class="modal-body">
						<div class="form-left">
							<ul>
								<li class="form_text">Ngày thi</li>
								<li><input type="date" class="form_input" name="ngayThi"></li>
							</ul>
							<ul>
								<li class="form_text">Phòng thi</li>
								<li><input type="text" class="form_input" name="phongThi"></li>
							</ul>
							<ul>
								<li class="form_text">Mã môn</li>
								<li><input type="text" class="form_input" name="maMon"></li>
							</ul>
							<ul>
								<li class="form_text">Giáo viên</li>
								<li><input type="text" class="form_input" name="giangVien"></li>
							</ul>
						</div>
						<div class="form-right">
							<ul>
								<li class="form_text">Ca thi</li>
								<li><input type="text" class="form_input" name="cathi"
									id="caThi" onchange="checkForm()"></li>
							</ul>
							<ul>
								<li class="form_text">Tên môn</li>
								<li><input type="text" class="form_input" name="tenMon"></li>
							</ul>
							<ul>
								<li class="form_text">Loại thi</li>
								<li><input type="text" class="form_input" name="loaiThi"></li>
							</ul>
							<ul>
								<li class="form_text">Lớp</li>
								<li><input type="text" class="form_input" name="lop"></li>
							</ul>
						</div>
					</div>
					<div class="modal-footer">
						<div class="button">
							<button class="button_form_update sub">SUBMIT</button>
						</div>
					</div>
				</form>
			</div>
		</div>
    </div>
    <script>
        const updateBtns = document.querySelectorAll('.js-btn_update');
        const addBtn = document.querySelector('.js-btn_add');
        const modal = document.querySelector('.js-modal_update');
        const closeBtn = document.querySelector('.js-btn-close');
        const modalContainer = document.querySelector('.js-modal_container_update');

        // hiển thị modal, thêm class open
        function showModal() {
            modal.classList.add('open');
        }
        function hideModal() {
            modal.classList.remove('open');
        }

        // lặp qua để lấy sự kiện
        for (const updateBtn of updateBtns) {
            updateBtn.addEventListener('click', showModal);
        }
        
        modal.addEventListener('click', hideModal);
        
        addBtn.addEventListener('click', showModal);

        closeBtn.addEventListener('click', hideModal);

        modalContainer.addEventListener('click', function(event){
            event.stopPropagation();
        })
        function checkForm() {
            var ki = document.getElementById('cathi').value;
            if (isNaN(ki)) {
                document.getElementById('cathi').style.borderColor = "red";
                alert("Value input Ca thi not a number, Please try again!");
            } else {
                if (ki <=6 && ki >=1) {
                    document.getElementById('cathi').style.borderColor = "green";
                } else {
                    document.getElementById('cathi').style.borderColor = "red";
                    alert("Value input Ca thi must between 1 to 6, Please try again!");
                }
            }
        }
         function showMessage(id){
            var option = confirm('Are you sure to delete?');
            if(option === true){
                window.location.href = 'deletekht?khtID='+id;
            } else {
            }
        }
    </script>
    
</body>

</html>