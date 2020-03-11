package com.pedropadilha.calculadorapolinomial;

/**
 * Classe que representa um Polinômio e contém métodos de manipulação básicos
 * 
 * @author Pedro Padilha
 */
public class Polinomio {
    private int grau;
    private double[] termos;

    /**
     * Construtor da Classe Polinomio
     * 
     * @param grau grau do polinômio a ser instanciado
     */
    public Polinomio(int grau) {
        this.grau = grau;
        this.termos = new double[grau + 1];
    }

    /**
     * Método que grava um determinado valor no seu respectivo termo
     * 
     * @param termo índice do termo
     * @param valor valor do termo
     */
    public void setTermo(int termo, double valor) {
        this.termos[termo] = valor;
    }

    /**
     * Método que grava vários valores em seus termos (do menos para o mais
     * significativo, não sendo necessário preencher todos os termos)
     * 
     * @param termos valores dos termos do Polinômio (do menos para o mais
     *               significativo)
     */
    public void setTermos(double[] termos) {
        for (int i = 0; i < termos.length; i++) {
            this.termos[i] = termos[i];
        }
    }

    /**
     * 
     * @param termo especifica o termo a ser recuperado
     * @return valor do termo especificado em termo
     */
    public double getTermo(int termo) {
        if (termo > this.grau) {
            return 0;
        } else {
            return this.termos[termo];
        }
    }

    /**
     * Checa se o Polinômio é constante
     * 
     * @return isConstante
     */
    public boolean isConstante() {

        boolean isConstante = true;
        int i = this.grau;

        while (isConstante && i > 0) {
            isConstante = this.getTermo(i) == 0;
            i--;
        }

        return isConstante;
    }

    /**
     * Calcula o valor do Polinômio a partir de uma variável x especificada
     * 
     * @param x variável a ser substituiída no Polinômio
     * @return valor calculado do Polinômio
     */
    public double calcular(int x) {
        double valor = 0;
        for (int i = grau; i >= 0; i--) {
            valor += termos[i] * Math.pow(x, i);
        }
        return valor;
    }

    /**
     * Calcula a soma do Polinômio com outro especificado em p
     * 
     * @param p Polinômio a ser somado
     * @return um objeto Polinomio resultante da soma com p
     */
    public Polinomio soma(Polinomio p) {
        int newGrau = Math.max(this.grau, p.grau);

        Polinomio newPolinomio = new Polinomio(newGrau);

        for (int i = newGrau; i >= 0; i--) {
            newPolinomio.setTermo(i, this.getTermo(i) + p.getTermo(i));
        }

        return newPolinomio;
    }

    /**
     * Calcula a multiplicação do Polinômio com outro especificado em p
     * 
     * @param p Polinômio a ser multiplicado
     * @return um objeto Polinomio resultante da multiplicação com p
     */
    public Polinomio multiplica(Polinomio p) {

        Polinomio multiplicacao = new Polinomio(this.grau + p.grau);

        for (int i = 0; i <= this.grau; i++) {
            for (int j = 0; j <= p.grau; j++) {
                multiplicacao.setTermo((i + j), multiplicacao.getTermo(i + j) + (this.getTermo(i) * p.getTermo(j)));
            }
        }

        return multiplicacao;
    }

    /**
     * Monta o Polinômio para exibição
     */
    @Override
    public String toString() {

        String s = "P(x) =";

        if (this.isConstante()) {
            return s + " " + this.getTermo(0);
        }

        for (int i = grau; i >= 0; i--) {

            if (termos[i] != 0) {

                if (i != grau) {
                    s += " +";
                }

                s += " " + termos[i];

                if (i != 0) {
                    s += "x^" + i;
                }
            }

        }

        return s;
    }

    /**
     * Imprime o Polinômio na tela
     */
    public void mostra() {
        System.out.println(this);
    }

}
