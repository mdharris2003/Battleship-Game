import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static Scanner scan = new Scanner(System.in);
    public static void displayGame(char[][] board){
        System.out.println();
        System.out.print(" ");
        for(int i=1;i<=10;i++){
            System.out.print(i+" ");
        }
        System.out.println();
        char A = 'A';
        for(int i=0;i<board.length;i++){
            System.out.print(A);
            for(int j=0;j<board.length;j++){
                System.out.print(" ");
                System.out.print(board[i][j]);
            }
            A++;
            System.out.println();
        }
    }
    public static boolean adjacentCheckSameChar(int startModify,int endModify,char[][] board,int row1){
        for(int i=startModify;i<=endModify;i++) {
            int rowm=0;
            int colm=0;
            for(int j=0;j<3;j++){
                if(j==0){
                    rowm = row1-1;
                }else if(j==1){
                    rowm = row1;
                }else{
                    rowm = row1+1;
                }
                for(int k=0;k<3;k++){
                    if(k==0){
                        colm = i-1;
                    }else if(k==1){
                        colm = i;
                    }else{
                        colm = i+1;
                    }
                    if(rowm>=0 && rowm<10 && colm>=0 && colm<10 ){
                        if(board[rowm][colm]=='O'){
                            return false;
                        }
                    }else{
                        continue;
                    }

                }
            }
        }
        return true;
    }
    public static boolean adjacentCheckSameLength(int row1,int row2,char[][] board,int line1){
        for(int i=row1;i<=row2;i++) {
            int rowm=0;
            int colm=0;
            for(int j=0;j<3;j++){
                if(j==0){
                    rowm = i-1;
                }else if(j==1){
                    rowm = i;
                }else{
                    rowm = i+1;
                }
                for(int k=0;k<3;k++){
                    if(k==0){
                        colm = line1-1;
                    }else if(k==1){
                        colm = line1;
                    }else{
                        colm = line1+1;
                    }
                    if(rowm>=0 && rowm<10 && colm>=0 && colm<10 ){
                        if(board[rowm][colm]=='O'){
                            return false;
                        }
                    }else{
                        continue;
                    }
                }
            }
        }
        return true;
    }


    public static char[][] gameLogic(char[][] board,String name,int cells){
        while (true) {
            System.out.println();
            String cord = scan.nextLine();
        /*
        System.out.println("Letter"+cord.charAt(0));
        System.out.println("Length"+cord.charAt(cord.length()-1));
        */
            String[] split = cord.split(" ");

            String start = split[0];
            String end = split[1];

            char c1 = start.charAt(0);
            char c2 = end.charAt(0);

            String n1 = start.substring(1);
            int l1 = Integer.parseInt(n1);
            String n2 = end.substring(1);
            int l2 = Integer.parseInt(n2);

            if (c1 == c2) {
                if (l1 > 0 && l2 < 11) {

                    if (l1 < l2) {
                        int length = l2 - l1 + 1;
//                        System.out.println("Length: " + length);
                        if(length != cells) {
                            System.out.println("Error! Wrong length of the " + name + "! Try again:");
                            continue;
                        }
//                        System.out.print("Parts: ");
//                        for (int i = l1; i <= l2; i++) {
//                            System.out.print(c1 + "" + i + " ");
//                        }
//                        int row1 = 0;
//                        for (char i = 'A'; i < c1; i++) {
//                            row1++;
//                        }

                        int row1 = c1 - 'A';
                        int startModify = l1 - 1;
                        int endModify = l2 - 1;

                        boolean isAdjacent = adjacentCheckSameChar(startModify, endModify, board, row1);
                        if (!isAdjacent) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            continue;
                        }
                        for (int i = startModify; i <= endModify; i++) {
                            board[row1][i] = 'O';
                        }
                        return board;
                    } else {
                        int length = l1 - l2 + 1;
//                        System.out.println("Length: " + length);
                        if(length != cells) {
                            System.out.println("Error! Wrong length of the " + name + "! Try again:");
                            continue;
                        }
//                        System.out.print("Parts: ");
//                        for (int i = l1; i >= l2; i--) {
//                            System.out.print(c1 + "" + i + " ");
//                        }
                        int row1 = c1 - 'A';


                        int startModify = l1 - 1;
                        int endModify = l2 - 1;

                        boolean isAdjacent = adjacentCheckSameChar(endModify, startModify, board, row1);
                        if (!isAdjacent) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            continue;
                        }
                        for (int i = startModify; i >= endModify; i--) {
                            board[row1][i] = 'O';
                        }
                        return board;
                    }
                } else {
                    System.out.println("Error! Wrong ship location! Try again:");
                    continue;
                }
            } else if (l1 == l2) {
                if (c1 >= 'A' && c1 <= 'J'
                        && c2 >= 'A' && c2 <= 'J') {
                    if (c1 < c2) {
                        int length = c2 - c1 + 1;
//                        System.out.println("Length: " + length);
                        if(length != cells) {
                            System.out.println("Error! Wrong length of the " + name + "! Try again:");
                            continue;
                        }
//                        System.out.print("Parts: ");
//                        char k1 = c1;
//                        for (char i = c1; i <= c2; i++) {
//                            System.out.print(k1 + "" + l1 + " ");
//                            k1++;
//                        }
                        int line1 = l1 - 1;

                        int row1 = c1 - 'A';
                        int row2 = c2 - 'A';

                        boolean isAdjacent = adjacentCheckSameLength(row1, row2, board, line1);

                        if (!isAdjacent) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            continue;
                        }

                        for (int i = row1; i <= row2; i++) {
                            board[i][line1] = 'O';
                        }
                        return board;
                    } else {
                        int length = c1 - c2 + 1;
//                        System.out.println("Length: " + length);
                        if(length != cells) {
                            System.out.println("Error! Wrong length of the " + name + "! Try again:");
                            continue;
                        }
//                        System.out.print("Parts: ");
//                        char k1 = c1;
//                        for (int i = c1; i >= c2; i--) {
//                            System.out.print(k1 + "" + l1 + " ");
//                            k1--;
//                        }
                        int line1 = l1 - 1;
                        int row1 = c1 - 'A';
                        int row2 = c2 - 'A';

                        boolean isAdjacent = adjacentCheckSameLength(row2, row1, board, line1);

                        if (!isAdjacent) {
                            System.out.println("Error! You placed it too close to another one. Try again:");
                            continue;
                        }


                        for (int i = row2; i <= row1; i++) {
                            board[i][line1] = 'O';
                        }
                        return board;
                    }
                } else {
                    System.out.println("Error! Wrong ship location! Try again:");
                    continue;
                }
            } else {
                System.out.println("Error! Wrong ship location! Try again:");
                continue;
            }
        }
    }

    public static char[][] shipPlacement(char[][] board){
        System.out.println("Enter the coordinates of the Aircraft Carrier (5 cells):");
        gameLogic(board,"Aircraft Carrier",5);
        displayGame(board);
        System.out.println("Enter the coordinates of the Battleship (4 cells):");
        gameLogic(board,"Battleship ",4);
        displayGame(board);
        System.out.println("Enter the coordinates of the Submarine (3 cells):");
        gameLogic(board,"Submarine",3);
        displayGame(board);
        System.out.println("Enter the coordinates of the Cruiser (3 cells):");
        gameLogic(board,"Cruiser ",3);
        displayGame(board);
        System.out.println("Enter the coordinates of the Destroyer (2 cells):");
        gameLogic(board,"Destroyer",2);
        displayGame(board);
        return board;
    }
    public static boolean shipStillExists(char[][] board, int row, int col) {
        // check up
        for(int i=row-1; i>=0; i--) {
            if(board[i][col] == '~' || board[i][col] == 'M') break;
            if(board[i][col] == 'O') return true;
        }

        // check down
        for(int i=row+1; i<10; i++) {
            if(board[i][col] == '~' || board[i][col] == 'M') break;
            if(board[i][col] == 'O') return true;
        }

        // check left
        for(int j=col-1; j>=0; j--) {
            if(board[row][j] == '~' || board[row][j] == 'M') break;
            if(board[row][j] == 'O') return true;
        }

        // check right
        for(int j=col+1; j<10; j++) {
            if(board[row][j] == '~' || board[row][j] == 'M') break;
            if(board[row][j] == 'O') return true;
        }

        return false;
    }
    public static char[][] gameShoot(char[][] board,char[][] fogBoard){
     int keepShoot = 1;
        while(keepShoot<=100) {
            try {
                String shootRange = scan.nextLine();

                char c1 = (char) shootRange.charAt(0);
                String n1 = shootRange.substring(1);
                int l1 = Integer.parseInt(n1);
                l1 = l1 - 1;
                int row = c1 - 'A';
                if (board[row][l1] == 'O') {
                    board[row][l1] = 'X';
                    fogBoard[row][l1] = 'X';
                    displayGame(fogBoard);
                    if(shipStillExists(board,row,l1)) {
                        System.out.println("You hit a ship!");
                    } else {

                        boolean isThereShip = false;

                        for(int i=0;i<10;i++){
                            for(int j=0;j<10;j++){
                                if(board[i][j]=='O'){
                                    isThereShip = true;
                                    break;
                                }
                            }
                        }

                        if(isThereShip){
                            System.out.println("You sank a ship! Specify a new target:");
                        } else {
                            System.out.println("You sank the last ship. You won. Congratulations!");
                            break;
                        }
                    }
                }else if(board[row][l1] == 'M' || board[row][l1] == 'X'){
                    displayGame(fogBoard);
                    System.out.println("You already hit that spot");
                }
                else {
                    fogBoard[row][l1] = 'M';
                    displayGame(fogBoard);
                    System.out.println("You missed!");
                    board[row][l1] = 'M';
                }
                keepShoot++;
            } catch (Exception e) {
                System.out.println("Error! You entered the wrong coordinates! Try again:");
                continue;
            }
        }
        return board;
    }

    public static char[][] fogDisplay(){
        char[][] fogBoard = new char[10][10];

        for(int i=0;i<10;i++){
            Arrays.fill(fogBoard[i], '~');
        }
        return fogBoard;
    }

    public static void main(String[] args) {
        char[][] board = new char[10][10];
        for(int i=0;i<10;i++){
            Arrays.fill(board[i], '~');
        }
        displayGame(board);
        shipPlacement(board);
        char[][] fogBoard = fogDisplay();
        System.out.println("The game starts!");
        displayGame(fogBoard);
        System.out.println();
        System.out.println("Take a shot!");
        gameShoot(board,fogBoard);
    }
}
