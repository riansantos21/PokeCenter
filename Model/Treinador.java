package Model;

public class Treinador {
    private int idTreinador;
    private String nomeTreinador;
    private String cidade;

    // Construtor completo
    public Treinador(int idTreinador, String nomeTreinador, String cidade) {
        this.idTreinador = idTreinador;
        this.nomeTreinador = nomeTreinador;
        this.cidade = cidade;
    }

    // Construtor sem ID (quando o BD gera automaticamente ou quando não precisamos informar ainda)
    public Treinador(String nomeTreinador, String cidade) {
        this.nomeTreinador = nomeTreinador;
        this.cidade = cidade;
    }

    // Getters
    public int getIdTreinador() {
        return idTreinador;
    }

    public String getNomeTreinador() {
        return nomeTreinador;
    }

    public String getCidade() {
        return cidade;
    }

    // Setters
    public void setIdTreinador(int idTreinador) {
        this.idTreinador = idTreinador;
    }

    public void setNomeTreinador(String nomeTreinador) {
        this.nomeTreinador = nomeTreinador;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Treinador{" +
                "idTreinador=" + idTreinador +
                ", nomeTreinador='" + nomeTreinador + '\'' +
                ", cidade='" + cidade + '\'' +
                '}';
    }
}
