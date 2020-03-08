package com.pedropadilha.calculadorapolinomial;

/**
 *
 * @author Pedro Padilha
 */
public class Polinomio {
    private int grau;
    private double[] termos;
    
    public Polinomio(int grau) {
        this.grau = grau;
        this.termos = new double[grau + 1];
    }
    
    public void setTermo(int termo, double valor) {
        this.termos[termo] = valor;
    }
    
    public void setTermos(double[] termos) {
        for (int i = 0; i < termos.length; i++) {
            this.termos[i] = termos[i];
        }
    }
    
    public double getTermo(int termo) {
        if (termo > this.grau) {
            return 0;
        } else {
            return this.termos[termo];
        }
    }
    
    public void setTermos(int[] termos, double[] valores) {
        
    }
    
    public double calcular(int x) {
        double valor = 0;
        for (int i = grau; i >= 0; i--) {
            valor += termos[i] * Math.pow(x, i);
        }
        return valor;
    }
    
    public Polinomio soma(Polinomio p) {
        int newGrau = Math.max(this.grau, p.grau);
        
        Polinomio newPolinomio = new Polinomio(newGrau);
        
        for (int i = newGrau; i >= 0; i--) {
            newPolinomio.setTermo(i, this.getTermo(i) + p.getTermo(i));
        }
        
        return newPolinomio;
    }
    
    public Polinomio multiplica(Polinomio p) {
        return null;
    }
    
    public void mostra() {
        String s = "P(x) =";
        
        for (int i = grau; i >= 0; i--) {
            if (i == 0 || termos[i] != 0) {
                switch (i) {
                    case 1:
                        s += " " + termos[i] + "x" + " + ";
                        break;
                    case 0:
                        s += " " + termos[i];
                        break;
                    default:
                        s += " " + termos[i] + "x^" + i + " + ";
                        break;
                }
            }

        }
        
        System.out.println(s);
    }
    
}
