package joost.luijben.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Turn {
    private final Instant time;
    private List<Feedback> feedbacks;

    public Turn() {
        time = Instant.now();
        feedbacks = new ArrayList<>();
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Instant getTime() {
        return time;
    }
}
