import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Shop {
    static Basket load() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("shop.xml"));
        String bool = doc.getDocumentElement().getChildNodes().item(1).getChildNodes().item(1).getTextContent();
        String path = doc.getDocumentElement().getChildNodes().item(1).getChildNodes().item(3).getTextContent();
        File fileJson, fileTxt;
        if (bool.equals("true")) {
            if (path.equals("basket.json")) {
                fileJson = new File(path);
                if (fileJson.exists()) {
                    return Basket.loadFromJson();
                }
            } else if (path.equals("basket.txt")) {
                fileTxt = new File(path);
                if (fileTxt.exists()) {
                    return Basket.loadFromTxtFile();
                }
            }
        }
        return new Basket(new String[]{"Хлеб", "Яблоки", "Молоко"}, new int[]{50, 15, 70});
    }

    static void save(Basket basket) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("shop.xml"));
        String bool = doc.getDocumentElement().getChildNodes().item(3).getChildNodes().item(1).getTextContent();
        String format = doc.getDocumentElement().getChildNodes().item(3).getChildNodes().item(5).getTextContent();
        if (bool.equals("true")) {
            if (format.equals("json")) {
                basket.saveJson();
            } else {
                basket.saveTxt();
            }
        }
    }

    static void log(ClientLog person1) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new File("shop.xml"));

        String bool = doc.getDocumentElement().getChildNodes().item(5).getChildNodes().item(1).getTextContent();
        if (bool.equals("true")) {
            person1.exportAsCSV(person1.log);
        }
    }
}