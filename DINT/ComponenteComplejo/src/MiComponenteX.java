import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

class Hilo extends Thread
{
	private MiComponenteX boton;

	public Hilo(MiComponenteX boton) {
		this.boton = boton;
	}

	@Override
	public void run() {
		for (int i=0;i<boton.getSegundosHoverAlt();i++)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				break;
			}
			if (i==boton.getSegundosHoverAlt()-1 && boton.isEnabled())
			{
				boton.setBackground(boton.getColorHoverAlt());
			}
		}
	}
}

public class  MiComponenteX extends JButton
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color colorFondo;
	private Color colorHover;
	private Color colorDesactivado;
	private int maxClicks;
	private int numClicks;
	private Color colorHoverAlt;
	private Hilo hilo;
	private int segundosHoverAlt;

	public MiComponenteX() {
		super();
		maxClicks=5;
		numClicks=0;
		colorFondo=new Color(0,0,0);
		colorHover=new Color(255,255,255);
		colorDesactivado=new Color(255,0,255);
		colorHoverAlt=new Color(36,64,45);
		segundosHoverAlt=5;
		this.setBackground(colorFondo);
		MiComponenteX boton=this;
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (boton.isEnabled())
				{
					hilo=new Hilo(boton);
					hilo.start();
					boton.setBackground(colorHover);
				}
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hilo.interrupt();
				if (boton.isEnabled())
				{
					boton.setBackground(colorFondo);
				}
			}
		});
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				numClicks++;
				if (numClicks==maxClicks)
				{
					boton.setEnabled(false);
					boton.setBackground(colorDesactivado);
				}
			}
		});
	}

	public Color getColorFondo() {
		return colorFondo;
	}

	public void setColorFondo(Color colorFondo) {
		this.colorFondo = colorFondo;
	}
	
	public void setColorFondo(int r,int g,int b) {
		this.colorFondo = new Color(r,g,b);
	}

	public Color getColorHover() {
		return colorHover;
	}

	public void setColorHover(Color colorHover) {
		this.colorHover = colorHover;
	}

	public void setColorHover(int r,int g,int b) {
		this.colorHover = new Color(r,g,b);
	}
	
	public Color getColorDesactivado() {
		return colorDesactivado;
	}

	public void setColorDesactivado(Color colorDesactivado) {
		this.colorDesactivado = colorDesactivado;
	}
	
	public void setColorDesactivado(int r,int g,int b) {
		this.colorDesactivado = new Color(r,g,b);
	}

	public int getMaxClicks() {
		return maxClicks;
	}

	public void setMaxClicks(int maxClicks) {
		this.maxClicks = maxClicks;
	}

	public int getNumClicks() {
		return numClicks;
	}
	
	public void reiniciarClicks()
	{
		this.numClicks=0;
		this.setEnabled(true);
	}

	public Color getColorHoverAlt() {
		return colorHoverAlt;
	}

	public void setColorHoverAlt(Color colorHoverAlt) {
		this.colorHoverAlt = colorHoverAlt;
	}

	public int getSegundosHoverAlt() {
		return segundosHoverAlt;
	}

	public void setSegundosHoverAlt(int segundosHoverAlt) {
		this.segundosHoverAlt = segundosHoverAlt;
	}
}