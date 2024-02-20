package lab4;

import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.RouletteWheelSelection;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

import java.util.*;

public class NQueensAlg {
    
    public static void main(String[] args) {
        int populationSize = 10; // size of population

        Random random = new Random(); // random

        int dimension = 4;

        CandidateFactory<NQueensSolution> factory = new NQueensFactory(dimension); // generation of solutions

        ArrayList<EvolutionaryOperator<NQueensSolution>> operators = new ArrayList<EvolutionaryOperator<NQueensSolution>>();
        operators.add(new NQueensCrossover()); // Crossover
        operators.add(new NQueensMutation(0.5, 0.3,0.15,0.15)); // Mutation
        EvolutionPipeline<NQueensSolution> pipeline = new EvolutionPipeline<NQueensSolution>(operators);

        SelectionStrategy<Object> selection = new RouletteWheelSelection(); // Selection operator

        FitnessEvaluator<NQueensSolution> evaluator = new NQueensFitnessFunction(); // Fitness function

        EvolutionEngine<NQueensSolution> algorithm = new SteadyStateEvolutionEngine<NQueensSolution>(
                factory, pipeline, evaluator, selection, populationSize, false, random);

        algorithm.addEvolutionObserver(new EvolutionObserver() {
            public void populationUpdate(PopulationData populationData) {
                double bestFit = populationData.getBestCandidateFitness();
                System.out.println("Generation " + populationData.getGenerationNumber() + ": " + bestFit);
                NQueensSolution best = (NQueensSolution)populationData.getBestCandidate();
                System.out.println("\tBest solution = " + best.toString());
            }
        });

        TerminationCondition terminate = new TargetFitness(0.0D, false);
        algorithm.evolve(populationSize, 1, terminate);
    }
}
