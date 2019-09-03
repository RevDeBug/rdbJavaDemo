package Loops;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Looper {
    private final String FileXmlOutput = "cars.xml";

    public List<String> CollectCars()
    {
        List<String> myCars = new ArrayList<String>();

        ClassLoader classLoader = new Looper().getClass().getClassLoader();
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

        for(int i = 0; i < nodeList.getLength(); i++)
        {
            myCars.add(getString("Car", (Element) nodeList.item(i)));
        }

        return myCars;
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
