
function executeSomething() {
    const modal = document.querySelector('.start_game');
    const reg = document.querySelector('.reg');
    modal.style.display = 'none';
    reg.style.display = 'block';

}

$(document).ready(function() {
    $("#myForm").submit(function(e) {
        e.preventDefault();

        $.ajax({
                url: 'startGame',
                data: {
                    inputNumber: $('#inputNumber').val()
                },
                success: function (responseText) {
                    $('#text_area').append(responseText + "\n");
                    document.getElementById("inputNumber").value = "";

                },
                error: function (jqXHR) {
                    alert("error:" + jqXHR.status + " exception:" + jqXHR.responseText);
                }
            });


    });

});


function check(input) {
    input.value = input.value.replace(/[^\d]/g, "");
    if (input.value.length===4) {
        for(let i=0;i<4;i++){
            for(let j=i+1;j<4;j++){

                if( input.value[i]==input.value[j]){
                    input.value = input.value.replace(/^[0-9]{4}$/, "");
                }
            }

        }
    }

}
$(document).ready(function() {
    $("#myForm1").submit(function(e) {
        e.preventDefault();

        $.ajax({
            url: 'startGame',
            data: {
                inputNumber: $('#1b').val()
            },
            success: function (responseText) {
                $('#text_area').append(responseText + "\n");
                document.getElementById("inputNumber").value = "";

            },
            error: function (jqXHR) {
                alert("error:" + jqXHR.status + " exception:" + jqXHR.responseText);
            }
        });


    });

});

$(document).ready(function() {
    $("#myForm2").submit(function(e) {
        e.preventDefault();

        $.ajax({
            url: 'startGame',
            data: {
                inputNumber: $('#2b').val()
            },
            success: function (responseText) {
                $('#text_area').append(responseText + "\n");
                document.getElementById("inputNumber").value = "";

            },
            error: function (jqXHR) {
                alert("error:" + jqXHR.status + " exception:" + jqXHR.responseText);
            }
        });


    });

});

$(document).ready(function() {
    $("#myForm3").submit(function(e) {
        e.preventDefault();

        $.ajax({
            url: 'startGame',
            data: {
                inputNumber: $('#3b').val()
            },
            success: function (responseText) {
                $('#text_area').append(responseText + "\n");
                document.getElementById("inputNumber").value = "";

            },
            error: function (jqXHR) {
                alert("error:" + jqXHR.status + " exception:" + jqXHR.responseText);
            }
        });


    });

});

$(document).ready(function() {
    $("#myForm4").submit(function(e) {
        e.preventDefault();

        $.ajax({
            url: 'startGame',
            data: {
                inputNumber: $('#4b').val()
            },
            success: function (responseText) {
                $('#text_area').append(responseText + "\n");
                document.getElementById("inputNumber").value = "";

            },
            error: function (jqXHR) {
                alert("error:" + jqXHR.status + " exception:" + jqXHR.responseText);
            }
        });


    });

});

