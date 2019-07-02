package Clases;

import java.util.ArrayList;
import java.util.List;

public  class InfoVariedad  {
    public static int IDIOMA;
    public static final int VARIEDAD_RESISTENTE = 1;
    public static final int VARIEDAD_INTERMEDIO = 2;
    public static final int VARIEDAD_VULNERABLE = 3;
    public static String Titulo;
    public static String SubTitulo;
    public static String Descripcion;
    public static String SubTituloMain = "";
    public static int VARIEDAD_RULETA = -1;
    public static  String getAplicarRecomendacion(int tipo){
        if(tipo == VARIEDAD_VULNERABLE) return VariedadVulnerable.QueAplicar;
        if(tipo == VARIEDAD_INTERMEDIO) return VariedadIntermedio.QueAplicar;
        if(tipo == VARIEDAD_RESISTENTE) return VariedadResistente.QueAplicar;
        return  "";
    }
    public static  String getAplicarRecomendacion2(int tipo){
        if(tipo == VARIEDAD_VULNERABLE) return VariedadVulnerable.QueAplicar2;
        if(tipo == VARIEDAD_INTERMEDIO) return VariedadIntermedio.QueAplicar2;
        if(tipo == VARIEDAD_RESISTENTE) return VariedadResistente.QueAplicar2;
        return  "";
    }
    public static class VariedadResistente  {
        public static String Titulo;
        public static String SubTitulo;
        public static String PeruVariedad;
        public static String EcuadorVariedad;
        public static String MetodoEvaluacion ="";
        public static String QueAplicar = "";
        public static String QueAplicar2 = "";
    }

    public static class VariedadIntermedio  {
        public static String Titulo;
        public static String SubTitulo;
        public static String PeruVariedad;
        public static String EcuadorVariedad;
        public static String MetodoEvaluacion ="";
        public static String QueAplicar = "";
        public static String QueAplicar2 = "";
    }

    public static class VariedadVulnerable  {
        public static String Titulo;
        public static String SubTitulo;
        public static String PeruVariedad;
        public static String EcuadorVariedad;
        public static String MetodoEvaluacion ="";
        public static String QueAplicar = "";
        public static String QueAplicar2 = "";
    }
    public static List<String> Ayudas = new ArrayList<>();
    public  static List<String> FungicidaAplicar = new ArrayList<>();

    public static int calcular(int TIPO_VARIEDAD, int  valor1, int valor2)throws Exception{
        int retorno = -1;
        if(valor2 == 0)valor2=1;
        else if(valor2 == 1) valor2= 3;
        else if(valor2 == 2) valor2= 5;
        if(TIPO_VARIEDAD == VARIEDAD_RESISTENTE){
            int total = valor1 + valor2;
            if(total <= 3)retorno =  0;
            else if(total >= 4 && total <= 6)retorno =  1;
            else retorno = 2;
        }else if(TIPO_VARIEDAD == VARIEDAD_INTERMEDIO){
            if(valor1 == 2) valor1 = 3;
            int total = valor1 + valor2;
            if(total <= 3)retorno =  0;
            else if(total >= 4 && total <= 6)retorno =  1;
            else retorno = 2;
        } else if(TIPO_VARIEDAD == VARIEDAD_VULNERABLE){
            if(valor1 == 1) valor1 = 2;
            else if(valor1 == 2) valor1 = 5;
            int total = valor1 + valor2;
            if(total <= 3)retorno =  0;
            else if(total >= 4 && total <= 6)retorno =  1;
            else retorno = 2;
        }else throw  new Exception("TIPO_VARIEDAD NO VALIDO");
        return retorno;
    }
}