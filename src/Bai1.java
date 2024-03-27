import javax.xml.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.*;
import java.util.Scanner;

import org.w3c.dom.*;

public class Bai1 {
  private Document doc;

  public Bai1() {
    Scanner sc = new Scanner(System.in);
    System.out.print("Nhập đường dẫn: ");
    String path = sc.nextLine();
    File file = new File(path);

    try {
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      doc = dBuilder.newDocument();

      Element rootElement = doc.createElement(file.getName());
      doc.appendChild(rootElement);

      Export(rootElement, file);

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(new File("file.xml"));
      transformer.transform(source, result);

      StreamResult consoleResult = new StreamResult(System.out);
      transformer.transform(source, consoleResult);

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  public void Export(Element element, File file) {
    try {
      if (file.isDirectory()) {
        Element ele = doc.createElement(file.getName());
        element.appendChild(ele);
        File[] children = file.listFiles();
        for (File child : children) {
          Export(ele, child);
        }
      } else {
        Element subElement = doc.createElement(file.getName());
        element.appendChild(subElement);
      }
    } catch (Exception e) {

    }
  }

  public static void main(String[] args) {
    new Bai1();
  }
}
