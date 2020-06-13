package joost.luijben.infrastructure.input.rest.wordinput;

import io.restassured.RestAssured;
import joost.luijben.domain.Word;
import joost.luijben.domain.WordInput;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class WordInputRest implements WordInput {
    @Override
    public Set<Word> load(Integer wordLength) {
        String requestUrl = System.getenv("LINGOWORDS_URL") + "/words";
        if (wordLength != null) {
            requestUrl += "?wordLength=" + wordLength;
        }
        List<Word> words = RestAssured.get(requestUrl)
                .then().extract().body().jsonPath().getList(".", Word.class);
        return new HashSet<>(words);
    }
}
