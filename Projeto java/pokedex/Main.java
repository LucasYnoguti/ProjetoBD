
import dao.*;
import model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CidadeDAO cidadeDAO = new CidadeDAO();
        TreinadorDAO treinadorDAO = new TreinadorDAO();
        RotaDAO rotaDAO = new RotaDAO();
        PokemonDAO pokemonDAO = new PokemonDAO();

        Scanner sc = new Scanner(System.in);
        int choice =1;
        while(choice != 0) {
            mostra_menu();
            choice = sc.nextInt();
            switch(choice) {
                case 1:
                    // Mostrar cidades
                    System.out.println("CIDADES:");
                    cidadeDAO.listarCidades().forEach(Cidade::mostrar);
                    break;

                case 2:
                    // Mostrar rotas
                    System.out.println("\nROTAS:");
                    rotaDAO.listarRotas().forEach(Rota::mostrar);
                    break;

                case 3:
                    // Mostrar pokémons da rota
                    System.out.println("\nDigite o ID da rota para mostrar os pokémons:");
                    int rotaId = sc.nextInt();
                    rotaDAO.listarPokemonsDaRota(rotaId).forEach(Pokemon::mostrar);
                    break;

                case 4:// Mostrar treinador
                    System.out.println("\nDigite o ID do treinador para mostrar:");
                    int idTreinador = sc.nextInt();
                    Treinador t = treinadorDAO.buscarTreinadorPorId(idTreinador);
                    if (t != null) t.mostrar();
                    break;

                case 5:
                    // Adicionar movimento a um Pokémon
                    System.out.println("\nDigite o ID do pokemon e do movimento para adicionar:");
                    int idPokemon = sc.nextInt();
                    int idMovimento = sc.nextInt();
                    pokemonDAO.adicionarMovimentoAoPokemon(idPokemon, idMovimento);
                    break;

                case 6://Buscar pokemon
                    System.out.println("\nDigite o id do pokemon que quer buscar:");
                    idPokemon = sc.nextInt();
                    Pokemon p = pokemonDAO.buscarPokemonPorId(idPokemon);
                    p.mostrar();
                    p.getMovimentos().forEach(movimento -> movimento.mostrar());
                    break;

                case 7://Mostrar pokemons de um treinador
                    System.out.println("\nDigite o id do treinador que quer ver os pokemons:");
                    idPokemon = sc.nextInt();
                    treinadorDAO.buscarPokemonsDoTreinador(idPokemon).forEach(pokemon -> pokemon.mostrar());
                    break;
                default://
                    break;
            }
        }
    }

    public static void mostra_menu(){
        System.out.println("Menu");
        System.out.println("1: Mostrar cidades");
        System.out.println("2: Mostrar rotas");
        System.out.println("3: Mostrar pokemons em uma rota");
        System.out.println("4: Mostrar treinador");
        System.out.println("5: Adicionar movimentos a um pokemon");
        System.out.println("6: Buscar pokemon");
        System.out.println("7: Mostrar pokemons de um treinador");
        System.out.println("0: Sair");
    }

    
}
