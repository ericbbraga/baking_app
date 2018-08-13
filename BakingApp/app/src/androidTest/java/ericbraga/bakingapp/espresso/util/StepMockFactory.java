package ericbraga.bakingapp.espresso.util;

import java.util.ArrayList;
import java.util.List;

import ericbraga.bakingapp.model.Step;

public class StepMockFactory {
    private static final int TOTAL_STEPS = 3;

    private static final String VIDEO_URL =
        "https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4";

    public static List<Step> createMockSteps() {
        List<Step> steps = new ArrayList<>();
        for (int i = 0; i < TOTAL_STEPS; i++) {
            steps.add(
                new Step(i,
                    String.format("Short Description %d", (i + 1)),
                    String.format("Description %d", (i + 1)),
                    VIDEO_URL,
                    ""
                )
            );
        }
        return steps;
    }

}
