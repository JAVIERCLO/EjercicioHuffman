import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Nombre del archivo
        String texto = leerArchivo("texto.txt");

        //Verificar la lectura del archivo
        if (texto == null || texto.isEmpty()) {
            System.out.println("Hubo un error al leer el archivo.");
            return;
        }

        // Construir el árbol
        Nodo raiz = ArbolDeHuffman.ConstruirArbol(texto);

        // Generar los códigos de Huffman
        Map<Character, String> CodigoHuffman = new HashMap<>();
        ArbolDeHuffman.CodificarHuffman(raiz, "", CodigoHuffman);

        // Mostrar los códigos Huffman
        System.out.println("Códigos Huffman: " + CodigoHuffman);

        // Guardar los códigos de Huffman en un archivo
        guardarCodigosHuffman("codigos_huffman.txt", CodigoHuffman);

        // Comprimir el texto
        String textoComprimido = ArbolDeHuffman.comprimir(texto, CodigoHuffman);
        System.out.println("Texto comprimido: " + textoComprimido);

        // Guardar el texto comprimido en un archivo
        guardarArchivo("texto_comprimido.txt", textoComprimido);

        // Descomprimir el texto
        String textoDescomprimido = ArbolDeHuffman.descomprimir(textoComprimido, raiz);
        System.out.println("Texto descomprimido: " + textoDescomprimido);

        // Guardar el texto descomprimido en un archivo
        guardarArchivo("texto_descomprimido.txt", textoDescomprimido);

        // Verificar que la descompresión sea igual al texto
        if (texto.equals(textoDescomprimido)) {
            System.out.println("La descompresion fue exitosa.");
        } else {
            System.out.println("La descompresión ha fallado.");
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

    //Metodo para guardar archivo
    public static void guardarArchivo(String nombreArchivo, String texto) {
        try {
            File archivo = new File(nombreArchivo);
            
            File directorio = archivo.getParentFile();
            if (directorio != null) {
                directorio.mkdirs();
            }
            
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                bw.write(texto);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Guardar el caracter con su código de Huffman
    public static void guardarCodigosHuffman(String nombreArchivo, Map<Character, String> CodigoHuffman) {
        try {
            File archivo = new File(nombreArchivo);

            if (archivo.getParentFile() != null) {
                archivo.getParentFile().mkdirs();
            }
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo))) {
                for (Map.Entry<Character, String> entry : CodigoHuffman.entrySet()) {
                    bw.write(entry.getKey() + ": " + entry.getValue());
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
