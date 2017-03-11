
function setData(name,quantity,author,description,price,category)
{
	document.getElementById("pname").value = name;
	document.getElementById("quantity").value = quantity;
	document.getElementById("author").value = author;
	
	document.getElementById("description").value = description;
	document.getElementById("price").value = price;
	
	document.getElementById("category").value = category;
}