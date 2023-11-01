import java.util.HashMap;
import java.util.HashSet;
import java.util.Set; 
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Queue;
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner;

class DegreesOfSeparation{

    private HashMap<String, Integer> vertices;
    private ArrayList<ArrayList<Integer>> edges;

    public DegreesOfSeparation(Set<String> vertexSet, ArrayList<Pair<String>> edges){

        // Inicializamos el grafo
        this.vertices = new HashMap<>();
        this.edges = new ArrayList<ArrayList<Integer>>();
        Integer nextInd = 0;

        for (String vertex: vertexSet){
            this.vertices.put(vertex, nextInd);
            nextInd++;
        }

        for (int i = 0; i < vertexSet.size(); i++ ){
            this.edges.add(new ArrayList<Integer>());
        }

        for (Pair<String> edge: edges){
            
            String person1 = edge.val1;            
            String person2 = edge.val2;

            int indPerson1 = this.vertices.get(person1);
            int indPerson2 = this.vertices.get(person2);

            ArrayList<Integer> neighborsPer1 = this.edges.get(indPerson1);            
            ArrayList<Integer> neighborsPer2 = this.edges.get(indPerson2);

            // Dado que se trata de un grafo no dirigido, se añande tanto
            // la arita (person1, person2) como (person2, person1)
            // Se asume que el archivo input.text solo una de estas aristas.
            neighborsPer1.add(indPerson2);
            neighborsPer2.add(indPerson1);
        }
    }

    public int degreeOfSeparation(String start, String end){

        // Si alguno de los vertices no esta en el grafo, entoces no existe un
        // grado de separacion entre start o end  
        if (!this.vertices.containsKey(start) || !this.vertices.containsKey(end)){
            throw new IllegalArgumentException("Uno de los argumentos pasados no es un vertice del grafo");
        }

        // Por definicion, el grado de separacion de una persona consigo misma
        // es cero
        if (start == end){
            return 0;
        }

        // Usamos una version modificada de BFS para encontrar el camino mas
        // corto entre start y end

        Integer keyStart = this.vertices.get(start);
        Integer keyEnd = this.vertices.get(end);

        // Inicializamos una queue para saber que vertices tenemos que 
        // visitar
        Queue<Integer> toVisit = new LinkedList<Integer>();

        // Inicializamos un arreglo que nos ayudara a saber cuales 
        // vertices ya hemos visitado 
        boolean[] visitedArray = new boolean[this.vertices.size()];

        // Creamos un arreglo de enteros de tamaño igual al número de vértices. Este arreglo 
        // se utiliza para llevar un control de la distancia entre el vértice start y 
        // cada vértice que BFS esté procesando. Si encontramos el vértice end, solo 
        // tenemos que retornar su casilla correspondiente del arreglo para tener el grado 
        // de separacion entre start y end.
        int[] distance = new int[this.vertices.size()];
        for (int i = 0; i < vertices.size(); i++){
            distance[i] = 0;
        }

        toVisit.add(keyStart);

        while (!toVisit.isEmpty()){

            Integer keyVertex = toVisit.poll();

            if (visitedArray[keyVertex]) {
                continue;
            }

            visitedArray[keyVertex] = true;

            for (Integer successor: edges.get(keyVertex)){
                if (visitedArray[successor]) {
                    continue;
                }
                toVisit.add(successor);
                distance[successor] = distance[keyVertex] + 1;
                if(successor.equals(keyEnd)){
                    return distance[successor];
                }

            }
        }

        // Si el programa llegó hasta aquí, significa que no hay ningun camino 
        // que una a start y end, por tanto su grado de separacion es -1.
        return -1;
    }
    public static void main(String[] args){

        try {
            File input = new File("input.txt");
            Scanner reader = new Scanner(input);
            
            // Vamos a agregar al conjunto vertexSet los nombres que 
            // aparezcan en input.txt y a edges le agregamos un par
            // que representa la relacion de amistad de cada linea.
            Set<String> vertexSet = new HashSet<String>();
            ArrayList<Pair<String>> edges = new ArrayList<Pair<String>>();

            while (reader.hasNextLine()) {
                
                    String data = reader.nextLine();
                    String[] friends = data.split(" ");

                    vertexSet.add(friends[0]);
                    vertexSet.add(friends[1]);

                    Pair<String> edge = new Pair<String>(friends[0], friends[1]);
                    edges.add(edge);

            }
            reader.close();

            // Creamos el grafo a partir de los datos recogidos.
            DegreesOfSeparation graph = new DegreesOfSeparation(vertexSet, edges);

            // Revisamos que se esten pasado los dos parametros esperados
            if(args.length !=2){
                System.out.println("error: se esperan como argumentos dos nombres");
                System.exit(1);
            }

            // Finalmente llamamos a la funcion de grado de separacion con los 
            // argumentos pasados e imprimos el grado
            try {
                System.out.println(graph.degreeOfSeparation(args[0], args[1]));
            } catch (IllegalArgumentException e){
                System.out.println("error: una de las personas en los argumentos no esta en el archivo input.txt");
                e.printStackTrace();
            }
            

        } catch (FileNotFoundException e) {
            System.out.println("Ha ocurrido un error con el archivo input.txt");
            e.printStackTrace();
        }
    }
}