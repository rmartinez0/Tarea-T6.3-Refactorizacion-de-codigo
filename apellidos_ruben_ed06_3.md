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
     ![alt text](image-2.png)
     ![alt text](image-3.png)
     ![alt text](image-4.png)
     ![alt text](image-5.png)
     ![alt text](image-6.png)


* **Code Smell:** Método Largo (Long Method).
    * **Problema:** La comprobación de si un cliente pasaba a ser VIP estaba mezclada con la lógica de reserva dentro de `reservarHabitacion`.
    * **Solución (Extract Method):** Se ha extraído esta lógica a un nuevo método privado llamado `comprobarYActualizarVip()`.
    * **Evidencia:**
    **Antes**
    ![alt text](image-7.png)
    **Depues**
    ![alt text](image-8.png)

* **Code Smell:** Intimidad Inapropiada (Falta de encapsulamiento).
    * **Problema:** La clase `Hotel` accedía directamente a los atributos públicos de `Cliente` (ej. `cliente.nombre`, `cliente.esVip`).
    * **Solución:** Se cambiaron los atributos a `private` y se modificó `Hotel` para usar los *Getters* y *Setters* (`getNombre()`, `setEsVip()`, etc.).
    * **Evidencia:**
    **Antes**
    ![alt text](image-9.png)
    **Despues**
    ![alt text](image-10.png)
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
      ![alt text](image-11.png)
      

* **Code Smell:** Nombres poco descriptivos.
    * **Problema:** En el método `listarReservas`, el bucle `forEach` usaba las variables genéricas `key` y `value`.
    * **Solución (Rename):** Se han renombrado a `numeroHabitacion` y `listaReservas` para dotar de contexto al código.
    * **Evidencia:**
     **Antes**
      ![alt text](image-12.png)
      **Despues**
      ![alt text](image-13.png)

### 2.2. Clase `Reserva.java`
* **Code Smell:** Nombres poco descriptivos.
    * **Problema:** Variables como `n`, `pb` y `pf` no aportaban significado.
    * **Solución (Rename):** Se han renombrado a `numeroNoches`, `precioBaseTotal` y `precioFinal`.
    * **Evidencia:**
      **Antes**
      ![alt text](image-28.png)
      **Despues**
      ![alt text](image-29.png)

* **Code Smell:** Números Mágicos (Magic Numbers).
    * **Problema:** Se usaban valores literales como `0.9` y `0.95` para los descuentos sin explicar su origen.
    * **Solución (Introduce Constant):** Se han extraído a constantes descriptivas: `DESCUENTO_VIP` y `DESCUENTO_POR7_DIAS`.
    * **Evidencia:**
    * **Antes**
      ![alt text](image-22.png)
      **Despues**
      ![alt text](image-23.png)
      **Antes**
      ![alt text](image-24.png)
      **Despues**
      ![alt text](image-25.png)

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
      ![alt text](image-26.png)
      **Despues**
      ![alt text](image-27.png)


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
      ![alt text](image-30.png)
      **Despues**
      ![alt text](image-31.png)
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
