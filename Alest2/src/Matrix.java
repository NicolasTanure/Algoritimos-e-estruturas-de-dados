package src;

import java.util.*;

public class Matrix {

    private int rows, columns, sum;
    private String direction;
    private String[][] matrix;
    private Map<String, int[]> directionMap;
    private Set<String> numbers;

    public Matrix(int rows, int columns) {
        matrix = new String[rows][columns];
        this.rows = rows;
        this.columns = columns;
        sum = 0;
        directionMap = new HashMap<>();
        directionMap.put("N", new int[]{-1, 0});
        directionMap.put("S", new int[]{1, 0});
        directionMap.put("L", new int[]{0, 1});
        directionMap.put("O", new int[]{0, -1});
        numbers = new HashSet<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
    }

    public void setValue(int x, int y, String value) {
        try {
            matrix[x][y] = value;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Índices de matriz inválidos.");
        }
    }

    private void move(int[] cords) {
        cords[0] += directionMap.get(direction)[0];
        cords[1] += directionMap.get(direction)[1];
    }

    public void traverse(int start) {
        try {
            int[] cords = new int[]{start, 0};
            String position = matrix[cords[0]][cords[1]];
            String value = "";
            direction = "L";
            while (!position.equals("#")) {
                switch (position) {
                    case "-":
                    case "|":
                        move(cords);
                        position = matrix[cords[0]][cords[1]];
                        break;
                    case "/":
                        direction = switch (direction) {
                            case "N" -> "L";
                            case "S" -> "O";
                            case "L" -> "N";
                            case "O" -> "S";
                            default -> throw new IllegalStateException("Unexpected value: " + direction);
                        };
                        move(cords);
                        position = matrix[cords[0]][cords[1]];
                        break;
                    case "\\":
                        direction = switch (direction) {
                            case "N" -> "O";
                            case "S" -> "L";
                            case "L" -> "S";
                            case "O" -> "N";
                            default -> throw new IllegalStateException("Unexpected value: " + direction);
                        };
                        move(cords);
                        position = matrix[cords[0]][cords[1]];
                        break;
                    default:
                        if (numbers.contains(position)) {
                            value += position;
                            move(cords);
                            position = matrix[cords[0]][cords[1]];
                            if (!numbers.contains(position)) {
                                sum += Integer.parseInt(value);
                                value = "";
                            }
                        }
                        break;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Índices de matriz inválidos durante a travessia.");
        } catch (NumberFormatException e) {
            System.err.println("Valor numérico inválido encontrado durante a travessia.");
        }
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public int getSum() {
        return sum;
    }

    
}