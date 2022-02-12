
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Database {

    private Connection conn;
    private Statement statement;
    public Connection openConnection() {
        if (conn == null) {
            String url = "jdbc:mysql://188.68.231.165/";
            String dbName = "pizzeria";
            String driver =  "com.mysql.cj.jdbc.Driver";
            String userName = "pizzeria";
            String password = "vauvfsWNrGcRWrH7";

            try {
                Class.forName(driver);
                this.conn =   DriverManager.getConnection(url+dbName,userName,password);
                System.out.println("CONNECTION SUCCEFUL");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    public Client[] clients() {
        String query = "SELECT * FROM `client`";
        Statement statement;
        ArrayList<Client> clients = new ArrayList<>();
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                String phoneNumber = resultSet.getString("phoneNumber");
                String adress = resultSet.getString("adress");
                String apartamentNumber = resultSet.getString("apartamentNumber");
                clients.add(new Client(id, name,lastName,phoneNumber,adress,apartamentNumber));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return clients.toArray(new Client[clients.size()]);
    }



    public Pizza[] typesOfPizza() {
        String query = "SELECT * FROM `pizze`";
        Statement statement;
        Pizza[] result = new Pizza[numberOfTypesOfPizzas()];
        int i = 0;
        try {
            statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next()) {
                int id = resultset.getInt("id");
                String nazwa = resultset.getString("nazwa");
                String skladniki = resultset.getString("skladniki");
                ArrayList<String> skl = new ArrayList<>();
                String temp = "";
                for (int j = 0; j < skladniki.length(); j++) {
                    if (skladniki.charAt(j) != ',') {
                        temp += skladniki.charAt(j);
                    } else {
                        skl.add(temp);
                        temp ="";
                        j++;
                    }
                }
                float cena = resultset.getFloat("cena");
                result[i] = new Pizza(id,nazwa,skl,cena);
                i++;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;

    }

    public void addPizza(String name, String skladniki, float  cena) {
        if(name.equals("") && skladniki.equals("") && cena <= 0.00) {
            System.err.println("zla wartosc");
            return;
        }
        try {
            statement = conn.createStatement();
            String query = "INSERT INTO `pizze`(`id`, `nazwa`, `skladniki`, `cena`) VALUES (NULL,'"+ name +"', '" + skladniki + "','"+ cena +"')";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateClient(Client c){
        try {
            statement = conn.createStatement();
            String query = "UPDATE `client` SET `adress`='" + c.getAdress() +"',`apartamentNumber`='" + c.getApartamentNumber() + "' WHERE `phoneNumber`='"  + c.getPhoneNumber() + "'";
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public int numberOfTypesOfPizzas() {
        String query = "SELECT * FROM `pizze`";
        Statement statement;
        int result = 0;
        try {
            statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next()) {
                result++;

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }



    public void addClient(Client c) {
        try {
            statement = conn.createStatement();
            if (searchByPhoneNumber(c.getPhoneNumber()) == null) {
                String query = "INSERT INTO `client` (`id`, `name`, `lastName`, `phoneNumber`, `adress`, `apartamentNumber`) VALUES (NULL, '" + c.getName()  + "', '" + c.getLastName()  + "', '" + c.getPhoneNumber()  + "', '" + c.getAdress()  +  "', '" + c.getApartamentNumber()  + "')" ;
                System.out.println("Add Client");
                statement.executeUpdate(query);
                statement.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public Client searchByPhoneNumber(String phoneNumber) {
        String query = "SELECT * FROM `client` WHERE `phoneNumber` LIKE '" + phoneNumber +"'";
        //System.out.println(query);
        Statement statement;
        Client client = null;
        try {
            statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next()) {
                //System.out.println("found");
                int id = resultset.getInt("id");
                String name = resultset.getString("name");
                String lastName = resultset.getString("lastName");
                String adress = resultset.getString("adress");
                String apartamentNumber = resultset.getString("apartamentNumber");
                client = new Client(id, name , lastName, phoneNumber, adress, apartamentNumber );
                //System.out.println(client);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //System.out.println(client);
        return client;
    }

    public int getIdByPhoneNumber(String phoneNumber) {
        String query = "SELECT * FROM `client` WHERE `phoneNumber` LIKE '" + phoneNumber +"'";
        //System.out.println(query);
        Statement statement;
        Client client = null;
        int id = 0;
        try {
            statement = conn.createStatement();
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next()) {
                //System.out.println("found");
                id = resultset.getInt("id");
                resultset.getString("name");
                resultset.getString("lastName");
                resultset.getString("adress");
                resultset.getString("apartamentNumber");
                //System.out.println(client);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //System.out.println(client);
        return id;
    }


    public void addOrder(Order o) {
        String[] pizzas = new String[o.getPosition().size()];
        for (int i = 0; i < pizzas.length; i++) {
            pizzas[i] = o.getPosition().get(i).getPizza().getId() + " " + o.getPosition().get(i).getAmmount();
        }

        long millis=System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        try {
            statement = conn.createStatement();
            String query = "INSERT INTO `orders`(`id`, `idCustomer`, `idPizzas`, `totalPrice`, `orderTime`, `timeOfOrder`, `realized`) VALUES (NULL,'" + o.getClient().getId() + "','" + Arrays.toString(pizzas) +  "','" + o.getTotalPrice() + "','" + date + "','" + formatter.format(date) + "','"+ 0 + "')" ;
            //System.out.println(query);
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
