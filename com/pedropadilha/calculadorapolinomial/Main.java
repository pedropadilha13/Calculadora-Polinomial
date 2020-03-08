package com.pedropadilha.calculadorapolinomial;

import java.util.Scanner;

/**
 *
 * @author Pedro Padilha
 */
public class Main {

    public static void main(String[] args) {

        Polinomio p1 = new Polinomio(3);
        Polinomio p2 = new Polinomio(4);

        double[] termos1 = { 2, 2, 5, 1 };
        double[] termos2 = { 4, 0, 5, 6, 9 };

        p1.setTermos(termos1);
        p2.setTermos(termos2);

        p1.mostra();
        p2.mostra();

        Polinomio soma = p1.soma(p2);

        soma.mostra();

        Polinomio multiplicacao = p1.multiplica(p2);
        multiplicacao.mostra();

    }

}
