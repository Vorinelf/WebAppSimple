package flat.room;

import flat.device.BathroomDevice;
import flat.device.Device;

import java.util.*;

import flat.device.KitchenDevice;
import flat.device.LongueRoomDevice;
import org.apache.log4j.Logger;

import java.util.stream.Collectors;

public class Room {
    private static Logger logger = Logger.getLogger(Room.class);
    private List<Device> list = new ArrayList<>();

    public void addDevices() {
        logger.info("ROOM: I am buying electrical devices");
        list.add(new KitchenDevice("Multicooker", 1, true,20,200));
        list.add(new KitchenDevice("Kittle", 2, true,30,250));
        list.add(new KitchenDevice("Microwave", 4, true,15,180));
        list.add(new BathroomDevice("hairdryer", 3, true, true));
        list.add(new BathroomDevice("hairdryer", 5, true, true));
        list.add(new BathroomDevice("washing_machine", 7, true, true));
        list.add(new LongueRoomDevice("TV", 2, true, true, true, true));
        list.add(new LongueRoomDevice("Computer", 4, false, false, true, false));
        logger.info("ROOM: Buy devices completed");
    }

    public void sortDevicesByPower() {
        logger.info("ROOM: I sort devices");
        Comparator<Device> comp = Comparator.comparingDouble(Device::getPowerSizekW);
        list.sort(comp);
        logger.info("ROOM: Sort devices completed");
        System.out.println(list);

    }

    public void getRangeByPower() {
        logger.info("ROOM: I choose my devices by some value of power");
        List<Device> powerFull = list
                .stream()
                .filter(l -> l.getPowerSizekW() > 1 && l.getPowerSizekW() < 5)
                .collect(Collectors.toList());
        System.out.println(powerFull);
        logger.info("ROOM: Choice by power completed");
    }

    public void sumAllPower() {
        logger.info("ROOM: I sum all power of devices ");
        IntSummaryStatistics powerSumm = list
                .stream()
                .collect(Collectors.summarizingInt(s -> s.getPowerSizekW()));
        System.out.println(powerSumm);
        logger.info("ROOM: Sum devices by power completed");

    }

    public void sumDevicesNow() {
        logger.info("ROOM: I count my devices now");
        System.out.println(" There is(are) " + list.size() + " electrical devices in my flat");
        logger.info("ROOM: Count devices completed");
    }

    public void printDevices() {
        System.out.println(list);
        logger.info("ROOM: All my devices were printed in console");


    }

}

