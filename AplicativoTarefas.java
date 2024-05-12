import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// Classe abstrata para representar uma tarefa genérica
abstract class Tarefa implements Comparable<Tarefa> {
    // Atributos da tarefa
    protected String descricao;
    protected boolean concluida;
    protected String categoria;

    // Construtor da tarefa
    public Tarefa(String descricao, String categoria) {
        this.descricao = descricao;
        this.categoria = categoria;
        this.concluida = false;
    }

    // Método para marcar a tarefa como concluída
    public void marcarComoConcluida() {
        this.concluida = true;
    }

    // Método abstrato para exibir os detalhes da tarefa (implementado em subclasses)
    public abstract void exibirDetalhes();

    // Método de comparação para ordenação de tarefas (implementado de acordo com a necessidade)
    @Override
    public int compareTo(Tarefa outraTarefa) {
        // Implemente a lógica de comparação, por exemplo, por prioridade ou prazo
        return 0;
    }
}

// Classe para representar uma tarefa simples
class TarefaSimples extends Tarefa {
    // Construtor da tarefa simples
    public TarefaSimples(String descricao, String categoria) {
        super(descricao, categoria);
    }

    // Método para exibir os detalhes da tarefa simples
    @Override
    public void exibirDetalhes() {
        String status = concluida ? "Concluída" : "Pendente";
        System.out.println("Tarefa Simples: " + descricao + " | Categoria: " + categoria + " | Status: " + status);
    }
}

// Classe para representar uma tarefa com prazo
class TarefaComPrazo extends Tarefa {
    private Date prazo;

    // Construtor da tarefa com prazo
    public TarefaComPrazo(String descricao, String categoria, Date prazo) {
        super(descricao, categoria);
        this.prazo = prazo;
    }

    // Método para exibir os detalhes da tarefa com prazo
    @Override
    public void exibirDetalhes() {
        String status = concluida ? "Concluída" : "Pendente";
        System.out.println("Tarefa com Prazo: " + descricao + " | Categoria: " + categoria + " | Prazo: " + prazo + " | Status: " + status);
    }
}

// Classe para representar uma tarefa importante
class TarefaImportante extends Tarefa {
    // Atributo para armazenar a prioridade da tarefa
    private int prioridade;

    // Construtor da tarefa importante
    public TarefaImportante(String descricao, String categoria, int prioridade) {
        super(descricao, categoria);
        this.prioridade = prioridade;
    }

    // Método para exibir os detalhes da tarefa importante
    @Override
    public void exibirDetalhes() {
        String status = concluida ? "Concluída" : "Pendente";
        System.out.println("Tarefa Importante: " + descricao + " | Categoria: " + categoria + " | Prioridade: " + prioridade + " | Status: " + status);
    }
}

// Classe principal do aplicativo de tarefas
public class AplicativoTarefas {
    // Lista para armazenar as tarefas
    private List<Tarefa> listaTarefas;

    // Construtor da aplicação
    public AplicativoTarefas() {
        this.listaTarefas = new ArrayList<>();
    }

    // Método para adicionar uma tarefa à lista
    public void adicionarTarefa(Tarefa tarefa) {
    listaTarefas.add(tarefa);
    System.out.println("Tarefa adicionada com sucesso:");
    tarefa.exibirDetalhes();
    }

    // Método para exibir todas as tarefas da lista
    public void exibirTarefas() {
        if (listaTarefas.isEmpty()) {
            System.out.println("Nenhuma Tarefa cadastrada");
        } else {
            for (int i = 0; i < listaTarefas.size(); i++) {
                Tarefa tarefa = listaTarefas.get(i);
                System.out.print((i + 1) + ". ");
                tarefa.exibirDetalhes();
            }
        }
    }

    // Método para excluir uma tarefa da lista pelo índice
    public void excluirTarefa(int indice) {
        int indiceReal = indice - 1;
        if (indiceReal >= 0 && indiceReal < listaTarefas.size()) {
            listaTarefas.remove(indiceReal);
            System.out.println("Tarefa excluída com sucesso!");
        } else {
            System.out.println("Índice inválido. Nenhuma tarefa foi excluída.");
        }
    }

    // Método para marcar uma tarefa como concluída pelo índice
    public void marcarComoConcluida(int indice) {
        int indiceReal = indice - 1;
        if (indiceReal >= 0 && indiceReal < listaTarefas.size()) {
            listaTarefas.get(indiceReal).marcarComoConcluida();
            System.out.println("Tarefa marcada como concluída com sucesso!");
        } else {
            System.out.println("Índice inválido. Nenhuma tarefa foi marcada como concluída.");
        }
    }

    // Método para editar a descrição da tarefa
    public void editarDescricao(int indice, String novaDescricao) {
        int indiceReal = indice - 1;
        if (indiceReal >= 0 && indiceReal < listaTarefas.size()) {
            listaTarefas.get(indiceReal).descricao = novaDescricao;
            System.out.println("Descrição da tarefa editada com sucesso!");
        } else {
            System.out.println("Índice inválido. Nenhuma tarefa foi editada.");
        }
    }

    // Método para editar a categoria da tarefa
    public void editarCategoria(int indice, String novaCategoria) {
        int indiceReal = indice - 1;
        if (indiceReal >= 0 && indiceReal < listaTarefas.size()) {
            listaTarefas.get(indiceReal).categoria = novaCategoria;
            System.out.println("Categoria da tarefa editada com sucesso!");
        } else {
            System.out.println("Índice inválido. Nenhuma tarefa foi editada.");
        }
    }

    // Método para editar o status da tarefa
    public void editarStatus(int indice, boolean novoStatus) {
        int indiceReal = indice - 1;
        if (indiceReal >= 0 && indiceReal < listaTarefas.size()) {
            listaTarefas.get(indiceReal).concluida = novoStatus;
            System.out.println("Status da tarefa editado com sucesso!");
        } else {
            System.out.println("Índice inválido. Nenhuma tarefa foi editada.");
        }
    }

    // Método para calcular a porcentagem de tarefas concluídas
    public double calcularPorcentagemConcluidas() {
        int totalTarefas = listaTarefas.size();
        int tarefasConcluidas = 0;
        for (Tarefa tarefa : listaTarefas) {
            if (tarefa.concluida) {
                tarefasConcluidas++;
            }
        }
        if (totalTarefas == 0) {
            return 0.0;
        }
        return (double) tarefasConcluidas / totalTarefas * 100;
    }

    // Método para capturar a entrada do usuário
    private String capturarEntrada(String mensagem) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(mensagem);
        return reader.readLine();
    }

    // Método principal
    public static void main(String[] args) throws IOException {
        AplicativoTarefas app = new AplicativoTarefas();

        while (true) {
            // Menu de opções
            System.out.println("\nMENU:");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Exibir Tarefas");
            System.out.println("3. Excluir Tarefa");
            System.out.println("4. Marcar Tarefa como Concluída");
            System.out.println("5. Editar Descrição da Tarefa");
            System.out.println("6. Editar Categoria da Tarefa");
            System.out.println("7. Editar Status da Tarefa");
            System.out.println("8. Calcular Porcentagem de Tarefas Concluídas");
            System.out.println("0. Sair");

            // Solicitação da opção ao usuário
            System.out.println("\nEscolha uma opção:");
            Scanner scanner = new Scanner(System.in);
            if(scanner.hasNextInt()) {
                int opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        // Adicionar tarefa
                        String descricao = app.capturarEntrada("Digite a descrição da tarefa:");
                        String categoria = app.capturarEntrada("Digite a categoria da tarefa:");
                        Tarefa tarefa = new TarefaSimples(descricao, categoria);
                        app.adicionarTarefa(tarefa);
                        break;
                    case 2:
                        // Exibir tarefas
                        System.out.println("\nTAREFAS:");
                        app.exibirTarefas();
                        break;
                    case 3:
                        // Excluir tarefa
                        System.out.println("Digite o índice da tarefa a ser excluída:");
                        int indiceExcluir = scanner.nextInt();
                        app.excluirTarefa(indiceExcluir);
                        System.out.println("\nTAREFAS ATUALIZADAS:");
                        app.exibirTarefas();
                        break;
                    case 4:
                        // Marcar tarefa como concluída
                        System.out.println("Digite o índice da tarefa a ser marcada como concluída:");
                        int indiceConcluir = scanner.nextInt();
                        app.marcarComoConcluida(indiceConcluir);
                        System.out.println("\nTAREFAS ATUALIZADAS:");
                        app.exibirTarefas();
                        break;
                    case 5:
                        // Editar descrição da tarefa
                        System.out.println("Digite o índice da tarefa cuja descrição deseja editar:");
                        try {
                            int indiceEditarDescricao = scanner.nextInt();
                            String novaDescricao = app.capturarEntrada("Digite a nova descrição:");
                            app.editarDescricao(indiceEditarDescricao, novaDescricao);
                            System.out.println("\nTAREFAS ATUALIZADAS:");
                            app.exibirTarefas();
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor, insira um número inteiro válido.");
                        }
                        break;
                    case 6:
                        // Editar categoria da tarefa
                        System.out.println("Digite o índice da tarefa cuja categoria deseja editar:");
                        try {
                            int indiceEditarCategoria = scanner.nextInt();
                            String novaCategoria = app.capturarEntrada("Digite a nova categoria:");
                            app.editarCategoria(indiceEditarCategoria, novaCategoria);
                            System.out.println("\nTAREFAS ATUALIZADAS:");
                            app.exibirTarefas();
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor, insira um número inteiro válido.");
                        }
                        break;
                    case 7:
                        // Editar status da tarefa
                        System.out.println("Digite o índice da tarefa cujo status deseja editar:");
                        try {
                            int indiceEditarStatus = scanner.nextInt();
                            System.out.println("Digite o novo status (true para concluída, false para pendente):");
                            boolean novoStatus = scanner.nextBoolean();
                            app.editarStatus(indiceEditarStatus, novoStatus);
                            System.out.println("\nTAREFAS ATUALIZADAS:");
                            app.exibirTarefas();
                        } catch (InputMismatchException e) {
                            System.out.println("Por favor, insira um número inteiro válido.");
                        }
                        break;
                    case 8:
                        // Calcular porcentagem de tarefas concluídas
                        double porcentagem = app.calcularPorcentagemConcluidas();
                        System.out.println("Porcentagem de tarefas concluídas: " + porcentagem + "%");
                        break;
                    case 0:
                        // Sair do programa
                        System.out.println("Saindo...");
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida!");
                }
            } else {
                System.out.println("Por favor, insira um número inteiro válido.");
                scanner.next(); // Limpar o buffer do Scanner
            }
        }
    }
}
