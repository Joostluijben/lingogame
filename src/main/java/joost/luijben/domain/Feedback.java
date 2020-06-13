package joost.luijben.domain;

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
}
