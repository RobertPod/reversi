package robert.reversi_v2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.Serializable;
import java.util.TooManyListenersException;

import javax.swing.JLabel;
import javax.swing.JPanel;

import robert.reversi_v2.api.GamePad;
import robert.reversi_v2.api.VirtualGamePad;
import robert.reversi_v2.domain.CellCollor;

public class GamePadImpl extends JPanel implements GamePad, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VirtualGamePad virtualGamePad;
	private JLabel redCountLabel;
	private JLabel blackCountLabel;
	// inna koncepcja obsługi zdarzeń, patrz: 'public void notifyListeners()'
	// private ArrayList<ActionListener> actionListeners = new
	// ArrayList<ActionListener>();
	private ActionListener actionListener;

	public GamePadImpl(VirtualGamePad virtualGamePad, JLabel redCount, JLabel blackCount) {
		this.virtualGamePad = virtualGamePad;
		this.redCountLabel = redCount;
		this.blackCountLabel = blackCount;
		addMouseListener(new ML());
		addMouseMotionListener(new MML());
		paintGamePad();
	}

	public VirtualGamePad getVirtualGamePad() {
		return virtualGamePad;
	}

	public void paintGamePad() {

		Dimension dim = new Dimension(
				virtualGamePad.getSizeTable() * virtualGamePad.getSizeCell() + virtualGamePad.getSizeTable(),
				virtualGamePad.getSizeTable() * virtualGamePad.getSizeCell() + virtualGamePad.getSizeTable());
		setPreferredSize(dim);
	}

	public void setField(int x, int y, CellCollor cellColor) {
		// TODO Auto-generated method stub

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int maxWidth = getWidth();
		int maxHeight = getHeight();
		g.setColor(Color.RED);
		g.drawLine(1, 1, 1, maxHeight - 1);
		g.drawLine(1, maxHeight - 1, maxWidth - 1, maxHeight - 1);
		g.drawLine(1, 1, maxWidth - 1, 1);
		g.drawLine(maxWidth - 1, 1, maxWidth - 1, maxHeight - 1);
		for (int i = 1; i < virtualGamePad.getSizeTable(); i++) {
			g.drawLine(1, i * (virtualGamePad.getSizeCell() + 1), maxWidth - 1, i * (virtualGamePad.getSizeCell() + 1));
			g.drawLine(i * (virtualGamePad.getSizeCell() + 1), 1, i * (virtualGamePad.getSizeCell() + 1),
					maxHeight - 1);
		}
		for (int i = 0; i < virtualGamePad.getSizeTable(); ++i)
			for (int j = 0; j < virtualGamePad.getSizeTable(); ++j) {
				switch (virtualGamePad.getCell(i, j)) {
				case RED:
					g.setColor(Color.RED);
					break;
				case BLACK:
					g.setColor(Color.BLACK);
					break;
				default:
					g.setColor(getBackground());
				}
				int x = (i) * (virtualGamePad.getSizeCell() + 1) + (virtualGamePad.getSizeDiameter() / 2);
				int y = (j) * (virtualGamePad.getSizeCell() + 1) + (virtualGamePad.getSizeDiameter() / 2);
				int r = virtualGamePad.getSizeCell() - virtualGamePad.getSizeDiameter();
				g.fillOval(x, y, r, r);
			}
		int i1 = virtualGamePad.amountPawn(CellCollor.RED);
		redCountLabel.setText(String.valueOf(i1));
		i1 = virtualGamePad.amountPawn(CellCollor.BLACK);
		blackCountLabel.setText(String.valueOf(i1));
	}

	// This is a multicast listener, which is more typically
	// used than the unicast approach taken in BangBean.java:
	public synchronized void addActionListener(ActionListener l) throws TooManyListenersException {
		if (actionListener != null)
			throw new TooManyListenersException();
		actionListener = l;
	}

	public synchronized void removeActionListener(ActionListener l) {
		actionListener = null;
	}

	// Notice this isn't synchronized:
	// Po co to jest?
	// inna koncepcja obsługi zdarzeń, patrz: deklaracja
	/*
	 * public void notifyListeners() { ActionEvent a = new
	 * ActionEvent(GamePadImpl.this, ActionEvent.ACTION_PERFORMED, null);
	 * ArrayList<ActionListener> lv = null; // Make a shallow copy of the List
	 * in case // someone adds a listener while we're // calling listeners:
	 * synchronized (this) { lv = new
	 * ArrayList<ActionListener>(actionListeners); } // Call all the listener
	 * methods: for (ActionListener al : lv) al.actionPerformed(a); }
	 */

	class ML extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			int xm = e.getX();
			int ym = e.getY();
			int clickCount = e.getClickCount();
			int button = e.getButton();
			int x = 1;
			int y = 1;

			if (clickCount == 1) {
				for (int i = 1; i <= virtualGamePad.getSizeTable(); ++i) {
					if (xm < i * (virtualGamePad.getSizeCell() + 1)) {
						x = i - 1;
						break;
					}
				}
				for (int i = 1; i <= virtualGamePad.getSizeTable(); ++i) {
					if (ym < i * (virtualGamePad.getSizeCell() + 1)) {
						y = i - 1;
						break;
					}
				}
				if (virtualGamePad.getCell(x, y) != CellCollor.WHITE)
					virtualGamePad.setCell(x, y, CellCollor.WHITE);
				else if (button == 1)
					virtualGamePad.setCell(x, y, CellCollor.RED);
				else
					virtualGamePad.setCell(x, y, CellCollor.BLACK);
			}
			// notifyListeners();
			repaint();

		}
	}

	class MML extends MouseMotionAdapter {
		public void mouseMoved(MouseEvent e) {
			// notifyListeners();
		}
	}

}
