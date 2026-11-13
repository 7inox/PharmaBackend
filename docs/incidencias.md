# Registro de incidencias

| ID / fecha | Módulo / severidad | Pasos, esperado y observado | Causa, corrección y evidencia |
|---|---|---|---|
| INC-01 · 12/11 | Productos · Alta | Filtrar por una categoría que tiene registros en otra página. Se esperan todos sus productos. El filtro solo miraba la página visible. | Filtro local. Oracle filtra antes de paginar en backend `5cb445a` y frontend `707c796`. Ver flujo de venta E2E y captura de stock. |
| INC-02 · 12/11 | Ventas · Alta | Pedir 20 ventas. Se espera leer una página. Se recuperaba todo el conjunto antes de responder. | Recorte en Java. Backend `5cb445a` consulta IDs paginados y frontend `3312341` conserva el filtro. Ver paginación E2E y mediciones. |
| INC-03 · 12/11 | Nueva venta · Media | Abrir Nueva venta con miles de productos. Se esperan búsquedas acotadas. La SPA descargaba todos los catálogos. | Filtro en memoria. Backend `5cb445a` y frontend `3312341` trasladaron el filtro a Oracle con espera de 300 ms. Ver flujo de venta E2E. |
| INC-04 · 12/11 | Playwright · Media | Cambiar de vendedor a administrador durante la prueba de reporte. Se esperaba iniciar sesión. El usuario quedaba vacío intermitentemente. | La transición volvía a montar el formulario. Frontend `69f4470` usa otro contexto. Ver tres repeticiones del flujo. |
| INC-05 · 05/11 | Logging · Alta | Crear un cliente y revisar el log. Se esperaba un mensaje sin DNI. Se registraba el identificador personal. | El log interpolaba el DNI. Backend `2e310ce` elimina ese campo. Revisión del log de alta y E2E-15 para la bitácora. |
| INC-06 · 05/11 | Configuración · Media | Arrancar con perfil explícito y configuración Redis. Se esperaba respetar el entorno elegido. La configuración de perfiles estaba mezclada. | Backend `2b1fe5b` separa la activación de perfiles y las propiedades Redis. Ver arranque y salud del entorno de pruebas. |

Todas están cerradas. Por severidad, 3 altas y 3 medias. Por módulo, una en cada uno de los seis módulos de la tabla. Cero críticas o altas abiertas.
