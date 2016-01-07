$(document).ready(function(){

    $(".menu-list li").click(function(e){
        $(".menu-list li").removeClass("active");
        $(this).addClass("active");
    });

        $('#myTab a:last').tab('show');

});
