package lab4;

import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NQueensMutation implements EvolutionaryOperator<NQueensSolution> {

    double insertProb;
    double swapProb;
    double inversionProb;
    double scrambleProb;

    public NQueensMutation(double insertProb, double swapProb, double inversionProb, double scrambleProb) {
        this.insertProb = insertProb;
        this.swapProb = swapProb;
        this.inversionProb = inversionProb;
        this.scrambleProb = scrambleProb;
    }


    public NQueensSolution doInsertMutation(Random random, int length, ArrayList<Integer> rowNumbers) {
        int a = random.nextInt(length);
        int b = random.nextInt(length);

        int i = Math.min(a, b);
        int j = Math.max(a, b);
        rowNumbers.add(j, rowNumbers.get(i));
        rowNumbers.remove(i);
        return new NQueensSolution(rowNumbers);
    }

    public NQueensSolution doSwapMutation(Random random, int length, ArrayList<Integer> rowNumbers) {
        int i = random.nextInt(length);
        int j = random.nextInt(length);
        int i_val = rowNumbers.get(i);
        rowNumbers.set(i, rowNumbers.get(j));
        rowNumbers.set(j, i_val);
        return new NQueensSolution(rowNumbers);
    }

    public NQueensSolution doInversionMutation(Random random, int length, ArrayList<Integer> rowNumbers) {
        int a = random.nextInt(length);
        int b = random.nextInt(length);

        int start = Math.min(a, b);
        int end = Math.max(a, b);

        int sub_array_length = end - start + 1;
        int[] inverted_sub_array = new int[sub_array_length];

        for (int i = 0; i < sub_array_length; i++) {
            inverted_sub_array[sub_array_length - 1 - i] = rowNumbers.get(start + i);
        }

        for (int i = start; i < end + 1; i++) {
            rowNumbers.set(i, inverted_sub_array[i - start]);
        }
        return new NQueensSolution(rowNumbers);
    }

    public NQueensSolution doScrambleMutation(Random random, int length, ArrayList<Integer> rowNumbers) {
        int a = random.nextInt(length);
        int b = random.nextInt(length);

        int start = Math.min(a, b);
        int end = Math.max(a, b);

        ArrayList<Integer> sub_array = new ArrayList<>();
        for (int i = start; i < end + 1; i++) {
            sub_array.add(rowNumbers.get(i));
        }

        Collections.shuffle(sub_array);

        for (int i = start; i < end + 1; i++) {
            rowNumbers.set(i, sub_array.get(i - start));
        }
        return new NQueensSolution(rowNumbers);
    }

    public List<NQueensSolution> apply(List<NQueensSolution> population, Random random) {
        int length = population.get(0).getRowNumbers().size();

        ArrayList<NQueensSolution> new_population = new ArrayList<>(population.size());

        for (NQueensSolution solution : population) {
            NQueensSolution mutatedSolution;
            if (random.nextDouble() < insertProb) {
                mutatedSolution = doInsertMutation(random, length, solution.getRowNumbers());
                new_population.add(mutatedSolution);
            } else if (random.nextDouble() < swapProb) {
                mutatedSolution = doSwapMutation(random, length, solution.getRowNumbers());
                new_population.add(mutatedSolution);
            } else if (random.nextDouble() < inversionProb) {
                mutatedSolution = doInversionMutation(random, length, solution.getRowNumbers());
                new_population.add(mutatedSolution);
            } else if (random.nextDouble() < scrambleProb) {
                mutatedSolution = doScrambleMutation(random, length, solution.getRowNumbers());
                new_population.add(mutatedSolution);
            } else {
                new_population.add(solution);
            }
        }
        return new_population;
    }
}
