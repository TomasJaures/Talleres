
Discusión: ¿qué función hash produjo menos colisiones?, ¿en qué dataset?, ¿por qué?
Conclusión técnica de 8 a 12 líneas.


# Informe Hash
- Tomas Jaures
- ICC708-1

<img src=logo.png height=200px>

## Explicacion

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

[Codigo con tabla hash implementado](https://github.com/TomasJaures/Talleres)

# Tabla comparativa

"Tabla comparativa con las métricas para cada dataset y cada función hash."