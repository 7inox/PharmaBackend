# Resultados de pruebas

Ejecución del 12 de noviembre de 2026, hora de Lima. Código probado: backend `846793b` y frontend `9fa985a`. Entorno de laboratorio con Oracle Free, Redis 7 y Angular 22.

| Comprobación | Resultado | Duración |
|---|---|---:|
| Playwright, corrida 1 | 12/12 | |
| Playwright, corrida 2 | 12/12 | |
| Playwright, corrida 3 | 12/12 | |
| Playwright, total | 36 aprobadas, 0 fallidas, 0 reintentos | 58,0 s |
| Newman | 11 peticiones, 13 aserciones, 0 fallos | 1,197 s |

El [informe HTML capturado](evidencias/s14-estabilizacion/playwright.png) muestra el total. La [colección Postman](postman/PharmaBackend.postman_collection.json) cubre 200, 400, 401, 403, 404 y 409. El flujo de venta y los dos nuevos eventos también comprobaron respuestas 201.

Los cinco casos añadidos verifican stock insuficiente durante la selección, venta vacía, filtros y recarga de Auditoría, restricción para vendedor y trazabilidad de altas de clientes y bajas de categorías.

La primera ejecución que atravesó el reinicio del backend se descartó. Con los servicios arriba, la prueba reveló que la etiqueta del filtro Acción se asociaba de forma implícita. El frontend `9fa985a` vincula etiqueta y selector por ID. Las tres corridas finales se ejecutaron juntas con `npx playwright test --repeat-each=3`.

## Recorridos de demostración

| Recorrido | Fecha Lima | Venta | Flujo | Total | Stock |
|---|---|---:|---:|---:|---|
| 1 | 12/11/2026 | 20018 | 2,403 s | 3,110 s | 10 → 8 |
| 2 | 12/11/2026 | 20019 | 2,512 s | 3,190 s | 10 → 8 |
| 3, grabado | 12/11/2026 | 20020 | 12,880 s | 46,143 s | 10 → 8 |

Cada recorrido automatizado abrió una sesión nueva y creó datos de prueba propios sobre el mismo esquema. El tercero añadió pausas para la grabación. Los resultados no equivalen a ensayos manuales del expositor ni a tres reinstalaciones de la base. La grabación usa ng serve. El build de producción se verificó por separado.
