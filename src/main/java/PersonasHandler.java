import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class PersonasHandler extends DefaultHandler {

    private ArrayList<Persona> activas = new ArrayList<>();
    private ArrayList<Persona> noActivas = new ArrayList<>();

    private Persona persona;
    private StringBuilder buffer = new StringBuilder();

    public ArrayList<Persona> getActivas() {
        return activas;
    }

    public ArrayList<Persona> getNoActivas() {
        return noActivas;
    }


    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        buffer.delete(0, buffer.length());
        buffer.append(ch, start, length);

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        switch (localName){

            case "persona":
                persona = new Persona();
                if (uri=="http://www.personas.com/active"){
                    activas.add(persona);
                    persona.setId(Long.parseLong(attributes.getValue("id")));
                }else if (uri=="http://www.personas.com"){
                noActivas.add(persona);
                persona.setId(Long.parseLong(attributes.getValue("id")));}

                break;

        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName){
            case "nombre":
                persona.setNombre(buffer.toString());
                break;
            case "dni":
                persona.setDni(buffer.toString());
                break;
            case "edad":
                persona.setEdad(Integer.parseInt(buffer.toString()));
                break;
            case "salario":
                persona.setSalario(Float.parseFloat(buffer.toString()));
                break;
        }
    }


}
