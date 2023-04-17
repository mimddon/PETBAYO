$(document).ready(function() {
    $('#email-login-form').submit(function(e) {
        e.preventDefault();
        var email = $('#email').val();
        $.ajax({
            type: "POST",
            url: "/email-login",
            data: {
                email: email
            },
            success: function(result) {
                if (result === 'success') {
                    alert('로그인에 성공하였습니다.');
                    window.location.href = '#';
                } else {
                    alert('로그인에 실패하였습니다.');
                }
            },
            error: function(jqXHR, textStatus, errorThrown) {
                alert('서버와의 통신이 실패하였습니다.');
            }
        });
    });
});
