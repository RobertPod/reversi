package robert.reversi_v2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import robert.reversi_v2.api.GamePad;
import robert.reversi_v2.api.VirtualGamePad;
import robert.reversi_v2.impl.VirtualGamePadImpl;

/**
 * Głowne (i jedyne) okno aplikacji
 * 
 * @author Robert
 * 
 */
class GamePadWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3381211325245699445L;
	VirtualGamePad virtualGamePad = new VirtualGamePadImpl();
	JLabel redCount = new JLabel("0", SwingConstants.LEFT);
	JLabel blackCount = new JLabel("0", SwingConstants.RIGHT);
	GamePad gamePad = new GamePadImpl(virtualGamePad, redCount, blackCount);
	JButton endButton = new JButton("Wyjście");
	JButton clearButton = new JButton("Czyść");

	public GamePadWindow() {

		super("Reversi v.2");

		JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		northPanel.add((Component) gamePad);
		JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		southPanel.add(redCount);
		southPanel.add(endButton);
		southPanel.add(clearButton);
		southPanel.add(blackCount);

		endButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				virtualGamePad.clearGamePad();
				repaint();
			}
		});

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// Dimension buttonHeight = endButton.getSize(null); rozmiar dostępny
		// dopiero po narysowaniu
		int width = Math.max((virtualGamePad.getSizeTable() + 0) * virtualGamePad.getSizeCell()
				+ (virtualGamePad.getSizeTable() + 1) + 16, 200);
		setSize(width, ((virtualGamePad.getSizeTable() + 0) * virtualGamePad.getSizeCell()
				+ (virtualGamePad.getSizeTable() + 1) + 75 /* na przyciski */ ));
		setResizable(false);
		setBackground(Color.WHITE);
		redCount.setForeground(Color.RED);
		blackCount.setForeground(Color.BLACK);

		setLayout(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
	}
}