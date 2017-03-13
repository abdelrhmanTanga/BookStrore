
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
	
	//go and delete this from DB
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
	if (this.readyState == 4 && this.status == 200) {
		//document.getElementById("demo").innerHTML = this.responseText;
		console.log(this.responseText);  //the message which is written in the our.println
		document.getElementById("productCount1").innerHTML = document.getElementById("productCount1").innerHTML-1;
		document.getElementById("productCount2").innerHTML = document.getElementById("productCount2").innerHTML-1;
		var row = document.getElementById(id);
		row.parentNode.removeChild(row);
	  }
	};
	xhttp.open("GET", "DeleteProductServlet?id="+id , true);
	xhttp.send();
}


function selectAll()
{
	var checkboxs = document.getElementsByName("checkboxs");
	var checkall = document.getElementById("checkall");
	if( checkall.checked )
	{
		for( var i = 0 ; i < checkboxs.length ; i++ )
			checkboxs[i].checked = true;
	}else{
		for( var i = 0 ; i < checkboxs.length ; i++ )
			checkboxs[i].checked = false;
	}
	
	
}


function deleteSelectedProducts()
{
	var checkboxs = document.getElementsByName("checkboxs");
	var ids = new Array();
	
	for( var i = 0 ; i < checkboxs.length ; i++ )
	{
		if( checkboxs[i].checked )
		{	
			var id = checkboxs[i].getAttribute("id");
			id = id.replace("select","");
			id = parseInt(id);
			ids.push(id);
		}
	}

	
	for( var j = 0 ; j < ids.length ; j++ )
	{
		var xhttp = new XMLHttpRequest();
		x = ids[j];
		console.log(x +" hh "+ ids[j] )
		xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			//document.getElementById("demo").innerHTML = this.responseText;
			//console.log(this.responseText);  //the message which is written in the our.println
			document.getElementById("productCount1").innerHTML = document.getElementById("productCount1").innerHTML-1;
			document.getElementById("productCount2").innerHTML = document.getElementById("productCount2").innerHTML-1;
			console.log(x);
			}
		};
		xhttp.open("GET", "DeleteProductServlet?id="+ids[j]+"&time="+new Date().getTime() , true);
		xhttp.send();
		var row = document.getElementById(x);
		row.parentNode.removeChild(row);
	}
	
	
}



function CheckIfProductsSelceted()
{
	var checkboxs = document.getElementsByName("checkboxs");
	var check = false;
	for( var i = 0 ; i < checkboxs.length ; i++ )
	{
		if( checkboxs[i].checked )
		{	
			check = true;
			break;
		}
	}
	
	if( check )
	{
		$( "#deleteselected" ).modal( "show" );
	}
}




