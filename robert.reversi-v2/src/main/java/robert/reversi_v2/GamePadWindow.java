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
	JLabel statementLab = new JLabel("Twoje są zawsze czerwone chcesz zacząć, wykonaj ruch", SwingConstants.CENTER);
	JButton startComputerButton = new JButton("Zaczyna komputer");
	GamePad gamePad = new GamePadImpl(virtualGamePad, redCount, blackCount, statementLab, startComputerButton);
	JButton endButton = new JButton("Wyjście");
	JButton clearButton = new JButton("Nowa gra");

	public GamePadWindow() {

		super("Reversi v.2 2.0.5. Improving the heuristic algorithm");

		JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		northPanel.add((Component) gamePad);
		JPanel middlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		middlePanel.add(statementLab);
		JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		southPanel.add(redCount);
		southPanel.add(endButton);
		southPanel.add(startComputerButton);
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
				((GamePadImpl) gamePad).initialState();
				repaint();
			}
		});

		startComputerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((VirtualGamePadImpl) virtualGamePad).makeBestConputerMove();
				((GamePadImpl) gamePad).computerMadeFirstMove();
				repaint();
			}
		});

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// Dimension buttonHeight = endButton.getSize(null); rozmiar dostępny
		// dopiero po narysowaniu
		int width = Math.max((virtualGamePad.getSizeTable() + 0) * virtualGamePad.getSizeCell()
				+ (virtualGamePad.getSizeTable() + 1) + 16, 400);
		setSize(width, ((virtualGamePad.getSizeTable() + 0) * virtualGamePad.getSizeCell()
				+ (virtualGamePad.getSizeTable() + 1) + 75 /* na przyciski */ + 25));
		setResizable(false);
		setBackground(Color.WHITE);
		redCount.setForeground(Color.RED);
		blackCount.setForeground(Color.BLACK);
		statementLab.setForeground(Color.RED);

		setLayout(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
		add(middlePanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);
	}
}