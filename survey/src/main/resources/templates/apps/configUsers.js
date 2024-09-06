const API_URL_USER = 'http://localhost:8080/api/auth/user'; 
const saltRounds = 10; // Definir el número de rondas de sal

document.getElementById('loginForm1').addEventListener('submit', async (e) => {
    e.preventDefault();

    const password1 = document.getElementById('password1').value;
    const password2 = document.getElementById('password2').value;

    if (password1 !== password2) {
        alert('Las contraseñas no coinciden');
        return;
    }

    try {
        const hashedPassword = await bcrypt.hash(password1, saltRounds);
        alert("Contraseña hasheada correctamente");

        const user = {
            username: document.getElementById('usernameP').value,
            password: hashedPassword,
            isEnable: true,
            accountNoExpired: true,
            accountNoLocked: true,
            credentialNoExpired: true,
            roles: "User"
        };

        alert("Se seleccionaron bien los atributos");

        const response1 = await fetch(API_URL_USER, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(user)
        });

        if (response1.ok) {
            alert('Usuario creado con éxito');
            document.getElementById('loginForm1').reset();
            // openSingIn(); // Asegúrate de definir esta función si la necesitas
        } else {
            const errorData = await response1.json();
            alert(`Error: ${errorData.message || 'Error desconocido'}`);
        }
    } catch (error) {
        alert(`Error: ${error.message}`);
    }
});
