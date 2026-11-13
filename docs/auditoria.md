# Auditoría de PharmaSoft

Las columnas `creado_por` y `modificado_por` identifican al autor de la fila o de su último cambio. La bitácora conserva el historial de acciones. Los logs técnicos registran el recorrido HTTP, los tiempos y los errores. `X-Request-Id` permite unir un evento con el log de la misma petición.

| Evento | Cuándo se guarda | Detalle |
|---|---|---|
| VENTA_REGISTRADA | Al confirmar la transacción | Total y número de ítems |
| PRECIO_MODIFICADO | Al confirmar un cambio de precio | Precio anterior y nuevo |
| PRODUCTO_ELIMINADO | Al confirmar la baja | Nombre del producto |
| USUARIO_DESACTIVADO | Al pasar una cuenta de activa a inactiva | Usuario afectado |
| CLIENTE_REGISTRADO | Al confirmar el alta | Solo el ID de entidad |
| CATEGORIA_ELIMINADA | Al confirmar la eliminación | Nombre de la categoría |
| LOGIN_EXITOSO | Después de validar las credenciales | Usuario, fecha y requestId |
| LOGIN_FALLIDO | Al fallar la autenticación | Usuario intentado, fecha y requestId |

`AuditoriaListener` recibe los eventos de negocio después del commit y `AuditoriaService` los escribe con `REQUIRES_NEW`. Un rollback de la operación descarta su evento. El login fallido usa directamente la transacción independiente porque su petición termina en error. Una falla de escritura de bitácora se registra en el log. La bitácora no guarda contraseñas, JWT, DNI, correo ni teléfono.

`GET /api/v1/auditoria` exige `USUARIOS_ADMINISTRAR`. Filtra por usuario, acción y fechas inclusivas y pagina de 1 a 100 filas. En la SPA los filtros y la página quedan en la URL. El campo **ID** corresponde a la entidad afectada.

## Comprobación

La [evidencia de la demostración](evidencias/s14-estabilizacion/trazabilidad.txt) relaciona la fila de `ventas`, la bitácora y el log. La captura [Auditoría](evidencias/s14-estabilizacion/auditoria.png) muestra la consulta filtrada.
