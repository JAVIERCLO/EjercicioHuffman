import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import java.util.*;

public class ArbolDeHuffman {

    public static Nodo ConstruirArbol(String texto) {
        Map<Character, Integer> MapaFrecuencias = new HashMap<>();
        for (char c : texto.toCharArray()) {
            Integer FrecuenciaCaracterActual = MapaFrecuencias.get(c);

            if (FrecuenciaCaracterActual == null) {
                FrecuenciaCaracterActual = 0;
            } else {
                FrecuenciaCaracterActual += 1;
            }
            //Actualizar la frecuencia en el mapa
            MapaFrecuencias.put(c, FrecuenciaCaracterActual);
        }

        PriorityQueue<Nodo> cola = new PriorityQueue<>();
        MapaFrecuencias.forEach((caracter, frecuencia) -> cola.add(new Nodo(caracter, frecuencia)));

        while (cola.size() > 1) {
            Nodo izquierdo = cola.poll();
            Nodo derecho = cola.poll();
            int suma = izquierdo.getFrecuencia() + derecho.getFrecuencia();
            cola.add(new Nodo(suma, izquierdo, derecho));
        }

        // Esta es la raíz del árbol
        return cola.poll();
    }

    public static void CodificarHuffman(Nodo nodo, String codigo, Map<Character, String> CodigoHuffman) {
        if (nodo == null) {
            return;
        }

        // Si es un nodo hoja, almacenamos el código
        if (nodo.izquierdo == null && nodo.derecho == null) {
            CodigoHuffman.put(nodo.getCaracter(), codigo);
        }

        //Para los nodos izuiqerdo y derecho
        CodificarHuffman(nodo.izquierdo, codigo + "0", CodigoHuffman);
        CodificarHuffman(nodo.derecho, codigo + "1", CodigoHuffman);
    }

    public static String comprimir(String texto, Map<Character, String> CodigoHuffman) {
        StringBuilder TextoComprimido = new StringBuilder();
        for (char c : texto.toCharArray()) {
            TextoComprimido.append(CodigoHuffman.get(c));
        }
        return TextoComprimido.toString();
    }

    public static String descomprimir(String TextoComprimido, Nodo raiz) {
        StringBuilder TextoDescomprimido = new StringBuilder();
        Nodo currentNode = raiz;

        for (int i = 0; i < TextoComprimido.length(); i++) {
            currentNode = (TextoComprimido.charAt(i) == '0') ? currentNode.izquierdo : currentNode.derecho;

            if (currentNode.izquierdo == null && currentNode.derecho == null) {
                TextoDescomprimido.append(currentNode.getCaracter());
                currentNode = raiz;
            }
        }
        return TextoDescomprimido.toString();
    }
}
