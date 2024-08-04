package src;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Reader {

    private BufferedReader br;
    private Scanner sc;
    private Matrix matrix;

    public Reader() {
        read();
    }

    public void read() {
        try {
            br = new BufferedReader(new FileReader("tests\\casoE50.txt"));
            sc = new Scanner(br.readLine());
            matrix = new Matrix(sc.nextInt(), sc.nextInt());
    
            int start = 0;
            for (int i = 0; i < matrix.getRows(); i++) {
                String line = br.readLine();
                System.out.println("Mapa da perseguição dos bandidos linha: " + (i + 1) + ": " + line);
                if (line == null) {
                    System.out.println("Arquivo de entrada incompleto.");
                    return;
                }
                String[] values = line.split("");
                if (values[0].equals("-")) {
                    start = i;
                }
                if (values.length >= matrix.getColumns()) {
                    for (int j = 0; j < matrix.getColumns(); j++) {
                        matrix.setValue(i, j, values[j]);
                    }
                } else {
                    int count = 0;
                    for (String string : values) {
                        matrix.setValue(i, count, string);
                        count++;
                    }
                    for (int k = count; k < matrix.getColumns(); k++) {
                        matrix.setValue(i, k, " ");
                    }
                }
            }
            matrix.traverse(start);
            System.out.println("--------------------------------------------------------------------------------");

            System.out.println("Dinheiro recuperado: " + matrix.getSum() + " reais");
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Formato inválido encontrado no arquivo: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }
    
    

    private void closeResources() {
        try {
            if (br != null) br.close();
            if (sc != null) sc.close();
        } catch (IOException e) {
            System.err.println("Erro ao fechar o recurso: " + e.getMessage());
        }
    }
}
