package lab4;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.ArrayList;
import java.util.List;

public class NQueensFitnessFunction implements FitnessEvaluator<NQueensSolution> {

    public double getFitness(NQueensSolution solution, List<? extends NQueensSolution> list) {

        double fitness = 0;

        int dimension = solution.getRowNumbers().size();
        ArrayList<Integer> rowIndexes = solution.getRowNumbers();
        for (int i = 0; i < dimension; i++) {
            int i_row_idx = rowIndexes.get(i);
            int x1 = i - i_row_idx;
            int y1 = i + i_row_idx;

            for (int j = 0; j < dimension; j++) {
                if (i != j) {
                    int j_row_idx = rowIndexes.get(j);
                    int x2 = j - j_row_idx;
                    int y2 = j + j_row_idx;

                    if (x1 == x2 || y1 == y2) {
                        fitness += 1;
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        return fitness;
    }

    public boolean isNatural() {
        return false;
    }
}
