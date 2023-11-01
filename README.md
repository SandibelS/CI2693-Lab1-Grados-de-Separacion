# CI2693 Laboratorios

Sandibel Soares, 1710614.

Repositorio destinado a guardar los laboratorios elaborados para la materia CI2693.

## Laboratorio 1: Grados de separación

Para este laboratorio se usó el algoritmo BFS ligeramente modificado para calcular el grado 
de separacion entre dos vertices. También se usó la siguiente representacion para el grafo:

  - Un conjunto de pares llaves/valores para almacenar los vertices. Las llaves seran
    los nombres de los usuarios y los valores seran un entero único para cada usuario.
    (Implementado con la estructura HashMap)
    
  - Una lista donde cada casilla _i_ contiene la lista de vecinos correspondiente al usuario
    cuyo valor sea igual a _i_ en el conjunto de pares.
    (Implementado con la estructura ArrayList)

Esta representacion presenta como ventajas:

  - Una manipulacion mas sencilla al usar como valor de los vertices enteros en vez de Strings,
    permitiendo usar el valor para indexear cuando es necesario y evitar el uso del hash
    para los Strings.
  - Lo anterior también influye de forma positiva al uso de la memoria.
  - Dado que el BFS trabaja de forma constante con los sucesores de cada vertice que procesa,
    el hecho de que esta representación permita que esta acción se realice en O(1) beneficia
    el tiempo del algoritmo.
    
