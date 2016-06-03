/* -*-mode:java; c-basic-offset:2; indent-tabs-mode:nil -*- */
import com.jcraft.jsch.*;

import java.awt.*;

import javax.swing.*;

import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import java.io.*;

public class Exec{
  public static void main(String[] arg){
      //SSHRunner ss = new SSHRunner("hkguxifx02","xpag1","20Pachk10",System.in,System.out);
	  Shell s = new Shell();
	  OutputProxy textOutputProxy = new OutputProxy(new StyledText(s, 0),System.out);
	  InputProxy myInputStream = new InputProxy(new ByteArrayInputStream("ls\ncd /uat\nls\n".getBytes()),System.in);
	  SSHRunner ss = new SSHRunner("hkguxifx02","xpag1","20Pachk10",myInputStream,textOutputProxy);
      //System.out.println(ss.trigger("ls"));
	  //System.out.println(ss.trigger("cat /u/xpag1/water/sync/pol.1"));
	  //SSHRunner ss = new SSHRunner();
	  //System.out.println(ss.trigger("ls"));
	  //System.out.println("............Done");

}}