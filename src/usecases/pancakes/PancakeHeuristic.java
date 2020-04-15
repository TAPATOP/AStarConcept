package usecases.pancakes;

import concept.heuristic.Heuristic;

public class PancakeHeuristic implements Heuristic<PancakeAbstract> {
    @Override
    public int heuristic(PancakeAbstract current, PancakeAbstract goal) {
        if (current.equals(goal)) {
            return 0;
        }

        int heuristic = 1; // since it isn't solved(the if above), heuristic should be > 0
        int heuristic2 = 0;
        int heuristic3 = 0;
        int pileSize = current.size();

        for(int i = 0; i < pileSize - 1; i++) {
            if (Math.abs(current.pancakeAt(i) - current.pancakeAt(i + 1)) != 1) {
                if ( (i < pileSize - 2) && Math.abs(current.pancakeAt(i) - current.pancakeAt(i + 2)) != 2) {
                    heuristic2++;
                }
                heuristic++;
            }
            if (i != current.findProperPlace(current.pancakeAt(i))) {
                heuristic3++;
            }
        }
        return heuristic + heuristic2;// + heuristic3;
    }
}
