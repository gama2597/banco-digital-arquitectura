# ADR-001: Adopción de Arquitectura Hexagonal (Ports & Adapters)

## Estado
Aceptado

## Fecha
2025-12-08

## Contexto
El sistema "Banco Digital" requiere una alta integridad en las reglas de negocio (validaciones de saldo, comisiones, transferencias). Necesitamos asegurar que la lógica del negocio no se vea afectada por cambios tecnológicos futuros (cambio de base de datos, cambio de framework web o integraciones externas) y facilitar las pruebas unitarias del núcleo.

## Decisión
Se decide implementar la **Arquitectura Hexagonal (Ports & Adapters)**. Se aislará completamente el Dominio (núcleo) de la Infraestructura (frameworks y BD) mediante interfaces (Puertos).

## Alternativas Consideradas
1. **Arquitectura en Capas (Layered):** Controller -> Service -> Repository. Descartada porque tiende a generar acoplamiento entre la lógica de negocio y las librerías de persistencia (JPA).
2. **Microservicios:** Descartada por ser sobredimensionada para el alcance actual y la complejidad operativa.
3. **Arquitectura Hexagonal - ELEGIDA:** Permite desacoplar el núcleo.

## Consecuencias
### Positivas
- **Independencia Tecnológica:** El dominio no conoce a Spring Boot ni a la Base de Datos.
- **Testabilidad:** Se puede probar la lógica de negocio (casos de uso) sin levantar el contexto de Spring ni la BD.
- **Mantenibilidad:** Las reglas de negocio están centralizadas y protegidas en la carpeta `domain`.

### Negativas
- **Complejidad Inicial:** Requiere crear más clases (Interfaces/Puertos, Adaptadores, Mappers) que una arquitectura simple.
- **Curva de Aprendizaje:** El equipo debe entender la inversión de dependencias y el mapeo de objetos.