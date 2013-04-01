<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<div class="container" style="margin-top:15px;">
	<div class="row">
		<div class="span8">
			<div>
				<c:if test="${not empty registrationerror}">
					<div class="alert alert-error">${registrationerror}</div>
				</c:if>
				<form id="userForm" method="post" action="" style="color:#272828;" autocomplete="off" >
					<div class="white rounded form-signup-div">
						<h3>About You</h3>
						<div class="control-group <c:if test="${not empty usernameerror}">info</c:if>">
							<label for="userLoginName"  style="color:#272828;">Username</label>
							<input type="text" id="userLoginName" name="username" tabindex="1" class="required" placeholder="Username" value='${username}'/>
						</div>
						<c:if test="${not empty usernameerror}">
							<div class="alert alert-info">${usernameerror}</div>
						</c:if>
					</div>
					
					<div class="white rounded form-signup-div">
						<h3>Choose a password for your new account.</h3>
						<div class="control-group <c:if test="${not empty passworderror}">info</c:if>">
							<label for="password1" style="color:#272828;">Password</label>
							<input type="password" id="password1" name="pword1" tabindex="2" class="required" placeholder="Password"/>
							<label for="password2" style="color:#272828;">Repeat Password</label>
							<input type="password" id="password2" name="pword2" tabindex="3" class="required" placeholder="Password"/>
						</div>
						<c:if test="${not empty passworderror}">
							<div class="alert alert-info">${passworderror}</div>
						</c:if>
					</div>
			
					<div class="white rounded form-signup-div">
						<h3>Enter your email address.</h3>
						<div class="help">You must enter a valid email address to activate your account.</div>
						<div class="control-group <c:if test="${not empty emailerror}">info</c:if>">
							<label for="email1" style="color:#272828;">Email</label>
							<input type="text" id="email1" name="email1" tabindex="4" class="required email" placeholder="Email Address" value='${email1}'/>
							<label for="email2" style="color:#272828;">Repeat Email</label>
							<input type="text" id="email2" name="email2" tabindex="5" class="required email" placeholder="Email Address" value='${email2}'/>
						</div>
						<c:if test="${not empty emailerror}">
							<div class="alert alert-info">${emailerror}</div>
						</c:if>
					</div>					
					<input class="btn btn-large btn-primary" type="submit" id="submitform" tabindex="8" value="Complete Signup &raquo;" /><br/><br/>
					
					<div id="disclaimer">By clicking the complete signup button you are agreeing to our <a href="<c:url value="/tos" />">Terms of Service</a></div>
				</form>
			</div>
		</div>
	</div>
</div>