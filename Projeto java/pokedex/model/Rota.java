
package model;

import model.Cidade;

public class Rota {
    private int id;
    private String nomeRota;
    private String bioma;
    private String clima;
    private Cidade cidadeInicio;
    private Cidade cidadeDestino;

    public Rota(int id, String nomeRota, String bioma, String clima, Cidade inicio, Cidade destino) {
        this.id = id;
        this.nomeRota = nomeRota;
        this.bioma = bioma;
        this.clima = clima;
        this.cidadeInicio = inicio;
        this.cidadeDestino = destino;
    }

    public void mostrar() {
        System.out.println("Rota " + nomeRota + " | Bioma: " + bioma + " | Clima: " + clima);
        System.out.println("De: " + cidadeInicio.getIdCidade() + " -> Para: " + cidadeDestino.getIdCidade());
    }

    public int getId() { return id; }
}
