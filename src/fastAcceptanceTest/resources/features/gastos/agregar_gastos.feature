# language: es

Característica: Agregar gastos a los grupos

  Regla: El monto de un gasto debe ser positivo

    Escenario: Agregar un gasto positivo al grupo del picnic
      Dado el grupo "Picnic en Palermo" sin gastos
      Cuando se registra un gasto de $ 4000
      Entonces el total del grupo "Picnic en Palermo" debería ser $ 4000

    Escenario: No agregar un gasto sin monto al grupo del asado
      Dado el grupo "Asado del sábado" con un total de $ 2500
      Cuando se intenta registrar un gasto de $ 0
      Entonces el total del grupo "Asado del sábado" debería seguir siendo $ 2500

    Escenario: No agregar un gasto negativo al grupo del regalo
      Dado el grupo "Regalo para Ana" con un total de $ 1800
      Cuando se intenta registrar un gasto de $ -500
      Entonces el total del grupo "Regalo para Ana" debería seguir siendo $ 1800
