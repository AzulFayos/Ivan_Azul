const BPUrl="http://localhost:8080/Ivan_Azul/pedidos";
const myModal = new bootstrap.Modal(document.getElementById("idModal")); // Para los mensajes de error y avisos
const modalWait = new bootstrap.Modal(document.getElementById("idModalWait")); // Para los mensajes de error y avisos

window.onload = init;

function init() {
    const queryStr = window.location.search.substring(1);
    const parametro = queryStr.split("=");
    let idpedido = parametro[1];
    const URL = "http://localhost:8080/Ivan_Azul/productos/pedido/";
    rellenapedido(idpedido,URL);
  function rellenapedido(idpedido,URL) {
  const peticionHTTP = fetch(URL+ idpedido);

  peticionHTTP
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("Return not ok");
    })
    .then((productos) => {
      let tblBody = document.getElementById("id_tblPedidos");
      for (const products of productos) {
        let fila = document.createElement("tr");
        let elemento = document.createElement("td");
        elemento.innerHTML = products.id;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = products.product_name;
        fila.appendChild(elemento);
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = products.list_price;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = products.category ?? "";
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = products.quantity_per_unit ?? "";
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML =
          `<button style="color:red;" class="btn btn-link"  onclick="borrarpedido(${products.id})"><i class="bi-x-circle"></i></button>`;
        fila.appendChild(elemento);

        tblBody.appendChild(fila);
      }
      

      // Todo ha ido bien hast aquí, habilito el boton de añadir pedido

      document.getElementById("idAddPedido").addEventListener("click", addpedido);
    })
    .catch((error) => {
      muestraMsg("¡M**rd!", "¡No he podido recuperar el listado de pedidos!<br>" + error, false, "error");
    });
}}

function editapedido(idpedido) {
  window.location.href = `editarPedido.html?idpedido=${idpedido}`;
}

function productospedido(idpedido) {
  window.location.href = `editarPedido.html?idpedido=${idpedido}`;
}

function addpedido() {
  window.location.href = "editarpedido.html";
}

function borrarpedido(idpedido) {
  muestraMsg(
    "¡Atención!",
    `¿Estas seguró de querer borrar el pedido ${idpedido}?`,
    true,
    "question",
    "Adelante con los faroles!",
    "Naaa, era broma..."
  );
  document.getElementById("idMdlOK").addEventListener("click", () => {
    
    borrarpedidoAPI(idpedido);
  });
}

function borrarpedidoAPI(idpedido) {
  myModal.hide();
  modalWait.show();
  opciones = {
    method: "DELETE", // Modificamos la BBDD
  };

  fetch(BPUrl + "/" + idpedido, opciones)
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else 
      {
        throw new Error(`Fallo al borrar, el servidor responde con ${respuesta.status}-${respuesta.statusText}`);
      }
        
    })
    .then((respuesta) => {
      modalWait.hide();
      muestraMsg(`¡pedido ${idpedido} Borrado!`, "¡A tomar por saco!", false, "success");
      document.getElementById('idMdlClose').addEventListener("click", () => {
        location.reload();
        document.getElementById('idMdlClose').removeEventListener("click");
      })
      
    })
    .catch((error) => {
      modalWait.hide();
      muestraMsg(
        "pedido NO borrado",
        "¿Es posible que este pedido tenga algún pedido? 🤔<br>" + error,
        false,
        "error"
      );
    });
}

/**
 * Muestra un mensaje en el modal
 */
function muestraMsg(titulo, mensaje, okButton, tipoMsg, okMsg = "OK", closeMsg = "Close") {
  document.getElementById("idMdlOK").innerHTML = okMsg;
  document.getElementById("idMdlClose").innerHTML = closeMsg;

  myModal.hide();
  switch (tipoMsg) {
    case "error":
      {
        titulo = "<i style='color:red ' class='bi bi-exclamation-octagon-fill'></i> " + titulo;
      }
      break;
    case "question":
      {
        titulo = "<i style='color:blue' class='bi bi-question-circle-fill'></i> " + titulo;
      }
      break;
    default:
      {
        titulo = "<i style='color:green' class='bi bi-check-circle-fill'></i> " + titulo;
      }
      break;
  }
  document.getElementById("idMdlTitle").innerHTML = titulo;
  document.getElementById("idMdlMsg").innerHTML = mensaje;
  document.getElementById("idMdlOK").style.display = okButton ? "block" : "none";

  myModal.show();
}
