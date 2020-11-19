package view;

import java.awt.BorderLayout;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class CreateView {

	public void createViewFor(String allUsersdetails) {
		JFrame frame = new JFrame();
		frame.add(new JLabel(" Outout"), BorderLayout.NORTH);

		JTextArea ta = new JTextArea();
		View taos = new View(ta, 60);
		PrintStream ps = new PrintStream(taos);
		System.setOut(ps);
		System.setErr(ps);

		frame.add(new JScrollPane(ta));

		frame.pack();
		frame.setVisible(true);
		frame.setSize(800, 600);

		System.out.println(allUsersdetails);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
