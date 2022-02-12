
import java.io.InputStream;
import java.io.Serial;
import java.io.Serializable;

public class El extends InputStream implements Serializable {
    private Pizza pizza;
    private int ammount;
    private float price;

    @Serial
    private static final long serialVersionUID = 1L;

    public El(Pizza pizza, int ammount, float price) {
        this.pizza = pizza;
        this.ammount = ammount;
        this.price = price;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return   pizza +
                ", ilość: " + ammount +
                ", cena: " + price;
    }

    @Override
    public int read() {
        return 0;
    }
}
