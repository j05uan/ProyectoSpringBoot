document.addEventListener('DOMContentLoaded', () => {
    const loginContainer = document.getElementById('login-container');
    const appContainer = document.getElementById('app-container');
    const loginForm = document.getElementById('loginForm');
    const loginMessage = document.getElementById('loginMessage');
    // const profileLink = document.getElementById('profile');
    appContainer.style.display = 'none';

    function isAuthenticated() {
        return localStorage.getItem('token') !== null;
    }

    
    function handleAuthentication() {
        if (isAuthenticated()) {
            loginContainer.style.display = 'none';
            appContainer.style.display = 'block';
        } else {
            loginContainer.style.display = 'block';
            appContainer.style.display = 'none';
        }
    }

    loginForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        const username = document.getElementById('username').value;
        const password = document.getElementById('password').value;

        try {
            const response = await fetch('http://localhost:8080/auth/log-in', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ username, password }),
            });

            if (!response.ok) {
                throw new Error('Error en el inicio de sesión');
            }

            const result = await response.json();
            localStorage.setItem('token', result.token); 
            handleAuthentication(); 
        } catch (error) {
            loginMessage.textContent = 'Error al iniciar sesión: ' + error.message;
        }
    });

    function handleLogout() {
        localStorage.removeItem('token');
        handleAuthentication(); 
    }


    profileLink.addEventListener('click', () => {
        if (!isAuthenticated()) {
            loginMessage.textContent = 'Debes iniciar sesión para acceder.';
            loginContainer.style.display = 'block';
            appContainer.style.display = 'none';
        }
    });

    handleAuthentication();
});


