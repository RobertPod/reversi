package robert.reversi_v2;

import javax.swing.SwingUtilities;

/**
 * Gra w Reversi wersja standolone - interejs jak interfejs ale mechanizm
 * logicznej obsługi ma być doelowy dla wersji standalone, sieciowej,
 * przeglądarkowej i mobilnej
 * 
 * @author Robert
 * @category Game
 * @version 2.0 {@code}
 */
public class ReversiV2 {

	public static void main(String[] args) throws Exception {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GamePadWindow gamePad = new GamePadWindow();
				gamePad.setVisible(true);
			}
		});
	}
}