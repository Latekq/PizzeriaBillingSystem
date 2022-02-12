
import java.io.IOException;
import java.io.InputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Order extends InputStream implements Serializable {
    private ArrayList<El> position = new ArrayList<>();
    private float totalPrice;
    private int orderType = -1;
    private Client client;

    @Serial
    private static final long serialVersionUID = 1L;

    public Order() {
    }


    public Order(ArrayList<El> position, float totalPrice, int orderType, Client client) {
        setPosition(position);
        setTotalPrice(totalPrice);
        setOrderType(orderType);
        setClient(client);
    }


    public void addPizza(Pizza pizza, int ammount) {
        El element = new El(pizza, ammount, pizza.getPrice()*ammount);
        System.out.println(element);
        position.add(element);
        setTotalPrice( getTotalPrice() + element.getPrice() );
    }

    public ArrayList<El> getPosition() {
        return position;
    }

    public void setPosition(ArrayList<El> position) {
        this.position = position;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public El[] toArray(){
        return position.toArray(El[]::new);
    }

    public String getOrderType() {
        return switch (orderType) {
            case 1 -> "Na miejscu.";
            case 2 -> "Z odbierem osobistym.";
            case 3 -> "Dostawa.";
            default -> "Brak wybranej opcji.";
        };
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Order{" +
                "position=" + position +
                ", totalPrice=" + totalPrice +
                ", orderType=" + getOrderType() +
                ", client=" + client +
                '}';
    }

    @Override
    public int read() {
        return 0;
    }
}
