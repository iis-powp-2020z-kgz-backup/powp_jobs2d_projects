package edu.kis.powp.jobs2d.command.manager;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.OperateToCommand;
import edu.kis.powp.jobs2d.command.SetPositionCommand;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class XMLCommandLoader implements CommandLoader {
    private String formattedData, name;

    public XMLCommandLoader(String formattedData, String name) {
        this.formattedData = formattedData;
        this.name = name;
    }

    @Override
    public synchronized LoadedCommand loadCommandFromExternalSource(String formattedData) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        List<Command> commandsList = new ArrayList<>();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource( new StringReader(formattedData));
            Document doc = dBuilder.parse(inputSource);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("Command");
            for (int i = 0; i < nodeList.getLength(); i++) {
                commandsList.add(getCommandFromNode(nodeList.item(i)));
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        List<DriverCommand> myDriverCommands = new ArrayList<>();
        for (Command command : commandsList) {
            switch (command.operation) {
                case "SetPositionCommand":
                    myDriverCommands.add(new SetPositionCommand(command.x, command.y));
                    break;
                case "OperateToCommand"	:
                    myDriverCommands.add(new OperateToCommand(command.x, command.y));
                    break;
            }
        }

        return new LoadedCommand(myDriverCommands, name);
    }

    private Command getCommandFromNode(Node node) {
        Command command = new Command();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            command.setOperation(getTagValue("operation", element));
            command.setX(Integer.parseInt(getTagValue("x", element)));
            command.setY(Integer.parseInt(getTagValue("y", element)));
        }

        return command;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}