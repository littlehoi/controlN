import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Text;
import org.junit.Test;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;


public class SSHRunner {


    String host="hkguxwas01"; 
    String user="wasadmin";
    String pass="abcd3456";
    JSch jsch;
    Session session;
    Channel channel;
	private OutputStream myPrintStream;
/*	public SSHRunner() {
	    try{
        jsch=new JSch();          
        session=jsch.getSession(user, host, 22); 
        session.setConfig( "StrictHostKeyChecking" , "no" );
        session.setConfig("PreferredAuthentications", "password");
        session.setPassword(pass);
        //channel=session.openChannel("exec");

        }
	      catch(Exception e){
	    	  e.printStackTrace();
	      }
	}*/
	public SSHRunner(String host, String user,String pass,InputProxy inputStream, OutputProxy myPrintStream) {
	    try{
        jsch=new JSch();          
        session=jsch.getSession(user, host, 22); 
        session.setConfig( "StrictHostKeyChecking" , "no" );
        session.setConfig("PreferredAuthentications", "password");
        session.setPassword(pass);
        session.connect(300000);   // making a connection with timeout.
        System.out.println("session:"+session);
        channel=session.openChannel("shell");

        // Enable agent-forwarding.
        //((ChannelShell)channel).setAgentForwarding(true);

        channel.setInputStream(inputStream);
        /*
        // a hack for MS-DOS prompt on Windows.
        channel.setInputStream(new FilterInputStream(System.in){
            public int read(byte[] b, int off, int len)throws IOException{
              return in.read(b, off, (len>1024?1024:len));
            }
          });
         */
        channel.setOutputStream(myPrintStream);
        //channel.setOutputStream(System.out);

        /*
        // Choose the pty-type "vt102".
        ((ChannelShell)channel).setPtyType("vt102");
        */

        /*
        // Set environment variable "LANG" as "ja_JP.eucJP".
        ((ChannelShell)channel).setEnv("LANG", "ja_JP.eucJP");
        */

        //channel.connect();
        channel.connect(3*1000);
    
	    
        System.out.println("connected channel:"+channel);
        }
	      catch(Exception e){
	    	  e.printStackTrace();
	      }
	}

	public String trigger(String inText) {
		ArrayList outputList = new ArrayList();
	    try{
	    	channel.setInputStream(new ByteArrayInputStream(inText.getBytes()));
	    	System.out.print("channel ...");
	        //try{Thread.sleep(1000);}catch(Exception ee){ee.printStackTrace();}
	
	      }
	      catch(Exception e){
	    	  e.printStackTrace();
	      }
	    return outputList.toString();
	    }
	public void close() {
		try{
        channel.disconnect();
        session.disconnect();

    }
    catch(Exception e){
  	  e.printStackTrace();
    }
	}

}
