package model;

public class Movimento {
    private int idMovimento;
    private int dano;
    private int prioridade;
    private String tipo;
    private String efeitosExtra;

    public Movimento(int idMovimento, int dano, int prioridade, String tipo, String efeitosExtra) {
        this.idMovimento = idMovimento;
        this.dano = dano;
        this.prioridade = prioridade;
        this.tipo = tipo;
        this.efeitosExtra = efeitosExtra;
    }

    public int getIdMovimento() {
        return idMovimento;
    }

    public int getDano() {
        return dano;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEfeitosExtra() {
        return efeitosExtra;
    }

    public void mostrar() {
        System.out.println("Movimento: " + idMovimento + " | Tipo: " + tipo + " | Dano: " + dano + " | Prioridade: " + prioridade + " | Efeitos: " + efeitosExtra);
    }
}
