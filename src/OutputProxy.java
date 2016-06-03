import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.eclipse.swt.custom.StyledText;


public class OutputProxy extends PrintStream {
	StringBuilder anotatedText;
	StyledText styledText;
	 public OutputProxy(PrintStream sysOut){
		 super(sysOut);
	 }

	 public OutputProxy(StyledText styledText,PrintStream sysOut) {
		 super(sysOut);
	     System.out.println("~out");
	  anotatedText = new StringBuilder();
	  this.styledText=styledText;
	 }

 @Override
	public void write(byte[] b, int off, int len) {
		super.write(b,off,len);
		String s = new String(b, off, len);
	     anotatedText.append(s);
	}


	 public String getOutText() {
		 styledText.setText(anotatedText.toString());
	     return anotatedText.toString();
	 }
	 
	 byte[] toBytes(int i)
	 {
	   byte[] result = new byte[4];

	   result[0] = (byte) (i >> 24);
	   result[1] = (byte) (i >> 16);
	   result[2] = (byte) (i >> 8);
	   result[3] = (byte) (i /*>> 0*/);

	   return result;
	 }

}
