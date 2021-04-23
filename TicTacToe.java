package com.tts;

import java.util.*;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> computerPositions = new ArrayList<Integer>();

    public static void main(String[] args) {

        char[][] gameBoard = { {' ', '|', ' ', '|', ' '}, //index row 0, each element is column
                {'*', '*', '*', '*', '*'},
                {' ', '|', ' ', '|', ' '},
                {'*', '*', '*', '*', '*'},
                {' ', '|', ' ', '|', ' '}};

        printGameBoard(gameBoard);

        //looping game, so that computer is always listening and ready to go
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("enter your placement (1 -9):");
            int playerPosition = scan.nextInt();
            while (playerPositions.contains(playerPosition) || computerPositions.contains(playerPositions)){
                System.out.println("Position Taken! Enter a correct Positions");
                playerPosition=scan.nextInt();
            }

            placePiece(gameBoard, playerPosition, "player");
            String result= checkWinner();
                if (result.length()>0){
                    System.out.println(result);
                    break;
                }

            Random rand = new Random();
            int computerPosition = rand.nextInt(9) + 1;
            while (playerPositions.contains(computerPosition) || computerPositions.contains(computerPosition)){
                computerPosition = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, computerPosition, "computer");

            printGameBoard(gameBoard);

             result= checkWinner();
            if (result.length()>0){
                System.out.println(result);
                break;
            }

        }

    }

    public static void printGameBoard(char [][] gameBoard) {
        for (char [] row : gameBoard) {
            for (char c: row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece (char [] [] gameBoard, int position, String user) {

        char symbol= 'X';
        if(user.equals(" player")){
            symbol = 'X';
            playerPositions.add(position);
        } else if (user.equals("computer")){
            symbol= 'O';
            computerPositions.add(position);
        }

        switch (position) {
            case 1:
//                row, col
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
//                second row
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
//                last row
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);
        //diagonal (top left to bottom right)
        List cross1 = Arrays.asList(1,5,9);
        //diagonal (bottom left to top right)
        List cross2 = Arrays.asList(7,5,3);

        List <List> winningConditions = new ArrayList<List>();
        winningConditions.add(topRow);
        winningConditions.add(midRow);
        winningConditions.add(botRow);
        winningConditions.add(leftCol);
        winningConditions.add(midCol);
        winningConditions.add(rightCol);
        winningConditions.add(cross1);
        winningConditions.add(cross2);

        for (List l : winningConditions) {
            if (playerPositions.containsAll(l)) {
                return "Congratulations you won!!";
            } else if (computerPositions.contains(l)) {
                return " Computer won, you lost :(";
            }else if (playerPositions.size() + computerPositions.size() == 9) {
                return "No winner this round";
            }
        }

        return "";
    }
}
