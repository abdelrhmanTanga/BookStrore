$(document).ready(function () {
    var pw = $("#pw").val();


    $(".editlink").on("click", function (e) {
        e.preventDefault();
        var dataset = $(this).prev(".datainfo");
        var savebtn = $(this).next(".savebtn");
        var theid = dataset.attr("id");
        var newid = "#" + theid;
        var name = $(newid).attr("name");
        var value = $(newid).attr("value");
        $(newid).prop("readonly", false);
        var currval = dataset.text();
        dataset.empty();

        $(this).css("display", "none");
        savebtn.css("display", "block");

    });
    $(".savebtn").on("click", function (e) {
        e.preventDefault();
        var elink = $(this).prev(".editlink");
        var dataset = elink.prev(".datainfo");
        var newid = dataset.attr("id");
        var cinput = "#" + newid;
        var name = $(cinput).attr("name");
        var value = $(cinput).attr("value");
        /*        var fieldName = newid + "-form";
         var einput = $(cinput);
         var newval = einput.attr("value");
         var newid2 = "#" + newid;*/
        $(cinput).prop("readonly", true);
        $(this).css("display", "none");
        dataset.html(value);
        elink.css("display", "block");
       $.ajax({
            url:'ProfileEditor',
            data:{name:value},
            type:'post',
            success:function(data){
               //$('#somediv').text(saved); 
            }
         }
    );
    });
});