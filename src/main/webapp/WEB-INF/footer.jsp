<%@ page import="java.time.Year"%>
<!-- Start Footer -->
<footer style="background-color: #0C1248;">
	<div class="container">
		<div class="row text-light py-4">
			<div class="col-6">
				<img style="height: 200px; width: 300px;"
					src="res/img/new-logo.jpeg" height="100px" alt="logo">
				<a href="AvisServl?action=avis"><p>> Les avis de nos utilisateurs</p></a>
				<p>> Foire aux Questions</p>
				
				<p>> Termes et conditions</p>
				<p>> Politiaque de Cookie</p>
				<p>> Qui somme-nous</p>
			</div>

			<div id="contactus" class="col-6">
				<h4 class="my-4">Contactez-nous</h4>
				<form action="EmailSending" method="post">
					<div class="form-group">
						<label for="footer-nom">Votre Nom </label> <input
							type="text" class="form-control" name="nom" id="footer-nom"
							placeholder="nom ici..." required>
					</div>
					<div class="form-group"><label for="footer-email">Adresse email</label> <input type="email"
				name="email"		class="form-control" id="footer-email"
						required placeholder="name@example.com">
			</div>
			<div class="form-group">
				<label for="footer-message">Message</label>
				<textarea required name="message" class="form-control" id="footer-message" rows="6"></textarea>
			</div>
		<div class="form-group">		<input type="submit" value="Envoiyer"></div>
			</form>
		</div>

	</div>

	<div class="row text-light py-1">
		<ul class="social pt-3">
			<li><a href="" target="_blank"><i
					class="fab fa-facebook fa-2x"></i></a></li>
			<li><a href="" target="_blank"><i
					class="fab fa-twitter fa-2x"></i></a></li>
			<li><a href="" target="_blank"><i
					class="fab fa-instagram fa-2x"></i></a></li>
			<li><a href="" target="_blank"><i
					class="fab fa-youtube fa-2x"></i></a></li>
		</ul>
	</div>


	</div>
</footer>
<!-- End Footer -->



<!-- Start Socket -->
<div class="socket text-light text-center py-3">
	<p>
		&copy; ${ Year.now().toString()} <a href="" target="_blank">www.krihally.mr</a>
	</p>
</div>
<!-- End Socket -->