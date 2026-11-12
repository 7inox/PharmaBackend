-- Ejecutar en el esquema Oracle de pruebas después de V4.
whenever sqlerror exit failure rollback
set define off

insert into categorias (nombre, descripcion, estado, fecha_creacion, fecha_modificacion, creado_por)
select 'S14 Volumen', 'Categoria de datos sinteticos', true, systimestamp, systimestamp, 'carga-s14'
from dual where not exists (select 1 from categorias where nombre = 'S14 Volumen');

insert into clientes (dni, nombres, apellidos, email, estado, fecha_creacion, creado_por)
select '91000014', 'Cliente', 'Volumen S14', 'volumen.s14@demo.invalid', true, systimestamp, 'carga-s14'
from dual where not exists (select 1 from clientes where dni = '91000014');

insert into producto (nombre, precio, stock, estado, categoria_id, fecha_creacion, creado_por)
select lote.nombre, lote.precio, lote.stock, true, c.id, systimestamp, 'carga-s14'
from (
    select 'Producto S14 volumen ' || lpad(level, 5, '0') nombre,
           round(dbms_random.value(1, 200), 2) precio, trunc(dbms_random.value(0, 500)) stock
    from dual connect by level <= 5000
) lote
join categorias c on c.nombre = 'S14 Volumen'
where not exists (select 1 from producto p where p.nombre = lote.nombre);

-- Las ventas sintéticas carecen de ítems y quedan fuera de los reportes por producto.
insert into ventas (fecha, total, estado, cliente_id, creado_por)
select lote.fecha, lote.total, 'REGISTRADA', c.id, 'carga-s14'
from (
    select cast(sysdate - mod(level - 1, 365) - mod(level - 1, 86400) / 86400 as timestamp) fecha,
           round(dbms_random.value(5, 500), 2) total
    from dual connect by level <= 20000
) lote
join clientes c on c.dni = '91000014'
where not exists (select 1 from ventas v where v.creado_por = 'carga-s14' and v.cliente_id = c.id);

commit;

select count(*) as productos_carga from producto where creado_por = 'carga-s14';
select count(*) as ventas_carga from ventas where creado_por = 'carga-s14';
