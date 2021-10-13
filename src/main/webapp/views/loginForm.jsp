<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet" href="./views/assets/base.css">
<link rel="stylesheet" href="./views/assets/grid.css">
<link rel="stylesheet" href="./views/assets/login.css">
</head>

<body>
	<div id="app">
		<header>
		<div class="header">
			<div class="hearder_logo">
				<img class="header_img" src="./views/assets/image/Poly.png" alt="">
			</div>
		</div>
		</header>
		<div class="container">
			<div class="grid wide">
				<div class="row sm-gutter">
					<div class="col l-6">
						<form method="post" action="/Toolpdt/login-google" enctype="multipart/form-data">
							<div class="formlogin">
								<div class="titleForm">
									<h1 class="fonttext">LOGIN FORM</h1>
								</div>
								<div class="field">
									<input class="fieldLogin" type="text" name="" id=""
										placeholder="ENTER EMAIL">
								</div>
								<div class="field">
									<input class="fieldLogin" type="text" name="" id=""
										placeholder="ENTER PASSWORD">
								</div>
								<div class="field">
									<button class="button" type="submit">SUBMIT</button>
								</div>
								<a
									href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/Toolpdt/login-google&response_type=code
    &client_id=46349360502-oksdnai40vgkko6iecnrf851jal0e0ca.apps.googleusercontent.com&approval_prompt=force"
									class="google button"> <img class="img_google"
									src="./views/assets/image/google.png" alt="">
								</a>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>

	</div>
</body>

</html>