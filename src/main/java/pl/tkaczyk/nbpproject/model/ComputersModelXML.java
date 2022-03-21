package pl.tkaczyk.nbpproject.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "faktura")
public class ComputersModelXML {
    @XmlElement(name = "komputer")
    private List<ComputerModel> computerModelListXML;

    public ComputersModelXML() {
    }

    public ComputersModelXML(List<ComputerModel> computerModelListXML) {
        this.computerModelListXML = computerModelListXML;
    }

    public List<ComputerModel> getComputerModelListXML() {
        return computerModelListXML;
    }

    public void setComputerModelListXML(List<ComputerModel> computerModelListXML) {
        this.computerModelListXML = computerModelListXML;
    }
}
