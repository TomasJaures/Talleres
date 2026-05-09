# Informe Hash
- Tomas Jaures
- Programacion avanzada
- ICC708-1

<img src=imgs/logo.png height=200px>

# Explicacion

Una tabla HASH es una estructura de dato en la cual se busca una complejidad O(1) para diversas operaciones, generalmente:

- Search (Busqueda de un elemento)
- Insert (Insercion de un elemento)
- Delete (Eliminacion de un elemento)

Esta estructura evita la busqueda secuencial y **promete** un acceso casi instantaneo a la accion realizada, una diferencia fundamental en contra de otras estructuras de datos... Como podrian ser Arrays, Linked List, Etc.

### ¿Como funciona?

La tablas HASH puede variar su funcionamiento dependiendo de diversos factores, pero generalmente siguen el siguiente patron:

1. A un valor se le asigna una **clave**
2. Esta clave pasa como parametro a una funcion llamada **funcion hash**
3. Esta **funcion hash** convierte esta clave unica en un nuevo valor numerico que sirve como indice para la tabla
4. Es nuevo valor numerico **(Dependiendo del punto de ruptura)**, promete dejar el elemento en un indice dificil de repetir a menos que se tenga la misma clave.


# Repositorio

[Codigo con tabla hash implementada](https://github.com/TomasJaures/Talleres)

# Tabla comparativa

"Tabla comparativa con las métricas para cada dataset y cada función hash."

### Dataset: **RANDOM**

| Propiedad | Sum | Polynomial |
| - | - | - |
|Size | 211 | 211 |
| Elements | 1000 | 1000 |
| Load Factor | "4,739" | "4,739" |
| Collisions | 886 | 790 |
| Used Buckets | 114 | 210 |
| Max Bucket Size | 27 | 12 |
| Insert Time | "0,002538" | "0,000753" |


**Terminal** <img src=imgs/random.png height=70px>

### Dataset: **SEQUENTIAL**

| Propiedad | Sum | Polynomial |
| - | - | - |
| Size | 211 | 211 |
| Elements | 1000 | 1000 |
| Load Factor | "4,739" | "4,739" |
| Collisions | 945 | 789 |
| Used Buckets | 55 | 211 |
| Max Bucket Size | 70 | 8 |
| Insert Time | "0,000514" | "0,000247" |

**Terminal** <img src=imgs/sequential.png height=70px>

### Dataset: **CLUSTERED**

| Propiedad | Sum | Polynomial |
| - | - | - |
| Size | 211 | 211 |
| Elements | 1000 | 1000 |
| Load Factor | "4,739" | "4,739" |
| Collisions | 945 | 789 |
| Used Buckets | 55 | 211 |
| Max Bucket Size | 70 | 10 |
| Insert Time | "0,000586" | "0,000297" |

**Terminal** <img src=imgs/clustered.png height=70px>

### Comparacion completa

| Dataset    | Propiedad       | Sum      | Polynomial |
| ---------- | --------------- | -------- | ---------- |
| **RANDOM**     | Size            | 211      | 211        |
| **RANDOM**     | Elements        | 1000     | 1000       |
| **RANDOM**     | Load Factor     | 4,739    | 4,739      |
| **RANDOM**     | Collisions      | 886      | 790        |
| **RANDOM**     | Used Buckets    | 114      | 210        |
| **RANDOM**     | Max Bucket Size | 27       | 12         |
| **RANDOM**     | Insert Time | 0,002538 | 0,000753   |
| **SEQUENTIAL** | Size            | 211      | 211        |
| **SEQUENTIAL** | Elements        | 1000     | 1000       |
| **SEQUENTIAL** | Load Factor     | 4,739    | 4,739      |
| **SEQUENTIAL** | Collisions      | 945      | 789        |
| **SEQUENTIAL** | Used Buckets    | 55       | 211        |
| **SEQUENTIAL** | Max Bucket Size | 70       | 8          |
| **SEQUENTIAL** | Insert Time | 0,000514 | 0,000247   |
| **CLUSTERED**  | Size            | 211      | 211        |
| **CLUSTERED**  | Elements        | 1000     | 1000       |
| **CLUSTERED**  | Load Factor     | 4,739    | 4,739      |
| **CLUSTERED**  | Collisions      | 945      | 789        |
| **CLUSTERED**  | Used Buckets    | 55       | 211        |
| **CLUSTERED**  | Max Bucket Size | 70       | 10         |
| **CLUSTERED**  | Insert Time | 0,000586 | 0,000297   |


# Discusión

### - ¿Qué función hash produjo menos colisiones?

La funcion HASH que menos produjo colisiones en los 3 ejemplos fue la **funcion hash polynomial**

### - ¿En qué dataset?

CLUSTERED y SEQUENTIAL fueron los 2 datasets que menos colisiones provocaron, habiendo un empate con 789 colisiones.

### - ¿Por qué?"

En cuanto a funciones hash, esta claro que es debido a la manera que maneja la funcion polynomial a las claves, al retornar numeros con mayor diferencia entre si aunque las claves sean parecidas, ayuda mucho a la disminucion de colisiones.

En cuanto a los DATASET, seguramente sea debido a la manera en que se obtienen las claves. CLUSTERED y SEQUENTIAL dentro de lo que cabe tienen claves parecidas entre si, mientras que random tiene claves completamente aleatorias.

# Conclusión

En conclusión, la tabla hash es una estructura de datos muy util en la gran mayoria de casos, principalmente gracias a su complejidad de O(1) para diversas operaciones en la gran mayoria de casos, ademas de su posible flexibildad dependiendo del problema al que se enfrenta, pudiendo tomar diversas tecnicas para evitar colisiones, como a su vez variar la manera en que se ejecuta la "funcion hash" dependiendo del DATASET con el que se esta trabajando. Esto sin nombrar factores claves que nos ayudan a definir la manera en que construimos o usamos esta tabla, ya sea el factor de carga o el punto de ruptura de estos mismos, o tambien como opera la tabla hash como tal, organizando de manera eficiente el uso de listas enlazadas (Buckets) y evitando en la medida de lo posible conflictos como clusters (Agrupaciones) entre los buckets.