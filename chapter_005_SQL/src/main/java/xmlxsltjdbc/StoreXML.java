package xmlxsltjdbc;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

public class StoreXML {
    File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public void save(List<Entry> list) {
        Entries entries = new Entries();
        entries.setEntries(list);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Entry.class, Entries.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(entries, target);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

@XmlRootElement
class Entries {

    List<Entry> entries;

    @XmlElement(name = "entry")
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public List<Entry> getEntries() {
        return entries;
    }
}

@XmlRootElement
class Entry {

    int field;

    public int getField() {
        return field;
    }

    @XmlElement
    public void setField(int field) {
        this.field = field;
    }
}
