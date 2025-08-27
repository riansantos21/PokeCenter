import Controller.PokemonController;
import Controller.TreinadorController;
import Model.Pokemon;

import View.ListaPokemonsPanel;
import View.ListaTreinadorPanel;
import View.PokemonForm;
import View.TreinadorForm;

import javax.swing.*;

public class Main extends JFrame {

    private JDesktopPane desktopPane;
    private PokemonController pokemonController;
private TreinadorController treinadorController;

    public Main() {
        super("Sistema de Gerenciamento de Pokémons");
        this.pokemonController = new PokemonController();
        this.treinadorController= new TreinadorController();

        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

        createMenuBar();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Menu Pokémons


        JMenu menuPokemons = new JMenu("Pokémons");
        JMenuItem itemCadastrarPokemon = new JMenuItem("Cadastrar Pokémon");
        JMenuItem itemListarPokemons = new JMenuItem("Listar Pokémons");
        JMenuItem itemRealizarCargasMassivas = new JMenuItem("Listar Pokémons lote");
        itemCadastrarPokemon.addActionListener(e -> openPokemonForm(null));
        itemListarPokemons.addActionListener(e -> openListaPokemonsPanel());

        itemRealizarCargasMassivas.addActionListener(e -> realizarCargasMassivas());
        menuPokemons.add(itemRealizarCargasMassivas);

        menuPokemons.add(itemCadastrarPokemon);
        menuPokemons.add(itemListarPokemons);

        menuBar.add(menuPokemons);


        // Menu Treinadores
        JMenu menuTreinadores = new JMenu("Treinadores");
        JMenuItem itemCadastrarTreinador = new JMenuItem("Cadastrar Treinador");
        JMenuItem itemListarTreinadores = new JMenuItem("Listar Treinadores");

        itemCadastrarTreinador.addActionListener(e -> openTreinadorForm(null));
        // Dentro de createMenuBar(), logo após o addActionListener do Cadastrar Treinador:
        itemListarTreinadores.addActionListener(e -> openListaTreinadoresPanel());


        menuTreinadores.add(itemCadastrarTreinador);
        menuTreinadores.add(itemListarTreinadores);

        menuBar.add(menuTreinadores);



        // Menu Sair
        JMenu menuSair = new JMenu("Sair");
        JMenuItem itemSair = new JMenuItem("Sair do Sistema");
        itemSair.addActionListener(e -> System.exit(0));

        menuSair.add(itemSair);
        menuBar.add(menuSair);

        setJMenuBar(menuBar);
    }



    private void openPokemonForm(Integer idPokemon) {
        PokemonForm pokemonForm = new PokemonForm(pokemonController, idPokemon);
        desktopPane.add(pokemonForm);
        pokemonForm.setVisible(true);
        pokemonForm.toFront();
    }

    private void openTreinadorForm(Integer idTreinador) {
        TreinadorForm treinadorForm = new TreinadorForm(treinadorController, idTreinador);
        desktopPane.add(treinadorForm);
        treinadorForm.setVisible(true);
        treinadorForm.toFront();
    }

    private void openListaPokemonsPanel() {
        ListaPokemonsPanel listaPokemons = new ListaPokemonsPanel(pokemonController);
        desktopPane.add(listaPokemons);
        listaPokemons.setVisible(true);
        listaPokemons.toFront();
    }
    private void openListaTreinadoresPanel() {
        ListaTreinadorPanel listaTreinadores = new ListaTreinadorPanel(treinadorController);
        desktopPane.add(listaTreinadores);
        listaTreinadores.setVisible(true);
        listaTreinadores.toFront();
    }


    // Dentro da classe Main, abaixo de openListaPokemonsPanel()

    private void realizarCargasMassivas () {
        Pokemon[] pokemons = new Pokemon[]{

                new Pokemon("Bulbasaur", "Planta", "Venenoso", 5, 45),
                new Pokemon("Ivysaur", "Planta", "Venenoso", 16, 60),
                new Pokemon("Venusaur", "Planta", "Venenoso", 32, 80),
                new Pokemon("Charmander", "Fogo", null, 5, 39),
                new Pokemon("Charmeleon", "Fogo", null, 16, 58),
                new Pokemon("Charizard", "Fogo", "Voador", 36, 78),
                new Pokemon("Mega Charizard X", "Fogo", "Dragão", 36, 90),
                new Pokemon("Mega Charizard Y", "Fogo", "Voador", 36, 90),
                new Pokemon("Squirtle", "Água", null, 5, 44),
                new Pokemon("Wartortle", "Água", null, 16, 59),
                new Pokemon("Blastoise", "Água", null, 36, 79),
                new Pokemon("Mega Blastoise", "Água", null, 36, 100),
                new Pokemon("Caterpie", "Inseto", null, 3, 45),
                new Pokemon("Metapod", "Inseto", null, 7, 50),
                new Pokemon("Butterfree", "Inseto", "Voador", 10, 60),
                new Pokemon("Weedle", "Inseto", "Venenoso", 3, 40),
                new Pokemon("Kakuna", "Inseto", "Venenoso", 7, 45),
                new Pokemon("Beedrill", "Inseto", "Venenoso", 10, 65),
                new Pokemon("Mega Beedrill", "Inseto", "Venenoso", 10, 80),
                new Pokemon("Pidgey", "Normal", "Voador", 3, 40),
                new Pokemon("Pidgeotto", "Normal", "Voador", 18, 63),
                new Pokemon("Pidgeot", "Normal", "Voador", 36, 83),
                new Pokemon("Rattata", "Normal", null, 3, 30),
                new Pokemon("Raticate", "Normal", null, 20, 55),
                new Pokemon("Spearow", "Normal", "Voador", 5, 40),
                new Pokemon("Fearow", "Normal", "Voador", 20, 65),
                new Pokemon("Ekans", "Venenoso", null, 5, 35),
                new Pokemon("Arbok", "Venenoso", null, 22, 60),
                new Pokemon("Pikachu", "Elétrico", null, 5, 35),
                new Pokemon("Raichu", "Elétrico", null, 25, 60),
                new Pokemon("Raichu de Alola", "Elétrico", "Psíquico", 25, 60),
                new Pokemon("Sandshrew", "Terrestre", null, 5, 50),
                new Pokemon("Sandslash", "Terrestre", null, 22, 75),
                new Pokemon("Nidoran♀", "Venenoso", null, 5, 55),
                new Pokemon("Nidorina", "Venenoso", null, 16, 70),
                new Pokemon("Nidoqueen", "Venenoso", "Terrestre", 36, 90),
                new Pokemon("Nidoran♂", "Venenoso", null, 5, 46),
                new Pokemon("Nidorino", "Venenoso", null, 16, 61),
                new Pokemon("Nidoking", "Venenoso", "Terrestre", 36, 81),
                new Pokemon("Clefairy", "Fada", null, 5, 70),
                new Pokemon("Clefable", "Fada", null, 20, 95),
                new Pokemon("Vulpix", "Fogo", null, 5, 38),
                new Pokemon("Ninetales", "Fogo", null, 22, 73),
                new Pokemon("Jigglypuff", "Normal", "Fada", 5, 115),
                new Pokemon("Wigglytuff", "Normal", "Fada", 20, 140),
                new Pokemon("Zubat", "Venenoso", "Voador", 5, 40),
                new Pokemon("Golbat", "Venenoso", "Voador", 22, 75),
                new Pokemon("Oddish", "Planta", "Venenoso", 5, 45),
                new Pokemon("Gloom", "Planta", "Venenoso", 21, 60),
                new Pokemon("Vileplume", "Planta", "Venenoso", 36, 75),
                new Pokemon("Paras", "Inseto", "Planta", 5, 35),
                new Pokemon("Parasect", "Inseto", "Planta", 24, 60),
                new Pokemon("Venonat", "Inseto", "Venenoso", 5, 60),
                new Pokemon("Venomoth", "Inseto", "Venenoso", 31, 70),
                new Pokemon("Diglett", "Terrestre", null, 5, 10),
                new Pokemon("Dugtrio", "Terrestre", null, 26, 35),
                new Pokemon("Meowth", "Normal", null, 5, 40),
                new Pokemon("Persian", "Normal", null, 28, 65),
                new Pokemon("Psyduck", "Água", null, 5, 50),

                new Pokemon("Golduck", "Água", null, 33, 80),
                new Pokemon("Mankey", "Lutador", null, 5, 40),
                new Pokemon("Primeape", "Lutador", null, 28, 65),
                new Pokemon("Growlithe", "Fogo", null, 5, 55),
                new Pokemon("Arcanine", "Fogo", null, 34, 90),
                new Pokemon("Poliwag", "Água", null, 5, 40),
                new Pokemon("Poliwhirl", "Água", null, 25, 65),
                new Pokemon("Poliwrath", "Água", "Lutador", 36, 90),
                new Pokemon("Abra", "Psíquico", null, 5, 25),
                new Pokemon("Kadabra", "Psíquico", null, 16, 40),
                new Pokemon("Alakazam", "Psíquico", null, 36, 55),
                new Pokemon("Machop", "Lutador", null, 5, 40),
                new Pokemon("Machoke", "Lutador", null, 28, 80),
                new Pokemon("Machamp", "Lutador", null, 36, 90),
                new Pokemon("Bellsprout", "Planta", "Venenoso", 5, 40),
                new Pokemon("Weepinbell", "Planta", "Venenoso", 21, 65),
                new Pokemon("Victreebel", "Planta", "Venenoso", 36, 80),
                new Pokemon("Tentacool", "Água", "Venenoso", 5, 40),
                new Pokemon("Tentacruel", "Água", "Venenoso", 30, 80),
                new Pokemon("Geodude", "Pedra", "Terrestre", 5, 40),
                new Pokemon("Graveler", "Pedra", "Terrestre", 25, 70),
                new Pokemon("Golem", "Pedra", "Terrestre", 36, 90),
                new Pokemon("Ponyta", "Fogo", null, 5, 50),
                new Pokemon("Rapidash", "Fogo", null, 40, 65),
                new Pokemon("Slowpoke", "Água", "Psíquico", 5, 60),
                new Pokemon("Slowbro", "Água", "Psíquico", 36, 95),
                new Pokemon("Mega Slowbro", "Água", "Psíquico", 36, 95),
                new Pokemon("Magnemite", "Elétrico", "Metálico", 5, 40),
                new Pokemon("Magneton", "Elétrico", "Metálico", 30, 60),
                new Pokemon("Farfetch'd", "Normal", "Voador", 5, 52),
                new Pokemon("Doduo", "Normal", "Voador", 5, 35),
                new Pokemon("Dodrio", "Normal", "Voador", 36, 60),
                new Pokemon("Seel", "Água", null, 5, 65),
                new Pokemon("Dewgong", "Água", "Gelo", 36, 90),
                new Pokemon("Grimer", "Venenoso", null, 5, 50),
                new Pokemon("Muk", "Venenoso", null, 36, 105),
                new Pokemon("Shellder", "Água", null, 5, 30),
                new Pokemon("Cloyster", "Água", "Gelo", 36, 70),
                new Pokemon("Gastly", "Fantasma", "Venenoso", 5, 30),
                new Pokemon("Haunter", "Fantasma", "Venenoso", 25, 45),
                new Pokemon("Gengar", "Fantasma", "Venenoso", 36, 60),
                new Pokemon("Mega Gengar", "Fantasma", "Venenoso", 36, 60),
                new Pokemon("Onix", "Pedra", "Terrestre", 5, 35),
                new Pokemon("Drowzee", "Psíquico", null, 5, 60),
                new Pokemon("Hypno", "Psíquico", null, 36, 85),
                new Pokemon("Krabby", "Água", null, 5, 30),
                new Pokemon("Kingler", "Água", null, 36, 55),
                new Pokemon("Voltorb", "Elétrico", null, 5, 40),
                new Pokemon("Electrode", "Elétrico", null, 30, 60),
                new Pokemon("Exeggcute", "Planta", "Psíquico", 5, 60),
                new Pokemon("Exeggutor", "Planta", "Psíquico", 36, 95),
                new Pokemon("Cubone", "Terrestre", null, 5, 50),
                new Pokemon("Marowak", "Terrestre", null, 36, 80),
                new Pokemon("Hitmonlee", "Lutador", null, 36, 70),
                new Pokemon("Hitmonchan", "Lutador", null, 36, 70),
                new Pokemon("Lickitung", "Normal", null, 30, 90),
                new Pokemon("Koffing", "Venenoso", null, 5, 40),
                new Pokemon("Weezing", "Venenoso", null, 36, 65),
                new Pokemon("Rhyhorn", "Terrestre", "Pedra", 5, 80),
                new Pokemon("Rhydon", "Terrestre", "Pedra", 36, 105),
                new Pokemon("Chansey", "Normal", null, 36, 250),
                new Pokemon("Tangela", "Planta", null, 36, 65),
                new Pokemon("Kangaskhan", "Normal", null, 36, 105),
                new Pokemon("Mega Kangaskhan", "Normal", null, 36, 105),
                new Pokemon("Horsea", "Água", null, 5, 30),
                new Pokemon("Seadra", "Água", null, 36, 55),
                new Pokemon("Goldeen", "Água", null, 5, 45),
                new Pokemon("Seaking", "Água", null, 36, 80),
                new Pokemon("Staryu", "Água", null, 5, 30),
                new Pokemon("Starmie", "Água", "Psíquico", 36, 60),
                new Pokemon("Mr. Mime", "Psíquico", "Fada", 36, 60),
                new Pokemon("Scyther", "Inseto", "Voador", 36, 70),
                new Pokemon("Jynx", "Gelo", "Psíquico", 36, 65),
                new Pokemon("Electabuzz", "Elétrico", null, 36, 65),
                new Pokemon("Magmar", "Fogo", null, 36, 65),
                new Pokemon("Pinsir", "Inseto", null, 36, 65),
                new Pokemon("Mega Pinsir", "Inseto", "Voador", 36, 65),
                new Pokemon("Tauros", "Normal", null, 36, 75),
                new Pokemon("Magikarp", "Água", null, 5, 20),
                new Pokemon("Gyarados", "Água", "Voador", 36, 95),
                new Pokemon("Mega Gyarados", "Água", "Noturno", 36, 95),
                new Pokemon("Lapras", "Água", "Gelo", 36, 130),
                new Pokemon("Ditto", "Normal", null, 36, 48),
                new Pokemon("Eevee", "Normal", null, 5, 55),
                new Pokemon("Vaporeon", "Água", null, 36, 130),
                new Pokemon("Jolteon", "Elétrico", null, 36, 65),
                new Pokemon("Flareon", "Fogo", null, 36, 65),
                new Pokemon("Porygon", "Normal", null, 36, 65),
                new Pokemon("Omanyte", "Pedra", "Água", 5, 35),
                new Pokemon("Omastar", "Pedra", "Água", 36, 70),
                new Pokemon("Kabuto", "Pedra", "Água", 5, 30),
                new Pokemon("Kabutops", "Pedra", "Água", 36, 60),
                new Pokemon("Aerodactyl", "Pedra", "Voador", 36, 80),
                new Pokemon("Mega Aerodactyl", "Pedra", "Voador", 36, 80),
                new Pokemon("Snorlax", "Normal", null, 36, 160),
                new Pokemon("Articuno", "Gelo", "Voador", 36, 90),
                new Pokemon("Zapdos", "Elétrico", "Voador", 36, 90),
                new Pokemon("Moltres", "Fogo", "Voador", 36, 90),
                new Pokemon("Dratini", "Dragão", null, 5, 41),
                new Pokemon("Dragonair", "Dragão", null, 36, 61),
                new Pokemon("Dragonite", "Dragão", "Voador", 36, 91),
                new Pokemon("Mewtwo", "Psíquico", null, 36, 106),
                new Pokemon("Mega Mewtwo X", "Psíquico", "Lutador", 36, 106),
                new Pokemon("Mega Mewtwo Y", "Psíquico", null, 36, 106),
                new Pokemon("Mew", "Psíquico", null, 50, 100),
                new Pokemon("Rowlet", "Planta", "Voador", 5, 68),
                new Pokemon("Dartrix", "Planta", "Voador", 16, 78),
                new Pokemon("Decidueye", "Planta", "Fantasma", 36, 78),
                new Pokemon("Litten", "Fogo", null, 5, 45),
                new Pokemon("Torracat", "Fogo", null, 16, 65),
                new Pokemon("Incineroar", "Fogo", "Noturno", 36, 95),
                new Pokemon("Popplio", "Água", null, 5, 50),
                new Pokemon("Brionne", "Água", null, 16, 70),
                new Pokemon("Primarina", "Água", "Fada", 36, 80),
                new Pokemon("Pikipek", "Normal", "Voador", 5, 35),
                new Pokemon("Trumbeak", "Normal", "Voador", 18, 55),
                new Pokemon("Toucannon", "Normal", "Voador", 36, 80),
                new Pokemon("Yungoos", "Normal", null, 5, 48),
                new Pokemon("Gumshoos", "Normal", null, 20, 88),
                new Pokemon("Grubbin", "Inseto", null, 5, 47),
                new Pokemon("Charjabug", "Inseto", "Elétrico", 18, 57),
                new Pokemon("Vikavolt", "Inseto", "Elétrico", 36, 77),
                new Pokemon("Crabrawler", "Lutador", null, 5, 47),
                new Pokemon("Crabominable", "Lutador", "Gelo", 36, 97),
                new Pokemon("Oricorio Baile", "Fogo", "Voador", 5, 75),
                new Pokemon("Oricorio PomPom", "Elétrico", "Voador", 5, 75),
                new Pokemon("Oricorio Pu", "Psíquico", "Voador", 5, 75),
                new Pokemon("Oricorio Sensu", "Fantasma", "Voador", 5, 75),
                new Pokemon("Cutiefly", "Inseto", "Fada", 5, 40),
                new Pokemon("Ribombee", "Inseto", "Fada", 25, 60),
                new Pokemon("Rockruff", "Pedra", null, 5, 45),
                new Pokemon("Lycanroc Midday", "Pedra", null, 25, 75),
                new Pokemon("Lycanroc Midnight", "Pedra", null, 25, 75),
                new Pokemon("Lycanroc Dusk", "Pedra", null, 25, 75),
                new Pokemon("Wishiwashi Solo", "Água", null, 5, 45),
                new Pokemon("Wishiwashi School", "Água", null, 25, 175),
                new Pokemon("Mareanie", "Venenoso", "Água", 5, 50),
                new Pokemon("Toxapex", "Venenoso", "Água", 36, 90),
                new Pokemon("Mudbray", "Terrestre", null, 5, 70),
                new Pokemon("Mudsdale", "Terrestre", null, 36, 125),
                new Pokemon("Dewpider", "Água", "Inseto", 5, 38),
                new Pokemon("Araquanid", "Água", "Inseto", 36, 68),
                new Pokemon("Fomantis", "Planta", null, 5, 40),
                new Pokemon("Lurantis", "Planta", null, 36, 70),
                new Pokemon("Morelull", "Grama", "Fada", 5, 40),
                new Pokemon("Shiinotic", "Grama", "Fada", 36, 60),
                new Pokemon("Salandit", "Veneno", "Fogo", 5, 48),
                new Pokemon("Salazzle", "Veneno", "Fogo", 36, 68),
                new Pokemon("Stufful", "Normal", "Lutador", 5, 70),
                new Pokemon("Bewear", "Normal", "Lutador", 36, 120),
                new Pokemon("Bounsweet", "Planta", null, 5, 35),
                new Pokemon("Steenee", "Planta", null, 18, 55),
                new Pokemon("Tsareena", "Planta", null, 36, 72),
                new Pokemon("Comfey", "Fada", null, 5, 51),
                new Pokemon("Oranguru", "Normal", "Psíquico", 36, 90),
                new Pokemon("Passimian", "Lutador", null, 36, 100),
                new Pokemon("Wimpod", "Inseto", "Água", 5, 25),
                new Pokemon("Golisopod", "Inseto", "Água", 36, 75),
                new Pokemon("Sandygast", "Fantasma", "Terrestre", 5, 55),
                new Pokemon("Palossand", "Fantasma", "Terrestre", 36, 85),
                new Pokemon("Pyukumuku", "Água", null, 36, 55),
                new Pokemon("Type Null", "Normal", null, 36, 95),
                new Pokemon("Silvally", "Normal", null, 36, 95),
                new Pokemon("Minior Red", "Rochoso", "Voador", 5, 40),
                new Pokemon("Komala", "Normal", null, 36, 65),
                new Pokemon("Turtonator", "Fogo", "Dragão", 36, 72),
                new Pokemon("Togedemaru", "Elétrico", "Aço", 36, 35),
                new Pokemon("Mimikyu", "Fantasma", "Fada", 36, 55),
                new Pokemon("Bruxish", "Água", "Psíquico", 36, 68),
                new Pokemon("Drampa", "Normal", "Dragão", 36, 78),
                new Pokemon("Dhelmise", "Fantasma", "Grama", 36, 90),
                new Pokemon("Jangmoo", "Dragão", null, 5, 45),
                new Pokemon("Hakamoo", "Dragão", "Lutador", 25, 65),
                new Pokemon("Kommoo", "Dragão", "Lutador", 36, 75),
                new Pokemon("Tapu Koko", "Elétrico", "Fada", 36, 70),
                new Pokemon("Tapu Lele", "Psíquico", "Fada", 36, 70),
                new Pokemon("Tapu Bulu", "Grama", "Fada", 36, 70),
                new Pokemon("Tapu Fini", "Água", "Fada", 36, 70),
                new Pokemon("Cosmog", "Psíquico", null, 5, 43),
                new Pokemon("Cosmoem", "Psíquico", null, 25, 43),
                new Pokemon("Solgaleo", "Psíquico", "Metálico", 36, 137),
                new Pokemon("Lunala", "Psíquico", "Fantasma", 36, 137),
                new Pokemon("Nihilego", "Rochoso", "Veneno", 36, 79),
                new Pokemon("Buzzwole", "Inseto", "Lutador", 36, 107),
                new Pokemon("Pheromosa", "Inseto", "Lutador", 36, 71),
                new Pokemon("Xurkitree", "Elétrico", null, 36, 83),
                new Pokemon("Celesteela", "Aço", "Voador", 36, 97),
                new Pokemon("Kartana", "Aço", "Grama", 36, 59),
                new Pokemon("Guzzlord", "Noturno", "Dragão", 36, 223),
                new Pokemon("Necrozma", "Psíquico", null, 36, 97),
                new Pokemon("Magearna", "Aço", "Fada", 36, 80),
                new Pokemon("Marshadow", "Lutador", "Fantasma", 36, 90),
                new Pokemon("Poipole", "Veneno", null, 5, 67),
                new Pokemon("Naganadel", "Veneno", "Dragão", 36, 73),
                new Pokemon("Stakataka", "Pedra", "Aço", 36, 61),
                new Pokemon("Blacephalon", "Fogo", "Fantasma", 36, 71),
                new Pokemon("Zeraora", "Elétrico", null, 36, 88)};


        for (Pokemon p : pokemons) {
            try {
                pokemonController.cadastrarPokemon(
                        p.getNome(),
                        p.getTipoPrimario(),
                        p.getTipoSecundario(),
                        p.getNivel(),
                        p.getHpMaximo()
                );
            } catch (Exception e) {
                System.err.println("Erro ao inserir " + p.getNome() + ": " + e.getMessage());
            }
        }

        JOptionPane.showMessageDialog(this, "Carga massiva de Pokémons concluída!");
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Main().setVisible(true);
        });
    }
}