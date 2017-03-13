

function setData(name,quantity,author,description,price,category)
{
	document.getElementById("pname").value = name;
	document.getElementById("quantity").value = quantity;
	document.getElementById("author").value = author;
	
	document.getElementById("description").value = description;
	document.getElementById("price").value = price;
	
	document.getElementById("category").value = category;
}

function clearAllAddFields()
{
	document.getElementById("pname").value = '';
	document.getElementById("quantity").value = '';
	document.getElementById("author").value = '';
	
	document.getElementById("description").value = '';
	document.getElementById("price").value = '';
	document.getElementById("pimage").value = '';
	document.getElementById("isbn").value = '';
	
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
				window.scrollTo(0,250);
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
			window.scrollTo(0,250);
			$("#danger-alert-filename").fadeTo(2000, 500).slideUp(600, function(){
				$("#danger-alert-filename").slideUp(600);
			});	
		}
	}
	
};


function addProductToDB()
{
	var form = new FormData($("#addproductform")[0]);
	//console.log(form);
	
	var opts = {
	url: 'adminadd?time='+new Date().getTime(),
	data: form,
	cache: false,
	contentType: false,
	processData: false,
	type: 'POST',
	success: function(data){  //data is the response content
			if( data == "false"  )
			{
				window.scrollTo(0,250);
				$("#danger-alert-product-not-added").fadeTo(2000, 500).slideUp(600, function(){
					$("#danger-alert-product-not-added").slideUp(600);
				});	
				 document.getElementById('isbn').value = '';
			}else
			{
				window.scrollTo(0,250);
				$("#danger-alert-product-add").fadeTo(2000, 500).slideUp(600, function(){
					$("#danger-alert-product-add").slideUp(600);
				});	
				clearAllAddFields();
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


function addNewCategory()
{
	
}
