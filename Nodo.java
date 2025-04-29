public class Nodo implements Comparable<Nodo> {

    private char caracter;
    private int frecuencia;

    Nodo izquierdo;
    Nodo derecho;

    public Nodo(char caracter, int frecuencia){
        this.caracter = caracter;
        this.frecuencia = frecuencia;
        this.derecho = null;
        this.izquierdo = null;
    }

    @Override
    public int compareTo(Nodo other){
        return Integer.compare(this.frecuencia, other.frecuencia);
    }
}