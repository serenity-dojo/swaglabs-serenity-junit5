package swaglabs.features.shopping_cart;

import net.serenitybdd.junit5.SerenityJUnit5Extension;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.annotations.CastMember;
import net.serenitybdd.screenplay.ensure.Ensure;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import swaglabs.screenplay.cart.ChooseItem;
import swaglabs.screenplay.login.LogOff;
import swaglabs.screenplay.login.Login;

import static swaglabs.screenplay.cart.ShoppingCartBar.SHOPPING_CART_BADGE;

@ExtendWith(SerenityJUnit5Extension.class)
@DisplayName("When managing the shopping cart")
@Tag("cart")
class WhenManagingTheShoppingCart {

    @CastMember
    Actor charles;

    @BeforeEach
    void openSite() {
        charles.attemptsTo(
                Login.asAStandardUser()
        );
    }

    @AfterEach
    void closeSite() {
        charles.attemptsTo(
                LogOff.fromTheSite()
        );
    }
    @DisplayName("When we are on the product catalog page")
    @Nested
    class FromWithinTheProductPage {

        @DisplayName("We can add an item to the cart directly from the product catalog")
        @Test
        void addASingleItem() {

            charles.attemptsTo(
                    ChooseItem.called("Sauce Labs Backpack").andAddItToTheShoppingCart(),
                    Ensure.that(SHOPPING_CART_BADGE).text().asAnInteger().isEqualTo(1)
            );
        }

        @DisplayName("We can add multiple items to the cart directly from the product catalog")
        @Test
        void addSeveralItems() {
            charles.attemptsTo(
                    ChooseItem.called("Sauce Labs Backpack").andAddItToTheShoppingCart(),
                    ChooseItem.called("Sauce Labs Bike Light").andAddItToTheShoppingCart(),
                    Ensure.that(SHOPPING_CART_BADGE).text().asAnInteger().isEqualTo(2)
            );
        }
        @Test
        @DisplayName("We can add and remove items to/from the cart directly from the product catalog")
        void addItem() {
            charles.attemptsTo(
                    ChooseItem.called("Sauce Labs Backpack").andAddItToTheShoppingCart(),
                    ChooseItem.called("Sauce Labs Bike Light").andAddItToTheShoppingCart(),
                    ChooseItem.called("Sauce Labs Backpack").andRemoveItFromTheShoppingCart(),

                    Ensure.that(SHOPPING_CART_BADGE).text().asAnInteger().isEqualTo(1)
            );
        }
    }
}
