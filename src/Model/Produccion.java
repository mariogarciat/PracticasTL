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

    public Produccion(Expresion ladoIzq, Expresion ladoDer) {
        this.produccion = ladoIzq.toString() + "->" + ladoDer.toString();
        this.ladoIzq = ladoIzq;
        this.ladoDer = ladoDer;
    }

    public Produccion(String ladoIzq, String ladoDer) {
        this.produccion = ladoIzq + "->" + ladoDer;
        this.ladoIzq = new Expresion(ladoIzq);
        this.ladoDer = new Expresion(ladoDer);
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
