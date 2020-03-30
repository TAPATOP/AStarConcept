package pancakes;

import usecases.pancakes.PancakeAbstract;
import usecases.pancakes.PancakeImpl;

public class TestCases {
   public static final int[] goalData = new int[]{1, 2, 3, 4, 5, 6};
   public static final PancakeAbstract goal = new PancakeImpl(goalData);

   public static final PancakeAbstract oneFlipMirrored = new PancakeImpl(new int[]{6, 5, 4, 3, 2, 1});
   public static final PancakeAbstract oneFlip1 = new PancakeImpl(new int[]{2, 1, 3, 4, 5, 6});
   public static final PancakeAbstract oneFlip2 = new PancakeImpl(new int[]{5, 4, 3, 2, 1, 6});

   public static final PancakeAbstract twoFlips1 = new PancakeImpl(new int[]{5, 4, 3, 1, 2, 6});
   public static final PancakeAbstract twoFlips2 = new PancakeImpl(new int[]{4, 5, 6, 3, 2, 1});

   public static final PancakeAbstract maximumFlips = new PancakeImpl(new int[]{4, 6, 2, 5, 1, 3});
}
