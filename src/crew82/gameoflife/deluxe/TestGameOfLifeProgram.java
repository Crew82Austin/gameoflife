package crew82.gameoflife.deluxe;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.Test;

public class TestGameOfLifeProgram
{
	@Test
	public void testCreateGlider00()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false},
			{false, false, false},
			{false, false, false},
		});
		GameOfLifeProgram.createGlider(life, 0, 0);

		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outBytes);
		life.print(out);
		assertEquals("+---+\n"
				+ "| X |\n"
				+ "|  X|\n"
				+ "|XXX|\n"
				+ "+---+\n",
				outBytes.toString());
	}

	@Test
	public void testCreateGlider()
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, false, false, false, false},
			{false, false, false, false, false},
			{false, false, false, false, false},
			{false, false, false, false, false},
			{false, false, false, false, false},
			{false, false, false, false, false},
		});
		GameOfLifeProgram.createGlider(life, 3, 2);

		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outBytes);
		life.print(out);
		assertEquals("+-----+\n"
				+ "|     |\n"
				+ "|     |\n"
				+ "|     |\n"
				+ "|   X |\n"
				+ "|    X|\n"
				+ "|  XXX|\n"
				+ "+-----+\n",
				outBytes.toString());
	}

	@Test
	public void testRunGame() throws Exception
	{
		GameOfLifeBoard life = new GameOfLifeBoard(new boolean[][]
		{
			{false, true, false},
			{false, true, false},
			{false, true, false},
		});
		BufferedReader in = new BufferedReader(new StringReader("\n\n"));
		ByteArrayOutputStream outBytes = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(outBytes);
		GameOfLifeProgram.runGame(life, in, out);
		assertEquals("+---+\n"
				+ "| X |\n"
				+ "| X |\n"
				+ "| X |\n"
				+ "+---+\n"
				+ "Hit <Enter> to continue to next turn\n"
				+ "+---+\n"
				+ "|   |\n"
				+ "|XXX|\n"
				+ "|   |\n"
				+ "+---+\n"
				+ "Hit <Enter> to continue to next turn\n"
				+ "+---+\n"
				+ "| X |\n"
				+ "| X |\n"
				+ "| X |\n"
				+ "+---+\n"
				+ "Hit <Enter> to continue to next turn\n",
				outBytes.toString());
	}
}
