#### 1. ¿Qué problema resuelve el ViewModel en Android?
El **ViewModel** resuelve el problema de la persistencia de datos frente a cambios de configuración (como rotación de pantalla). Permite que la lógica y los datos sobrevivan al ciclo de vida de la Activity o Fragment, evitando que se pierdan y reduciendo la necesidad de recargar información.

#### 2. ¿Por qué LiveData es "lifecycle-aware" y qué beneficio trae?
**LiveData** es *lifecycle-aware* porque observa el ciclo de vida de Activities y Fragments. Solo notifica cambios a los observadores que están activos (STARTED o RESUMED). El beneficio es que evita fugas de memoria y actualizaciones innecesarias, garantizando que la UI siempre muestre datos coherentes con su estado.

#### 3. Explica con tus propias palabras el flujo de datos en MVVM
En MVVM, los datos fluyen así:
- El **Repository** obtiene la información (de base de datos o red).
- El **ViewModel** procesa y expone esos datos mediante LiveData.
- La **View (Activity/Fragment)** observa el LiveData y se actualiza automáticamente cuando cambian los datos.  
Esto asegura una separación clara entre lógica de negocio y presentación.

#### 4. ¿Qué ventaja tiene usar Fragments vs múltiples Activities?
Usar **Fragments** permite reutilizar componentes de UI dentro de una sola Activity, facilita la navegación con el Navigation Component y reduce la sobrecarga de manejar múltiples ciclos de vida de Activities. Además, mejora la modularidad y la consistencia de la aplicación.

#### 5. ¿Cómo ayuda el Repository Pattern a la arquitectura?
El **Repository Pattern** centraliza el acceso a datos, ya sea de una API, base de datos local o caché. Esto desacopla la fuente de datos del ViewModel, facilita pruebas unitarias y permite cambiar la implementación de almacenamiento sin afectar la lógica de presentación.

## Taller 3
- Implementación de lista de tareas con Fragments.
- Navegación al detalle usando Safe Args.
- Recordatorio implementado mediante notificación en la barra de estado.
