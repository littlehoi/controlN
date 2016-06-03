import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;

import org.eclipse.swt.widgets.Text;


public class InputProxy extends SequenceInputStream{
	StringBuilder anotatedText;
	String inText;
	 public InputProxy(Enumeration<? extends InputStream> sysIn){
		 super(sysIn);
	 }
	public InputProxy(ByteArrayInputStream inTextStream, InputStream sysIn) {
		super(inTextStream,sysIn);	
	  System.out.println("~in");
	  anotatedText = new StringBuilder();
	}

}
