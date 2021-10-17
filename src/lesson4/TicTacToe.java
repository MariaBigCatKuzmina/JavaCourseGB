package lesson4;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    private static final char DOT_X = 'X';
    private static final char DOT_O = '0';
    private static final char DOT_EMPTY = '•';
    private static final int SIZE = 3;

    private static char[][] map;
    private static Scanner scanner;
    private static Random randomIndex;

    public static void main(String[] args) {
        map = new char[SIZE][SIZE];
        scanner = new Scanner(System.in);
        randomIndex = new Random();

        initMap();
        printMap();

        while(true) {
            playerTurn();
            if (checkWin(DOT_X)) {
                System.out.println("Победил игрок");
                break;
            }
            if (checkDraw()) {
                System.out.println("Ничья");
                break;
            }

            aiTurn();
            if (checkWin(DOT_O)) {
                System.out.println("Победил компьютер");
                break;
            }

            if (checkDraw()) {
                System.out.println("Ничья");
                break;
            }
        }
    }

    private static void playerTurn() {
        System.out.println("Введите координаты row col:");
        int row = readIndex();
        int col = readIndex();

        while (!checkRange(row) || !checkRange(col))  {
            System.out.println("Координаты должны быть в зоне от 1 до " + SIZE + ". Повторите ввод:");
            row = readIndex();
            col = readIndex();
        }
        while (!checkEmptySlot(row, col)){
            System.out.println("Ячейка "+ row + ", " + col + " уже занята. Повторите ввод:");
            row = readIndex();
            col = readIndex();
        }

        map[row - 1][col - 1] = DOT_X;

        printMap();
    }

    private static void aiTurn() {
        if (!blockPlayerMove())
        fillRandomSlot();
        System.out.println("Ход компьютера: ");
        printMap();
    }

    private static boolean blockPlayerMove() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(checkEmptySlot(i + 1, j + 1)) {
                    map[i][j] = DOT_X;
                    if (checkWin(DOT_X)) {
                        map[i][j] = DOT_O;
                        return true;
                    }
                    else {
                        map[i][j] = DOT_EMPTY;
                    }
                }
            }
        }
        return false;
    }

    private static void fillRandomSlot() {
        int row;
        int col;
        do {
            row = randomIndex.nextInt(SIZE);
            col = randomIndex.nextInt(SIZE);
        } while (!checkEmptySlot(row + 1, col + 1));
        map[row][col] = DOT_O;
    }

    private static boolean checkDraw() {
        int emptySlotCount = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY)
                    emptySlotCount ++;
            }
        }
        return emptySlotCount == 0;
    }

    private static boolean checkWin(char checkVal){
        int colCheckCount=0;
        int rowCheckCount=0;
        int mainDiagCount = 0;
        int reverseDiagCount = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == checkVal) {
                    rowCheckCount++;
                 }
                if (map[j][i] == checkVal) {
                    colCheckCount ++;
                }
            }
            if (rowCheckCount == SIZE || colCheckCount == SIZE) {
                return true;
            }

            if (map[i][SIZE - 1 - i] == checkVal) {
                reverseDiagCount++;
            }
            if(map[i][i] == checkVal) mainDiagCount++;

            if (mainDiagCount == SIZE || reverseDiagCount == SIZE) {
                return true;
            }
            rowCheckCount = 0;
            colCheckCount = 0;
        }
        return rowCheckCount == SIZE || colCheckCount == SIZE || mainDiagCount == SIZE || reverseDiagCount == SIZE;
    }

    private static boolean checkEmptySlot(int mapRow, int mapCol){
        return map[mapRow - 1][mapCol - 1] == DOT_EMPTY;
    }

    private static int readIndex() {
        while (!scanner.hasNextInt()){
            System.out.println("");
            scanner.nextInt();
        }
        return scanner.nextInt();
    }

    private static boolean checkRange(int index) {
        return index >= 1 && index <= SIZE;
    }

    private static void initMap() {
        for (char[] field : map) {
            Arrays.fill(field, DOT_EMPTY);
        }
    }

    private static void printMap() {
        printMapHeader();
        printMapBody();
    }

    private static void printMapBody() {
        for (int i = 0; i < map.length; i++) {
            System.out.print(i + 1 + " ");

            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void printMapHeader() {
        for (int i = 0; i <= SIZE; i++) {
             System.out.print(i + " ");
         }
        System.out.println();
    }

}
