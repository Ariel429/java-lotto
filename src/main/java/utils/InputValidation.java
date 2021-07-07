package utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class InputValidation {
    private static final String LOTTO_PRICE_PATTERN = "\\d*000";
    private static final String COMMA = ",";
    private static final int BOUND_MIN = 0;
    private static final int BOUND_MAX = 46;
    private static final int LOTTO_LENGTH = 6;
    private static final String ALERT_CHECK_COMMA = String.format("구분자를 \"%s\"로 입력하셨는지 확인해주세요.", COMMA);
    private static final String ALERT_CHECK_NULL_OR_EMPTY = String.format("\"%s\"로 구분한 지난 주 당첨번호를 입력해주세요.", COMMA);
    private static final String ALERT_CHECK_BOUND = String.format("당첨번호는 %d - %d 사이값을 입력해주세요", BOUND_MIN, BOUND_MAX);
    private static final String ALERT_CHECK_LENGTH = String.format("당첨번호는 %d개 이어야 합니다.", LOTTO_LENGTH);
    private static final String ALERT_CHECK_DUPLICATION = "중복되는 숫자가 포함되어 있는지 확인해주세요.";

    public int checkGivenMoney(String givenMoney) {
        if (!Pattern.matches(LOTTO_PRICE_PATTERN, givenMoney)) {
            throw new RuntimeException("1000원 단위의 금액만 투입할 수 있습니다.");
        }
        return Integer.parseInt(givenMoney);
    }

    public static List<Integer> toIntegers(List<String> input) {
        return new ArrayList<>(Collections.unmodifiableList(input.stream()
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList())));
    }

    public static List<String> splitByComma(String input) {
        if (!input.contains(COMMA)) {
            throw new IllegalArgumentException(ALERT_CHECK_COMMA);
        }
        return Arrays.stream(input.split(COMMA))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    private void checkNullOrEmpty(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException(ALERT_CHECK_NULL_OR_EMPTY);
        }
    }

    public List<Integer> checkWinningNumber(String winningNumber) {
        checkNullOrEmpty(winningNumber);
        List<Integer> winningNumbers = toIntegers(splitByComma(winningNumber));
        validate(winningNumbers);
        return winningNumbers;
    }

    private void validate(List<Integer> winningNumbers) {
        checkListBound(winningNumbers);
        checkLength(winningNumbers);
        checkDuplicate(winningNumbers);
    }

    private void checkListBound(List<Integer> winningNumbers) {
        for (Integer winningNumber : winningNumbers) {
            checkBound(winningNumber);
        }
    }

    private void checkBound(int number) {
        if (BOUND_MIN < number && BOUND_MAX > number) {
            throw new IllegalArgumentException(ALERT_CHECK_BOUND);
        }
    }

    private void checkLength(List<Integer> winningNumbers) {
        if (winningNumbers.size() != LOTTO_LENGTH) {
            throw new IllegalArgumentException(ALERT_CHECK_LENGTH);

        }
    }

    private void checkDuplicate(List<Integer> winningNumbers) {
        int countOfDeDuplication = (int) winningNumbers.stream()
                .distinct()
                .count();

        if (countOfDeDuplication != winningNumbers.size()) {
            throw new IllegalArgumentException(ALERT_CHECK_DUPLICATION);
        }

    }
}
