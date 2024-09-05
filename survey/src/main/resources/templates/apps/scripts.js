document.addEventListener('DOMContentLoaded', () => {
    const loginLink = document.getElementById('loginLink');
    const profileSection = document.getElementById('profile');
    const loginSection = document.getElementById('login');
    const homeSection = document.getElementById('home');
    const profileNameValue = document.getElementById('profileNameValue');

    loginLink.addEventListener('click', () => {
        loginSection.style.display = 'block';
        homeSection.style.display = 'none';
        profileSection.style.display = 'none';
    });

// document.getElementById('loginForm').addEventListener('submit', async (e) => {
//         e.preventDefault();
        
//         const email = document.getElementById('loginEmail').value;
//         const password = document.getElementById('loginPassword').value;

//         try {
//             const response = await fetch('http://localhost:8080/api/login', {
//                 method: 'POST',
//                 headers: { 'Content-Type': 'application/json' },
//                 body: JSON.stringify({ email, password })
//             });

//             if (response.ok) {
//                 const data = await response.json();
//                 localStorage.setItem('token', data.token);
//                 loginSection.style.display = 'none';
//                 homeSection.style.display = 'block';
//                 profileSection.style.display = 'block';
//                 fetchProfile();
//             } else {
//                 alert('Error al iniciar sesión');
//             }
//         } catch (error) {
//             alert(error.message);
//         }
// });

async function fetchProfile() {
        const token = localStorage.getItem('token');

        try {
            const response = await fetch('http://localhost:8080/api/profile', {
                headers: { 'Authorization': `Bearer ${token}` }
            });

            if (response.ok) {
                const profile = await response.json();
                profileNameValue.textContent = profile.name;
            } else {
                alert('Error al obtener perfil');
            }
        } catch (error) {
            alert(error.message);
        }
    }

    // Muestra la sección de inicio por defecto
    homeSection.style.display = 'block';
    profileSection.style.display = 'none';
});

