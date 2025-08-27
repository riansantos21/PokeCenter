package Controller;

import Model.DAO.PokemonDao;
import Model.Pokemon;

import java.sql.SQLException;
import java.util.List;

public class PokemonController {
    private PokemonDao pokemonDAO;

    public PokemonController() {
        this.pokemonDAO = new PokemonDao();
    }

    // ---------- Cadastrar ----------
    public void cadastrarPokemon(String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo) throws Exception {
        // --- Validações ---
        nome = nome.trim();
        tipoPrimario = tipoPrimario.trim();
        if (tipoSecundario != null) tipoSecundario = tipoSecundario.trim();

        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("O nome do Pokémon não pode ser vazio.");
        if (!nome.matches("[a-zA-Z ]+")) throw new IllegalArgumentException("O nome só pode conter letras e espaços.");
        if (tipoPrimario == null || tipoPrimario.isBlank()) throw new IllegalArgumentException("O tipo primário é obrigatório.");
        if (tipoPrimario.equalsIgnoreCase(tipoSecundario)) throw new IllegalArgumentException("O tipo primário e o tipo secundário não podem ser iguais.");
        if (nivel < 1 || nivel > 100) throw new IllegalArgumentException("O nível do Pokémon deve estar entre 1 e 100.");
        if (hpMaximo <= 0 ) throw new IllegalArgumentException("O HP Máximo deve ser maior que 0.");
        if (hpMaximo >= 500 && !nome.equals("Zorua")) throw new IllegalArgumentException("O HP Máximo deve ser menor que 500 .");

        // Cria objeto Pokémon
        Pokemon pokemon = new Pokemon(nome, tipoPrimario, tipoSecundario, nivel, hpMaximo);

        try {
            pokemonDAO.inserir(pokemon);
        } catch (SQLException e) {
            throw new Exception("Erro ao cadastrar Pokémon no banco de dados: " + e.getMessage());
        }
    }

    // ---------- Atualizar ----------
    public void atualizarPokemon(int id, String nome, String tipoPrimario, String tipoSecundario, int nivel, int hpMaximo) throws Exception {
        if (id <= 0) throw new IllegalArgumentException("ID inválido. O ID deve ser maior que 0.");

        Pokemon pokemon = new Pokemon(id, nome, tipoPrimario, tipoSecundario, nivel, hpMaximo);

        try {
            pokemonDAO.atualizar(pokemon);
        } catch (SQLException e) {
            throw new Exception("Erro ao atualizar Pokémon no banco de dados: " + e.getMessage());
        }
    }

    // ---------- Listar todos ----------
    public List<Pokemon> listarTodosPokemons() {
        return pokemonDAO.listarTodos();
    }

    // ---------- Buscar por ID ----------
    public Pokemon buscarPokemonPorId(int id) {
        return pokemonDAO.buscarPorId(id);
    }

    // ---------- Remover ----------
    public void removerPokemon(int id) throws Exception {
        if (id <= 0) throw new IllegalArgumentException("ID inválido. O ID deve ser maior que 0.");

        try {
            pokemonDAO.remover(id);
        } catch (SQLException e) {
            throw new Exception("Erro ao remover Pokémon: " + e.getMessage());
        }
    }

    // ---------- Buscar por nome ----------
    public List<Pokemon> buscarPokemonPorNome(String nome) {
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("O nome não pode ser vazio.");
        if (!nome.matches("[a-zA-Z ]+")) throw new IllegalArgumentException("O nome deve conter apenas letras e espaços.");

        List<Pokemon> lista = pokemonDAO.buscarPorNome(nome);

        if (lista.isEmpty()) {
            throw new IllegalArgumentException("Nenhum Pokémon encontrado com esse nome.");
        }
        return lista;
    }

    // ---------- Carga massiva ----------
    public void realizarCargasMassivas() {
        Pokemon[] pokemonsArray = new Pokemon[]{
                new Pokemon("Bulbasaur", "Planta", "Venenoso", 5, 45),
                new Pokemon("Ivysaur", "Planta", "Venenoso", 16, 60),
                new Pokemon("Venusaur", "Planta", "Venenoso", 32, 80),
                new Pokemon("Charmander", "Fogo", null, 5, 39),
                new Pokemon("Charmeleon", "Fogo", null, 16, 58),
                new Pokemon("Charizard", "Fogo", "Voador", 36, 78),
                new Pokemon("Squirtle", "Água", null, 5, 44),
                new Pokemon("Wartortle", "Água", null, 16, 59),
                new Pokemon("Blastoise", "Água", null, 36, 79)
        };

        try {
            pokemonDAO.inserirPokemonsEmLote(List.of(pokemonsArray));
            System.out.println("Carga massiva de Pokémons concluída!");
        } catch (Exception e) {
            System.err.println("Erro ao realizar carga massiva: " + e.getMessage());
        }
    }
}
