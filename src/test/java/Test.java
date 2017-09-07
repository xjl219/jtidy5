import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.xml.transform.dom.DOMSource;

import org.w3c.tidy5.Tidy;
import org.w3c.tidy5.premium.SaxonSelector;

import net.sf.saxon.s9api.DocumentBuilder;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.XPathCompiler;
import net.sf.saxon.s9api.XPathExecutable;
import net.sf.saxon.s9api.XPathSelector;
import net.sf.saxon.s9api.XdmItem;
import net.sf.saxon.s9api.XdmNode;

public class Test {
static PrintWriter pw = new PrintWriter(new Writer() {
		
		@Override
		public void write(char[] cbuf, int off, int len) throws IOException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void flush() throws IOException {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void close() throws IOException {
			// TODO Auto-generated method stub
			
		}
	});
	public static void main(String[] args) {
		try {
			FileReader f = new FileReader(
					"/Users/xujl-mac/work/projects/workspacetest/Xpath/src/main/resources/simple.xml");
		SaxonSelector selector = SaxonSelector.selector(f);
		String string = selector.evaluate("//div[@class='texttit_m1']");
		System.out.println(string);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	private static void t1() {
		try {

			Tidy tidy =  Tidy.me();
			FileReader f = new FileReader(
					"/Users/xujl-mac/work/projects/workspacetest/Xpath/src/main/resources/simple.xml");
			System.out.println(f.getEncoding());
			FileInputStream in = new FileInputStream(
					"/Users/xujl-mac/work/projects/workspacetest/Xpath/src/main/resources/simple.xml");
//			Document doc = tidy.parseDOM(f, null);
//			System.out.println(tidy.asString(doc));
			DOMSource parseDOMSource = tidy.parseDOMSource(f);
			
			Processor proc = new Processor(false);
//			 tidy.pprint(doc, System.err);
			XPathCompiler xpath = proc.newXPathCompiler();
			DocumentBuilder builder = proc.newDocumentBuilder();
//			builder.setLineNumbering(true);
//			builder.setWhitespaceStrippingPolicy(WhitespaceStrippingPolicy.ALL);
//			builder.setDTDValidation(false);
//			builder.newBuildingContentHandler();
			XdmNode booksDoc;
			booksDoc = builder.build(parseDOMSource);
//			booksDoc = builder.build(new DOMSource(doc));
			XPathExecutable compile = xpath.compile("//div[@class='texttit_m1']");
			XPathSelector selector = compile.load();
			selector.setContextItem(booksDoc);
			System.err.println();
			System.err.println();
			System.err.println();
			for (XdmItem xdmItem : selector) {
				System.err.println(xdmItem.toString());
				// System.out.println(toXMLString2( xdmItem));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
