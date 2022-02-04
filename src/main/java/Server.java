import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        Database db = new Database();
        db.openConnection();

        int port = 9900;


        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println();
                System.out.println("New client connected");

                OutputStream output = socket.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(output);

                InputStream input = socket.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(input);


                Log log = new Log();
                String select;
                do {
                    select = String.valueOf(ois.readObject());

                    System.out.println("[" + select + "]");
                    try {
                        switch (select) {
                            case "1":
                                log.add("Return all pizza");
                                oos.writeObject(db.typesOfPizza());
                                break;
                            case "2":
                                log.add("Return clients");
                                oos.writeObject(db.clients());
                                break;
                            case "3":
                                log.add("Recive data from client");
                                Order order = (Order) ois.readObject() ;
                                System.out.println(order);
                                db.addOrder(order);
                                System.out.println("Received order");
                                log.add("Recived order" + order);
                                break;
                            case "4":
                                log.add("Adding a client to the database");
                                Client client = (Client) ois.readObject();
                                System.out.println(client);
                                db.addClient(client);
                                oos.writeObject(db.getIdByPhoneNumber(client.getPhoneNumber()));
                                System.out.println("Add a client");
                                break;
                            case "5":
                                log.add("Update a client in database");
                                Client clientToUpdate = (Client) ois.readObject();
                                System.out.println(clientToUpdate);
                                db.updateClient(clientToUpdate);
                                System.out.println("Update");
                                break;
                            case "6":
                                log.add("Searching by phone number");
                                String phoneNumber = (String) ois.readObject();
                                System.out.println("Nr telefonu : " + phoneNumber);
                                int id = db.getIdByPhoneNumber(phoneNumber);
                                System.out.println(id);
                                Client client1 = db.searchByPhoneNumber(phoneNumber);
                                System.out.println("Found : "  + client1);
                                oos.writeObject(client1); // do zmiany zmienna
                                System.out.println("Get a number");
                                break;
                            case "7":
                                log.add("Getting id by phone number");
                                String number = (String) ois.readObject();
                                oos.writeObject(db.getIdByPhoneNumber(number));
                            default:
                                System.out.println("Wrong case");
                                System.out.println(select);
                                log.add("Wrong case");
                        }
                    } catch (NotSerializableException e) {
                        System.out.println ("Object wont be serialized");
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        System.out.println("Error class not found");
                        e.printStackTrace();
                    } catch (IOException e) {
                        System.out.println("Error while sending data ");
                        e.printStackTrace();
                    }


                } while (select.equals("-1"));

            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
