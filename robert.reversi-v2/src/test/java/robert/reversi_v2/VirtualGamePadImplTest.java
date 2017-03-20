package robert.reversi_v2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import robert.reversi_v2.api.VirtualGamePad;
import robert.reversi_v2.domain.CellCollor;
import robert.reversi_v2.impl.VirtualGamePadImpl;

public class VirtualGamePadImplTest {
	static VirtualGamePad testGamePad;

	@BeforeClass
	public static void createGamePad() {
		testGamePad = new VirtualGamePadImpl();
	}

	@Before
	public void clearGamePad() {
		testGamePad.clearGamePad();
	}

	@Test
	public void testClearAndSetGamePad() {
		assertEquals("Game Pad not cleared", 4,
				testGamePad.amountPawn(CellCollor.BLACK) + testGamePad.amountPawn(CellCollor.RED));
		assertEquals("Game Pad not cleared - RED are not equals 2", 2, testGamePad.amountPawn(CellCollor.RED));
	}

}
