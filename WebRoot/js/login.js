$(document).ready(function(){
    startCount();
});

function  startCount(){
    var endDays=3;
    var endPersons=5;
    var endCodeline=10000;
    var endEffort=100000;
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
    }, 300);

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
    }, 200);

    var codelines = setInterval(function ()
    {
        if (currentCodeline === endCodeline)
        {
            clearInterval(codelines);
        }
        else
        {
            currentCodeline=currentCodeline+50;
            $('#codeline').text(currentCodeline).animate();
        }
    },2);

    var efforts = setInterval(function ()
    {
        if (currentEffort === endEffort)
        {
            clearInterval(efforts);
        }
        else
        {
            currentEffort=currentEffort+400;
            $('#effort').text(currentEffort).animate();
        }
    },2);
}