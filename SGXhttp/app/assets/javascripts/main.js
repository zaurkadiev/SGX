$(document).ready(function () {

    var access_token = $("#access_tocken_vk").val();

    $("#user-range-form-id").change(function () {
        $form = $(this);
        $("#user-range-form-label-id").text($form.val())
    });

    $("#search-form-id").click(function () {
        $(this).text("");
    });

    $("#clear-search-form-id").click(function () {
        console.log("Cleared input and result field");
        clearResultField();
        clearSearchForm();
        enableSearchForm();
    });

    // REQUEST RESPOND
    $("#form-id").on("ajax:success", function(evt, data, status, xhr){
        console.log("Success");
        $alert = $('#alert');
        var code = data.code;
        var message = data.message;
        switch(code){
            case "task_new":
                $alert.css('background-color', '#05a400');
                $(".loader2").fadeIn();
                break;
            case "task_existed":
                $alert.css('background-color', '#e20600');
        }
        $alert.text(message);
        $alert.fadeIn();
    }).on("ajax:error", function(evt, data, status, xhr){
        console.log("Error");
        alert("Error");
    });

    $('#alert').click(function () {
        $(this).fadeOut();
        q = 0;
    });


    $(document).on('click', ".user-card", function(){
        var userId = $(this).attr("id");
        var firstName = $(this).find("b").text();
        var lastName = $(this).find("span").text();
        $userIdForm =  $("#form-user-id");
        $searchForm = $("#search-form-id");
        $userIdForm.val(userId);
        $searchForm.val(firstName + " " + lastName + " (id=" + userId + ")");
        disableSearchForm();
        clearResultField();
        console.log("Clicked on user card id=" + userId + firstName + lastName);
    });

    // LOADING
    var canLoad = 1;
    $("#search-form-id").keypress(function(e) {
        if(e.which === 13 && canLoad === 1) {
            console.log("Search form pressed enter.");
            loadUsers();
        }
    });

    function loadUsers() {
        $form = $("#search-form-id");
        $container = $("#result-field-id");
        $container.html("<div style='padding-bottom: 30px; color: #666666; font-weight: bold'>Загрузка...</div>");
        var q = $form.val().split(" ");
        var paramsString = encodeParam(q);
        canLoad = 0;
        $.getJSON(decodeURIComponent("/search_user?"+"q="+paramsString), function( data ) {
            $container.html("");
            $users = data.response.items;

            if($users.length === 0)
                $container.html("<div style='padding-bottom: 30px; color: #666666; font-weight: bold'>Нет результатов.</div>");

            data.response.items.forEach(function (item) {
                console.log("Success. Respond received.");
                $container.append(
                    "<div class='col-sm-3'>"+
                        "<div class='user-card' id='"+item.id+"'>"+
                            "<img id='user-card-image-id' src='"+item.photo_100+"' class='img-circle user-card-img'>"+"<br>"+
                            "<b id='user-card-first-name-id'>"+item.first_name+"</b>"+"<br>"+
                            "<span id='user-card-last-name-id'>" +item.last_name + "</span>"+"<br>"+
                            "<a href='http://www.vk.com/id"+item.id+"'>На страницу<a>"+
                        "</div>"+
                    "</div>"
                );
            });
            canLoad = 1;
        }).error(function() {
            console.log("Error sending request.");
            alert("Ошибка отправки запроса. Попробуйте еще раз.");
            canLoad = 1;
        });
    }

    // TOOLS
    function encodeParam(data) {
        var str = "";
        for (var d in data)
            str+=data[d]+"_"
        str = str.slice(0, -1);
        return str
    }

    // disable search for
    function clearResultField() {
        $container = $("#result-field-id");
        $container.html("");
    }
    function clearSearchForm() {
        $searchForm = $("#search-form-id");
        $searchForm.val("");
        $userIdForm.val("");
    }
    function disableSearchForm() {
        $searchForm = $("#search-form-id");
        $searchForm.attr("disabled", true);
        $searchForm.css('border-color', '#0fa000');
        $searchForm.css('background-color', '#ddffce');
    }
    function enableSearchForm() {
        $searchForm = $("#search-form-id");
        $searchForm.attr("disabled", false);
        $searchForm.css('border-color', '#dddddd');
        $searchForm.css('background-color', '#ffffff');
    }
});