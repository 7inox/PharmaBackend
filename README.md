# PharmaBackend

API Spring Boot 4 con Oracle, autenticación JWT y caché Redis para [PharmaSoft](https://github.com/7inox/pharma-frontend).

## Producción local

1. Iniciar Redis y Nginx con el Docker Compose de `pharma-frontend/deploy`.
2. Configurar `SPRING_PROFILES_ACTIVE=prod`, `DB_URL`, `DB_USERNAME`, `DB_PASSWORD` y `JWT_SECRET` en el entorno. La clave JWT debe decodificar de Base64 a 32 bytes como mínimo.
3. Con el esquema Oracle existente, compilar con `./mvnw clean package` o `.\mvnw.cmd clean package` en Windows.
4. Iniciar con `java -jar target/Practica-0.0.1-SNAPSHOT.jar`. El backend escucha en 8080 y Nginx sirve la SPA en 8081.

La salud se consulta en `http://localhost:8080/actuator/health`. Las métricas requieren el permiso
`USUARIOS_ADMINISTRAR`. El log rotativo se escribe en `logs/pharma-backend.log` y cada
petición se puede localizar con la cabecera `X-Request-Id`.

El perfil `dev` usa el puerto 8081 por defecto y actualiza el esquema. Usarlo en una base de desarrollo separada.
