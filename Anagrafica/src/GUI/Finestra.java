package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class Finestra extends Frame {
	JPanel c;

	public Finestra(String x) {
		super(x);
		setUndecorated(true);
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setLocation(70, 70);
		setPreferredSize(Est.standard);
		setBackground(Est.sfondo);
		Header testa = new Header();
		add(testa);
		c = new JPanel();
		c.setLayout(new BorderLayout(100, 10));
		c.setBorder(Est.borCol);
		c.setOpaque(false);
		add(c);

	}
}
