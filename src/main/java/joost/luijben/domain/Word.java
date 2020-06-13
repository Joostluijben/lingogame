package joost.luijben.domain;

import java.util.Objects;

public class Word {
    private String value;

    public Word() {
    }

    public Word(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word1 = (Word) o;
        return Objects.equals(value, word1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
