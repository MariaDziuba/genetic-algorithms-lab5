package lab4;

import java.util.ArrayList;

public class NQueensSolution {

    ArrayList<Integer> rowNumbers = new ArrayList<>();

    public NQueensSolution() {

    }
    public NQueensSolution(ArrayList<Integer> rowNumbers) {
        this.rowNumbers = rowNumbers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int number: rowNumbers) {
            sb.append(number + 1).append(" ");
        }
        return sb.toString();
    }

    public ArrayList<Integer> getRowNumbers() {
        return rowNumbers;
    }
    // any required fields and methods
}
