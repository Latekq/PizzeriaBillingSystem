import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

public class ConnectionToServer {
    private final String hostname = "192.168.1.35";
    private final int port = 9900;
    private Client[] clients = null;
    private Pizza[] pizzas;


    public ConnectionToServer() {
    }


    public Client[] getClients() {
        try {
            Socket socket = new Socket(hostname, port);
            OutputStream output = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(output);

            InputStream input = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(input);
            String select;
            select = "2";
            oos.writeObject(select);
            clients = (Client[]) ois.readObject();
            System.out.println(Arrays.toString(clients));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return clients;
    }

    public Pizza[] getPizzas() throws IOException {
        try {

            Socket socket = new Socket(hostname, port);
            OutputStream output = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(output);

            InputStream input = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(input);

            String select;
            select = "1";
            oos.writeObject(select);
            pizzas =(Pizza[]) ois.readObject();
            output.close();
            ois.close();
            input.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pizzas;
    }

    public boolean sendOrder(Order order) {
        try {
            Socket socket = new Socket(hostname, port);
            OutputStream output = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(output);


            String select;
            select = "3";
            oos.writeObject(select);
            oos.writeObject(order);
            System.out.println(order);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Client searchByPhoneNumber(String number) {
        try {
            Socket socket = new Socket(hostname, port);
            OutputStream output = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(output);

            InputStream input = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(input);

            String select;
            select = "6";
            oos.writeObject(select);
            oos.writeObject(number);
            Client client = (Client) ois.readObject();
            System.out.println(client);
            return client;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int addClinet(Client client) {
        try {
            Socket socket = new Socket(hostname, port);
            OutputStream output = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(output);

            InputStream input = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(input);

            String select;
            select = "4";
            oos.writeObject(select);
            oos.writeObject(client);
            return (int) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public void updateClient(Client client) {
        try {
            Socket socket = new Socket(hostname, port);
            OutputStream output = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(output);

            String select;
            select = "5";

            oos.writeObject(select);
            oos.writeObject(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getIdByPhoneNumber(String phoneNumber) {
        try {
            Socket socket = new Socket(hostname, port);
            OutputStream output = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(output);

            InputStream input = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(input);

            String select;
            select = "7";
            oos.writeObject(select);
            oos.writeObject(phoneNumber);
            return (int) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
