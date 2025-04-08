package GUI;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class Ribbon extends AnchorPane {
    ImageView body;
    ImageView material;
    int amount = 100;

    Text text = new Text();

    public Ribbon(Image body, Image material, int amount) {
        this.body = new ImageView(body);
        this.material = new ImageView(material);
        this.amount = amount;
        text.setText(String.valueOf(amount));
        getChildren().addAll(this.body, this.material, text);
    }

    public int getAmount() {
        return amount;
    }

    public void update (int amountChange){ //updates the amount of gathered recources (bigger/smaller)
        amount += amountChange;
        text.setText(String.valueOf(amount));
    }

}
