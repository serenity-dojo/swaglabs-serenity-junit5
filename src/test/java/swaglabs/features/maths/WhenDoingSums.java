package swaglabs.features.maths;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.annotations.Steps;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
class WhenDoingSums {

    @Steps
    CalculatorSteps calulate;

    StopWatch stopWatch;
    @BeforeEach
    void openTheCalculator() {
        stopWatch = new StopWatch();
        calulate.openTheCalculatorApp();
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1,+,2,3",
            "7,-,3,4",
            "3,*,2,6",
            "10,/,2,5",
    })
    void shouldCalculateResults(String a, String operator, String b, String expectedResult) {
        String actualResult = calulate.theAnswerTo(a, operator, b);
        assertThat("" + actualResult).isEqualTo(expectedResult);
    }

    @Test
    void shouldReportDivisionByZero() {
        String result = calulate.theAnswerTo("10", "/", "0");
        assertThat(result).isEqualTo("Infinity");
    }
    @ParameterizedTest
    @ArgumentsSource(WhenDoingSums.MyArgumentsProvider.class)
    void shouldCalculateResultsWithArgSource(String calculation) {
        String[] parts = calculation.split(",");
        String actualResult = calulate.theAnswerTo(parts[0], parts[1], parts[2]);
        assertThat("" + actualResult).isEqualTo(parts[3]);
    }

    public static class MyArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    "1,+,2,3",
                    "7,-,3,4",
                    "3,*,2,6",
                    "10,/,2,5")
                    .map(Arguments::of);
        }
    }


    @ParameterizedTest
    @ArgumentsSource(MultiArgumentsProvider.class)
    void shouldCalculateResultsWithMultipleArgSource(String a, String op, String b, String res) {
        String actualResult = calulate.theAnswerTo(a, op, b);
        assertThat("" + actualResult).isEqualTo(res);
    }

    public static class MultiArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                            new Object[]{"1","+","2","3"},
                            new Object[]{"7","-","3","4"}
                    )
                    .map(Arguments::of);
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ExtensionContextArgumentsProvider.class)
    void shouldCalculateResultsForArgumentSourceThatNeedsTheExtensionContext(String a, String op, String b, String res) {
        System.out.println(a + " " + op + " " + b + " = " + res);
    }
    public static class ExtensionContextArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            context.getDisplayName();
            return Stream.of(
                            new Object[]{"1","+","2","3"},
                            new Object[]{"7","-","3","4"}
                    )
                    .map(Arguments::of);
        }
    }

}
