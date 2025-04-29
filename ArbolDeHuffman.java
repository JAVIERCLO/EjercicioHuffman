import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ArbolDeHuffman {

    public static Nodo ConstruirArbol(String texto){
        Map<caracter, Integer> MapaFrecuencias = new HashMap<>();
        for(char c : texto.toCharArray()){
            Integer FrecuenciaCaracterActual = MapaFrecuencias.get(c);

            if (FrecuenciaCaracterActual == null) {
                FrecuenciaCaracterActual = 0;
            }else{
            FrecuenciaCaracterActual += 1;
            }
            //Actualizar la frecuencia en el mapa
            MapaFrecuencias.put(c, FrecuenciaCaracterActual);
        }
    PriorityQueue<Nodo> cola = new PriorityQueue<>();
    MapaFrecuencias.forEach((caracter, frecuencia) -> cola.add(new Nodo(caracter, frecuencia)));
    while (cola.size() > 1) {
        cola.add(new Nodo(cola.poll().frecuencia + cola.poll().frecuencia, cola.poll(), cola.poll()));
    }
    //Esta es la raiz
    return cola.poll();

    public static void CodificarHuffman(Nodo nodo, String codigo, Map<caracter, String> CodigoHuffman) {
        if (nodo == null) {
            return;
        }

    }

}
