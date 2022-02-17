package swaglabs.screenplay.cart;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.ui.Button;
import net.serenitybdd.screenplay.ui.PageElement;

public class ChooseItem {

    private final String itemName;

    public ChooseItem(String name) {
        itemName = name;
    }

    public static ChooseItem called(String name) {
        return new ChooseItem(name);
    }

    static Target addToCartButtonFor(String productName) {
        return Button.withText("Add to cart")
                     .inside(PageElement.withCSSClass("inventory_item")
                                        .containingText(productName));
    }

    static Target removeFromCartButtonFor(String productName) {
        return Button.withText("Remove")
                .inside(PageElement.withCSSClass("inventory_item")
                                   .containingText(productName));
    }

    public Performable andAddItToTheShoppingCart() {
        return Click.on(addToCartButtonFor(itemName));
    }

    public Performable andRemoveItFromTheShoppingCart() {
        return Click.on(removeFromCartButtonFor(itemName));
    }
}
