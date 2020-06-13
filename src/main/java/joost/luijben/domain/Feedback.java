package joost.luijben.domain;

import java.util.Objects;

public class Feedback {
    private char letter;
    private Status status;

    public Feedback(char letter, Status status) {
        this.letter = letter;
        this.status = status;

    }

    public Feedback(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feedback feedback = (Feedback) o;
        return letter == feedback.letter &&
                status == feedback.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, status);
    }
}
