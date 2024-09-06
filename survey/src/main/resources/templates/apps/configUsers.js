const API_URL_USER = 'http://localhost:8080/api/auth/user'; 

document.getElementById('createUserForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const password1 = document.getElementById('password1').value;
    const password2 = document.getElementById('password2').value;

    if (password1 !== password2) {
        alert('Las contraseñas no coinciden');
        return;
    }

    
    const saltRounds = 10; 
    const hashedPassword = await bcrypt.hash(password1, saltRounds);

    
    const user = {
        username: document.getElementById('usernameP').value,
        password: hashedPassword,
        isEnable: true, 
        accountNoExpired: true,
        accountNoLocked: true,
        credentialNoExpired: true
    };

    try {
        const response = await fetch(API_URL_USER, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(user)
        });

        if (response.ok) {
            alert('Usuario creado con éxito');
            document.getElementById('createUserForm').reset(); 
        } else {
            const errorData = await response.json();
            alert(`Error: ${errorData.message || 'Error desconocido'}`);
        }
    } catch (error) {
        alert(`Error: ${error.message}`);
    }
});