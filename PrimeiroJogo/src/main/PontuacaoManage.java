package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class PontuacaoManage {
    
	//VARIÁVEIS DO ARQUIVO QUE IRÁ ARMAZENAR, E INT DE RECORDE E ULTIMA PONTUAÇÃO
    private static final File pontuacao = new File("pontuacao.txt");
    private  static int recorde, ultima;
    
    //REALIZA A LEITURA LINHA A LINHA DO ARQUIVO 
    public static void ler(){
        try {
            FileReader reader = new FileReader(pontuacao);
            BufferedReader breader = new BufferedReader(reader);
            String linha;
            
            linha = breader.readLine();
            String[] leitura = linha.split("=");
            recorde = Integer.parseInt(leitura[1]);
            
            linha = breader.readLine();
            leitura = linha.split("=");
            ultima = Integer.parseInt(leitura[1]);
            
        } catch (Exception e) {
            System.out.println("Erro ao ler arquivo pontuacao.txt\n" +e);
        }
    }
    
    //REESCREVE O ARQUIVO COM A NOVA PONTUAÇÃO
    public static void escrever(){
        try {
            FileWriter writer = new FileWriter(pontuacao);
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write("Recorde="+String.valueOf(getRecorde())
                  +"\nUltima="+String.valueOf(getUltima()));
            bw.flush();
        } catch (Exception e) {
            System.out.println("Erro no writer\n" +e);
        }
    }

    
    //GETTERS E SETTERS
    public static int getRecorde() {
        return recorde;
    }

    public static int getUltima() {
        return ultima;
    }

    public static void setRecorde(int recorde) {
        PontuacaoManage.recorde = recorde;
    }

    public static void setUltima(int ultima) {
        PontuacaoManage.ultima = ultima;
    }
    
    
    
}
