var mali_pname ;
var mali_quantity ;
var mali_author ;
var mali_isbn ;
var mali_description ;
var mali_price ;
var mali_category ;
var mali_pimage ;


function makePagination(productsCount)
{
	var pagenumbers = Math.trunc(productsCount / 10);
	
	if( pagenumbers < (productsCount / 10) )
		pagenumbers++;
	
	var pagination = document.getElementById("pagination");
	
	for( var i = 0 ; i < pagenumbers ; i++ )
	{
		var myli = document.createElement('li');
		var mya = document.createElement('a');
		mya.innerHTML = i+1;
		mya.setAttribute('href','HomeServletController?page='+(i+1));
		myli.appendChild(mya);
		pagination.appendChild(myli);
	}
	
	var myli1 = document.createElement('li');
	var mya1 = document.createElement('a');
	var myspan = document.createElement('span');
	myspan.setAttribute('class','fa fa-chevron-right');
	mya1.appendChild(myspan);
	myli1.appendChild(mya1);
	
	pagination.appendChild(myli1);
}


function editProduct()
{
	id = document.getElementsByName("editbutton");
	id = id[0].getAttribute('id');
	console.log(id);
	/*var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		//document.getElementById("demo").innerHTML = this.responseText;
			console.log(this.responseText);  //the message which is written in the our.println //each value printed in each line
		}
	};
	xhttp.open("POST", "pdateProductServlet", true);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	mali_pname = document.getElementById("pname").value;
	mali_quantity = document.getElementById("quantity").value;
	mali_author = document.getElementById("author").value;


	mali_isbn = document.getElementById("isbn").value;
	mali_description = document.getElementById("description").value;

	mali_price = document.getElementById("price").value;
	mali_pimage = document.getElementById("pimage").value;
	console.log(mali_pimage);

	mali_category = document.getElementById('category');*/
	/*xhttp.send("id="+id+"&"+"pname="+mali_pname+"&"+"quantity="+mali_quantity+"&"+"author="+mali_author+"&"+"isbn="+mali_isbn+"&"+"description="+mali_description+"&"+"price="+mali_price+"&"+"category="+mali_category+"&"+"image="+mali_pimage);*/
	
	var fd = new FormData();
    var file_data = $('input[type="file"]')[0].files; // for multiple files
    fd.append("pimage", file_data[0]);

	fd.append("id",id);
	
    var other_data = $('form').serializeArray();
    $.each(other_data,function(key,input){
        fd.append(input.name,input.value);
    });
	console.log(file_data[0]);
    $.ajax({
        url: 'UpdateProductServlet',
        data: fd,
		cache:false,
        contentType: false,
        processData: false,
        type: 'GET',
        success: function(data){
           // console.log(data);
        }
    });
	
	
	
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

function deleteProduct(id)
{
	var deleteFinalName = document.getElementsByName("deleteFinalName");
	id = id.replace("delete","");
	id = parseInt(id);
	deleteFinalName[0].setAttribute('id',id);
}

function deleteFinal(id)
{
	//console.log(id);
	var row = document.getElementById(id);
	row.parentNode.removeChild(row);
	
	//go and delete this from DB
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		//document.getElementById("demo").innerHTML = this.responseText;
		console.log(this.responseText);  //the message which is written in the our.println
		document.getElementById("productCount1").innerHTML = document.getElementById("productCount1").innerHTML-1;
		document.getElementById("productCount2").innerHTML = document.getElementById("productCount2").innerHTML-1;
	  }
	};
	xhttp.open("GET", "DeleteProductServlet?id="+id , true);
	xhttp.send();
}

