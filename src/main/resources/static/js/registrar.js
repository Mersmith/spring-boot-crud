// Call the dataTables jQuery plugin
$(document).ready(function() {

});

async function registrarUsuario(){
    let datos = {};
    datos.nombre = document.getElementById('nombre').value;
    datos.apellido = document.getElementById('apellido').value;
    datos.email = document.getElementById('email').value;
    datos.telefono = document.getElementById('telefono').value;
    datos.password = document.getElementById('password').value;

    console.log(datos);

    const request = await fetch('api/usuario',{
    method: 'POST',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        },
    body: JSON.stringify(datos)
    });

    alert("Registrado correctamente.");
    window.location.href = 'login.html';
}