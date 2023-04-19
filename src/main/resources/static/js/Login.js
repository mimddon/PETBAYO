const form = document.querySelector('form');
form.addEventListener('submit', (event) => {
  event.preventDefault(); // 기본 동작 방지

  // 입력된 이메일과 비밀번호 값 가져오기
  const email = document.getElementById('email').value;
  const password = document.getElementById('pw').value;

  // 입력된 이메일과 비밀번호를 서버로 전송하는 로직 구현
  const email = document.getElementById('email').value;
  const password = document.getElementById('pw').value;

  const formData = new FormData();
  formData.append('email', email);
  formData.append('password', password);

  fetch('/login', {
    method: 'POST',
    body: formData
  })
    .then(response => response.json())
    .then(data => {
      // 서버 응답에 대한 처리
    })
    .catch(error => {
      console.error('Error:', error);
    });

  // 로그인 성공 시 메인 페이지로 이동
  window.location.href = 'main';
});
