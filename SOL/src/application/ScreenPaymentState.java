package application;
import javafx.scene.control.Toggle;

public class ScreenPaymentState {
    private static Toggle tgTypePayment;

    public static Toggle getTgTypePayment() {
        return tgTypePayment;
    }

    public static void setTgTypePayment(Toggle tgTypePayment) {
        ScreenPaymentState.tgTypePayment = tgTypePayment;
    }
}
