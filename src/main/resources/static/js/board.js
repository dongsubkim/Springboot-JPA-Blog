let index ={
	init: function() {
		$("#btn-save").on("click", ()=>{
			this.save();
		}); 
		$("#btn-delete").on("click", ()=>{
			this.deleteById();
		}); 

	},
	save:function() {
		let data = {
			title:$("#title").val(),
			content:$("#content").val()
		}

		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8", 
			dataType: "json" 
		}).done(function(resp){
			alert("successfully posted");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error)); 
		});	
	},
	deleteById:function() {
		var id = $("#id").text();
		console.log(id);
		$.ajax({
			type: "DELETE",
			url: "/api/board/" + id,
			dataType: "json" 
		}).done(function(resp){
			alert("successfully deleted");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error)); 
		});	
	}
}

index.init();