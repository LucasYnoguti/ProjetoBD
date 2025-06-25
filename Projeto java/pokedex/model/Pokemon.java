
package model;

import java.util.List;

public class Pokemon {
    private int id;
    private String nome;
    private int vida;
    private int physicalAtk;
    private int physicalDef;
    private int specialAtk;
    private int specialDef;
    private int speed;
    List<Movimento> movimentos;

    public Pokemon(int id, String nome, int vida, int physicalAtk, int physicalDef, int specialAtk, int specialDef, int speed) {
        this.id = id;
        this.nome = nome;
        this.vida = vida;
        this.physicalAtk = physicalAtk;
        this.physicalDef = physicalDef;
        this.specialAtk = specialAtk;
        this.specialDef = specialDef;
        this.speed = speed;
    }


    public void mostrar() {
        System.out.println("Pokemon: " + id + " - " + nome);
        System.out.println("Vida: " + vida);
        System.out.println("Physical Atk: " + physicalAtk);
        System.out.println("Physical Def: " + physicalDef);
        System.out.println("Special Atk: " + specialAtk);
        System.out.println("Special Def: " + specialDef);
        System.out.println("Speed: " + speed);
    }
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public List<Movimento> getMovimentos() {
        return movimentos;
    }

    public void setMovimentos(List<Movimento> movimentos) {
        this.movimentos = movimentos;
    }
    public void adicionarMovimento(Movimento movimento) {
        movimentos.add(movimento);
    }
}
