$(document).ready(function () {
    $(".editlink").on("click", function (e) {
        
        e.preventDefault();
        var dataset1 = $(this).prev(".datainfo");
        var savebtn = $(this).next(".savebtn");
        var theid = dataset1.attr("id");
        var newid1 = "#" + theid;
        var name1 = $(newid1).attr("name");

        var value1 = $(newid1).attr("value");
        $(newid1).prop("readonly", false);
        $(newid1).focus();
        var currval = dataset1.text();
        dataset1.empty();

        $(".editlink").css("display", "none");

        $(this).css("display", "none");
        savebtn.css("display", "block");
        
        
        

    });
    $(".savebtn").on("click", function (e) {
        e.preventDefault();
        elink = $(this).prev(".editlink");
        dataset = elink.prev(".datainfo");
        newid = dataset.attr("id");
        cinput = "#" + newid;
        einput = $(cinput);
        name = einput.attr("name");
        newvalue = einput.val();
        req = null;
        if (window.XMLHttpRequest)
            req = new XMLHttpRequest();
        else if (window.ActiveXobject)
            req = new ActiveXobject(Microsoft.XMLHTTP);
        req.onreadystatechange = handleReq;

        req.open("GET", "/BookStore/ProfileEditor?fieldname=" + name + "&newvalue=" + newvalue + "&Date =" + new Date(), true);
        req.send(null);

    });
});
function handleReq()
{
    if (req.readyState == 4)
    {
        if (req.status == 200)
        {

            console.log(req.responseText);
            if (req.responseText == "true")
            {
                einput.prop("readonly", true);
                $(".savebtn").css("display", "none");
                dataset.html(newvalue);
                elink.css("display", "block");
                $(".editlink").css("display", "block");
                $('#msg').text(name +" is Saved");
                $('#msg').delay(500).fadeIn();
                $('#msg').delay(1000).fadeOut();
            } else
            {
 $('#msg').text("not valid data");
 $('#msg').delay(1000).fadeIn();
 $('#msg').delay(1000).fadeOut();
            }
        } else {
            //$('#textfield1').val("Error Code : " + req.status);
        }
    }

}