
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    PrintWriter writer = new PrintWriter(System.out);
    public Log() {

    }

    public void add(String log) {
        new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        System.out.println("[" + formatter.format(date)+ "] " + log);
        try {
            writer = new PrintWriter(new FileOutputStream("src/main/resources/logs.txt", true));
            writer.write("[" + formatter.format(date)+ "] " + log + "\n");
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
