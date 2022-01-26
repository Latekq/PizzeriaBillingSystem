import java.util.ArrayList;

public class Pizza {
    private int id;
    private String name;
    private ArrayList<String> ingredients;
    private float price;

    public Pizza(int id, String name, ArrayList<String> ingredients, float price) {
        setId(id);
        setPrice(price);
        setName(name);
        setIngredients(ingredients);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return name +
                " - skladniki to :" + getIngredients()  +
                ", cena : " + String.format("%.2f", getPrice());
    }



}
