package org.nem.peer.trust.score;

import org.nem.peer.trust.score.CredibilityScore;
import org.nem.peer.trust.score.Score;
import org.nem.peer.trust.score.Scores;

public class CredibilityScores
extends Scores<CredibilityScore> {
    protected CredibilityScore ey() {
        return new CredibilityScore();
    }

    @Override
    protected /* synthetic */ Score ez() {
        return this.ey();
    }
}