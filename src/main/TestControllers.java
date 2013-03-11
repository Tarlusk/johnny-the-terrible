/*
 * Copyright (c) 2002-2008 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package main;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.lwjgl.input.Controller;
import org.lwjgl.input.Controllers;

/**
 * Oops. Forgot to document this one.
 *
 * @author Kevin Glass
 */
public class TestControllers extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5225530503419352750L;

	public static int total;

	private JTextField[] values;
	private JTextField[] names;
	private JComboBox<String> combobox;
	private Controller controller;
	private int buttonCount;
	private int itemCount;
	private JPanel panel = new JPanel();
	private String[] controllerNames;
	private JScrollPane scroll;

	public TestControllers() {
		try {
			Controllers.create();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		int count = Controllers.getControllerCount();
		System.out.println(count+" Controllers Found");
		for (int i=0;i<count;i++) {
			Controller controller = Controllers.getController(i);
			System.out.println(controller.getName());
		}

		if (count == 0) {
			System.exit(0);
		}
		controllerNames = new String[count];
		
		for (int i=0;i<count;i++) 
		{
			controllerNames[i] = Controllers.getController(i).getName();
		}
		combobox = new JComboBox<String>(controllerNames);
		combobox.setBounds(0,0,200,30);
		controller = Controllers.getController(combobox.getSelectedIndex());
		
		combobox.addItemListener(new ItemListener() {
			/** Handle item selection */
			public void itemStateChanged(ItemEvent e) {
				controller = Controllers.getController(combobox.getSelectedIndex());
				updateDisplay();
			}
		});
		scroll = new JScrollPane(panel);
		updateDisplay();
		setContentPane(scroll);
	}
	
	private synchronized void setController(Controller c)
	{
		synchronized (controller)
		{
			controller = c;
		}
	}
	
	private synchronized void updateDisplay()
	{
		
		synchronized (controller)
		{
			panel.removeAll();
			panel.setLayout(null);
			panel.add(combobox);
			combobox.setBounds(0,0,200,30);
			buttonCount = controller.getButtonCount();
			itemCount = controller.getButtonCount() + controller.getAxisCount() + 2;
			values = new JTextField[itemCount];
			names = new JTextField[itemCount];
	
			for (int i=0;i<controller.getButtonCount();i++) {
				names[i] = new JTextField();
				names[i].setEditable(false);
				names[i].setBounds(0,i*30 +30,100,30);
				names[i].setText(controller.getButtonName(i));
				panel.add(names[i]);
				values[i] = new JTextField();
				values[i].setEditable(false);
				values[i].setBounds(100,i*30+30,100,30);
				panel.add(values[i]);
			}
			for (int i=buttonCount;i<buttonCount+controller.getAxisCount();i++) {
				names[i] = new JTextField();
				names[i].setEditable(false);
				names[i].setBounds(0,i*30 +30,100,30);
				names[i].setText(controller.getAxisName(i-buttonCount));
				panel.add(names[i]);
				values[i] = new JTextField();
				values[i].setEditable(false);
				values[i].setBounds(100,i*30+30,100,30);
				panel.add(values[i]);
			}
			int i = itemCount - 2;
			names[i] = new JTextField();
			names[i].setEditable(false);
			names[i].setBounds(0,i*30+30,100,30);
			names[i].setText("POV X");
			panel.add(names[i]);
			values[i] = new JTextField();
			values[i].setEditable(false);
			values[i].setBounds(100,i*30+30,100,30);
			panel.add(values[i]);
	
			i = itemCount - 1;
			names[i] = new JTextField();
			names[i].setEditable(false);
			names[i].setBounds(0,i*30+30,100,30);
			names[i].setText("POV Y");
			panel.add(names[i]);
			values[i] = new JTextField();
			values[i].setEditable(false);
			values[i].setBounds(100,i*30+30,100,30);
			panel.add(values[i]);
			panel.setPreferredSize(new Dimension(200,30*itemCount+30));
			panel.revalidate();
		}
	}

	public synchronized void updateDetails() {
		synchronized (controller)
		{
			for (int i=0;i<controller.getButtonCount();i++) {
				values[i].setText(""+controller.isButtonPressed(i));
			}
			for (int i=buttonCount;i<buttonCount+controller.getAxisCount();i++) {
				values[i].setText(""+controller.getAxisValue(i-buttonCount));
			}
	
			values[itemCount-2].setText(""+controller.getPovX());
			values[itemCount-1].setText(""+controller.getPovY());
		}
	}

	public static void main(String[] argv) 
	{
		TestControllers window = new TestControllers();
		window.setSize(220,400);
		window.setTitle("Controller Setup");
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		boolean running = true;
		while (running) {
			try { Thread.sleep(100); } catch (Exception e) {};

			Controllers.poll();

			window.updateDetails();
		}
	}
}
