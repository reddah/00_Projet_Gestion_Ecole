<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <!-- Ajout de la taglib core -->
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html lang="fr">
<head>
	<meta charset="UTF-8">
    <meta name="theme-color" content="#563d7c">
<title>page d'authentification - conseiller</title>

    <link rel="stylesheet" type="text/css" href="./assets/style/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="./assets/style/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="./assets/style/style_sign-in.css">
    
</head>
<body id="contour" class="text-center">
	
		<!-- Affichage des message d'erreurs -->
		<!-- 
				> message contenus dans la liste renvoyée par la servlet 
				  authentificationServlet (méthode doPost) en cas d'erreurs
				> la liste à attribut : attribut_erreurs
		 -->



   <form class="form-signin" method="POST" action="authentification" >
   
      	<img class="mb-4" src="./images/logoIntiFormation.png" alt="" width="200" height="100">
      	<div>
			<ul>
				<c:forEach var="message" items="${attribut_erreurs}">
					<li style="color: red; font-size: larger; font-family: sans-serif">
						<c:out value="${message}"/>
					</li>
				</c:forEach>
			</ul>
		</div>
      	<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
      	<label for="inputEmail" class="sr-only">Login : </label>
      	<input type="email" id="inputEmail" class="form-control" placeholder="Login : " required autofocus name="pLogin">
      	<label for="inputPassword" class="sr-only">Mot De Passe :</label>
      	<input type="password" id="inputPassword" class="form-control" placeholder="Mot De Passe :" required name="pMotDePasse">
      	<div class="checkbox mb-3">
        	<label>
          	<input type="checkbox" value="remember-me"> Remember me
        	</label>
      	</div>
      	<input class="btn btn-lg btn-primary btn-block" type="submit" value="Se Connecter"/>
      	<p class="mt-5 mb-3 text-muted">&copy; 2019-2020</p>
    </form>

</body>
</html>