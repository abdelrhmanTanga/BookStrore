function productSearch(s)
{
	var search = document.getElementById("search");
	var keyword = document.getElementById("search").value;
	var categorySearch = document.getElementById("searchcategory").value;
	if( s == 0 )
		s = 1;
	
	
	if( search.value )
	{
		$.ajax({
        type: "GET",
        url: "SearchProductController",
		data : { "keyword":search.value , "category":categorySearch , "page":s },
        contentType: 'application/json',
        dataType: 'json',
        success: function (data, textStatus, jqXHR) {
			
				//change count of users and producs
				$("#userscount").text(data.usersCount);
				$("#productCount1").text(data.productsCount);
				$("#productCount2").text(data.productsCount);
                mytbody = document.getElementById("tbody");
				mytbody.parentElement.removeChild(mytbody);

			
				 var mytable = document.getElementById("mytable");
				 var tbody = document.createElement("tbody");
			     tbody.setAttribute('id','tbody');
				 mytable.appendChild(tbody);
			
				for( var i = 0 ; i <  data.products.length ; i++ )
				{
				    pid =  data.products[i].id;
					
					var row = document.createElement("tr");
					row.setAttribute('id',pid);
					
					col1 =  document.createElement("td");
					checkbox =  document.createElement("input");
					checkbox.setAttribute('type','checkbox');
					checkbox.setAttribute('name','checkboxs');
					checkbox.setAttribute('id','select'+pid);
					checkbox.setAttribute('class','checkthis');
					col1.appendChild(checkbox);
					
					col2 = document.createElement("td"); col2.innerHTML = data.products[i].name;
					col3 = document.createElement("td"); col3.innerHTML = data.products[i].quantity;
					col4 = document.createElement("td"); col4.innerHTML = data.products[i].ISBN;
					col5 = document.createElement("td"); col5.innerHTML = data.products[i].price;
					col6 = document.createElement("td"); 
					
						//category
					for( var j = 0 ; j < data.categories.length ; j++ )
					{
						if( data.products[i].category == data.categories[j].id  )
							col6.innerHTML = data.categories[j].name;
					}
					
					col7 = document.createElement("td");
					p1 = document.createElement("p");
					p1.setAttribute('data-placement','top');
					p1.setAttribute('data-toggle','tooltip');
					p1.setAttribute('title','Edit');
					
					b1 = document.createElement("button");
					b1.setAttribute('class',"btn btn-primary btn-xs");
					b1.setAttribute('onclick',"setUpdataData(this.id)");
					b1.setAttribute('data-title',"Edit");
					b1.setAttribute('data-toggle',"modal");
					b1.setAttribute('id',"edit"+pid);
					b1.setAttribute('data-target',"#edit");
					
					s1 = document.createElement("span");
					s1.setAttribute('class',"glyphicon glyphicon-pencil");
					
					b1.appendChild(s1);
					p1.appendChild(b1);
					col7.appendChild(p1);
					
					col8 = document.createElement("td");
					p2 = document.createElement("p");
					p2.setAttribute('data-placement','top');
					p2.setAttribute('data-toggle','tooltip');
					p2.setAttribute('title','Delete');
					
					b2 = document.createElement("button");
					b2.setAttribute('class',"btn btn-danger btn-xs");
					b2.setAttribute('onclick',"deleteProduct(this.id)");
					b2.setAttribute('data-title',"Delete");
					b2.setAttribute('data-toggle',"modal");
					b2.setAttribute('id',"delete"+pid);
					b2.setAttribute('data-target',"#delete");
					
					s2 = document.createElement("span");
					s2.setAttribute('class',"fa fa-times");
					
					b2.appendChild(s2);
					p2.appendChild(b2);
					col8.appendChild(p2);
					
					
					
					row.appendChild(col1);
					row.appendChild(col2);
					row.appendChild(col3);
					row.appendChild(col4);
					row.appendChild(col5);
					row.appendChild(col6);
					row.appendChild(col7);
					row.appendChild(col8);
					tbody.appendChild(row);
					
				}
			
				//darw the pagination
				var searchpage = document.getElementById("pagination");
				$("#pagination").empty();
				makePagination(data.productsCount,2);
				
        	}
     	});   
	}

}


function test()
{
	
}