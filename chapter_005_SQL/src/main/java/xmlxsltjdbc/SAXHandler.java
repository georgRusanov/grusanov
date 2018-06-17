package xmlxsltjdbc;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {

    private int sum = 0;

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        String value = atts.getValue(0);
        if (value != null) {
            sum += Integer.parseInt(value);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println(sum);
    }
}
