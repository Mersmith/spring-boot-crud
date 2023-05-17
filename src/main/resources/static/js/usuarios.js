// Call the dataTables jQuery plugin
$(document).ready(function() {
    cargarUsuarios();
  $('#usuarios').DataTable();
});

async function cargarUsuarios(){
    const request = await fetch('api/usuarios',{
    method: 'GET',
    headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
        }
    });

    const usuarios = await request.json();

    let listadoHtml = '';

    for(let usuario of usuarios){
        let boton_eliminar = '<a href="#" onclick="eliminarUsuario('+ usuario.id +')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

        let usuarioHtml = '<tr><td>'+
        usuario.id +'</td><td>'+
        usuario.nombre +'</td><td>'+
        usuario.apellido +'</td><td> '+
        usuario.telefono +'</td><td>'+
        boton_eliminar +'</td></tr>';

        listadoHtml +=usuarioHtml;
    }

    console.log(usuarios);
    document.querySelector('#usuarios tbody').outerHTML = listadoHtml;
}

async function eliminarUsuario(id){
    //alert(id);

    if(!confirm('¿Desea eliminar?')){
        return;
    }

    const request = await fetch('api/usuario/' + id,{
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': localStorage.token
            }
        });

        location.reload();
}
