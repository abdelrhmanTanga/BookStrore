function editProduct()
{
	id = document.getElementsByName("editbutton");
	id = id[0].getAttribute('id');
	console.log(id+"test");
	var g = document.getElementById("pid");
	g.setAttribute('value',id);
	
	
	
	var form = new FormData($("#feedback_form")[0]);
	console.log(form);
	
	var opts = {
	url: 'UpdateProductServlet',
	data: form,
	cache: false,
	contentType: false,
	processData: false,
	type: 'POST',
	success: function(data){
		console.log(this.responseText);
		alert(this.responseText);
		}
	};
	if(form.fake) {
		// Make sure no text encoding stuff is done by xhr
		opts.xhr = function() { var xhr = jQuery.ajaxSettings.xhr(); xhr.send = xhr.sendAsBinary; return xhr; }
		opts.contentType = "multipart/form-data; boundary="+form.boundary;
		opts.data = form.toString();
	}
	jQuery.ajax(opts);
	
}



function setUpdataData(id)
{
	id = id.replace("edit","");
	id = parseInt(id);
	//set id to the update button
	var editbutton = document.getElementsByName("editbutton");
	editbutton[0].setAttribute('id',id);
	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		//document.getElementById("demo").innerHTML = this.responseText;
			console.log(this.responseText);  //the message which is written in the our.println //each value printed in each line
			var values = this.responseText.split('\n');
		
			mali_pname = document.getElementById("pname").value = values[0];
			mali_quantity = document.getElementById("quantity").value = parseInt(values[1]);
			mali_author = document.getElementById("author").value = values[2];
			
			
			mali_isbn = document.getElementById("isbn").value = parseInt(values[3]);
			mali_description = document.getElementById("description").value = values[4];

			mali_price = document.getElementById("price").value = parseInt(values[5]);
			//document.getElementById("pimage").value = values[6];
			
			mali_category = document.getElementById('category');
			mali_category.value = parseInt(values[7]);
	}
	};
	xhttp.open("GET", "UpdateProductPageServlet?data="+id , true);
	xhttp.send();
	
}