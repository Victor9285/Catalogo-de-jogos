import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Biblioteca biblioteca = new Biblioteca();
        API api = new API();

        int numDeEscolha;
        do {
            System.out.println("""
                    1- Buscar jogo
                    2- Ver biblioteca
                    3- Salvar biblioteca em JSON
                    4- Remover jogo
                    7- Mostrar quantidade de jogos cadastrados
                    8- Ordenar alfabeticamente
                    9- Sair
                    """);
            numDeEscolha = scanner.nextInt();
            scanner.nextLine();
            switch (numDeEscolha) {
                case 1:
                    System.out.println("Buscando jogo...");
                    System.out.print("Digie o nome do jogo:");
                    String nome = scanner.nextLine();
                    if (api.requisicao(nome) == null) {
                        System.out.println("Erro na busca");
                        return;
                    }
                    System.out.println("JOGO ENCONTRADO");
                    PesquisaJogo pesquisa = api.filtraJogo();
                    System.out.println("Jogo: " + pesquisa.name());
                    System.out.println("Lancamento: " + pesquisa.released());
                    System.out.println("""
                            Deseja adicionar a sua biblioteca?
                            1- Sim
                            2- Nao
                            """);
                    numDeEscolha = scanner.nextInt();
                    scanner.nextLine();
                    if (numDeEscolha == 1) {
                        Jogo jogo = new Jogo(pesquisa.name(), pesquisa.released());
                        System.out.println("Adicionando a biblioteca...");
                        biblioteca.adicionarJogo(jogo);
                    }
                    break;
                case 2:
                    System.out.println("""
                            1- Mostrar biblioteca
                            2- Buscar na biblioteca por genero
                                    """);
                    numDeEscolha = scanner.nextInt();
                    scanner.nextLine();
                    if (numDeEscolha == 1) {
                        System.out.println("SUA BIBLIOTECA:");
                        biblioteca.mostrarBiblioteca();
                    } else if(numDeEscolha ==2){
                        System.out.println("Qual genero que deseja pesquisar? (Em ingles)");
                        String genero = scanner.nextLine();
                        api.buscarPorGenero(genero);
                        
                    } else{
                        System.out.println("Opcao invalida!");
                    }

                    break;
                case 3:
                    System.out.println("Salvando biblioteca como json...");
                    biblioteca.salvarJson();
                    break;
                case 4:
                    System.out.println("Qual jogo deseja remover?");
                    String nomeJogo = scanner.nextLine();
                    biblioteca.removerJogo(nomeJogo);
                    break;
                case 5:

                    break;
                default:
                    System.out.println("Opcao Invalida!");
                    break;
            }
            System.out.println("APERTE ENTER PARA VOLTAR AO MENU");
            scanner.nextLine();
        } while (numDeEscolha != 9);
        scanner.close();
    }
}
