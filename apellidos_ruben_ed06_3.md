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
    ![alt text](image-14.png)
    **Despues**
    ![alt text](image-15.png)
    **Antes**
    ![alt txt](image-16.png)
    **Despues**
    ![alt text](image-17.png)
    **Antes**
    ![alt text](image-20.png)
    **Despues**
    ![alt text](image-21.png)

* **Code Smell:** Código Muerto (Dead Code).
    * **Problema:** El método `registrarHabitaciones` estaba definido pero nunca se invocaba.
    * **Solución (Safe Delete):** Se procedió a su eliminación.
    * **Evidencia:**
  <img width="736" height="118" alt="image" src="https://github.com/user-attachments/assets/3930965e-7d2e-4554-bbbc-df38a44a9674" />

* **Code Smell:** Nombres poco descriptivos.
    * **Problema:** En el método `listarReservas`, el bucle `forEach` usaba las variables genéricas `key` y `value`.
    * **Solución (Rename):** Se han renombrado a `numeroHabitacion` y `listaReservas` para dotar de contexto al código.
    * **Evidencia:**
     **Antes**
      <img width="739" height="203" alt="image" src="https://github.com/user-attachments/assets/50234f46-a3b0-4e09-86de-0b659ff3f0d3" />
      **Despues**
      <img width="729" height="197" alt="image" src="https://github.com/user-attachments/assets/00c541b5-8f77-41fd-8aaa-5cee3d9bb7e4" />
      
### 2.2. Clase `Reserva.java`
* **Code Smell:** Nombres poco descriptivos.
    * **Problema:** Variables como `n`, `pb` y `pf` no aportaban significado.
    * **Solución (Rename):** Se han renombrado a `numeroNoches`, `precioBaseTotal` y `precioFinal`.
    * **Evidencia:**
      **Antes**
      <img width="662" height="126" alt="image" src="https://github.com/user-attachments/assets/79747cbc-151c-498e-8532-5241b189cd3d" />
      **Despues**
      <img width="664" height="132" alt="image" src="https://github.com/user-attachments/assets/5fa8e230-8e8e-4d85-9e46-3843e4935143" />


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
      ![alt text](image-18.png)
      **Despues**
      ![alt text](image-19.png)

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
      ![alt text](image-32.png)
      **Despues**
      ![alt text](image-33.png)

* **Code Smell:** Long Method.
    * **Problema:** El método `main` y su bloque `switch` contenían demasiada lógica de negocio y entrada/salida.
    * **Solución (Extract Method):** Se extrajo la lógica hacia métodos especializados: `registrarNuevoCliente()`, `registrarHabitacion()`, `realizarReserva()` y `cargarDatos()`.
    * **Evidencia:**
      **Antes**
      <img width="549" height="745" alt="image" src="https://github.com/user-attachments/assets/4cb26dec-fa4e-4371-941d-3006d0a15a07" />
      **Despues**
      <img width="468" height="700" alt="image" src="https://github.com/user-attachments/assets/24003da2-94e0-440b-95d8-f6656dccb446" />
      **Antes**
      ![alt text](image-36.png)
      **Despues**
      ![alt text](image-37.png)
      **Antes**
      ![alt text](image-34.png)
      **Despues**
      ![alt text](image-35.png)
      **Antes**
      ![alt text](image-38.png)
      **Depues**
      ![alt text](image-39.png)

* **Code Smell:** Código Muerto (Dead Code).
    * **Problema:** Variables declaradas sin uso (`String tipo` global) e importaciones innecesarias (`java.util.Locale`, `Date`).
    * **Solución (Optimize Imports / Safe Delete):** Eliminación del ruido visual del archivo.
    * **Evidencia:**
     **Antes**
      ![alt text](image-40.png)
      **Despues**
      ![alt text](image-41.png)


### 2.4. Clase `Cliente.java`
* **Code Smell:** Condicionales inútiles (Redundancia).
    * **Problema:** El constructor comprobaba con un `if(validarNombre(...))` métodos que ya lanzaban una excepción si fallaban. El `if` nunca iba a evaluar a `false`.
    * **Solución (Simplify):** Se han eliminado los bloques `if` inútiles dejando un código lineal.
    * **Evidencia:**
     **Antes**
      ![alt text](image-42.png)
      **Despues**
      ![alt text](image-43.png)

* **Code Smell:** Mal diseño de firma de método.
    * **Problema:** Los métodos de validación devolvían `boolean` y un redundante `return true;` cuando en realidad funcionaban lanzando excepciones ante los errores.
    * **Solución (Change Signature):** Se ha cambiado el tipo de retorno a `void` y eliminado los `return`.
    * **Evidencia:**
     **Antes**
      ![alt text](image-44.png)
      **Despues**
      ![alt text](image-45.png)

### 2.5. Clase `Habitacion.java`
* **Code Smell:** Bug lógico.
    * **Problema:** Al reservar una habitación, el atributo se reasignaba a `disponible = true`, causando un error en la persistencia del estado.
    * **Solución:** Se corrigió la asignación a `disponible = false;`.
    * **Evidencia:**
      **Antes**
      ![alt text](image-46.png)
      **Despues**
      ![alt text](image-47.png)

---

## 3. Conclusión
Gracias a la identificación sistemática de *Code Smells* y a la aplicación de técnicas de refactorización respaldadas por el IDE, el proyecto ha mejorado drásticamente su legibilidad, mantenibilidad y robustez. Se han corregido bugs subyacentes, eliminado el código muerto y garantizado el principio de Responsabilidad Única y el encapsulamiento de datos, dejando una arquitectura más limpia y preparada para escalar.
