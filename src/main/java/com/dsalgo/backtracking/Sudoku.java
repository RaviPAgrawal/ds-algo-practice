package com.dsalgo.backtracking;

public class Sudoku {
    public static final int BOARD_SIZE = 9;
    public static final int MIN_NUM = 1;
    public static final int MAX_NUM = 9;
    public static final int BOX_SIZE = 3;

    private int[][] sudokuTable;

    public Sudoku(int[][] sudokuTable) {
        this.sudokuTable = sudokuTable;
    }

    public void solve() {
        if (solveSudoku(0, 0)) {
            printResult();
        } else {
            System.out.println("No solution");
        }
    }

    private void printResult() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            if (i % 3 == 0) {
                System.out.print(" ");
            }
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (j % 3 == 0) {
                    System.out.print(" ");
                }
                System.out.print(sudokuTable[i][j] + " ");
            }
            System.out.println(" ");
        }

    }

    //We will solve the sudoku columnwise. Traverse first column, then second, then third and so on..
    private boolean solveSudoku(int rowIndex, int columnIndex) {
        if (rowIndex == BOARD_SIZE && ++columnIndex == BOARD_SIZE) {
            return true;
        }

        //if current column end is reached, then we need to go to the next column
        if (rowIndex == BOARD_SIZE) {
            rowIndex = 0;
            //columnIndex+=1; //uncomment this and make ++columnIndex to columnIndex+1 in above if() clause. This also works
        }

        //handle already filled values in given sudoku
        if (sudokuTable[rowIndex][columnIndex] != 0) {
            return solveSudoku(rowIndex + 1, columnIndex);
        }

        //else
        for (int number = MIN_NUM; number <= MAX_NUM; number++) {
            if (valid(rowIndex, columnIndex, number)) {
                sudokuTable[rowIndex][columnIndex] = number;

                if (solveSudoku(rowIndex+1, columnIndex)) {
                    return true;
                }

                //Backtrack !!!
                sudokuTable[rowIndex][columnIndex] = 0;
            }
        }

        return false;
    }

    //We will solve the sudoku columnwise. Traverse first column, then second, then third and so on..
    private boolean solveSudoku2(int rowIndex, int columnIndex) {
        if (rowIndex == BOARD_SIZE && columnIndex+1 == BOARD_SIZE) {
            return true;
        }

        //if current column end is reached, then we need to go to the next column
        if (rowIndex == BOARD_SIZE) {
            rowIndex = 0;
            columnIndex+=1;
        }

        //handle already filled values in given sudoku
        if (sudokuTable[rowIndex][columnIndex] != 0) {
            return solveSudoku(rowIndex + 1, columnIndex);
        }

        //else
        for (int number = MIN_NUM; number <= MAX_NUM; number++) {
            if (valid(rowIndex, columnIndex, number)) {
                sudokuTable[rowIndex][columnIndex] = number;

                if (solveSudoku(rowIndex+1, columnIndex)) {
                    return true;
                }

                //Backtrack !!!
                sudokuTable[rowIndex][columnIndex] = 0;
            }
        }

        return false;
    }

    //We will solve the sudoku columnwise. Traverse first column, then second, then third and so on..
    private boolean solveSudoku3(int rowIndex, int columnIndex) {
        if (rowIndex == BOARD_SIZE && columnIndex+1 == BOARD_SIZE) {
            return true;
        }

        //if current column end is reached, then we need to go to the next column
        if (rowIndex == BOARD_SIZE) {
            return solveSudoku(0, columnIndex+1);
        }

        //handle already filled values in given sudoku
        if (sudokuTable[rowIndex][columnIndex] != 0) {
            return solveSudoku(rowIndex + 1, columnIndex);
        }

        //else
        for (int number = MIN_NUM; number <= MAX_NUM; number++) {
            if (valid(rowIndex, columnIndex, number)) {
                sudokuTable[rowIndex][columnIndex] = number;

                if (solveSudoku(rowIndex+1, columnIndex)) {
                    return true;
                }

                //Backtrack !!!
                sudokuTable[rowIndex][columnIndex] = 0;
            }
        }

        return false;
    }

    private boolean valid(int rowIndex, int columnIndex, int number) {
        //check if number present in row
        for (int i=0; i < BOARD_SIZE; i++) {
            if (sudokuTable[rowIndex][i] == number) {
                return false;
            }
        }

        //check if number present in column
        for (int i=0; i < BOARD_SIZE; i++) {
            if (sudokuTable[i][columnIndex] == number) {
                return false;
            }
        }

        //check if number present in current box
        int boxRowOffset = (rowIndex / 3) * BOX_SIZE;
        int boxColumnOffset = (columnIndex / 3) * BOX_SIZE;
        for (int i = 0; i < BOX_SIZE; i++) {
            for (int j = 0; j < BOX_SIZE; j++) {
                if (sudokuTable[boxRowOffset + i][boxColumnOffset + j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] sudokuTable = {
                {3,0,6,5,0,8,4,0,0},
                {5,2,0,0,0,0,0,0,0},
                {0,8,7,0,0,0,0,3,1},
                {0,0,3,0,1,0,0,8,0},
                {9,0,0,8,6,3,0,0,5},
                {0,5,0,0,9,0,6,0,0},
                {1,3,0,0,0,0,2,5,0},
                {0,0,0,0,0,0,0,7,4},
                {0,0,5,2,0,6,3,0,0}

        };
        Sudoku sudoku = new Sudoku(sudokuTable);
        sudoku.solve();
    }
}
