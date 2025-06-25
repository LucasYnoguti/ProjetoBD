
package model;

public class Cidade {
    private int idCidade;
    private String locCentroPkm, locLoja, locGinasio;
    private Regiao regiao;

    public Cidade(int idCidade, String locCentroPkm, String locLoja, String locGinasio, Regiao regiao) {
        this.idCidade = idCidade;
        this.locCentroPkm = locCentroPkm;
        this.locLoja = locLoja;
        this.locGinasio = locGinasio;
        this.regiao = regiao;
    }

    public void mostrar() {
        System.out.println("Cidade " + idCidade + " (" + regiao.getIdRegiao() + "): " +
                locCentroPkm + ", " + locLoja + ", " + locGinasio);
    }

    public int getIdCidade() {
        return idCidade;
    }
}
