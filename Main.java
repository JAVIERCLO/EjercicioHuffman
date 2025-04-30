import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Nombre del archivo
        String texto = leerArchivo("texto.txt");

        //Verificar la lectura del archivo
        if (texto == null || texto.isEmpty()) {
            System.out.println("Hubo un error al leer el codigo");
            return;
        }

        //Construir el árbol
        Nodo raiz = ArbolDeHuffman.ConstruirArbol(texto);

        //Generar los códigos de Huffman
        Map<Character, String> CodigoHuffman = new HashMap<>();
        ArbolDeHuffman.CodificarHuffman(raiz, "", CodigoHuffman);

        // Mostrar los códigos Huffman
        System.out.println("Codigos Huffman: " + CodigoHuffman);

        // Comprimir el texto
        String textoComprimido = ArbolDeHuffman.comprimir(texto, CodigoHuffman);
        System.out.println("Texto comprimido: " + textoComprimido);

        // Descomprimir el texto
        String textoDescomprimido = ArbolDeHuffman.descomprimir(textoComprimido, raiz);
        System.out.println("Texto descomprimido: " + textoDescomprimido);

        //Verificar que el proceso se hizo de forma correcta
        if (texto.equals(textoDescomprimido)) {
            System.out.println("La descompresion fue exitosa.");
        } else {
            System.out.println("La descompresion ha fallado.");
        }
    }

    //Método para leer el archivo
    public static String leerArchivo(String nombreArchivo) {
        StringBuilder texto = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                texto.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return texto.toString();
    }
}
