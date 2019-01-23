package flat.servlet;

import flat.parser.Parser;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServApp extends HttpServlet {

    private static Logger loggerServApp = Logger.getLogger(ServApp.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        loggerServApp.info("SERVAPP: Start to get parameters");
        String kitchenDevice = request.getParameter("kitchenDevice");
        String bathroomDevice = request.getParameter("bathroomDevice");
        String longueRoomDevice = request.getParameter("longueRoomDevice");
        loggerServApp.info("SERVAPP: End to get parameters");

        loggerServApp.info("SERVAPP: Start to create new object PARSER");
        Parser parser = new Parser();
        loggerServApp.info("SERVAPP: End create new object PARSER");

        try {
            loggerServApp.info("SERVAPP: Start to check parameter <kitchenDevice> ");
            if (kitchenDevice != null) {
                parser.buildListDeviceKitchen();
                writer.println("<p>" + parser.getAllDevices() + "</p>");
                writer.println("<p>" + kitchenDevice + "</p>");// для проверки работы
                loggerServApp.info("SERVAPP: End to check parameter <kitchenDevice> ");
            }
            loggerServApp.info("SERVAPP: Start to check parameter <bathroomDevice> ");
            if (bathroomDevice != null) {
                parser.buildListDeviceBathroom();
                writer.println("<p>" + parser.getAllDevices() + "</p>");
                writer.println("<p>" + bathroomDevice + "</p>");// для проверки работы
                loggerServApp.info("SERVAPP: End to check parameter <bathroomDevice> ");
            }
            loggerServApp.info("SERVAPP: Start to check parameter <klongueRoomDevice> ");
            if (longueRoomDevice != null) {
                parser.buildListDeviceLongueRoom();
                writer.println("<p>" + parser.getAllDevices() + "</p>");
                writer.println("<p>" + longueRoomDevice + "</p>"); // для проверки работы
                loggerServApp.info("SERVAPP: End to check parameter <longueRoomDevice> ");
            }

        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
    }
}