# Trabajo Práctico 2: BDD

75.48 Calidad en desarrollo de software FIUBA

Dentro del proyecto Repartir se debe agregar una regla y dos o más escenarios que la ilustren, y luego implementar la regla en el código del proyecto (https://github.com/dfontde/repartir-2024). La regla a agregar es libre, aunque no debe estar ya implementada en el proyecto. Un ejemplo que pueden utilizar es "El total de un grupo no debe ser negativo".

## Condiciones de entrega

Hacer un fork del proyecto en GitHub.

Los escenarios deben cumplir con las características BRIEF:

- Orientado al negocio/dominion (B de Business)
- Datos reales cuando sea pertinentes (R de Real data)
- Revelar intención (I de Intention revealing)
- Esencial, sin nada que no haga falta para ilustrar el comportamiento
- Enfocado ilustran una única regla (F de focused)

Se debe escribir primero la regla, luego los escenarios, luego implementar las pruebas de aceptación automatizadas y finalmente la solución, siguiendo el ciclo de BDD.

Las pruebas de aceptación automatizadas pueden ser del tipo acceptanceTest (punta a punta con Selenium) o fastAcceptanceTest (sobre los objetos de dominio). Se recomienda este último tipo de prueba para quienes hayan tenido problemas de entorno (por ejemplo, Docker o WebDriver).

Se debe entregar link al repositorio público de GitHub con el código del proyecto pasando todas las pruebas (`./gradlew check`), e incluyendo la nueva funcionalidad implementada (la excepción la constituyen pruebas que no puedan correr por problemas de entorno como se describe en el ítem anterior).

Se recomienda probar manualmente la solución cuando las pruebas automatizadas estén pasando.
