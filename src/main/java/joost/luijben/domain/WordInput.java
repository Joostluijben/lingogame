package joost.luijben.domain;

import java.util.Set;

public interface WordInput {
    Set<Word> load(Integer wordLength);
}
