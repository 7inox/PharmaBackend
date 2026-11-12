# Incidencias de la sesión 14

| ID | Módulo | Cómo se reproduce | Severidad | Causa | Corrección | Estado |
|---|---|---|---|---|---|---|
| INC-01 | Productos | Con más de una página, filtrar por una categoría cuyos productos están en otra página. | Alta | El filtro se ejecutaba en la SPA sobre los registros de la página abierta. | Backend `5cb445a` filtra antes de paginar. Frontend `707c796` envía el filtro al servidor y vuelve a página 1. | Cerrada |
| INC-02 | Ventas | Pedir 20 ventas de un rango amplio. Oracle devuelve todas las coincidencias y el controlador devuelve solo 20. | Alta | El controlador paginaba una lista ya cargada. | Backend `5cb445a` pagina IDs en Oracle y recupera los detalles de la página. Frontend `3312341` comparte el paginador y restablece la página al aplicar filtros. | Cerrada |
| INC-03 | Nueva venta | Abrir el formulario con 5.000 productos. La SPA solicita todas las páginas para habilitar las búsquedas. | Media | El formulario filtraba catálogos completos en memoria. | Backend `5cb445a` expone búsquedas acotadas. Frontend `3312341` espera 300 ms y cancela consultas anteriores. | Cerrada |

Las pruebas de paginación y de venta en `pharma-frontend/e2e` cubren los tres cambios.

