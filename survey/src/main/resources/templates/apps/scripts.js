

const loginC = document.getElementById('login-container');
const contenedorJP = document.getElementById('contenedor');
const SignC = document.getElementById('createUserForm');

const openSingIn =()=>{
    loginC.style.display='grid';
    loginC.style.placeItems='center';
    contenedorJP.style.display='none';
}

const openSingUp =()=>{
    SignC.style.display='grid';
    loginC.style.display='none';
    SignC.style.placeItems='center';
    contenedorJP.style.display='none';
}