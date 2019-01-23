package flat.parser;

import flat.device.BathroomDevice;
import flat.device.Device;
import flat.device.KitchenDevice;
import flat.device.LongueRoomDevice;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

public class Parser {
    private static Logger loggerParser = Logger.getLogger(Parser.class);
    private List<Device> allDevices;
    private DocumentBuilder documentBuilder;

    public Parser() {
        loggerParser.info("PARSER: Create new arraylist");
        this.allDevices = new ArrayList<>();
        loggerParser.info("PARSER: Create new arraylist completed successfully");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public List<Device> getAllDevices() {
        loggerParser.info("PARSER: Printing all devises after parser now: " + allDevices);
        loggerParser.info("PARSER: Size of list allDevices is " + allDevices.size());
        return allDevices;
    }

    public void buildListDeviceKitchen() throws IOException, SAXException {
        loggerParser.info("PARSER: Start method <buildListDeviceKitchen>");
        try {
            Document doc1 = documentBuilder.parse("deviceHands.xml");
            Element root = doc1.getDocumentElement();
            NodeList deviceListKitchen = root.getElementsByTagName("KitchenDevice");
            for (int i = 0; i < deviceListKitchen.getLength(); i++) {
                Element kitchenElement = (Element) deviceListKitchen.item(i);
                Device kitchen = buildDeviceKitchen(kitchenElement);
                allDevices.add(kitchen);
                loggerParser.info("PARSER: Method <buildListDeviceKitchen> completed successfully");
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buildListDeviceBathroom() throws IOException, SAXException {
        loggerParser.info("PARSER: Start method <buildListDeviceBathroom>");
        try {
            Document doc2 = documentBuilder.parse("deviceHands.xml");
            Element root = doc2.getDocumentElement();
            NodeList deviceListBath = root.getElementsByTagName("BathroomDevice");
            for (int j = 0; j < deviceListBath.getLength(); j++) {
                Element bathElement = (Element) deviceListBath.item(j);
                Device bath = buildDeviceBath(bathElement);
                allDevices.add(bath);
                loggerParser.info("PARSER: Method < buildListDeviceBathroom> completed successfully");            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void buildListDeviceLongueRoom() throws IOException, SAXException {
        loggerParser.info("PARSER: Start method <buildListDeviceLongueRoom>");
        try {
            Document doc1 = documentBuilder.parse("deviceHands.xml");
            Element root = doc1.getDocumentElement();
            NodeList deviceListLongue = root.getElementsByTagName("LongueRoomDevice");
            for (int k = 0; k < deviceListLongue.getLength(); k++) {
                Element longueElement = (Element) deviceListLongue.item(k);
                Device longue = buildDeviceLongue(longueElement);
                allDevices.add(longue);
                loggerParser.info("PARSER: Method <buildListDeviceLongueRoom> completed successfully");
            }
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Device buildDeviceKitchen(Element kitchenElement) {
        loggerParser.info("PARSER: Start method <buildDeviceKitchen>");
        KitchenDevice kitchenDevice = new KitchenDevice();
        getDeviceField(kitchenDevice, kitchenElement);
        Integer temperatureMinim = Integer.parseInt(getElementTextContent(kitchenElement, "minTemperature"));
        kitchenDevice.setMinTemperature(temperatureMinim);
        Integer temperatureMaxim = Integer.parseInt(getElementTextContent(kitchenElement, "maxTemperature"));
        kitchenDevice.setMaxTemperature(temperatureMaxim);
        loggerParser.info("PARSER: Method <buildDeviceKitchen> completed successfully");
        return kitchenDevice;
    }

    private Device buildDeviceBath(Element bathElement) {
        loggerParser.info("PARSER: Start method <buildDeviceBath>");
        BathroomDevice bathroomDevice = new BathroomDevice();
        getDeviceField(bathroomDevice, bathElement);
        Boolean water = Boolean.parseBoolean(getElementTextContent(bathElement, "waterproof"));
        bathroomDevice.setWaterproof(water);
        loggerParser.info("PARSER: Method <buildDeviceBath> completed successfully");
        return bathroomDevice;
    }

    private Device buildDeviceLongue(Element longueElement) {
        loggerParser.info("PARSER: Start method <buildDeviceLongue>");
        LongueRoomDevice longueRoomDevice = new LongueRoomDevice();
        getDeviceField(longueRoomDevice, longueElement);
        Boolean display = Boolean.parseBoolean(getElementTextContent(longueElement, "lcdDisplay"));
        longueRoomDevice.setLcdDisplay(display);
        Boolean wifi = Boolean.parseBoolean(getElementTextContent(longueElement, "wiFi"));
        longueRoomDevice.setWiFi(wifi);
        Boolean bluet = Boolean.parseBoolean(getElementTextContent(longueElement, "bluetooth"));
        longueRoomDevice.setBluetooth(bluet);
        loggerParser.info("PARSER: Method <buildDeviceLongue> completed successfully");
        return longueRoomDevice;
    }

    private static String getElementTextContent(Element element, String elementName) {
        loggerParser.info("PARSER: Start method <getElementTextContent>");
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        loggerParser.info("PARSER: Method <getElementTextContent> completed successfully");
        return text;
    }

    private <T extends Device> void getDeviceField(T device, Element dev) {
        loggerParser.info("PARSER: Start method <getDeviceField>");
        device.setName(getElementTextContent(dev, "name"));
        Integer powerSize = Integer.parseInt(getElementTextContent(dev, "powerSizekW"));
        device.setPowerSizekW(powerSize);
        Boolean power = Boolean.parseBoolean(getElementTextContent(dev, "powerON"));
        device.setPowerON(power);
        Integer serial = Integer.parseInt(getElementTextContent(dev, "serialNumber"));
        device.setSerialNumber(serial);
        loggerParser.info("PARSER: Method <getDeviceField> completed successfully");
    }
}