function showByName(name) {
    $('.'+name).show();
}

function hideByName(name) {
    $('.'+name).hide();
}

$(document).ready(function(){
    $(".collapsible .member-heading").click(function(){
        $(this).parent().find(".member-details").toggle();
    });
    $('#filter').change(function(){
        $(this).find('.filter input:checkbox').each(function(){
            var name = $(this).attr('id');
            if ($(this).is(':checked')) {
                $(this).parent().parent().css('background-color', 'white');
                $(this).parent().parent().css('border-color', '#777');
                showByName(name);
            } else {
                $(this).parent().parent().css('background-color', '#EBEBEB');
                $(this).parent().parent().css('border-color', '#AAA');
                hideByName(name);
            }
        });
    }).change();
    $('#show-all').click(function(){
        $('.filter input:checkbox').attr('checked',true);
        $('#filter').change();
    });
    $('#hide-all').click(function(){
        $('.filter input:checkbox').attr('checked',false);
        $('#filter').change();
    });
});

