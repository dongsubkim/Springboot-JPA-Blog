let index ={
	init: function() {
		$("#btn-save").on("click", ()=>{
			this.save();
		}); 
		$("#btn-login").on("click", ()=>{
			this.login();
		}); 
	},
	save:function() {
		//console.log("Save function called");
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		}
		//console.log(data);
		
		// ajax: async on default
		$.ajax({
			type: "POST",
			url: "/api/user",
			data: JSON.stringify(data), // http body data
			contentType: "application/json; charset=utf-8", // request body data type(MIME)
			dataType: "json" // response type; response is always String(byte); but if it looks like json => convert to js object
		}).done(function(resp){
			alert("successfully joined");
			// console.log(resp)
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});	
	},
	login:function() {
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
		}
		$.ajax({
			type: "POST",
			url: "/api/user/login",
			data: JSON.stringify(data), // http body data
			contentType: "application/json; charset=utf-8", // request body data type(MIME)
			dataType: "json" // response type; response is always String(byte); but if it looks like json => convert to js object
		}).done(function(resp){
			alert("successfully login");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
	}
}

index.init();