package lab4;

import org.uncommons.watchmaker.framework.operators.AbstractCrossover;

import java.util.*;

public class NQueensCrossover extends AbstractCrossover<NQueensSolution> {
    protected NQueensCrossover() {
        super(1);
    }

    protected NQueensSolution doOrderedCrossover(ArrayList<Integer> p1, ArrayList<Integer> p2, Random random, int length) {
        int a = random.nextInt(length);
        int b = random.nextInt(length);
        int start = Math.max(a, b);
        int end = Math.max(a, b);
        Set<Integer> used = new HashSet<>();
        for (int i = start; i < end + 1; i++) {
            used.add(p1.get(i));
        }

        int i_idx = (end + 1) % length;
        ArrayList<Integer> child = new ArrayList<>(p1);

        for (int i = 0; i < length; i++) {
            int cur = p2.get((end + 1 + i) % length);
            if (!used.contains(cur)) {
                child.set(i_idx, cur);
                i_idx = (i_idx + 1) % length;
            }
        }
        used = null;

        return new NQueensSolution(child);
    }

    protected List<NQueensSolution> mate(NQueensSolution p1, NQueensSolution p2, int i, Random random) {
        ArrayList children = new ArrayList();

        ArrayList<Integer> p1_rowNumbers = p1.getRowNumbers();
        ArrayList<Integer> p2_rowNumbers = p2.getRowNumbers();
        int length = p1_rowNumbers.size();

        children.add(doOrderedCrossover(p1_rowNumbers, p2_rowNumbers, random, length));
        children.add(doOrderedCrossover(p1_rowNumbers, p2_rowNumbers, random, length));
        return children;
    }
}
