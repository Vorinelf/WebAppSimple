package flat.encode;
import flat.device.*;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class Encode {
    public void getEncodeToXml() {
        Device device1 = new KitchenDevice("Multicooker", 1, true,20,200);
        Device device2 = new KitchenDevice("Kittle", 2, true,30,250);
        Device device3 = new KitchenDevice("Microwave", 4, true,15,180);
        Device device4 = new BathroomDevice("hairdryer", 3, true, true);
        Device device5 = new BathroomDevice("hairdryer", 5, true, true);
        Device device6 = new BathroomDevice("washing_machine", 7, true, true);
        Device device7 = new LongueRoomDevice("TV", 2, true, true, true, true);
        Device device8 = new LongueRoomDevice("Computer", 4, false, false, true, false);
        try {
            FileOutputStream fos = new FileOutputStream(new File("./deviceAuto.xml"));
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.writeObject(device1);
            encoder.writeObject(device2);
            encoder.writeObject(device3);
            encoder.writeObject(device4);
            encoder.writeObject(device5);
            encoder.writeObject(device6);
            encoder.writeObject(device7);
            encoder.writeObject(device8);
            encoder.close();
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
