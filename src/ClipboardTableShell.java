import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.TextTransfer;
import org.junit.Test;

public class ClipboardTableShell {


	// static String in = "07833583 90084424";
	static String in = "00000000 00000000";
	ArrayList<String>  clientArray=null;
	ArrayList<String>  policyArray=null;

	 
	public ClipboardTableShell(){
		 clientArray=new ArrayList<String> ();
		 policyArray=new ArrayList<String> ();
	}

	public ArrayList<String> getClientArray() {
		return clientArray;
	}

	public void setClientArray(ArrayList<String> clientArray) {
		this.clientArray = clientArray;
	}

	public ArrayList<String> getPolicyArray() {
		return policyArray;
	}

	public void setPolicyArray(ArrayList<String> policyArray) {
		this.policyArray = policyArray;
	}

	public ClipboardTableShell(Display display,Shell shell) {
		this();
		final Clipboard c = new Clipboard(display);

	    Color red = display.getSystemColor(SWT.COLOR_RED);
	    
		final Table table = new Table(shell, SWT.BORDER | SWT.CHECK | SWT.MULTI| SWT.FULL_SELECTION);

	    GridData gridData = new GridData();
	    gridData.widthHint = 600;
	    gridData.heightHint = 300;
		table.setLayoutData(gridData);
	    
		TableColumn tc1 = new TableColumn(table, SWT.CENTER);
		TableColumn tc2 = new TableColumn(table, SWT.CENTER);
		TableColumn tc3 = new TableColumn(table, SWT.CENTER);
		tc1.setText("Account Name");
		tc2.setText("Policy Number");
		tc3.setText("DELETE");
		
		tc1.setWidth(170);
		tc2.setWidth(170);
		tc3.setWidth(120);
		table.setHeaderVisible(true);
		table.setSize(600,400);
		//if (tableItems.length<=1)
		//transformString(in, table);

		Listener sortListener = new Listener() {
            public void handleEvent(final Event e) {
                //TableColumn column = (TableColumn) e.widget;
                //table.setSortColumn(column);
                
                String data = (String) c.getContents(TextTransfer.getInstance());
				// synchronize contact details with the selected contact
				testAndTransformData(data,table);
				TableItem[] tableItems = table.getItems();
				for (int j=0; j<tableItems.length; j++){
					clientArray.add(tableItems[j].getText());
					System.out.println("clientArray["+j+"]=" +clientArray.get(j) );
				}
            }
        };
        tc1.addListener(SWT.Selection, sortListener);
        
		table.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				String data = (String) c.getContents(TextTransfer.getInstance());
				int i = table.getSelectionIndex();
				System.out.println("hello" + i );
				if (i < 0)
					return;
				// synchronize contact details with the selected contact
				testAndTransformData(data,table);
			}
		});

	}

	protected void testAndTransformData(String data, Table table) {
		int i = table.getSelectionIndex();
		System.out.println("hello" + i );
		if (data.matches("([[\\s]*\\d]{5,8}[\\r\\n|\\n]+)+")) {
			System.out.println("pol:" + i + " ~" + data);
			transformString(data, table,1);
		}else if (data.matches("([\\s]*[\\d]{5,}[\\s]+[\\d]{5,}[\\s]*[\r\n]*)+")) {
			System.out.println("client&pol" + i + " ~" + data);
			transformString(data, table, 2);
		}else {
			System.out.println("??"+data);
		}
	}
@Test
public void testTrans(){
	String data = "00000000 00000000";
	Table table = new Table(new Shell(), SWT.BORDER | SWT.CHECK | SWT.MULTI| SWT.FULL_SELECTION);
	testAndTransformData(data,table);
	data = "20001952\n"+"20030325\n";
	testAndTransformData(data,table);
	
}
	private void transformString(String in, Table table, int inputRows) {
//7833583	90084424
		String[] inFormated = in.split("\\r?\\n");
		TableItem[] tableItems = table.getItems();
		TableItem item1 = null;
		String[] r=null; 


		String row=null;
		switch(inputRows)   
		  
		{   
		  
		case 1:   
		  
		System.out.println(1);   

		for (int i=0;i<inFormated.length;i++) {
			row = inFormated[i];
			row=removeSpace(row);
			if (notExists(row,tableItems))
			{
			row=String.format("%012d", Integer.parseInt(row));
			item1 = new TableItem(table, SWT.NONE);
			r= new String[2];
			r[0]="";
			r[1]=row;
			item1.setText(r);
			clientArray.add("");
			policyArray.add(row);
			
			}
		}
		break;   
		  
		case 2:   
		  
		System.out.println(2);   

		for (int i=0;i<inFormated.length;i++) {
			row = inFormated[i];
			row=removeSpace(row);
			r= row.split(" ");
			if ((r.length>1)&&(r[0]!=null)&&(notExists(r[0],tableItems)))
			{
				if (r[0].length()<7)
				r[0]=String.format("%07d", Integer.parseInt(r[0]));
				if (r[1].length()<12)
				r[1]=String.format("%012d", Integer.parseInt(r[1]));
				
			item1 = new TableItem(table, SWT.NONE);
			item1.setText(r);
			clientArray.add(r[0]);
			policyArray.add(r[1]);
			
			//last del button
			TableEditor delEditor = new TableEditor(table);
			delEditor.grabHorizontal  = true;
			delEditor.grabVertical = true;
			Button delButton= new Button(table, SWT.PUSH);
			delButton.setText("del...");
			delButton.computeSize(SWT.DEFAULT, table.getItemHeight());
			delEditor.setEditor(delButton, item1,2);
			delEditor.layout();
			
			}
		}
		
		break;    
		  
		default:   
		  
		System.out.println("default");   
		  
		break;   
		  
		}   

	}

	private String removeSpace(String row) {
		row=row.replace("	"," ");
		row=row.replace("  "," ");
		row=row.replace("  "," ");
		row=row.replace("  "," ");
		return row;
	}

	private boolean notExists(String string, TableItem[] tableItems) {
		for (TableItem item : tableItems) {
			System.out.println(item);
			if (item.getText().equalsIgnoreCase(string))
				return false;
		}
		return true;
	}

	public static void main(String[] argv) {
		new ClipboardTableShell(new Display(), new Shell());
	}

}