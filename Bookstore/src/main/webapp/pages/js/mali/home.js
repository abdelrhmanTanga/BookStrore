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





