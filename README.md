# jtidy5
HTML5 Tidy for Java (vers. 2017-09-05) 
JTidy is a Java port of HTML Tidy, a HTML5 syntax checker and pretty printer. Like its non-Java cousin, JTidy can be used as a tool for cleaning up malformed and faulty HTML. In addition, JTidy provides a DOM interface to the document that is being processed, which effectively makes you able to use JTidy as a DOM parser for real-world HTML.

JTidy was written by Andy Quick, who later stepped down from the maintainer position. Now JTidy is maintained by a group of volunteers. 

for maven 
<dependency>
  <groupId>com.github.xujlibs</groupId>
  <artifactId>jtidy5</artifactId>
  <version>0.999</version>
</dependency>

Tidy tidy =  Tidy.me();

Document document = Jsoup.connect(url).get();
DOMSource parseDOMSource = tidy.parseDOMSource(new StringReader(document.html()));


1. FileReader reader = new FileReader("simple.xml");

	XpathV3Selector selector = XpathV3Selector.selector(reader);
	
	String string = selector.evaluate("//div[@class='texttit_m1']");
	
	System.out.println(string);
  
  
  
  	XpathV3Selector selector = XpathV3Selector.selector(new StringReader(domStr));
	
	String string = selector.evaluate("//div[@class='texttit_m1']");
		
	System.out.println(string);
    
    
    
  	  XpathV3Selector selector = XpathV3Selector.selector(new FileInputStream("simple.xml"));
	
	String string = selector.evaluate("//div[@class='texttit_m1']");
	
	System.out.println(string);
	
  String clear = XQPCleaner.clear(evaluate, "-//div[@class=\"textimg text-n1\"]","-//strong");
  
  
  
