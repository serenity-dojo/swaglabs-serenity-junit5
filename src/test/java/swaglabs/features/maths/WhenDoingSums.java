package swaglabs.features.maths;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.thucydides.core.annotations.Steps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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

        @Test
        void shouldCalculateAdditions() {
            int result = calulate.theAnswerTo("1","+","2");
            assertThat(result).isEqualTo(3);
        }

        @Test
        void shouldCalculateSubtractions() {
            int result = calulate.theAnswerTo("7","-","3");
            assertThat(result).isEqualTo(4);
        }

        @Test
        void shouldCalculateMultiplications() {
            int result = calulate.theAnswerTo("3","*","2");
            assertThat(result).isEqualTo(6);
        }

        @Test
        void shouldCalculateDivisions() {
            int result = calulate.theAnswerTo("10","/","2");
            assertThat(result).isEqualTo(5);
        }
    }
}
