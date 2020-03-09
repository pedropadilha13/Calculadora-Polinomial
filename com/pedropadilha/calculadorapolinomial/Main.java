/*
Entrega de trabalho - Calculadora Polinomial

Eu, Pedro Padilha Farroco, declaro que

todas as respostas são fruto do meu próprio trabalho,
não copiei respostas de colegas externos à equipe,
não disponibilizei minhas respostas para colegas externos à equipe e
não realizei quaisquer outras atividades desonestas para me beneficiar ou prejudicar outros.
*/

package com.pedropadilha.calculadorapolinomial;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Pedro Padilha
 */
public class Main {
    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<Polinomio> polinomios = new ArrayList<Polinomio>();

        int opcao;

        do {

            opcao = mainMenu();

            switch (opcao) {
                case 1:
                    Polinomio p = createNewPolinomio();
                    System.out.println("\nNovo Polinômio criado!");
                    p.mostra();
                    polinomios.add(p);
                    break;
                case 2:
                    printPolinomios(polinomios);
                    break;
                case 3:
                    int escolhido;

                    do {
                        printPolinomios(polinomios);
                        escolhido = readInt("Selecione o Polinômio: ");
                    } while (escolhido <= 0 || escolhido > polinomios.size());

                    Polinomio polinomioEscolhido = polinomios.get(escolhido - 1);

                    loopPolinimio: do {

                        opcao = polinomioMenu();

                        switch (opcao) {
                            case 1:
                                int x = readInt("Valor de x: ");

                                double valor = polinomioEscolhido.calcular(x);
                                System.out.println("Valor do Polinômio: " + valor);
                                break;
                            case 2:
                                do {
                                    printPolinomios(polinomios);
                                    escolhido = readInt("Selecione o Polinômio para somar: ");
                                } while (escolhido <= 0 || escolhido > polinomios.size());

                                Polinomio soma = polinomioEscolhido.soma(polinomios.get(escolhido - 1));

                                System.out.println("Resultado da soma polinomial: " + soma);
                                break;

                            case 3:
                                do {
                                    printPolinomios(polinomios);
                                    escolhido = readInt("Selecione o Polinômio para multiplicar: ");
                                } while (escolhido <= 0 || escolhido > polinomios.size());

                                Polinomio produto = polinomioEscolhido.multiplica(polinomios.get(escolhido - 1));

                                System.out.println("Resultado do produto polinomial: " + produto);
                                break;
                            case 4:
                                polinomios.remove(polinomioEscolhido);
                            case 5:
                                break loopPolinimio;
                            default:
                                System.out.println("Escolha inválida!");

                        }
                    } while (true);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Escolha inválida!");
            }

            System.out.println("\n--------------------------------------------------------");
        } while (true);

    }

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

    public static int readInt(String message, String errorMessage) {
        int i = readInt(message);

        if (i == -1) {
            System.err.println(errorMessage);
        }

        return i;
    }

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

    public static void printPolinomios(ArrayList<Polinomio> polinomios) {
        System.out.println("\nPolinômios:");
        for (int i = 0; i < polinomios.size(); i++) {
            System.out.println((i + 1) + ") " + polinomios.get(i));
        }
    }

}
