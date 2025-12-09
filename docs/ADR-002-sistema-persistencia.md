# ADR-002: Estrategia de Persistencia (H2 Database en Memoria)

## Estado
Aceptado

## Fecha
2025-12-08

## Contexto
Necesitamos un mecanismo de persistencia relacional que soporte transacciones ACID y restricciones de integridad (Foreign Keys, Checks) para garantizar la consistencia financiera. Sin embargo, para efectos de revisión académica y portabilidad del entregable, se requiere que la aplicación sea "Ejecutar y Listo", sin pasos complejos de instalación de servidores de base de datos.

## Decisión
Se decide utilizar **H2 Database** en modo memoria (`mem`) compatible con MySQL, gestionado mediante **Spring Data JPA**.

## Alternativas Consideradas
1. **MySQL / PostgreSQL (Dockerizado):** Sería lo ideal para producción, pero requiere que el evaluador tenga Docker instalado o configure un servidor local. Descartado para facilitar la revisión.
2. **Archivos Planos / JSON:** Descartado porque no garantiza integridad transaccional ni soporta SQL estándar.
3. **H2 Database (Embedded) - ELEGIDA:** Ofrece soporte SQL completo, es muy rápido y no requiere instalación externa.

## Consecuencias
### Positivas
- **Portabilidad Total:** El proyecto funciona en cualquier máquina con Java instalado.
- **Rapidez de Desarrollo:** Ciclos de prueba rápidos al recrearse la BD en cada inicio.
- **Validación Estricta:** Permite usar scripts `schema.sql` para definir restricciones reales.

### Negativas
- **Volatilidad de Datos:** Los datos se pierden al reiniciar la aplicación (mitigado con un script `data.sql` de precarga).
- **Diferencias de Dialecto:** Aunque emula MySQL, algunas funciones específicas pueden diferir de un motor de producción real.