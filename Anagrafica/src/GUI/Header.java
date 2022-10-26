package GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Header extends JPanel {
	public Header(){
		super();
		setBackground(Est.chiaro);
		setLayout(new FlowLayout());
		
		MyPanel pan=new MyPanel();
		pan.setOpaque(false);
		add(pan);
		Label tz=new Label();
		tz.setText("Powered by Pucchosa.sa :) ");
		add(tz);
		
		

	}
	
}