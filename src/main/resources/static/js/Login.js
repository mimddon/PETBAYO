$(function() {
    $('#loginForm').submit(function(e) {
        e.preventDefault();

        $.ajax({
            type: 'POST',
            url: '/login',
            contentType: 'application/json',
            data: JSON.stringify({
                email: $('#email').val(),
                password: $('#password').val(),
                rememberMe: $('#rememberMe').is(':checked')
            }),
            success: function(response) {
                if (response.success) {
                    // 로그인 성공 시 처리할 코드
                } else {
                    alert(response.message);
                }
            },
            error: function() {
                alert('서버와 통신 중 오류가 발생했습니다.');
            }
        });
    });
});