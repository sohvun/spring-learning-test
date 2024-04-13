package cholog.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringTest {
    @Test
    @DisplayName("String 클래스의 replace 메서드를 익히기 위한 테스트")
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    // 요구사항 1-1
    @Test
    @DisplayName("문자열을 ','로 분리하여 배열로 반환하는 테스트")
    void splitString() {
        String input = "1,2";
        String[] result = input.split(",");
        assertThat(result).contains("1", "2");
    }

    // 요구사항 1-2
    @Test
    @DisplayName("\"1\"을 ','로 분리하여 배열로 반환하는 테스트")
    void splitStringWithSingleElement() {
        String input = "1";
        String[] result = input.split(",");
        assertThat(result).containsExactly("1");
    }

    // 요구사항 2
    @Test
    @DisplayName("문자열에서 괄호를 제거하는 테스트")
    void removeParentheses() {
        String input = "(1,2)";
        String result = input.substring(1, input.length() - 1);
        assertThat(result).isEqualTo("1,2");
    }

    // 요구사항 3-1
    @Test
    @DisplayName("문자열에서 특정 인덱스의 문자를 가져오는 테스트")
    void getCharacterAtIndex() {
        String input = "abc";
        char result = input.charAt(1);
        assertThat(result).isEqualTo('b');
    }

    // 요구사항 3-2
    @Test
    @DisplayName("문자열에서 유효하지 않은 인덱스의 문자를 가져오는 테스트")
    void getCharacterAtInvalidIndex() {
        String input = "abc";
        assertThatThrownBy(() -> {
            char result = input.charAt(3); // 유효하지 않은 인덱스인 3에 대해 문자를 가져오려고 시도
        }).isInstanceOf(StringIndexOutOfBoundsException.class)
                .hasMessageContaining("Index: 3, Size: 3");
    }

    // 요구사항 3-3 : 모든 테스트에 대해 @DisplayName 추가
}

