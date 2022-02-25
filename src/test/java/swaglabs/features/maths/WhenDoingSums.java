package swaglabs.features.maths;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.SingleBrowser;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SerenityJUnit5Extension.class)
class WhenDoingSums {

    @Steps
    CalculatorSteps calulate;

    @Nested
    class ABasicCalculator {

        @BeforeEach
        void openTheCalculator() {
            calulate.openTheCalculatorApp();
        }

        @ParameterizedTest
        @CsvSource({
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
            String result = calulate.theAnswerTo("10","/","0");
            assertThat(result).isEqualTo("Infinity");
        }
    }
}
