package CarsEconomy;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Cars {

    private final String FileXmlOutput = "cars.xml";
    private final String FileXmlResult = "cars_result.xml";

    public boolean Calculate()
    {
        ClassLoader classLoader = new Cars().getClass().getClassLoader();
        File file = new File(classLoader.getResource(FileXmlOutput).getFile());

        Document doc = null;
        try {
            doc = parseXML(file.getPath());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("CarDetails");

        List<String> carsList = null;

        for(int i = 0; i < nodeList.getLength(); i++)
        {
            carsList.add(getString("Car", (Element) nodeList.item(i)));
        }

        return true;
    }

    private Document parseXML(String filePath) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(filePath);
        doc.getDocumentElement().normalize();
        return doc;
    }

    protected String getString(String tagName, Element element) {
        NodeList list = element.getElementsByTagName(tagName);
        if (list != null && list.getLength() > 0) {
            NodeList subList = list.item(0).getChildNodes();
            if (subList != null && subList.getLength() > 0) {
                return subList.item(0).getNodeValue();
            }
        }
        return null;
    }
}
