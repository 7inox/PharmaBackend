# Guion de demostración

| Tiempo | Bloque | Demostración |
|---|---|---|
| 0:00–1:00 | Contexto | Explicar cómo se conectan Angular, Spring Boot, Oracle y Redis. Mostrar la lámina de arquitectura. |
| 1:00–4:30 | Flujo | Entrar como vendedor, elegir cliente y producto, registrar dos unidades y abrir el comprobante. Volver a Productos y verificar el stock. |
| 4:30–6:00 | Seguridad | Intentar abrir Auditoría como vendedor. Mostrar el 403 de la API y el retorno al login ante un 401. |
| 6:00–7:30 | Volumen | Abrir Productos, avanzar de página y recargar. Mostrar el total, la URL, Actuator y las claves de caché del entorno de prueba. |
| 7:30–8:30 | Auditoría | Entrar como administrador y filtrar por vendedor, acción y fecha. Comparar ID de venta y requestId con Oracle y el log. |
| 8:30–10:00 | Calidad | Lanzar Playwright y mostrar el informe de tres repeticiones y el registro de incidencias. |

Consultar los [recorridos registrados](evidencias/s14-estabilizacion/recorridos.json) para ver duración, venta y stock de cada ejecución.
