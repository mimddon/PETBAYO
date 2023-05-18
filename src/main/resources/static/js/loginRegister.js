const signinbtn=document.querySelector('.signinbtn')
const signupbtn=document.querySelector('.signupbtn')
const formbox=document.querySelector('.form-box')
const body=document.querySelector('body')
signupbtn.onclick=function(){
    formbox.classList.add('active')
    body.classList.add('active')
}
signinbtn.onclick=function(){
    formbox.classList.remove('active')
    body.classList.remove('active')
}

const emailInput = document.getElementById('email-input');
const passwordInput = document.getElementById('password-input');
const loginButton = document.getElementById('login-button');
const errorMessage = document.getElementById('error-message');

loginButton.addEventListener('click', function() {
  const email = emailInput.value;
  const pwd = passwordInput.value;

  if (email === 'email' && password === 'pwd') {
    window.location.href = '/';

  } else {
    errorMessage.textContent = '아이디 또는 비밀번호를 잘못 입력하셨습니다.';
  }
});
