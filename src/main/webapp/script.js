document.addEventListener('DOMContentLoaded', function () {
    // Login form validation
    const loginForm = document.querySelector('#loginForm');
    if (loginForm) {
        loginForm.addEventListener('submit', function (event) {
            event.preventDefault();
            let isValid = true;
            const username = document.querySelector('#username').value.trim();
            const password = document.querySelector('#password').value.trim();
            let errorMessage = '';

            if (username.length < 3) {
                isValid = false;
                errorMessage += 'Username must be at least 3 characters long.<br>';
            }
            if (password.length < 6) {
                isValid = false;
                errorMessage += 'Password must be at least 6 characters long.<br>';
            }

            const errorDiv = document.querySelector('#loginError');
            if (!isValid) {
                errorDiv.innerHTML = errorMessage;
                errorDiv.style.display = 'block';
            } else {
                errorDiv.style.display = 'none';
                this.submit();
            }
        });
    }

    // Register form validation
    const registerForm = document.querySelector('#registerForm');
    if (registerForm) {
        registerForm.addEventListener('submit', function (event) {
            event.preventDefault();
            let isValid = true;
            const username = document.querySelector('#username').value.trim();
            const email = document.querySelector('#email').value.trim();
            const password = document.querySelector('#password').value.trim();
            let errorMessage = '';

            if (username.length < 3) {
                isValid = false;
                errorMessage += 'Username must be at least 3 characters long.<br>';
            }
            if (!email.match(/^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/)) {
                isValid = false;
                errorMessage += 'Please enter a valid email address.<br>';
            }
            if (password.length < 6) {
                isValid = false;
                errorMessage += 'Password must be at least 6 characters long.<br>';
            }

            const errorDiv = document.querySelector('#registerError');
            if (!isValid) {
                errorDiv.innerHTML = errorMessage;
                errorDiv.style.display = 'block';
            } else {
                errorDiv.style.display = 'none';
                this.submit();
            }
        });
    }
});