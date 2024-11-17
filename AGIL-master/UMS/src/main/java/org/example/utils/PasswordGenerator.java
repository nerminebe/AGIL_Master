package org.example.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PasswordGenerator {

    private static final Random random = new SecureRandom();

    private static Stream<Character> getRandomNumbers(int count) {
        return IntStream.range(0, count)
                .map(i -> '0' + random.nextInt(10))
                .mapToObj(num -> (char) num);
    }

    private static Stream<Character> getRandomSpecialChars(int count) {
        IntStream combinedIntStream = IntStream.concat(
                IntStream.concat(
                        IntStream.concat(random.ints(count / 4, 33, 47 + 1),
                                random.ints(count / 4, 58, 64 + 1)),
                        random.ints(count / 4, 91, 96 + 1)),
                random.ints(count - 3 * (count / 4), 123, 126 + 1) // Adjust the last range according to how the count divides
        );
        return combinedIntStream
                .mapToObj(c -> (char) c);
    }

    private static Stream<Character> getRandomAlphabets(int count, boolean upperCase) {
        int offset = upperCase ? 'A' : 'a';
        return random.ints(count, offset, offset + 26)
                .mapToObj(c -> (char) c);
    }

    public static String generateSecureRandomPassword() {
        Stream<Character> pwdStream = Stream.concat(getRandomNumbers(2),
                Stream.concat(getRandomSpecialChars(2),
                        Stream.concat(getRandomAlphabets(2, true), getRandomAlphabets(4, false))));

        List<Character> charList = pwdStream.collect(Collectors.toList());
        Collections.shuffle(charList);

        return charList.stream()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

}
