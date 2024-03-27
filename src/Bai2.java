import java.io.File;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;

public class Bai2 {
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory dbFactory = 
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
 
            Element rootElement = doc.createElement("class");
            doc.appendChild(rootElement);
            Attr totalStudentAttr = doc.createAttribute("totalStudents");
            totalStudentAttr.setValue("2");
            rootElement.setAttributeNode(totalStudentAttr);

            Element student1 = doc.createElement("student");
            rootElement.appendChild(student1);
            Attr attr1 = doc.createAttribute("name");
            attr1.setValue("Jessica");
            student1.setAttributeNode(attr1);

            Element age = doc.createElement("age");
            age.appendChild(doc.createTextNode("18"));
            student1.appendChild(age);

            Element gpa = doc.createElement("gpa");
            gpa.appendChild(doc.createTextNode("4.0"));
            student1.appendChild(gpa);
 
            Element student2 = doc.createElement("student");
            rootElement.appendChild(student2);

            Attr attr2 = doc.createAttribute("name");
            attr2.setValue("Eris");
            student2.setAttributeNode(attr2);
         
            Element age1 = doc.createElement("age");
            age1.appendChild(doc.createTextNode("18"));
            student2.appendChild(age1);
            
            Element gpa1 = doc.createElement("gpa");
            gpa1.appendChild(doc.createTextNode("3.7"));
            student2.appendChild(gpa1);
 
            TransformerFactory transformerFactory = 
                    TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(
                    new File("student.xml"));
            transformer.transform(source, result);
 
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
