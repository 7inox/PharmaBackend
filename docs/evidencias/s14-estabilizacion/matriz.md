# Matriz de integración

| ID | Verificación | Evidencia |
|---|---|---|
| I-01 | Cliente nuevo disponible para vender | Flujo E2E |
| I-02 | Catálogo ofrece producto activo con stock y precio vigente | [Antes de vender](venta-antes.png) |
| I-03 | Stock de 10 a 8 al volver a Productos | [Catálogo](stock.png) |
| I-04 | La venta suma unidades y monto al reporte | Flujo E2E |
| I-05 | La venta confirmada deja evento correlacionado | [Traza](trazabilidad.txt) |
| I-06 | Cambio de precio guarda actor y precios anterior y nuevo | Flujo E2E |
| I-07 | 401 durante el formulario conserva el destino | E2E con 401 simulado |
| I-08 | Cambiar filtros devuelve el listado a la primera página | E2E de paginación |
