# ADR-003: Estrategia de Mapeo de Objetos (MapStruct)

## Estado
Aceptado

## Fecha
2025-12-08

## Contexto
La Arquitectura Hexagonal exige separar las Entidades de Base de Datos (JPA) de los Modelos de Dominio. Esto implica una conversión constante de datos. Hacerlo manualmente es propenso a errores y repetitivo (Boilerplate code).

## Decisión
Se decide utilizar **MapStruct** para la generación automática de mappers en tiempo de compilación.

## Alternativas Consideradas
1. **Mapeo Manual (Getters/Setters):** Descartado por ser verboso y difícil de mantener.
2. **ModelMapper / Reflection:** Descartado por problemas de rendimiento y falta de seguridad de tipos (errores en tiempo de ejecución).
3. **MapStruct - ELEGIDA:** Genera código Java puro, es rápido y valida los tipos en tiempo de compilación.

## Consecuencias
### Positivas
- **Rendimiento:** Al no usar reflexión, es tan rápido como el código manual.
- **Seguridad:** Si los campos cambian de nombre, el proyecto no compila (fail-fast), evitando errores en producción.
- **Limpieza:** Reduce drásticamente el código repetitivo en los adaptadores.

### Negativas
- **Configuración:** Requiere configuración específica en Maven (annotation processors) junto con Lombok.