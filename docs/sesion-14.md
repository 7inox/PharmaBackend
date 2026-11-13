# Sesión 14. Integración y estabilización

## Arranque y migraciones

La rama de la sesión 13 creaba el esquema con Hibernate. Para un esquema vacío, Flyway
ejecuta la migración de línea base `B3__esquema_pre_auditoria.sql` y después `V4__auditoria.sql`.
Para un esquema S13 existente y sin historial, respaldarlo, cotejar el esquema con B3 y
establecer la línea base 3 explícitamente en el primer arranque
(`SPRING_FLYWAY_BASELINE_ON_MIGRATE=true`, `SPRING_FLYWAY_BASELINE_VERSION=3`).
Retirar esas variables tras la migración. El perfil dev usa `ddl-auto: validate`.

Configurar `DB_URL`, `DB_USERNAME`, `DB_PASSWORD`, `JWT_SECRET` y Redis según el README.
Para un esquema de laboratorio se puede habilitar `APP_SEED_DEMO_SECURITY=true` y definir
`PHARMA_DEMO_PASSWORD`. Ejecutar `docs/sql/volumen_pruebas.sql` después de V4.
Usar una instancia o una base lógica Redis de pruebas para que las claves no se crucen
con las de otras instancias de PharmaBackend.

## Contratos

- Productos: `GET /api/v1/productos?pagina=0&tamanio=20&ordenarPor=nombre&direccion=asc`.
  `categoriaId` filtra en Oracle. `q` y `vendible=true` se usan en la nueva venta.
- Ventas: `GET /api/v1/ventas/buscar?pagina=0&tamanio=20&desde=2026-11-01&hasta=2026-11-12`.
  Oracle pagina identificadores y la API recupera los detalles de esos identificadores.
  Los consumidores que omiten `pagina` conservan el contrato histórico.
- Auditoría: `GET /api/v1/auditoria?accion=VENTA_REGISTRADA&usuario=vendedor&pagina=0&tamanio=20`.
  Acepta `desde` y `hasta` inclusivos por día. Requiere `USUARIOS_ADMINISTRAR`.
  Los eventos de negocio se persisten después del commit. Los intentos de inicio de sesión
  se registran en una transacción independiente. El detalle excluye credenciales y tokens.

## Demostración

1. En Productos, avanzar, recargar y aplicar una categoría. Verificar URL y totales.
2. Como vendedor, seleccionar cliente y producto, confirmar dos unidades y abrir el comprobante.
3. Abrir Productos mediante el menú y comprobar el stock. Como administrador, consultar el
   reporte y cotejar el identificador de venta y `X-Request-Id` con la bitácora.
4. Ejecutar `npx playwright test --repeat-each=3` y abrir `npx playwright show-report`.

El conjunto de pruebas crea clientes y productos sintéticos y registra ventas en el esquema
de laboratorio. La respuesta 401 de una búsqueda se simula para comprobar la vuelta a la ruta.
