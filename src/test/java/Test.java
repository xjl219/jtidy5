import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.Writer;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;

import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.nodes.Node;
import org.w3c.tidy5.Tidy;
import org.w3c.tidy5.premium.XQPCleaner;
import org.w3c.tidy5.premium.XpathV3Selector;

import net.sf.saxon.dom.DOMSender;
import net.sf.saxon.om.MutableNodeInfo;
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
			FileReader in = new FileReader("/Users/xujl-mac/IdeaProjects/tt/src/main/resources/simple.xml");
//			DOMSource domSource = Tidy.toDOMSource(in);
			String domSource = Tidy.me(in).asString();
		String clear = XQPCleaner.cleaner(domSource).clearDomSource( "$//head","-//meta","-//script","_-财经频道-金融界");
//		String clear = XQPCleaner.clearDomSource(domSource, "$//head","-//meta","-//script","_-财经频道-金融界");
		System.err.println(clear);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		 System.out.println(html);
	}
	private static void test1() {
		try {
			 DOMSender.ignoreNoBindingNamespaces();
			String url="http://www.linkshop.com.cn/web/archives/2011/188353.shtml";
//			String url="http://www.ccstock.cn/simu/2013-02-08/A1095184.html";
//			String url="http://bank.jrj.com.cn/2013/12/24085616383108-c.shtml";
//			String url="http://auto.21cn.com/zixun/hangye/zhnb/2015/0309/07/29173861_all.shtml";
//			String url="http://bank.jrj.com.cn/2012/12/17152114832994.shtml";
//			String url="http://www.yahui.cc/forex/quote/1266705-1.htm";
//			String url="http://stock.jrj.com.cn/ipo/2017/09/01010823040976.shtml";
//			Document document = Jsoup.connect(url).get();
//			System.err.println(document.html());
//			and not(has-children(//div[@class=\"articleContent\"]))
//			"".replace(target, replacement)
//			 DOMSource domSource = Tidy.toDOMSource(new StringReader(document.html()));
			 DOMSource domSource = Tidy.toDOMSource(new FileReader("/Users/xujl-mac/IdeaProjects/tt/src/main/resources/simple.xml"));
//			 System.out.println(html);
//            System.out.println(XQPCleaner.clearDomSource(domSource,  "&replace value of node  //body with \"Goodbye\" ","-//title","-//script"));
//       
			XpathV3Selector selector = XpathV3Selector.selector(new FileReader("/Users/xujl-mac/IdeaProjects/tt/src/main/resources/simple.xml"));
			String evaluate = selector.evaluate("//head");
//			String evaluate = XpathV3Selector.selector(new StringReader(document.html())).evaluate("//h1");
//			String evaluate = XpathV3Selector.selector(new StringReader(document.html())).evaluate("//div[@class=\"articleContent\"][count(//div[@class=\"articleContent\"]/p) <1]");
//			String evaluate = XpathV3Selector.selector(new StringReader(document.html())).evaluate("//div[@class=\"textmain tmf14 jrj-clear\"]/p/replace(.,'在招商银行','ok')");
			System.out.println(evaluate);
			System.out.println(selector.evaluate("//meta"));
//			String evaluate = XpathV3Selector.selector(new StringReader(document.html())).evaluate("//textarea[contains(.,'Template Error')]");
//			System.out.println("evaluate:"+ XpathV3Selector.selector(new StringReader(document.html())).evaluate("//div[@class=\"articleContent\" and matches(.,\"^\\s*$\")]"));
			
//			String clear = XQPCleaner.clear(evaluate, "-//div[@class=\"textimg text-n1\"]","-//strong");
//			System.err.println(clear);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static void t2() {
		try {
			FileReader f = new FileReader(
					"/Users/xujl-mac/work/projects/workspacetest/Xpath/src/main/resources/simple.xml");
			String url="http://et.21cn.com/movie/xinwen/huayu/2004/05/20/1571743.shtml";
//			String url="http://www.yahui.cc/forex/quote/1266705-1.htm";
//			String url="http://stock.jrj.com.cn/ipo/2017/09/01010823040976.shtml";
			Document document = Jsoup.connect(url).get();
			Document d=new Document("");
			Node node = document.getElementsByTag("html").first().clearAttributes();
			d.appendChild(node);
			 d.outputSettings().syntax(OutputSettings.Syntax.xml);
			d 
			    .outputSettings()
			    .charset("UTF-8");
			W3CDom w3d= new W3CDom();
		org.w3c.dom.Document fromJsoup = w3d.fromJsoup(d);
		System.err.println(w3d.asString(fromJsoup));
//		SaxonSelector selector = SaxonSelector.selector(new DOMSource(fromJsoup));
		XpathV3Selector selector = XpathV3Selector.selector(new StringReader(w3d.asString(fromJsoup)));
		String string = selector.evaluate("//div[@class='texttit_m1']");
		System.out.println(string);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	private static void t1() {
		try {
			OutputStream  l;
			Tidy tidy =  Tidy.me();
			FileReader f = new FileReader(
					"/Users/xujl-mac/work/projects/workspacetest/Xpath/src/main/resources/simple.xml");
			System.out.println(f.getEncoding());
			FileInputStream in = new FileInputStream(
					"/Users/xujl-mac/work/projects/workspacetest/Xpath/src/main/resources/simple.xml");
String url="http://stock.jrj.com.cn/ipo/2017/09/01010823040976.shtml";
			//			Document doc = tidy.parseDOM(f, null);
//			System.out.println(tidy.asString(doc));
			Document document = Jsoup.connect(url).get();
			
			DOMSource parseDOMSource = tidy.parseDOMSource(new StringReader(document.html()));
			//http://stock.jrj.com.cn/ipo/2017/09/01010823040976.shtml
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
			XPathExecutable compile = xpath.compile("//div[@class=\"texttit_m1\"]|//*[@id=\"IDNewsDtail\"]|//div[@class=\"textmain tmf14 jrj-clear\"]");
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
