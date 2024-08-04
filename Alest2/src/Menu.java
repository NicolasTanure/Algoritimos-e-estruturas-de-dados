package src;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public Menu() {
        exibirMenu();
    }

    private void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        try {
            System.out.println("=== Rastreador de dinheiro da Polícia ===");
            System.out.println("1. Rastrear a rota dos bandidos");
            System.out.println("2. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    new Reader();
                    break;
                case 2:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Escolha novamente.");
            }
        } catch (InputMismatchException e) {
            System.err.println("Entrada inválida. A opção deve ser um número inteiro.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
