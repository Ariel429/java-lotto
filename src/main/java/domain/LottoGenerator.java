package domain;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoGenerator {
    private static List<Integer> balls;

    public LottoGenerator() {
        balls = createBalls();
    }

    private static List<Integer> createBalls() {
        return IntStream.range(1, 46).boxed().collect(Collectors.toList());
    }

    public Lotto generateLotto() {
        Collections.shuffle(balls);
        List<Integer> lottoNumbers = balls.subList(0, 6);
        Collections.sort(lottoNumbers);
        return new Lotto(lottoNumbers);
    }

}