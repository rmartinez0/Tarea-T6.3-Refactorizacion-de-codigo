# Informe de Refactorización: Gestión de Reservas de Hotel

**Autor:** Rubén Martínez Pérez


---

## 1. Introducción
En este documento se detallan los *Code Smells* identificados en el proyecto original de gestión de reservas hoteleras y las técnicas de refactorización aplicadas. Para llevar a cabo este proceso de forma segura y eficiente, se han utilizado principalmente las herramientas de refactorización automatizada que proporciona el IDE IntelliJ IDEA, garantizando que el comportamiento externo del programa se mantiene intacto .

---

## 2. Refactorizaciones Aplicadas por Clase

### 2.1. Clase `Hotel.java`
* **Code Smell:** Condicionales Complejos / Arrow Anti-Pattern.
    * **Problema:** El método `reservarHabitacion` tenía múltiples niveles de `if` anidados (`if-else`) que dificultaban seriamente su lectura.
    * **Solución (Invert If / Early Return):** Se han invertido las condiciones usando las sugerencias del IDE  y se han eliminado los bloques `else` para forzar salidas tempranas (*Early Return*).
    * **Evidencia:**
     <img width="670" height="420" alt="image" src="https://github.com/user-attachments/assets/d9a7b456-dcf1-4ded-a62a-d8d2466f8423" />
      <img width="669" height="420" alt="image" src="https://github.com/user-attachments/assets/ed790aef-6c54-4ca0-a850-         23efd9fdce27" />
      <img width="662" height="295" alt="image" src="https://github.com/user-attachments/assets/4a3a36e6-c2cb-4d60-8ed2-798631c6cf44" />
      <img width="646" height="270" alt="image" src="https://github.com/user-attachments/assets/a69c30a7-e687-4102-89ca-bc6f61bfa58d" />
      <img width="664" height="354" alt="image" src="https://github.com/user-attachments/assets/06308eef-c2de-4cde-82b5-088012152e30" />
      <img width="610" height="313" alt="image" src="https://github.com/user-attachments/assets/4a50584d-ed64-4014-bd55-0c3ea648cf2f" />

* **Code Smell:** Método Largo (Long Method).
    * **Problema:** La comprobación de si un cliente pasaba a ser VIP estaba mezclada con la lógica de reserva dentro de `reservarHabitacion`.
    * **Solución (Extract Method):** Se ha extraído esta lógica a un nuevo método privado llamado `comprobarYActualizarVip()`.
    * **Evidencia:**
    **Antes**
      <img width="680" height="299" alt="image" src="https://github.com/user-attachments/assets/93a8abc3-0a9e-4e82-bea4-edd22bde09db" />
      <img width="670" height="322" alt="image" src="https://github.com/user-attachments/assets/d50f22bb-6c10-4eaa-a6cd-64fc831932f1" />

* **Code Smell:** Intimidad Inapropiada (Falta de encapsulamiento).
    * **Problema:** La clase `Hotel` accedía directamente a los atributos públicos de `Cliente` (ej. `cliente.nombre`, `cliente.esVip`).
    * **Solución:** Se cambiaron los atributos a `private` y se modificó `Hotel` para usar los *Getters* y *Setters* (`getNombre()`, `setEsVip()`, etc.).
    * **Evidencia:**
    **Antes**
     <img width="740" height="72" alt="image" src="https://github.com/user-attachments/assets/1ef5d161-e8c4-4a8a-9516-e80182028c07" />
    **Despues**
     <img width="746" height="61" alt="image" src="https://github.com/user-attachments/assets/c56cac0a-075d-4179-beea-0e01b07ccf95" />
    **Antes**
      <img width="819" height="388" alt="image" src="https://github.com/user-attachments/assets/81881c64-ab22-454d-b86a-0b3ed9132adc" />
    **Despues**
     <img width="761" height="364" alt="image" src="https://github.com/user-attachments/assets/a0dc4b5b-d8d7-4265-99f8-49af701715f4" />
    **Antes**
     <img width="762" height="99" alt="image" src="https://github.com/user-attachments/assets/8c78f6d6-2bd3-478b-bdf5-f6273b96e0ed" />
    **Despues**
     <img width="768" height="75" alt="image" src="https://github.com/user-attachments/assets/7e67c27c-d29c-4666-a418-eb7d9b799b89" />
   **Antes**
     <img width="762" height="186" alt="image" src="https://github.com/user-attachments/assets/dd0187e0-69a4-472d-8f12-d2fd53b6bb21" />
    **Despues**
     <img width="770" height="217" alt="image" src="https://github.com/user-attachments/assets/906700a9-a9d9-4850-9c18-cf5c87cd3ad5" />
  
* **Code Smell:** Código Muerto (Dead Code).
    * **Problema:** El método `registrarHabitaciones` estaba definido pero nunca se invocaba.
    * **Solución (Safe Delete):** Se procedió a su eliminación.
    * **Evidencia:**
  <img width="767" height="130" alt="image" src="https://github.com/user-attachments/assets/6239e7e4-37a5-4e4f-9b84-ed708523abef" />

* **Code Smell:** Nombres poco descriptivos.
    * **Problema:** En el método `listarReservas`, el bucle `forEach` usaba las variables genéricas `key` y `value`.
    * **Solución (Rename):** Se han renombrado a `numeroHabitacion` y `listaReservas` para dotar de contexto al código.
    * **Evidencia:**
     **Antes**
     <img width="758" height="203" alt="image" src="https://github.com/user-attachments/assets/20321d7a-00f0-46d2-92a8-65c0e7281863" />
     **Despues**
     <img width="762" height="202" alt="image" src="https://github.com/user-attachments/assets/2dc7e6a9-da00-4ac1-a876-673282c0566b" />
      
### 2.2. Clase `Reserva.java`
* **Code Smell:** Nombres poco descriptivos.
    * **Problema:** Variables como `n`, `pb` y `pf` no aportaban significado.
    * **Solución (Rename):** Se han renombrado a `numeroNoches`, `precioBaseTotal` y `precioFinal`.
    * **Evidencia:**
      **Antes**
      <img width="765" height="141" alt="image" src="https://github.com/user-attachments/assets/69bb332d-2f67-448e-b570-9db28b72485f" />
      **Despues**
      <img width="748" height="147" alt="image" src="https://github.com/user-attachments/assets/569d551a-204b-4733-a3ae-9ebe0fc6f9a9" />

* **Code Smell:** Números Mágicos (Magic Numbers).
    * **Problema:** Se usaban valores literales como `0.9` y `0.95` para los descuentos sin explicar su origen.
    * **Solución (Introduce Constant):** Se han extraído a constantes descriptivas: `DESCUENTO_VIP` y `DESCUENTO_POR7_DIAS`.
    * **Evidencia:**
    * **Antes**
      <img width="240" height="74" alt="image" src="https://github.com/user-attachments/assets/b635527a-26f8-472f-9856-f72ee8c1ea7a" />
      **Despues**
      <img width="499" height="97" alt="image" src="https://github.com/user-attachments/assets/1a1f9f84-e897-4024-8fcc-057abce74949" />
      **Antes**
      <img width="245" height="69" alt="image" src="https://github.com/user-attachments/assets/2b1c79ca-c115-494f-ad47-16f5b35903e8" />
      **Despues**
      <img width="383" height="72" alt="image" src="https://github.com/user-attachments/assets/fd4bcd9c-979e-448f-b152-279f23f2e78d" />

* **Code Smell:** Bug potencial.
    * **Problema:** El cálculo de días restaba los días del año manualmente, lo que provocaba bugs en reservas entre distintos años (ej. fin de año).
    * **Solución:** Uso de la API estándar de fechas con `ChronoUnit.DAYS.between()`.
    * **Evidencia:**
    **Antes**
      <img width="758" height="49" alt="image" src="https://github.com/user-attachments/assets/a332cbb0-300c-4582-8e6c-5341b6007ae6" />
      **Despues**
     <img width="765" height="40" alt="image" src="https://github.com/user-attachments/assets/480216e6-39e1-4a8f-b59f-c54fef0a7f0b" />

* **Code Smell:** Responsabilidad Única (SRP) y Método Largo.
    * **Problema:** El método `mostrarReserva()` pintaba datos por consola mediante muchos `System.out.println()`, mezclando el modelo con la vista.
    * **Solución:** Se eliminó el método y se generó el método estándar `toString()` usando el IDE.
    * **Evidencia:**
     **Antes**
      <img width="735" height="209" alt="image" src="https://github.com/user-attachments/assets/9591d3ae-1430-42c2-9f2b-5625661c0b40" />
      **Despues**
      <img width="671" height="230" alt="image" src="https://github.com/user-attachments/assets/744702aa-915c-4eba-a9d7-9ae1369158fb" />

### 2.3. Clase `Main.java`
* **Code Smell:** Código Duplicado (Duplicated Code).
    * **Problema:** La petición de fechas por teclado se repetía de forma idéntica para la fecha de entrada y la de salida.
    * **Solución (Extract Method / Introduce Parameter):** Se creó el método `pedirFecha(String tipoFecha)` eliminando la duplicidad.
    * **Evidencia:**
      **Antes**
      <img width="676" height="485" alt="image" src="https://github.com/user-attachments/assets/23c97b33-10c0-41e0-bce5-f884347341bf" />
      **Despues**
      <img width="655" height="460" alt="image" src="https://github.com/user-attachments/assets/83e3860e-5189-445d-a6e0-5c3708147432" />

* **Code Smell:** Long Method.
    * **Problema:** El método `main` y su bloque `switch` contenían demasiada lógica de negocio y entrada/salida.
    * **Solución (Extract Method):** Se extrajo la lógica hacia métodos especializados: `registrarNuevoCliente()`, `registrarHabitacion()`, `realizarReserva()` y `cargarDatos()`.
    * **Evidencia:**
      **Antes**
      <img width="549" height="745" alt="image" src="https://github.com/user-attachments/assets/4cb26dec-fa4e-4371-941d-3006d0a15a07" />
      **Despues**
      <img width="468" height="700" alt="image" src="https://github.com/user-attachments/assets/24003da2-94e0-440b-95d8-f6656dccb446" />
      **Antes**
      <img width="674" height="160" alt="image" src="https://github.com/user-attachments/assets/fb04464a-44a6-43e8-8649-bb508e1a7177" />
      **Despues**
      <img width="670" height="210" alt="image" src="https://github.com/user-attachments/assets/5b1bfa6c-2757-4176-b5e8-a97ffa592652" />
      **Antes**
      <img width="673" height="304" alt="image" src="https://github.com/user-attachments/assets/e35f5a22-b9dc-46b5-ae81-e76e543049ed" />
      **Despues**
      <img width="669" height="340" alt="image" src="https://github.com/user-attachments/assets/77b605be-89c2-4a8c-8566-6586f37744ba" />
      **Antes**
      <img width="672" height="367" alt="image" src="https://github.com/user-attachments/assets/c8480df0-72c3-4e73-a47d-17a8d62515f9" />
      **Depues**
      <img width="675" height="407" alt="image" src="https://github.com/user-attachments/assets/b2e80376-4cf3-46c0-8bfb-6d8eea90970f" />

* **Code Smell:** Código Muerto (Dead Code).
    * **Problema:** Variables declaradas sin uso (`String tipo` global) e importaciones innecesarias (`java.util.Locale`, `Date`).
    * **Solución (Optimize Imports / Safe Delete):** Eliminación del ruido visual del archivo.
    * **Evidencia:**
     **Antes**
      <img width="337" height="91" alt="image" src="https://github.com/user-attachments/assets/4e274536-f48d-4c07-ab6c-d36de80d7306" />
      **Despues**
      <img width="266" height="54" alt="image" src="https://github.com/user-attachments/assets/07814bfb-dc34-46ff-a2d0-5e0a7955ab98" />

### 2.4. Clase `Cliente.java`
* **Code Smell:** Condicionales inútiles .
    * **Problema:** El constructor comprobaba con un `if(validarNombre(...))` métodos que ya lanzaban una excepción si fallaban. El `if` nunca iba a evaluar a `false`.
    * **Solución (Simplify):** Se han eliminado los bloques `if` inútiles dejando un código lineal.
    * **Evidencia:**
     **Antes**
      <img width="657" height="231" alt="image" src="https://github.com/user-attachments/assets/f9fa99b2-c9d2-47ce-a096-23e418817bf7" />
      **Despues**
      <img width="673" height="246" alt="image" src="https://github.com/user-attachments/assets/82a1079f-12c6-490c-93e0-6fa76286e346" />

* **Code Smell:** Mal diseño de firma de método.
    * **Problema:** Los métodos de validación devolvían `boolean` y un redundante `return true;` cuando en realidad funcionaban lanzando excepciones ante los errores.
    * **Solución (Change Signature):** Se ha cambiado el tipo de retorno a `void` y eliminado los `return`.
    * **Evidencia:**
     **Antes**
      <img width="674" height="437" alt="image" src="https://github.com/user-attachments/assets/4f386511-7601-4458-82e6-4a980e37b4fb" />
      **Despues**
      <img width="667" height="406" alt="image" src="https://github.com/user-attachments/assets/c503c6c5-339e-4917-9bd4-1d165fa9303b" />

### 2.5. Clase `Habitacion.java`
* **Code Smell:** Bug lógico.
    * **Problema:** Al reservar una habitación, el atributo se reasignaba a `disponible = true`, causando un error en la persistencia del estado.
    * **Solución:** Se corrigió la asignación a `disponible = false;`.
    * **Evidencia:**
      **Antes**
      <img width="633" height="155" alt="image" src="https://github.com/user-attachments/assets/367c8970-1171-4cd0-b9eb-c7afd23cc71a" />
      **Despues**
      <img width="660" height="144" alt="image" src="https://github.com/user-attachments/assets/472b9591-b98f-4503-adaf-5ab903018d8f" />

---

## 3. Conclusión
Gracias a la identificación sistemática de *Code Smells* y a la aplicación de técnicas de refactorización respaldadas por el IDE, el proyecto ha mejorado drásticamente su legibilidad, mantenibilidad y robustez. Se han corregido bugs subyacentes, eliminado el código muerto y garantizado el principio de Responsabilidad Única y el encapsulamiento de datos, dejando una arquitectura más limpia y preparada para escalar.
