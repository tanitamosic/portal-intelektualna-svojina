import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DOMParser domParser = new DOMParser();
        DOMWriter writer = new DOMWriter();
        Scanner scanner = new Scanner(System.in);
        Document document = null;
        String path = "./z1_primer.xml";
        String choice = "";
        while (!choice.equals("-1")){
            System.out.print("Za čitanje podrazumevanog XML dokumenta unesite 0\n" +
                    "Za čitanje određenog XML dokumenta unesite 1\n" +
                    "Za pisanje A-1 XML dokumenta unesite 2\n" +
                    "Za pisanje P-1 XML dokumenta unesite 3\n" +
                    "Za pisanje Z-1 XML dokumenta unesite 4\n" +
                    "Za izlazak iz aplikacije unesite -1\n" +
                    ">> ");
            choice = scanner.next();
            switch(choice){
                case "0":
                    document = domParser.buildDocument(path);
                    break;
                case "1":
                    System.out.print("Naziv dokumenta: ");
                    path = scanner.next();
                    document = domParser.buildDocument(path);
                    break;
                case "2":
                    document = writer.generateA1();
                    break;
                case "3":
                    document = writer.generateP1();
                    break;
                case "4":
                    document = writer.generateZ1();
                    break;
                case "-1":
                    System.exit(0);
                default:
                    System.out.println("Unesite jednu od ponudjenih opcija");
                    continue;
            }
            domParser.printNode(document);
        }
    }

}
