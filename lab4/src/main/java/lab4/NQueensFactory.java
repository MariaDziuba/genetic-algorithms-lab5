package lab4;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.*;

public class NQueensFactory extends AbstractCandidateFactory<NQueensSolution> {

    int dimension;
    public NQueensFactory(int dimension) {
        this.dimension = dimension;
    }

    public NQueensSolution generateRandomCandidate(Random random) {
        ArrayList<Integer> candidate = new ArrayList<>();
        for (int i = 0; i < dimension; i++) {
            candidate.add(i);
        }
        Collections.shuffle(candidate);
        return new NQueensSolution(candidate);
    }
}

