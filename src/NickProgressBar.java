import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;

public class NickProgressBar {
	ProgressBar hBar=null;
	IncresingOperator action;
	public NickProgressBar(Composite parent) {
		hBar = new ProgressBar(parent, SWT.HORIZONTAL | SWT.SMOOTH);
		hBar.setMinimum(0);
		hBar.setMaximum(30);

		ProgressBar hBar1 = new ProgressBar(parent, SWT.HORIZONTAL
				| SWT.INDETERMINATE);
		hBar1.setMinimum(0);
		hBar1.setMaximum(30);

		action = new IncresingOperator(hBar);
	}
	public void startGo() {
		 System.out.println(action.getName()+" Alive:"
			      +action.isAlive()+" State:" + action.getState() );
		 if ("TERMINATED".equalsIgnoreCase(action.getState().toString())){
				action = new IncresingOperator(hBar);
				java.util.Random a;
				action.setRate(ThreadLocalRandom.current().nextInt(1,100));
		 }else {
			 	
				action.start(); 
		 }
	}

	public void restore() {
		action.restore();
		
	}
	class IncresingOperator extends Thread {
		private ProgressBar bar;
		private int rate=1;

		IncresingOperator(ProgressBar bar) {
			this.bar = bar;
		}

		public void setRate(int rate) {
			this.rate = rate;			
		}

		public void restore() {
			bar.setSelection(0);
		}

		public void run() {
			/**
			 * 
			 */
			Display.getDefault().asyncExec(new Runnable() {
				public void run() {
					// TODO Auto-generated method stub
					/**
					 * Maybe it has been disposed.
					 * 
					 * 
					 */
					if (bar.isDisposed())
						return;

					for (int i = 0; i < bar.getMaximum(); i++) {
						try {
							/**
							 * In order to display the progress bar step by
							 * step, and we let the thread sleep 100ms
							 * 
							 * 
							 */
							Thread.sleep(100/rate);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

						/**
						 * Add the selection value.
						 * 
						 * 
						 */
						bar.setSelection(bar.getSelection() + 1);
					}
				}
			});
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("ProgressBar Test:");
		shell.setLayout(new FillLayout(SWT.VERTICAL));

		new NickProgressBar(shell);

		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();

	}

}