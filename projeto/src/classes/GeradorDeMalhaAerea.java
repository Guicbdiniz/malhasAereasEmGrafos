package classes;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GeradorDeMalhaAerea {

    private static final String CAMINHO_DA_PASTA_DE_DADOS = "D:\\Repositories\\malhasAereasEmGrafos\\projeto\\dados";
    private static final Charset ENCODING = StandardCharsets.UTF_8;

    private GeradorDeMalhaAerea() {

    }

    /**
     * Gera uma malha aérea a partir de dados da mesma que se encontram em um
     * arquivo de texto.
     * 
     * O arquivo de texto deve estar na pasta de dados do projeto. O caminho para a
     * pasta de dados sempre será pego pela constante da classe
     * CAMINHO_DA_PASTA_DE_DADOS.
     * 
     * As especificações quanto ao formato do arquivo texto se encontram no
     * enunciado do problema e na documentação do projeto.
     * 
     * Não é necessário usar um buffer pois os arquivos são sempre pequenos.
     * 
     * @param nomeDoArquivo - nome do arquivo com os dados.
     * @return uma malha aérea com os dados de vôos e rotas do arquivo.
     */
    public static MalhaAerea geraMalhaAerea(String nomeDoArquivo) {
        Grafo rotas = new Grafo();
        GrafoDirigido voos = new GrafoDirigido();

        try {

            Path caminho = Paths.get(CAMINHO_DA_PASTA_DE_DADOS, "/", nomeDoArquivo);
            List<String> linhasDoArquivo = Files.readAllLines(caminho, ENCODING);

            linhasDoArquivo.remove(0); // Removendo primeira linha inútil

            for (String linha : linhasDoArquivo) {
                String linhaSemEspacos = linha.replaceAll("\s", "");
                List<String> valoresDaLinha = new ArrayList<String>(Arrays.asList(linhaSemEspacos.split(";")));

                String idVerticeA = valoresDaLinha.remove(0);
                String idVerticeB = valoresDaLinha.remove(0);
                int direcao = Integer.parseInt(valoresDaLinha.remove(0));

                PesoDaAresta pesoDeVoo = pegaPesoDosValoresDaLinha(valoresDaLinha, true);
                PesoDaAresta pesoDaRota = pegaPesoDosValoresDaLinha(valoresDaLinha, false);

                rotas.adicionaAresta(idVerticeA, idVerticeB, pesoDaRota);

                if (direcao == 1) {
                    voos.adicionaAresta(idVerticeA, idVerticeB, pesoDeVoo);
                } else if (direcao == -1) {
                    voos.adicionaAresta(idVerticeB, idVerticeA, pesoDeVoo);
                } else {
                    throw new Exception("Valor de direcao diferente de 1 e -1.");
                }

            }

        } catch (Exception e) {
            System.out.println("Opa, houve um erro ao tentar ler o arquivo. Gerando uma malha vazia.");
            e.printStackTrace();
        }

        return new MalhaAerea(rotas, voos);
    }

    /**
     * Pega peso da aresta a partir dos valores da linha lida.
     * 
     * @param valoresDaLinha - valores de peso da linha lida.
     * @param eVoo           - parâmetro de diferenciação de vôo e rota.
     * @return peso da aresta.
     */
    public static PesoDaAresta pegaPesoDosValoresDaLinha(List<String> valoresDaLinha, boolean eVoo) {

        List<String> cloneDosValores = new ArrayList<String>(valoresDaLinha);

        int distancia = Integer.parseInt(cloneDosValores.remove(0));
        DuracaoDeVoo duracao = pegaDuracaoEmTexto(cloneDosValores.remove(0));

        if (eVoo) {
            List<Horario> horarios = pegaListaDeHorariosDoVoo(cloneDosValores);
            return PesoDaAresta.criaPesoDeVoo(distancia, duracao, horarios);
        } else {
            return PesoDaAresta.criaPesoDeRota(distancia, duracao);
        }

    }

    /**
     * Pega duração de vôo/rota a partir de texto no formato "x:y".
     * 
     * @param textoDeDuracao - texto com duraçào.
     * @return duracação de vôo/rota.
     */
    public static DuracaoDeVoo pegaDuracaoEmTexto(String textoDeDuracao) {
        List<String> valoresDoTexto = Arrays.asList(textoDeDuracao.split(":"));
        int horas = Integer.parseInt(valoresDoTexto.get(0));
        int minutos = Integer.parseInt(valoresDoTexto.get(1));

        return new DuracaoDeVoo(horas, minutos);
    }

    /**
     * Pega lista de horários a partir de horários em texto.
     */
    public static List<Horario> pegaListaDeHorariosDoVoo(List<String> horariosEmTexto) {
        List<Horario> horarios = new ArrayList<Horario>();

        for (String horarioEmTexto : horariosEmTexto) {
            List<String> valoresDoTexto = Arrays.asList(horarioEmTexto.split(":"));
            int horas = Integer.parseInt(valoresDoTexto.get(0));
            int minutos = Integer.parseInt(valoresDoTexto.get(1));
            horarios.add(new Horario(horas, minutos));
        }

        return horarios;
    }
}