import java.io.*;

public class ClientLog {

    protected File log;

    public void log(int productNum, int amount) throws Exception {
        log = new File("log.txt");
        try (FileWriter out = new FileWriter(log, true)) {
            out.write((productNum + 1) + "," + amount + "\n");
        }
    }

    public void exportAsCSV(File txtFile) throws IOException {
        try (BufferedReader in = new BufferedReader(new FileReader(txtFile));
             FileWriter outCsv = new FileWriter("log.csv")) {
            outCsv.write("productNum,amount" + "\n");
            while (in.ready()) {
                outCsv.write(in.readLine() + "\n");
            }
        }
    }
}