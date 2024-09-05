// document.getElementById('loginForm').addEventListener('submit', async (e) => {
//     e.preventDefault();

//     const username = document.getElementById('username').value;
//     const password = document.getElementById('password').value;

//     try {
//         const response = await fetch('http://localhost:8080/api/login', {
//             method: 'POST',
//             headers: { 'Content-Type': 'application/json' },
//             body: JSON.stringify({ username, password })
//         });

//         if (response.ok) {
//             const data = await response.json();
//             localStorage.setItem('token', data.token);
//             window.location.href = 'home.html';
//         } else {
//             alert('Credenciales inválidas');
//         }
//     } catch (error) {
//         console.error('Error al hacer login:', error);
//     }
// });
