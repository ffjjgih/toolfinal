<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link rel="stylesheet" href="./views/assets/home.css">
    <link rel="stylesheet" href="./views/assets/base.css">
    <link rel="stylesheet" href="./views/assets/grid.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
        integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="./assets/fontawesome-free-5.15.3-web/css/all.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
        integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>

<body>
    <div class="Container">
        <div class="row">
            <div class="col-2 container_left">
                <div class="container_left-header">
                    <img src="./views/assets/image/Poly.png" alt="">
                </div>
                <div class="container_left-body">
                    <button class="container_left-body-btn">DashBoard</button>
                </div>
            </div>
            <div class="col-10 container_right">
            <form method="post" action="/Toolpdt/Home/logout">
                <div class="container_right-header">
                    <div class="container_right-header-TT">
                        <a href="/Toolpdt/Home"><img class="header_right-icon_user" src="./views/assets/image/user.png" alt=""></a>
                        <h4 class="header_right-name_user">Nguyễn Lê Hải</h4>
                        <button type="submit" class="header_right-btn_user">Đăng xuất</button>
                    </div>
                </div>
                </form>
                <div class="container container_right-body">
                    <div class="container_right-body-intro">
                        <h2>Danh sách các kỳ</h2>
                    </div>
                    <div class="container container_right-body-controller">
                        <div class="container_right-body-controller-header">
                            <div class="container_right-body-controller-header-left">
                                <button type="button" class="btn btn-success btn_add js-btnAdd">Thêm mới</button>
                            </div>
                            <div class="container_right-body-controller-header-right">
                                <select class="form-select form-select-lg mb-3 cbb"
                                    aria-label=".form-select-lg example">
                                    <option value="20">20</option>
                                    <option value="40">40</option>
                                    <option value="60">60</option>
                                </select>
                            </div>
                        </div>
                        <div class="container_right-body-controller-table">
                            <table class="table table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th scope="col">Năm Học</th>
                                        <th scope="col">Học kì</th>
                                        <th scope="col">Block</th>
                                        <th scope="col">Trạng thái</th>
                                        <th scope="col">Thao tác</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${lstkythi}" var="kythi">
                                <c:choose>
                                	<c:when test="${kythi.trangThai=='Đã kết thúc' }">
                                	<tr>
                                        <th scope="row">${kythi.namHoc }</th>
                                        <td><a href="">${kythi.kyHoc }</a></td>
                                        <td>${kythi.blockid }</td>
                                        <td>${kythi.trangThai }</td>
                                        <td>
                                            <button class="button_update" disabled>Cập nhật</button>
                                            <button class="button_edit" disabled>Chỉnh sửa</button>
                                        </td>
                                    </tr>
                                	</c:when>
                                	<c:otherwise>
                                		<tr>
                                        <th scope="row">${kythi.namHoc }</th>
                                        <td><a href="">${kythi.kyHoc }</a></td>
                                        <td>${kythi.blockid }</td>
                                        <td>${kythi.trangThai }</td>
                                        <td>
                                            <a href="/Toolpdt/Readlsistmark?id=${kythi.idhk }" class="button_update">Cập nhật</a>
                                            <a href="/Toolpdt/Updatekihoc?id=${kythi.idhk }" class="button_edit">Chỉnh sửa</a>
                                        </td>
                                    </tr>
                                	</c:otherwise>
                                </c:choose>
                                    
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <div class="container container_right-body-footer">
                            <nav aria-label="Page navigation example">
                                <ul class="pagination pagination-lg">
                                    <li class="page-item"><a class="page-link" href="#">Previous</a></li>
                                    <li class="page-item"><a class="page-link" href="#">1</a></li>
                                    <li class="page-item"><a class="page-link" href="#">2</a></li>
                                    <li class="page-item"><a class="page-link" href="#">3</a></li>
                                    <li class="page-item"><a class="page-link" href="#">Next</a></li>
                                </ul>
                            </nav>
                        </div>
                    </div>

                </div>
            </div>
        </div>

        <!-- modal thêm kì mới -->
        <div class="model_add js-model_add ">
            <div class="model_add-container">
                <form action="/Toolpdt/Home" method="post">
                    <div class="container_form">
                        <h2>Thêm kỳ học mới</h2>
                        <div class="form-group">
                            <label for="inputAddress">Năm học</label>
                            <input type="text" class="form-control input-form" id="nam_hoc" name="nam_hoc"
                                placeholder="Điền năm học hiện tại">
                        </div>
                        <div class="btn-group">
                            <label for="">Block</label>
                            <select id="BLOCKID" name="BLOCKID" class="form-select form-select-lg mb-3 cbb"
                                aria-label=".form-select-lg example">
                                <option value="block1" id="BLOCKID">Block 1</option>
                                <option value="block2" id="BLOCKID">Block 2</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="kyhoc">Kỳ</label>
                            <input type="text" class="form-control input-form" id="kyhoc" name="kyhoc"
                                placeholder="Điền kỳ học hiện tại">
                        </div>
                        <button type="button" class="btn btn-danger btn_exit">Exit</button>
                        <button formaction="/Toolpdt/Home/insert" class="btn btn-primary btn_submit">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script>
        const btnThem = document.querySelector('.js-btnAdd');
        const model = document.querySelector('.js-model_add');
        const btnExit = document.querySelector('.btn_exit');
        function showModel(){
            model.classList.add('open');
        }
        function hideModel(){
            model.classList.remove('open');
        }

        btnThem.addEventListener('click', showModel);

        btnExit.addEventListener('click', hideModel);
    </script>
</body>

</html>