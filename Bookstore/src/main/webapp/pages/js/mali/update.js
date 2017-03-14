var mali_name;
var mali_quantity; 
var mali_author;

var mali_isbn;
var mali_description;
var mali_price;
var mali_image;
var mali_category;


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
				if( data == "true" )
				{
					//id
					 $('#'+id).children().eq(1).text(document.getElementById("pname").value);
					 $('#'+id).children().eq(2).text(document.getElementById("quantity").value);
					 $('#'+id).children().eq(3).text(document.getElementById("isbn").value);
					 $('#'+id).children().eq(4).text(document.getElementById("price").value);
					 $('#'+id).children().eq(5).text(document.getElementById("category").value);
					 $('#edit').modal('hide');
				}
				else if( data == "false")
				{
					$("#danger-alert-product-not-updated").fadeTo(2000, 500).slideUp(600, function(){
						$("#danger-alert-product-not-updated").slideUp(600);
					});
				}
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
	
	document.getElementById("category").value = $('#'+id).children().eq(5).text().replace(/\s/g, '');
	mali_category =  $('#'+id).children().eq(5).text().replace(/\s/g, '');
	
	
	
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		//document.getElementById("demo").innerHTML = this.responseText;
			console.log(this.responseText);  //the message which is written in the our.println //each value printed in each line
			var values = this.responseText.split('\n');
		
			mali_name = document.getElementById("pname").value = values[0];
			mali_quantity = document.getElementById("quantity").value = parseInt(values[1]);
			mali_author = document.getElementById("author").value = values[2];
			
			
			mali_isbn = document.getElementById("isbn").value = parseInt(values[3]);
			mali_description = document.getElementById("description").value = values[4];

			mali_price = document.getElementById("price").value = parseInt(values[5]);
			//mali_image = document.getElementById("pimage").value = values[6];
			//mali_image = document.getElementById("pimage").value = values[6];
			
			document.getElementById("pimage").value = '';
	}
	};
	xhttp.open("GET", "UpdateProductPageServlet?data="+id , true);
	xhttp.send();
	
}



var file = document.getElementById('pimage');


file.onchange = function(e){
    
	//check file extension
	var check = false;
	var ext = this.value.match(/\.([^\.]+)$/)[1];
    switch(ext)
    {
        case 'jpg':
        case 'bmp':
        case 'png':
            break;
        default:
            this.value='';
			window.scrollTo(0,250);
			$("#danger-alert").fadeTo(2000, 500).slideUp(600, function(){
				$("#danger-alert").slideUp(600);
			});
			check = true;
			
    }
	
	//check file size if the file extension is good
	if( !check )
	{
		var fileUpload = document.getElementById("pimage");
		if (typeof (fileUpload.files) != "undefined") 
		{
            var size = parseFloat(fileUpload.files[0].size / (1024*1024) ).toFixed(2);  //tofixed ( 2.233232 == 2.23 )
			if( size > 1 )
			{
				fileUpload.value='';
				//window.scrollTo(0,250);
				$("#danger-alert-size").fadeTo(2000, 500).slideUp(600, function(){
					$("#danger-alert-size").slideUp(600);
				});	
				check = true;
			}
				
		}
	
	}
	
	if( !check )
	{
		var filename = document.getElementById('pimage').value;
		filename = filename.replace(/^.*[\\\/]/, '');
		if( filename.length  > 50 )
		{
			document.getElementById("pimage").value ='';
			//window.scrollTo(0,250);
			$("#danger-alert-filename").fadeTo(2000, 500).slideUp(600, function(){
				$("#danger-alert-filename").slideUp(600);
			});	
		}
	}
	
};


function checkDataNotChanged()
{
	if( 
		$("#pname").text() == mali_name && 
		document.getElementById("quantity").value == mali_quantity  && 
		document.getElementById("category").value ==  mali_category &&
		document.getElementById("author").value ==  mali_author &&
		document.getElementById("isbn").value ==  mali_isbn &&
		document.getElementById("description").value ==  mali_description &&
		document.getElementById("price").value ==  mali_price &&
		document.getElementById("pimage").value == ""
	)
	return true;
	else
		return false;

}

