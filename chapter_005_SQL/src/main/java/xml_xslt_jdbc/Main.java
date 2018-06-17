package xml_xslt_jdbc;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        StoreSQL s = new StoreSQL(new Config());

        List<Integer> listFields = s.generate(n);

        List<Entry> entryList = new ArrayList<>();
        for (Integer field : listFields) {
            Entry entry = new Entry();
            entry.setField(field);
            entryList.add(entry);
        }

        StoreXML storeXML = new StoreXML(new File("file.xml"));
        storeXML.save(entryList);

        ConvertXSQT convertXSQT = new ConvertXSQT();
        convertXSQT.convert(new File("file.xml"), new File("newFile.xml"), new File("chapter_005_SQL/src/main/java/xml_xslt_jdbc/scheme.xsl"));

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        SAXHandler saxh = new SAXHandler();

        try {
            parser.parse(new File("newFile.xml"), saxh);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
