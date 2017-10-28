package Model;

public class Produccion {

    private final String produccion;
    private final Expresion ladoIzq;
    private final Expresion ladoDer;

    public Produccion(String produccion, Expresion ladoIzq, Expresion ladoDer) {
        this.produccion = produccion;
        this.ladoIzq = ladoIzq;
        this.ladoDer = ladoDer;
    }

    public String getProduccion() {
        return produccion;
    }

    public Expresion getLadoIzq() {
        return ladoIzq;
    }

    public Expresion getLadoDer() {
        return ladoDer;
    }
}
