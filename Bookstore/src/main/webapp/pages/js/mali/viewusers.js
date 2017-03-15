function searchUsers(pageNumber)
{
	
	var search = document.getElementById("search");
	var keyword = document.getElementById("search").value;
	
	if( search.value )
	{
	
		$.ajax({
			type: "GET",
			url: "SearchUsersController",
			data : { "keyword":keyword ,"page":pageNumber  },
			contentType: 'application/json',
			dataType: 'json',
			success: function (data, textStatus, jqXHR) {

					//change count of users and products
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
				
					for( var i = 0 ; i <  data.clients.length ; i++ )
					{
						var row = document.createElement("tr");
						row.setAttribute('id',data.clients[i].email);
					
						col1 =  document.createElement("td"); col1.innerHTML = data.clients[i].name;
						col2 =  document.createElement("td"); col2.innerHTML = data.clients[i].email;
						col3 =  document.createElement("td"); col3.innerHTML = data.clients[i].phone;
						col4 =  document.createElement("td"); col4.innerHTML = data.clients[i].address;
						col5 =  document.createElement("td"); col5.innerHTML = data.clients[i].country;
						col6 =  document.createElement("td"); 
						if( data.clients[i].gender == 'm' )
							col6.innerHTML = 'male';
						else
							col6.innerHTML = 'female';
						col7 =  document.createElement("td"); col7.innerHTML = data.clients[i].birthday;
						col8 =  document.createElement("td"); col8.innerHTML = data.clients[i].job;
						col9 =  document.createElement("td");
						b = document.createElement("button");
						b.setAttribute('type','button');
						b.setAttribute('onclick','javascript:window.location.href="ViewOrdersHistory?userMail='+data.clients[i].email+'"');
						b.setAttribute('class','btn btn-success');
						col9.appendChild(b);
						b.innerHTML = 'View Orders History';
						
					
						row.appendChild(col1);
						row.appendChild(col2);
						row.appendChild(col3);
						row.appendChild(col4);
						row.appendChild(col5);
						row.appendChild(col6);
						row.appendChild(col7);
						row.appendChild(col8);
						row.appendChild(col9);
						tbody.appendChild(row);
						
					}
					
					//darw the pagination
					var searchpage = document.getElementById("pagination");
					$("#pagination").empty();
					makePagination(data.usersCount,3);
				}
			});   
	}
}

