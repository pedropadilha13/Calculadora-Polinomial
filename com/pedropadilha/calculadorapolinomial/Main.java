package com.pedropadilha.calculadorapolinomial;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Entrega de trabalho - Calculadora Polinomial
 * 
 * Eu, Pedro Padilha Farroco, declaro que
 * 
 * todas as respostas são fruto do meu próprio trabalho, não copiei respostas de
 * colegas externos à equipe, não disponibilizei minhas respostas para colegas
 * externos à equipe e não realizei quaisquer outras atividades desonestas para
 * me beneficiar ou prejudicar outros.
 * 
 * ------------------------------------------------------------------------------
 * 
 * Classe "cliente" que demonstra o uso da clase Polinomio
 * 
 * É possível criar e excluir Polinômios, bem como realizar operaçoões de soma e
 * multiplicação com eles
 * 
 * @author Pedro Padilha
 */
public class Main {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        // ArrayList<Polinomio> polinomios armazena os Polinômios criados pelos usuários
        ArrayList<Polinomio> polinomios = new ArrayList<Polinomio>();

        // opcao armazena a escolha do usuário (menu)
        int opcao;

        do {

            opcao = mainMenu();

            switch (opcao) {
                case 1:
                    // 1) Criar novo Polinômio
                    // Cria, exibe e salva o Polinômio criado
                    Polinomio p = createNewPolinomio();
                    System.out.println("\nNovo Polinômio criado!");
                    p.mostra();
                    polinomios.add(p);
                    break;
                case 2:
                    // Exibe todos os Polinômios
                    printPolinomios(polinomios);
                    break;
                case 3:
                    // "Seleciona" um Polinômio e exibe o menu de ações que podem
                    // ser realizadas com ele

                    // Verifica se existe algum Polinômio.
                    // Em caso negativo, exibe uma mensagem e não deixa o usuário escolher
                    if (polinomios.size() == 0) {
                        System.out.println("Nenhum Polinômio disponível!");
                        break;
                    }

                    // escolhido armazena o Polinômio escolhido (índice)
                    int escolhido;

                    do {
                        printPolinomios(polinomios);
                        escolhido = readInt("Selecione o Polinômio: ");
                    } while (escolhido <= 0 || escolhido > polinomios.size());

                    // polinomioEscolhido é o Polinômio escolhido pelo usuário
                    Polinomio polinomioEscolhido = polinomios.get(escolhido - 1);

                    // loopPolinomio: menu de ações que podem ser realizadas quando um Polinômio é
                    // escolhido
                    loopPolinimio: do {

                        // opcao armazena a escolha feita no menu de Polinômios
                        opcao = polinomioMenu();

                        switch (opcao) {
                            case 1:
                                // Calcular o valor do Polinômio
                                int x = readInt("Valor de x: ");

                                double valor = polinomioEscolhido.calcular(x);
                                System.out.println("Valor do Polinômio: " + valor);
                                break;
                            case 2:
                                // Soma de Polinômios

                                // Escolher um Polinômio para ser somado
                                do {
                                    printPolinomios(polinomios);
                                    escolhido = readInt("Selecione o Polinômio para somar: ");
                                } while (escolhido <= 0 || escolhido > polinomios.size());

                                // Imprime o resultado da soma polinomial
                                Polinomio soma = polinomioEscolhido.soma(polinomios.get(escolhido - 1));

                                System.out.println("Resultado da soma polinomial: " + soma);
                                break;

                            case 3:
                                // Multiplicação de Polinômios

                                // Escolher o Polinômio para ser multiplicado
                                do {
                                    printPolinomios(polinomios);
                                    escolhido = readInt("Selecione o Polinômio para multiplicar: ");
                                } while (escolhido <= 0 || escolhido > polinomios.size());

                                // Imprime o resultado do produto polinomial
                                Polinomio produto = polinomioEscolhido.multiplica(polinomios.get(escolhido - 1));

                                System.out.println("Resultado do produto polinomial: " + produto);
                                break;
                            case 4:
                                // Excluir Polinômio

                                // Escolher o Polinômio a ser excluído
                                polinomios.remove(polinomioEscolhido);
                            case 5:
                                // Voltar ao "Menu Principal"
                                break loopPolinimio;
                            default:
                                // Qualquer escolha que seja inválida
                                System.out.println("Escolha inválida!");

                        }
                    } while (true);
                    break;
                case 4:
                    // Finalizar programa
                    System.exit(0);
                    break;
                default:
                    // Qualquer escolha que seja inválida
                    System.out.println("Escolha inválida!");
            }

            System.out.println("\n--------------------------------------------------------");
        } while (true);

    }

    /**
     * Criar novo Polinômio Recebe o grau do Polinômio, depois os termos de grau n
     * até 0
     * 
     * @return polinômio (Polinomio) criado
     */
    public static Polinomio createNewPolinomio() {

        int grau;

        do {
            grau = readInt("Grau do novo Polinômio: ");
        } while (grau < 0);

        Polinomio p = new Polinomio(grau);

        for (int i = grau; i >= 0; i--) {
            p.setTermo(i, readDouble("Termo de grau " + i + ": "));
        }

        return p;
    }

    /**
     * Exibe o "Menu Principal" e recebe a escolha feita pelo usuário
     * 
     * @return escolha feita
     */
    public static int mainMenu() {

        int opcao;

        do {
            opcao = 0;

            System.out.println("\n1) Criar novo Polinômio");
            System.out.println("2) Mostrar Polinômios");
            System.out.println("3) Selecionar Polinômio");
            System.out.println("4) Finalizar\n");

            opcao = readInt("Selecione: ", "Opção inválida!");
        } while (opcao < 1 || opcao > 4);

        return opcao;

    }

    /**
     * Exibe o "Menu Polinômio" e recebe a escolha feita pelo usuário
     * 
     * @return escolha feita
     */
    public static int polinomioMenu() {

        int opcao;

        do {

            opcao = 0;
            System.out.println("\n1) Calcular valor do Polinômio");
            System.out.println("2) Somar Polinômio");
            System.out.println("3) Multiplicar Polinômio");
            System.out.println("4) Excluir Polinômio");
            System.out.println("5) Voltar\n");

            opcao = readInt("Selecione: ", "Opção inválida!");
        } while (opcao < 1 || opcao > 5);

        return opcao;

    }

    /**
     * Exibe uma mensagem e lê um inteiro
     * 
     * @param message Mensagem exibida na tela
     * @return inteiro lido
     */
    public static int readInt(String message) {
        try {
            System.out.print(message);
            int i = teclado.nextInt();
            return i;
        } catch (InputMismatchException ime) {
            ime.printStackTrace();
            return -1;
        } finally {
            teclado.nextLine();
        }
    }

    /**
     * Exibe uma mensagem e lê um inteiro. Em caso de erro, exibe uma mensagem
     * 
     * @param message      Mensagem exibida na tela
     * @param errorMessage Mensagem de erro exibida em caso de Exception
     * @return inteiro lido
     */
    public static int readInt(String message, String errorMessage) {
        int i = readInt(message);

        if (i == -1) {
            System.err.println(errorMessage);
        }

        return i;
    }

    /**
     * Exibe uma mensagem e lê um double
     * 
     * @param message Mensagem exibida na tela
     * @return double lido
     */
    public static double readDouble(String message) {
        Double d = null;

        do {
            System.out.print(message);
            try {
                d = teclado.nextDouble();
            } catch (InputMismatchException ime) {
                ime.printStackTrace();
                d = null;
            } finally {
                teclado.nextLine();
            }
        } while (d < 0);

        return d;
    }

    /**
     * Exibe os Polinomöios na tela
     * 
     * @param polinomios ArrayList<Polinomio> que contém os Polinômios a serem
     *                   exibidos
     */
    public static void printPolinomios(ArrayList<Polinomio> polinomios) {
        System.out.println("\nPolinômios:");
        for (int i = 0; i < polinomios.size(); i++) {
            System.out.println((i + 1) + ") " + polinomios.get(i));
        }
    }

}
