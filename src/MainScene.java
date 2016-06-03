import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.junit.Test;

public class MainScene {
	static StyledText styledText;
	static Text inputText;
	static SSHRunner sss;
	static NickProgressBar npb = null;
	
	static String defaultHost;
	static String defaultUser;
	static String defaultPass;
	static String defaultShellText;
	static String createAccountRecordsButtonText;
	static String triggerCreateAccountScriptButtonText;
	static String syncPolicesButtonText;
	static String runButtonText;
	static String refreshButtonText;
	static String defaultCommondTextFiled;
	static String db2Host;
	static String db2User;
	static String db2Pass;
	static String triggerCreateAccountScriptCommand;
	static String syncPolicesFile;
	static String syncPolicesScript;
	
	

	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Properties prop = new Properties();

			prop.load(new FileInputStream("V:/uat/sensitive.properties"));
			defaultHost=prop.getProperty("defaultHost");
			System.out.println(defaultHost);
			defaultUser=prop.getProperty("defaultUser");
			System.out.println(defaultUser);
			defaultPass=prop.getProperty("defaultPass");
			System.out.println(defaultPass);
			defaultShellText=prop.getProperty("shellText");
			System.out.println(defaultShellText);
			createAccountRecordsButtonText=prop.getProperty("createAccountRecordsButtonText");
			System.out.println(createAccountRecordsButtonText);
			triggerCreateAccountScriptButtonText=prop.getProperty("triggerCreateAccountScriptButtonText");
			System.out.println(triggerCreateAccountScriptButtonText);
			syncPolicesButtonText=prop.getProperty("syncPolicesButtonText");
			System.out.println(syncPolicesButtonText);
			runButtonText=prop.getProperty("runButtonText");
			System.out.println(runButtonText);
			refreshButtonText=prop.getProperty("refreshButtonText");
			System.out.println(refreshButtonText);
			defaultCommondTextFiled=prop.getProperty("defaultCommondTextFiled");
			System.out.println(defaultCommondTextFiled);
			db2Host=prop.getProperty("db2Host");
			System.out.println(db2Host);
			db2User=prop.getProperty("db2User");
			System.out.println(db2User);
			db2Pass=prop.getProperty("db2Pass");
			System.out.println(db2Pass);
			triggerCreateAccountScriptCommand=prop.getProperty("triggerCreateAccountScriptCommand");
			System.out.println(triggerCreateAccountScriptCommand);
			syncPolicesFile=prop.getProperty("syncPolicesFile");
			System.out.println(syncPolicesFile);
			syncPolicesScript=prop.getProperty("syncPolicesScript");
			System.out.println(syncPolicesScript);
			
			

		final Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(800, 1600);
		shell.setText(defaultShellText);

		GridLayout gridLayout = new GridLayout();
		gridLayout.horizontalSpacing = 2;
		gridLayout.marginHeight = 3;
		gridLayout.numColumns = 1;
		gridLayout.makeColumnsEqualWidth = true;
		shell.setLayout(gridLayout);

		final ClipboardTableShell clipboardTableShell = new ClipboardTableShell(
				display, shell);

		final Button createAccountRecordsButton = new Button(shell, SWT.CHECK);
		createAccountRecordsButton.setText(createAccountRecordsButtonText);
		final Button triggerCreateAccountScriptButton = new Button(shell,
				SWT.CHECK);
		triggerCreateAccountScriptButton.setText(triggerCreateAccountScriptButtonText);
		final Button syncPolicesButton = new Button(shell, SWT.CHECK);
		syncPolicesButton.setText(syncPolicesButtonText);

		final Button runButton = new Button(shell, SWT.PUSH);
		runButton.setText(runButtonText);
		final Button refreshButton = new Button(shell, SWT.PUSH);
		refreshButton.setText(refreshButtonText);

		// final NickProgressBar npb = new NickProgressBar(display,shell);
		npb = new NickProgressBar(shell);
		inputText = new Text(shell, SWT.BORDER | SWT.FILL);
		inputText.setSize(64, 32);
		inputText.setText(defaultCommondTextFiled);
		styledText = new StyledText(shell, SWT.BORDER | SWT.V_SCROLL
				| SWT.MULTI | SWT.WRAP);
		styledText.setLayoutData(new GridData(GridData.FILL_BOTH));

		GridData gridData = new GridData();
		gridData.widthHint = 600;
		gridData.heightHint = 200;
		styledText.setLayoutData(gridData);

		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				if (event.widget == runButton) {
					if (createAccountRecordsButton.getSelection()) {
						createAccountRecords();
					}
					if (triggerCreateAccountScriptButton.getSelection()) {
						triggerCreateAccountScript();
					}
					if (syncPolicesButton.getSelection()) {
						syncPolices();
					}
				} else if (event.widget == refreshButton) {
					updateBar();
				} else {
					System.out.println("whatever");
				}

			}

			private void createAccountRecords() {
				System.out.println("createAccountRecords");
				ArrayList<String> clientArray = clipboardTableShell
						.getClientArray();
				System.out.println("working on " + Arrays.asList(clientArray));
				System.out.println("start............");
				Db2LocalRunner dr = new Db2LocalRunner();
				updateBar();
				System.out.println("delete............");
				dr.delete();
				System.out.println("insert............");
				dr.InsertSelectTables(clientArray);
				dr.SelectTables();
				System.out.println("............Done");
			}

			private void triggerCreateAccountScript() {
				System.out
						.println("start.....triggerCreateAccountScript.......");
				fire(triggerCreateAccountScriptCommand);
				System.out.println("............Done");
				updateBar();
			}

			private void syncPolices() {
				System.out.println("start..syncPolices..........");
				ArrayList<String> policyArray = clipboardTableShell
						.getPolicyArray();
				String inFile = "";
				for (String l : policyArray) {
					inFile = inFile + l + "\n";
				}
				System.out.println(".." + inFile);
				fireDB("echo '" + inFile + "' > "+syncPolicesFile+"\n"
						+ "cat "+syncPolicesFile+"\n" + "pwd\n"
						+ syncPolicesScript);
				updateBar();
				System.out.println("............Done all");

			}

		};
		runButton.addListener(SWT.Selection, listener);
		refreshButton.addListener(SWT.Selection, listener);

		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}

	protected static void updateBar() {
		npb.startGo();
		npb.restore();
	}

	@Test
	public void testfire() {
		fire("ls\n");
	}

	protected static void fire(String cmd) {
		OutputProxy textOutputProxy = new OutputProxy(styledText, System.out);
		InputProxy myInputStream = new InputProxy(new ByteArrayInputStream(
				cmd.getBytes()), System.in);
		sss = new SSHRunner(defaultHost, defaultUser, defaultPass, myInputStream, textOutputProxy);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	protected static void fireDB(String string) {
		OutputProxy textOutputProxy = new OutputProxy(styledText, System.out);
		InputProxy myInputStream = new InputProxy(new ByteArrayInputStream(
				string.getBytes()), System.in);
		sss = new SSHRunner(db2Host, db2User, db2Pass, myInputStream,
				textOutputProxy);

	}

}