package csc207.phase2.UTFantasy.Activities.evolutionUI;

import android.graphics.drawable.AnimationDrawable;

public interface EvolutionActivityModel {
    void runAnimation(int preEvolve, int postEvolve);

    void showImg(int preEvolve);

    void showText(String text);

    void end();
}
