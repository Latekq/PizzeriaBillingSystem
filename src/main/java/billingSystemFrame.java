import javax.swing.*;
import java.awt.*;

public class billingSystemFrame extends JFrame {
    private static Order order = new Order();
    private int numberOfQuanityPizza = 1;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int defaultWidth = 1600;
    private final int defaultHeight = 900;
    private boolean fullscreen;

    //variables that need refreshing ↓

    private final JList<El> list = new JList<>(order.toArray());
    private final JLabel orderTypeLabel = new JLabel(order.getOrderType());
    private Client client = new Client(" ", " ", " ", " ", " ");
    private final JLabel orderNameLabel = new JLabel(client.getName());
    private final JLabel orderLastNameLabel = new JLabel(client.getLastName());
    private final JLabel orderPhoneNumberLabel = new JLabel(client.getPhoneNumber());
    private final JLabel orderStreetLabel = new JLabel(client.getAdress());
    private final JLabel orderApartamentNumberLabel = new JLabel(client.getApartamentNumber());
    private final JLabel bill = new JLabel("rachunek");
    private Client[] clients;

    //customerDataComponent
    private final JList<Client> listClient = new JList<>();

    //customerData

    private final JButton inLocal = new JButton();
    private final JButton deliver = new JButton();
    private final JButton takeAway = new JButton();
    private final JTextField phoneNumberText = new JTextField();
    private final JTextField nameClientText = new JTextField();
    private final JTextField lastNameClientText = new JTextField();
    private final JTextField streetClientText = new JTextField();
    private final JTextField apartmentNumberText = new JTextField();
    //variables that need refreshing ↑

    public billingSystemFrame() {
        super("System rozliczeniowy");

        Database db = new Database();
        db.openConnection();


        MenuAction menuAction = new MenuAction();
        setJMenuBar(menuAction.menuBar);
        fullscreen = menuAction.isFullScreen();


        rightBar(db);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(defaultWidth,defaultHeight);
        setLocationRelativeTo(null);



        setLayout(null); // sets absolute positioning of components
        setVisible(true);
    }

    private void rightBar(Database db) {
        JPanel customerData = customerData(db);
        JPanel selectPizza = selectPizza(db);
        JPanel finalizeComponent = finalizeComponent(db);
        add(selectPizza);
        add(customerData);
        add(finalizeComponent);
        selectPizza.setVisible(false);
        customerData.setVisible(true);
        finalizeComponent.setVisible(false);
        clients = db.clients();
        listClient.setListData(clients);

        final int numbersElement = 4;
        int heightMenuBar = 100;
        int screenHeight = ((fullscreen ? screenSize.height : defaultHeight) - heightMenuBar)  / numbersElement;
        System.out.println(screenHeight);
        System.out.println(fullscreen);
        JButton firstButton = new JButton();
        firstButton.setBounds(0,0,100,screenHeight);
        firstButton.setText("<html>Dane<br>klienta</html>");
        add(firstButton);


        JButton secondButton = new JButton();
        secondButton.setBounds(0,screenHeight, 100, screenHeight);
        secondButton.setText("Pizze");
        add(secondButton);

        JButton trirdButton = new JButton();
        trirdButton.setBounds(0, screenHeight*2, 100, screenHeight);
        trirdButton.setText("Napoje");
        add(trirdButton);

        JButton fourthButton = new JButton();
        fourthButton.setBounds(0, screenHeight*3, 100, screenHeight);
        fourthButton.setText("Finalizuj");
        add(fourthButton);




        firstButton.addActionListener(e -> {
            System.out.println("wybrano dane klienta");
            customerData.setVisible(true);
            selectPizza.setVisible(false);
            finalizeComponent.setVisible(false);
            clients = db.clients();
            listClient.setListData(clients);
            repaint();
            revalidate();
            validate();
        });

        secondButton.addActionListener(e -> {
            System.out.println("wybierz pizze");
            customerData.setVisible(false);
            selectPizza.setVisible(true);
            finalizeComponent.setVisible(false);
            repaint();
            revalidate();
            validate();
        });

        trirdButton.addActionListener(e -> {
            System.out.println("dodaj napoje");
            infoBox("Brak możliwości dodania napojów na chwile biezącą.", "Error", "RED");
        });

        fourthButton.addActionListener(e -> {

            System.out.println("finalizuj");
            customerData.setVisible(false);
            selectPizza.setVisible(false);
            finalizeComponent.setVisible(true);
            list.setListData(order.toArray());
            orderTypeLabel.setText(order.getOrderType());
            orderNameLabel.setText(client.getName());
            orderLastNameLabel.setText(client.getLastName());
            orderPhoneNumberLabel.setText(client.getPhoneNumber());
            orderStreetLabel.setText(client.getAdress());
            orderApartamentNumberLabel.setText(client.getApartamentNumber());
            bill.setText("Do zapłaty : " + String.format("%.2f", order.getTotalPrice()) + " zł");
            repaint();
            revalidate();
            repaint();
        });




    }



    private JPanel customerData(Database db) {
        JPanel jpanel = new JPanel();
        jpanel.setLayout(null);
        jpanel.setBounds(100,0,1500,1500);


        JLabel phoneNumber = new JLabel("Please enter a phone number");
        phoneNumber.setBounds(50,50,1000,50);
        jpanel.add(phoneNumber);


        phoneNumberText.setBounds(250,55,200,30);
        jpanel.add(phoneNumberText);


        JLabel nameClient = new JLabel("Please enter a name");
        nameClient.setBounds(50,100,1000,50);
        jpanel.add(nameClient);


        nameClientText.setBounds(250,100,200,30);
        jpanel.add(nameClientText);

        JLabel lastNameClient = new JLabel("Please enter a  last name");
        lastNameClient.setBounds(50,150,1000,50);
        jpanel.add(lastNameClient);


        lastNameClientText.setBounds(250,150,200,30);
        jpanel.add(lastNameClientText);


        JLabel streetClient = new JLabel("Please enter a street");
        streetClient.setBounds(50,200,1000,50);
        jpanel.add(streetClient);


        streetClientText.setBounds(250,200,200,30);
        jpanel.add(streetClientText);


        JLabel apartmentNumber = new JLabel("Please enter a apartment number");
        apartmentNumber.setBounds(50,250,1000,50);
        jpanel.add(apartmentNumber);


        apartmentNumberText.setBounds(250,250,200,30);
        jpanel.add(apartmentNumberText);

        JButton search = new JButton();
        try {
            search.setIcon(new ImageIcon("C:\\Users\\latek\\Desktop\\Programowanie obiektowe\\Projekt\\src\\main\\java\\icons\\searchIcon.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        search.setBounds(450,55, 50,30);
        jpanel.add(search);

        inLocal.setText("W Lokalu");
        inLocal.setBounds(50,300,100,50);
        jpanel.add(inLocal);

        takeAway.setText("Na wynos");
        takeAway.setBounds(200,300,100,50);
        jpanel.add(takeAway);

        deliver.setText("Dostawa");
        deliver.setBounds(350,300,100,50);
        jpanel.add(deliver);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(listClient);

        scrollPane.setBounds(550,50,800,250);
        jpanel.add(scrollPane);


        inLocal.addActionListener(e -> {
            if (savingData(db, phoneNumberText, nameClientText, lastNameClientText, streetClientText, apartmentNumberText)) {
                deliver.setBackground(null);
                takeAway.setBackground(null);
                inLocal.setBackground(Color.cyan);
                listClient.setListData(db.clients());
                order.setOrderType(1);
            }
        });

        takeAway.addActionListener(e -> {
            if (savingData(db, phoneNumberText, nameClientText, lastNameClientText, streetClientText, apartmentNumberText)) {
                inLocal.setBackground(null);
                deliver.setBackground(null);
                takeAway.setBackground(Color.cyan);
                listClient.setListData(db.clients());
                order.setOrderType(2);
            }
        });

        deliver.addActionListener(e -> {
            if (savingData(db, phoneNumberText, nameClientText, lastNameClientText, streetClientText, apartmentNumberText)) {
                inLocal.setBackground(null);
                takeAway.setBackground(null);
                deliver.setBackground(Color.cyan);
                listClient.setListData(db.clients());
                order.setOrderType(3);
            }
        });


        search.addActionListener(e->{
            System.out.println("Szukaj");
            if(db.szukajPoNr(phoneNumberText.getText()) != null) {
                nameClientText.setText(db.szukajPoNr(phoneNumberText.getText()).getName());
                lastNameClientText.setText(db.szukajPoNr(phoneNumberText.getText()).getLastName());
                streetClientText.setText(db.szukajPoNr(phoneNumberText.getText()).getAdress());
                apartmentNumberText.setText(db.szukajPoNr(phoneNumberText.getText()).getApartamentNumber());
            } else {
                infoBox("Brak numeru telefonu.", "Error", "RED");
            }
        });

        listClient.addListSelectionListener(e->{
            System.out.println(listClient.getSelectedIndex());
            int i = listClient.getSelectedIndex()+1;
            if (i >= 1) {
                client = clients[i-1];
                phoneNumberText.setText(client.getPhoneNumber());
                nameClientText.setText(client.getName());
                lastNameClientText.setText(client.getLastName());
                streetClientText.setText(client.getAdress());
                apartmentNumberText.setText(client.getApartamentNumber());
            }
        });


        return jpanel;
    }



    private JPanel selectPizza(Database db) {
        JPanel jpanel = new JPanel();
        jpanel.setLayout(null);
        jpanel.setBounds(100,0,1500,1500);


        Pizza[] pizzas  = db.typesOfPizza();

        JScrollPane scrollPane = new JScrollPane();
        JList<Pizza> list = new JList<>(pizzas);
        scrollPane.setViewportView(list);


        //System.out.println(Arrays.toString(pizzas));
        scrollPane.setBounds(50,100,1000,300);
        jpanel.add(scrollPane);

        JLabel selectPizza = new JLabel("Wybierz pizze");
        selectPizza.setBounds(50,50,1000,50);
        jpanel.add(selectPizza);

        JLabel quanityText = new JLabel("ilosc");
        quanityText.setBounds(1150,50,1000,50);
        jpanel.add(quanityText);


        JLabel quanityPizza = new JLabel(String.valueOf(numberOfQuanityPizza));
        quanityPizza.setBounds(1165,100,50,50);
        jpanel.add(quanityPizza);

        JButton addNumberOfQuanityPizza = new JButton();
        addNumberOfQuanityPizza.setBounds(1200,100,50,50);
        addNumberOfQuanityPizza.setText("+");
        jpanel.add(addNumberOfQuanityPizza);

        JButton addPizzaToOrder = new JButton();
        addPizzaToOrder.setBounds(1100,150, 150, 50);
        addPizzaToOrder.setText("Dodaj Pizze");
        jpanel.add(addPizzaToOrder);

        JButton subtractsNumberOfQuantifyPizza = new JButton();
        subtractsNumberOfQuantifyPizza.setBounds(1100,100,50,50);
        subtractsNumberOfQuantifyPizza.setText("-");
        jpanel.add(subtractsNumberOfQuantifyPizza);


        addPizzaToOrder.addActionListener(e -> {
            order.addPizza(pizzas[list.getSelectedIndex() ], numberOfQuanityPizza);
            quanityPizza.setText("1");
            numberOfQuanityPizza = 1;
            //System.out.println(order);
        });

        list.addListSelectionListener(e -> System.out.println("List id : " + list.getSelectedIndex() + 1));

        addNumberOfQuanityPizza.addActionListener(e -> {
            numberOfQuanityPizza++;
            quanityPizza.setText(String.valueOf(numberOfQuanityPizza));
            //System.out.println(numberOfQuanityPizza);
        });


        subtractsNumberOfQuantifyPizza.addActionListener(e -> {
            if (numberOfQuanityPizza > 1) {
                numberOfQuanityPizza--;
                quanityPizza.setText(String.valueOf(numberOfQuanityPizza));
            }
            //System.out.println(numberOfQuanityPizza);
        });


        return jpanel;
    }



    private JPanel finalizeComponent(Database db) {
        JPanel jpanel = new JPanel();
        jpanel.setLayout(null);
        jpanel.setBounds(100,0,1500,1500);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);


        scrollPane.setBounds(50,100,1000,300);
        jpanel.add(scrollPane);

        JLabel yourOrder = new JLabel("Twoje zamowienie to:");
        yourOrder.setBounds(50,50,1000,50);
        jpanel.add(yourOrder);

        bill.setBounds(1100,50,300,50);
        jpanel.add(bill);

        JLabel typeLabel = new JLabel("Typ zamówienia ");
        typeLabel.setBounds(1100,100,100,50);
        jpanel.add(typeLabel);

        orderTypeLabel.setBounds(1200,100, 300, 50);
        jpanel.add(orderTypeLabel);

        JLabel nameLabel = new JLabel("Imie: ");
        nameLabel.setBounds(1100,150,100,50);
        jpanel.add(nameLabel);

        orderNameLabel.setBounds(1200,150,300,50);
        jpanel.add(orderNameLabel);

        JLabel lastNameLabel = new JLabel("Nazwisko: ");
        lastNameLabel.setBounds(1100,200,100,50);
        jpanel.add(lastNameLabel);

        orderLastNameLabel.setBounds(1200,200,300,50);
        jpanel.add(orderLastNameLabel);

        JLabel phoneNumberLabel = new JLabel("Nr. telefonu: ");
        phoneNumberLabel.setBounds(1100,250,100,50);
        jpanel.add(phoneNumberLabel);

        orderPhoneNumberLabel.setBounds(1200,250,300,50);
        jpanel.add(orderPhoneNumberLabel);

        JLabel streetLabel = new JLabel("Adress: ");
        streetLabel.setBounds(1100,300,100,50);
        jpanel.add(streetLabel);

        orderStreetLabel.setBounds(1200,300,300,50);
        jpanel.add(orderStreetLabel);

        JLabel apartamentNumberLabel = new JLabel("Nr. mieszkania: ");
        apartamentNumberLabel.setBounds(1100,350,100,50);
        jpanel.add(apartamentNumberLabel);

        orderApartamentNumberLabel.setBounds(1200,350,300,50);
        jpanel.add(orderApartamentNumberLabel);


        JButton removeItem = new JButton("Usuń z zamówienia");
        removeItem.setBounds(100,400,200 ,50);
        jpanel.add(removeItem);

        JButton pursue = new JButton("Realizuj");
        pursue.setBounds(300,400,200,50);
        jpanel.add(pursue);

        pursue.addActionListener(e -> {
            System.out.println("purse");
            System.out.println(order);
            if (order.getPosition().size() != 0  && order.getClient().getId() != 0 && order.getClient().getPhoneNumber().length() == 9 && order.getClient().getName().length() >= 3) {
                db.addOrder(order);
                infoBox("Dodano zamówienie!", "Dodano zamówienie", "Green");
                phoneNumberText.setText("");
                nameClientText.setText("");
                lastNameClientText.setText("");
                streetClientText.setText("");
                apartmentNumberText.setText("");
                orderTypeLabel.setText("");
                orderNameLabel.setText("");
                orderLastNameLabel.setText("");
                orderPhoneNumberLabel.setText("");
                orderStreetLabel.setText("");
                orderApartamentNumberLabel.setText("");
                deliver.setBackground(null);
                takeAway.setBackground(null);
                inLocal.setBackground(null);
                bill.setText("Do zapłaty : 0.00 zł");
                list.setListData(new El[1]);
                order = new Order();
                client = new Client();
                revalidate();
                repaint();
            } else {
                infoBox("Nie uzupełniono wszystkich danych!" +
                                (order.getPosition().size() != 0 ? "" : "Brak wybranej pizzy<br>")
                        +(order.getClient().getId() == 0? "" : "Zle dane Clienta<br>")
                        +(order.getClient().getPhoneNumber().length() == 9? "": "Zły nr telefonu<br>")
                                + (order.getClient().getName().length() >= 3? "": "Błąd w imieniu<br>")

                        , "Error", "RED");
            }
        });


        removeItem.addActionListener(e->{
            if(list.getModel().getSize() != 0) {
                if (list.getSelectedIndex() != -1) {
                    order.setTotalPrice(order.getTotalPrice() -  order.getPosition().get(list.getSelectedIndex()).getPrice());
                    order.getPosition().remove(list.getSelectedIndex());
                    list.setListData(order.toArray());
                    //System.out.println(order.getTotalPrice());
                    bill.setText(String.format("Do zapłaty %.2f",order.getTotalPrice()));
                    // System.out.println(bill.getText());
                    revalidate();
                    repaint();
                } else {
                    infoBox("Nie wybrano pizzy do usunięcia.", "Error", "RED");
                }

            } else {
                infoBox("Brak pizz do usunięcia.", "Error", "RED");
            }

        });

        list.addListSelectionListener(e -> System.out.println("List id : " + list.getSelectedIndex() + 1));

        return jpanel;
    }

    private boolean savingData(Database db, JTextField phoneNumberText, JTextField nameClientText, JTextField lastNameClientText, JTextField streetClientText, JTextField apartmentNumberText) {
        if(phoneNumberText.getText().length() == 9 && nameClientText.getText().length() >= 3 && lastNameClientText.getText().length() >= 3 && streetClientText.getText().length() >= 3 && apartmentNumberText.getText().length() >= 1) {


            client = new Client(
                    client.getId() == 0? 0: client.getId(),
                    nameClientText.getText(),
                    lastNameClientText.getText(),
                    phoneNumberText.getText(),
                    streetClientText.getText(),
                    apartmentNumberText.getText()
            );

            if(db.szukajPoNr(phoneNumberText.getText()) == null) {
                System.out.println("Utworzono klienta");
                System.out.println("Klient"  + client);
                db.addClient(client);
                client.setId(db.getIdByPhoneNumber(phoneNumberText.getText()));
            } else {
                System.out.println("Zakltualizowano klienta");
                db.updateClient(client);
            }



            System.out.println("Klient "  + client);
            order.setClient(client);
            return true;
        } else {
            infoBox("<html>Nie uzupełniono wszystkich danych!<br>"
                    + (phoneNumberText.getText().length() != 9? "Zły nr telefonu<br>":"")
                    + (nameClientText.getText().length() >= 3? "": "Złe imie.<br> ")
                    + (lastNameClientText.getText().length() >= 3? "": "Złe nazwisko.<br> ")
                    + (streetClientText.getText().length() >= 3? "": "Zła ulica.<br> ")
                    +(apartmentNumberText.getText().length() >= 1? "": "Zły nr mieszkania."),
                    "Error",
                    "RED");
            return false;
        }
    }

    public static void infoBox(String infoMessage, String titleBar, String color)
    {
        JOptionPane.showMessageDialog(null, "<html><BODY TEXT=\"" + color + "\">" +infoMessage + "</html>", titleBar.equals("Error") ? "ErrorBox" : "InfoBox" + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }

}

// nie dziala odklikniecie przyciskow inlocal, outstore, delivery
