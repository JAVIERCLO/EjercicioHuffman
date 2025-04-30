public class Nodo implements Comparable<Nodo> {

    private char caracter;
    private Integer frecuencia;

    Nodo izquierdo;
    Nodo derecho;

    public Nodo(char caracter, int frecuencia){
        this.caracter = caracter;
        this.frecuencia = frecuencia;
        this.derecho = null;
        this.izquierdo = null;
    }

    public Nodo(int suma, Nodo izquierdo2, Nodo derecho2) {
        //TODO Auto-generated constructor stub
    }

    @Override
    public int compareTo(Nodo other){
        return Integer.compare(this.frecuencia, other.frecuencia);
    }
    public char getCaracter() {
        return caracter;
    }

    public Integer getFrecuencia() {
        return frecuencia;
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }
}