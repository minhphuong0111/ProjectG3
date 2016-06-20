/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Function;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

/**
 *
 * @author MINHPHUONG
 * Create instance with construct not parameter
 * implement WriteFile method create xml file 
 * WriteFile method with parameters: HostName, Port, Database, username, password
 */
public class WriteXMLFile {
    public WriteXMLFile ()
    {
    }
    
    /**
     * 
     * @param hostname
     * @param port
     * @param dbname
     * @param username
     * @param pwd 
     * create new file
     */
    public void WriteFile(String hostname, String port, String dbname, String username, String pwd)
    {
        DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        
        String hostnameEncrypt;
        String portEncrypt;
        String dbnameEncrypt;
        String usernameEncrypt;
        String pwdEncrypt;
        
        //Encrypt
        Encryption ecr = new Encryption();
        hostnameEncrypt = ecr.EncryptWithKey(hostname);
        portEncrypt = ecr.EncryptWithKey(port);
        dbnameEncrypt = ecr.EncryptWithKey(dbname);
        usernameEncrypt = ecr.EncryptWithKey(username);
        pwdEncrypt = ecr.EncryptWithKey(pwd);
        
        try
        {
            icBuilder= icFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = icBuilder.newDocument();
            Element mainRootElement = doc.createElement("Connection");
            doc.appendChild(mainRootElement);
            
            //create xml structure
            mainRootElement.appendChild(getElementNode(doc, mainRootElement, "HostName", hostnameEncrypt));
            mainRootElement.appendChild(getElementNode(doc, mainRootElement, "Port", portEncrypt));
            mainRootElement.appendChild(getElementNode(doc, mainRootElement, "DBName", dbnameEncrypt));
            mainRootElement.appendChild(getElementNode(doc, mainRootElement, "username", usernameEncrypt));
            mainRootElement.appendChild(getElementNode(doc, mainRootElement, "pwd", pwdEncrypt));
            
            //create xml file
            //file name Connection.xml
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source =  new DOMSource(doc);
            StreamResult console = new StreamResult(new File("Connection.xml"));
            transformer.transform(source, console);
            
            //Message successful
           // System.out.println("\n xml create successful");
        }
        catch(Exception ex)
        {
            
        }
    }
    
    public static Node getElementNode(org.w3c.dom.Document doc, Element element, String name, String value)
    {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }
    
    /**
     * 
     * @return string array length = 5; 
     */
    public String[] ReadFile()
    {
        String[] stringConnection = new String[5];
        String[] stringConnectionDecr = new String[5];
        int i =0;
        try{
            File xmlFile = new File("Connection.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
            
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("Connection");
            Node nNode = nList.item(0);
            for(int temp = 1; temp<nNode.getChildNodes().getLength(); temp+=2)
            {
                //index = 0 result null
                //normal loop result: null - value - null - value (temp++)
                stringConnection[i] = nNode.getChildNodes().item(temp).getTextContent();
                //System.out.println("Node: " + nNode.getChildNodes().item(temp).getTextContent());
                i++;
            }
        }
        catch (Exception ex){System.out.println(ex.toString());}
        
        //decrypt information
        //create instance Encryption
        //use decrypt 
        Encryption ecr = new Encryption();
        for (int j = 0; j < 5; j++) {
            stringConnectionDecr[j] = ecr.DeCryptWitKey(stringConnection[j]);
        //    System.out.println(""+stringConnectionDecr[j]);
        }
        
        return stringConnectionDecr;
    }
    
    /**
     * 
     * @param stringConnection array with 5 members is a plaintext
     * output is new information encrypt
     * Hostname, port, dbname, username, pwd
     */
    public void ModifyXML(String[] stringConnection)
    {
        System.out.println("modify");
        String[] stringConnectionEncr = new String[5];
        Encryption ecr = new Encryption();
        for (int j = 0; j < 5; j++) {
            stringConnectionEncr[j] = ecr.EncryptWithKey(stringConnection[j]);//string connect is encrypted
        }
        
        int i=0;
        try{
            File xmlFile = new File("Connection.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
            
            doc.getDocumentElement().normalize();
            
//            NodeList nList = doc.getElementsByTagName("Connection");
//            Node nNode = nList.item(0);
            
            Node nodeRoot = doc.getElementsByTagName("Connection").item(0);
//            
//            NamedNodeMap attribute = nodeRoot.getAttributes();
//            Node nNodeHost = attribute.getNamedItem("HostName");
//            nNodeHost.setTextContent(stringConnection[0]);
//            
//            Node nNodePort = attribute.getNamedItem("Port");
//            nNodePort.setTextContent(stringConnection[1]);
//            
//            Node nNodeusr = attribute.getNamedItem("DBName");
//            nNodeusr.setTextContent(stringConnection[2]);
//            
//            Node nNodepwd = attribute.getNamedItem("username");
//            nNodepwd.setTextContent(stringConnection[3]);
            
//            Node nNodeHost = attribute.getNamedItem("pwd");
//            nNodeHost.setTextContent(stringConnection[4]);
//            
//            NodeList host = doc.getElementsByTagName("HostName");
//            Node nNodeHost = host.item(0);
//            String hostname = stringConnectionEncr[0];
//            nNodeHost.setTextContent("localhost");
//            System.out.println(""+hostname);
//            
//            NodeList port = doc.getElementsByTagName("Port");
//            Node nNodePort = host.item(0);
//            nNodePort.setTextContent(stringConnectionEncr[1]);
//            
//            NodeList dbName = doc.getElementsByTagName("DBName");
//            Node ndbname = host.item(0);
//            ndbname.setTextContent(stringConnectionEncr[2]);
//            
//            NodeList usr = doc.getElementsByTagName("username");
//            Node nNodeusr = host.item(0);
//            nNodeusr.setTextContent(stringConnectionEncr[3]);
//            
//            NodeList Pwd = doc.getElementsByTagName("pwd");
//            Node nNodepwd = host.item(0);
//            nNodepwd.setTextContent(stringConnectionEncr[4]);
            
//            for(int temp = 1; temp<nNode.getChildNodes().getLength(); temp+=2)
//            {
//                //index = 0 result null
//                //normal loop result: null - value - null - value (temp++)
//                nNode.getChildNodes().item(temp).setTextContent(null);
//                //System.out.println("Node: " + nNode.getChildNodes().item(temp).getTextContent());
//                i++;
//            }
            NodeList nodeListConnection = nodeRoot.getChildNodes();
            for (int j = 0; j < nodeListConnection.getLength(); j++) {
                Node element = nodeListConnection.item(j);
                switch(element.getNodeName())
                {
                    case "HostName": element.setTextContent(stringConnectionEncr[0]); break;
                    case "Port": element.setTextContent(stringConnectionEncr[1]); break;
                    case "DBName": element.setTextContent(stringConnectionEncr[2]); break;
                    case "username": element.setTextContent(stringConnectionEncr[3]); break;
                    case "pwd": element.setTextContent(stringConnectionEncr[4]); break;
                }
            }
            
            
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("Connection.xml"));
            transformer.transform(source, result);
            System.out.println("modify");
//            
        }
        catch (Exception ex){System.out.println("Exception");}
    }
}
