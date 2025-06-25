
package model;

public class Treinador {
    private int id;
    private int nivel;
    private int insignias;
    private String nome;
    private int idPokedex;

    public Treinador(int id, int nivel, int insignias, String nome, int idPokedex) {
        this.id = id;
        this.nivel = nivel;
        this.insignias = insignias;
        this.nome = nome;
        this.idPokedex = idPokedex;
    }

    public int getId() { return id; }
    public int getNivel() { return nivel; }
    public int getInsignias() { return insignias; }
    public String getNome() { return nome; }
    public int getIdPokedex() { return idPokedex; }

    public void mostrar() {
        System.out.println("Treinador: " + nome + " | Nível: " + nivel + " | Insígnias: " + insignias);
    }
}
