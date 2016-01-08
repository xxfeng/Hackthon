$(document).ready(function(){
    startCount();

    $(".btn-sign-up").click(function(){
        window.location.href='hacksonsignin.html';
    });
});

function  startCount(){
    var endDays=56;
    var endPersons=2;
    var endCodeline=18;
    var endEffort=3980;
    var currentDays=0;
    var currentPersons=0;
    var currentCodeline=0;
    var currentEffort=0;

    var days = setInterval(function ()
    {
        if (currentDays === endDays)
        {
            clearInterval(days);
        }
        else
        {
            currentDays++;
            $('#days').text(currentDays).animate();
        }
    }, 100);

    var persons = setInterval(function ()
    {
        if (currentPersons === endPersons)
        {
            clearInterval(persons);
        }
        else
        {
            currentPersons++;
            $('#persons').text(currentPersons).animate();
        }
    }, 300);

    var codelines = setInterval(function ()
    {
        if (currentCodeline === endCodeline)
        {
            clearInterval(codelines);
        }
        else
        {
            currentCodeline++;
            $('#codeline').text(currentCodeline).animate();
        }
    },100);
    var codelines = setInterval(function ()
    {
        if (currentCodeline === endCodeline)
        {
            clearInterval(codelines);
        }
        else
        {
            currentCodeline++;
            $('#codeline').text(currentCodeline).animate();
        }
    },100);

    var efforts = setInterval(function ()
    {
        if (currentEffort === endEffort)
        {
            clearInterval(efforts);
        }
        else
        {
             currentEffort+=20;
            $('#effort').text(currentEffort).animate();
        }
    },0.01);
}