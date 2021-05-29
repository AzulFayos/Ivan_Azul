const URL = "http://localhost:8080/Ivan_Azul/pedidos/cliente";
const myModal = new bootstrap.Modal(document.getElementById("idModal")); // Para los mensajes de error y avisos
const modalWait = new bootstrap.Modal(document.getElementById("idModalWait")); // Para los mensajes de error y avisos

window.onload = init;
function init() {
  if (window.location.search != "") {
    const queryStr = window.location.search.substring(1);
    const parametro = queryStr.split("=");
    idcliente = parametro[1];

    rellenaPedidos(idcliente);
  } else {
    muestraMsg(
      "¡M**rd!",
      "No he podido recupera este  Cliente " + error,
      false
    );
  }
  function rellenaPedidos() {
    const peticionHTTP = fetch(URL + "/" + idcliente);
  
    peticionHTTP
      .then((respuesta) => {
        if (respuesta.ok) {
          return respuesta.json();
        } else throw new Error("Return not ok");
      })
      .then((clientes) => {
        let tblBody = document.getElementById("id_tblPedidos");
        for (const cliente of clientes) {
          let fila = document.createElement("tr");
          let elemento = document.createElement("td");
          elemento.innerHTML = cliente.id;
          fila.appendChild(elemento);
          elemento = document.createElement("td");
          elemento.innerHTML = cliente.firstName;
          fila.appendChild(elemento);
          elemento = document.createElement("td");
          elemento.innerHTML = cliente.lastName;
          fila.appendChild(elemento);
          elemento = document.createElement("td");
          elemento.innerHTML = cliente.company;
          fila.appendChild(elemento);
          elemento = document.createElement("td");
          elemento.innerHTML = cliente.businessPhone ?? "";
          fila.appendChild(elemento);
          elemento = document.createElement("td");
          elemento.innerHTML = cliente.mobilePhone ?? "";
          fila.appendChild(elemento);
          elemento = document.createElement("td");
          elemento.innerHTML =
            `<button class="btn btn-link" onclick="editaCliente(${cliente.id})"><i class="bi-pencil"></i></button>` +
            `<button style="color:red;" class="btn btn-link"  onclick="borrarCliente(${cliente.id})"><i class="bi-x-circle"></i></button>`+
            `<button style="color:black;" class="btn btn-link"  onclick="pedidosCliente(${cliente.id})"><i class="bi bi-card-list"></i></button>`;
          fila.appendChild(elemento);
  
          tblBody.appendChild(fila);
        }
  
        // Todo ha ido bien hast aquí, habilito el boton de añadir cliente
  
        document.getElementById("idAddCliente").addEventListener("click", addCliente);
      })
      .catch((error) => {
        muestraMsg("¡M**rd!", "¡No he podido recuperar el listado de clientes!<br>" + error, false, "error");
      });
  }

      // Todo ha ido bien hast aquí, habilito el boton de añadir pedido

      document.getElementById("idAddPedido").addEventListener("click", addpedido);
    })
    .catch((error) => {
      muestraMsg("¡M**rd!", "¡No he podido recuperar el listado de pedidos!<br>" + error, false, "error");
    });


 
}

/**
 * Muestra un mensaje en el modal
 */
function muestraMsg(
  titulo,
  mensaje,
  okButton,
  tipoMsg,
  okMsg = "OK",
  closeMsg = "Close"
) {
  document.getElementById("idMdlOK").innerHTML = okMsg;
  document.getElementById("idMdlClose").innerHTML = closeMsg;

  myModal.hide();
  switch (tipoMsg) {
    case "error":
      {
        titulo =
          "<i style='color:red ' class='bi bi-exclamation-octagon-fill'></i> " +
          titulo;
      }
      break;
    case "question":
      {
        titulo =
          "<i style='color:blue' class='bi bi-question-circle-fill'></i> " +
          titulo;
      }
      break;
    default:
      {
        titulo =
          "<i style='color:green' class='bi bi-check-circle-fill'></i> " +
          titulo;
      }
      break;
  }
  document.getElementById("idMdlTitle").innerHTML = titulo;
  document.getElementById("idMdlMsg").innerHTML = mensaje;
  document.getElementById("idMdlOK").style.display = okButton
    ? "block"
    : "none";

  myModal.show();
}
