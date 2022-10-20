import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LectorSAX {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        saxParserFactory.setNamespaceAware(true);
        SAXParser saxParser = saxParserFactory.newSAXParser();
        File file = new File(Paths.get("src", "main","resources", "personas_ns.xml").toString());
        PersonasHandler handler = new PersonasHandler();
        saxParser.parse(file,handler);

        ArrayList<Persona> activas = handler.getActivas();
        ArrayList<Persona> noActivas = handler.getNoActivas();

        System.out.println("Personas activas: ");
        for (Persona a : activas){
            System.out.println(a);
        }
        System.out.println("---------------------------");
        System.out.println("Personas no activas: ");
        for (Persona n : noActivas){
            System.out.println(n);
        }


    }


}
