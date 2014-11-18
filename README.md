repvoting
=========

Repositorio compartido en el que se desarrollaran varios subsistemas del sistema de votado correspondiente a la asignatura EGC. 

Desde el grupo de autenticación se proponen las siguientes instrucciones de uso del repositorio:

* Se crearán ramas según las funcionalidades que haya que implementar, creando una rama por cada nueva funcionalidad. En la rama master se realizarán los cambios comunes a todos los grupos de ella se podrá tomar lo que se necesite de cada grupo. Además cada grupo tiene su propia carpeta creada en la rama master y de la que deberán crear una rama para trabajar. Las carpetas de cada grupo son las siguientes:
 * auth: Autenticación
 * votes: Creación/administración de votaciones
 * results: Sistema de modificación de resultados
 * storage: Almacenamiento de votos
 * deliberations: Deliberaciones
 * counting: Recuento
 * census: Creación/Administración de censos
 * results_frontend: Frontend de Resultados
 * results_view: Visualización de resultados
 * verification: Verificación
 * polling: Cabina de votación
* Si en algún momento en una rama hay código de utilidad perteneciente a otra rama, se hará un merge de esta última en la rama que puede utilizar el código (sin borrar ninguna de las dos). Si hay algún conflicto no relacionado con el código que se quiere pasar de una rama a otra, permanecerá siempre el código de la rama original. De esta manera, se pretende evitar la repetición de trabajo cuando es menos obvio la utilidad de este para todo el grupo, de manera que no se haya usado la branch de cambios globales. Para que esto sea posible, se requiere que los miembros del equipo puedan identificar trabajo que es posible que ya haya realizado otro miembro del grupo. Se actuará de igual manera cuando dos funcionalidades estén relacionadas y una necesite a parte de la otra.
* Se realizará un merge que incluya los cambios de una rama en la rama principal cuando se haya terminado la funcionalidad a la que está dedicada cada rama, cerrándose la rama dedicada a la funcionalidad. Con la exepción de la rama de cambios comunes, que nunca se cerrará, pero cuyos cambios se reflejarán siempre en las otras ramas.
* Todos los miembros del equipo tendrán derechos de escritura y lectura de todas las ramas.
* Siempre que se haga un cambio considerable se hará commit al respositorio remoto central, para que todos los miembros del equipo dispongan lo antes posible del código actualizado.
* Siempre que se realice un parche sobre el código (una modificación que no añada nuevas funcionalidades), la descripción de esta será <code>BUGFIX:</code> seguido del bug que se pretende corregir, y la plataforma sobre la que se produzca dicho bug, en caso de que haya una concreta.
