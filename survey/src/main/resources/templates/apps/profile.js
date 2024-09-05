// window.addEventListener('load', async () => {
//     const token = localStorage.getItem('token');

//     try {
//         const response = await fetch('http://localhost:8080/api/profile', {
//             headers: { 'Authorization': `Bearer ${token}` }
//         });

//         if (response.ok) {
//             const user = await response.json();
//             document.getElementById('userName').textContent = user.username;
//         } else {
//             alert('No autorizado');
//             window.location.href = 'login.html';
//         }
//     } catch (error) {
//         console.error('Error al cargar perfil:', error);
//     }
// });
