const URL = "http://localhost:8080/Ivan_Azul/productos";
const myModal = new bootstrap.Modal(document.getElementById("idModal")); // Para los mensajes de error y avisos
const modalWait = new bootstrap.Modal(document.getElementById("idModalWait")); // Para los mensajes de error y avisos

window.onload = init;

function init() {
  const peticionHTTP = fetch(URL);

  peticionHTTP
    .then((respuesta) => {
      if (respuesta.ok) {
        return respuesta.json();
      } else throw new Error("Return not ok");
    })
    .then((producto) => {
      let tblBody = document.getElementById("id_tblProductos");
      for (const products of producto) {
        let fila = document.createElement("tr");
        let elemento = document.createElement("td");
        elemento.innerHTML = products.id;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = products.product_name;
        fila.appendChild(elemento);
        elemento = document.createElement("td");
        elemento.innerHTML = products.standard_cost;
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
          `<button class="btn btn-link" onclick="editaproducto(${products.id})"><i class="bi-pencil"></i></button>` +
          `<button style="color:red;" class="btn btn-link"  onclick="borrarproducto(${products.id})"><i class="bi-x-circle"></i></button>`;
        fila.appendChild(elemento);

        tblBody.appendChild(fila);
      }

      // Todo ha ido bien hast aquí, habilito el boton de añadir producto

      document.getElementById("idAddproductos").addEventListener("click", addproducto);
    })
    .catch((error) => {
      muestraMsg("¡M**rd!", "¡No he podido recuperar el listado de producto!<br>" + error, false, "error");
    });
}

function editaproducto(idproducto) {
  window.location.href = `editarproducto.html?idproducto=${idproducto}`;
}

function addproducto() {
  window.location.href = "editarproducto.html";
}

function borrarproducto(idproducto) {
  muestraMsg(
    "¡Atención!",
    `¿Estas seguró de querer borrar el producto ${idproducto}?`,
    true,
    "question",
    "Adelante con los faroles!",
    "Naaa, era broma..."
  );
  document.getElementById("idMdlOK").addEventListener("click", () => {
    
    borrarproductoAPI(idproducto);
  });
}

function borrarproductoAPI(idproducto) {
  myModal.hide();
  modalWait.show();
  opciones = {
    method: "DELETE", // Modificamos la BBDD
  };

  fetch(URL + "/" + idproducto, opciones)
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
      muestraMsg(`¡producto ${idproducto} Borrado!`, "¡A tomar por saco!", false, "success");
      document.getElementById('idMdlClose').addEventListener("click", () => {
        location.reload();
        document.getElementById('idMdlClose').removeEventListener("click");
      })
      
    })
    .catch((error) => {
      modalWait.hide();
      muestraMsg(
        "producto NO borrado",
        "¿Es posible que este producto tenga algún pedido? 🤔<br>" + error,
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
