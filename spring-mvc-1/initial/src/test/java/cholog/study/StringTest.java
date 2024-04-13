package cholog.study;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {
    @Test
    void replace() { // String 클래스의 replace 메서드를 익히기 위한 테스트
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    // 요구사항 1-1
    @Test
    void splitString() { // 문자열을 ','로 분리하여 배열로 반환하는 테스트
        String input = "1,2";
        String[] result = input.split(",");
        assertThat(result).contains("1", "2");
    }

    // 요구사항 1-2
    @Test
    void splitStringWithSingleElement() { // "1"을 ','로 분리하여 배열로 반환하는 테스트
        String input = "1";
        String[] result = input.split(",");
        assertThat(result).containsExactly("1");
    }
}

