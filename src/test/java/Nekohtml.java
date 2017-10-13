import java.io.StringReader;

import javax.xml.transform.dom.DOMSource;

import org.cyberneko.html.parsers.DOMFragmentParser;
import org.cyberneko.html.parsers.DOMParser;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.w3c.tidy5.premium.XpathV3Selector;
import org.xml.sax.InputSource;

public class Nekohtml {

	public static void main(String[] args) {
		System.out.println("".lastIndexOf("/"));
	}

	private static void nekoTest() {
		//		  DOMFragmentParser parser = new DOMFragmentParser();
				  DOMParser parser = new DOMParser();
				    try {
				      parser
				          .setFeature(
				              "http://cyberneko.org/html/features/scanner/allow-selfclosing-iframe",
				              true);
				      parser.setFeature("http://cyberneko.org/html/features/augmentations",
				          true);
		//		      parser.setProperty(
		//		          "http://cyberneko.org/html/properties/default-encoding",
		//		          defaultCharEncoding);
				      parser
				          .setFeature(
				              "http://cyberneko.org/html/features/scanner/ignore-specified-charset",
				              true);
				      parser
				          .setFeature(
				              "http://cyberneko.org/html/features/balance-tags/ignore-outside-content",
				              false);
				      parser.setFeature(
				          "http://cyberneko.org/html/features/balance-tags/document-fragment",
				          true);
		//		      parser.setFeature("http://cyberneko.org/html/features/report-errors",
						String url="http://et.21cn.com/movie/xinwen/huayu/2004/05/20/1571743.shtml";
				      Document document = Jsoup.connect(url).get();
				      W3CDom wad= new W3CDom();
				      org.w3c.dom.Document fromJsoup = wad.fromJsoup(document);
		//		      parser.parse(new InputSource(new StringReader(document.html())));
		//		      org.w3c.dom.Document document2 = parser.getDocument();
				      XpathV3Selector selector = XpathV3Selector.selector(new DOMSource(fromJsoup));
				  	String string = selector.evaluate("//div[@class='texttit_m1']");
					System.out.println(string);
		//		      DOMSource
				    } catch (Exception e) {
				    	e.printStackTrace();
				    }
	}

}
