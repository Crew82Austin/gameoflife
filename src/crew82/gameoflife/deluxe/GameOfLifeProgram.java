package crew82.gameoflife.deluxe;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class GameOfLifeProgram
{
	public static void main(String[] args) throws Exception
	{
		int rows = 30;
		int columns = 70;
		if (args.length == 2)
		{
			rows = Integer.parseInt(args[0]); 
			columns = Integer.parseInt(args[1]);
		}
		boolean[][] boardCells = new boolean[rows][columns];
		GameOfLifeBoard board = new GameOfLifeBoard(boardCells);

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		edit(board, input, System.out);

		runGame(board, input, System.out);
	}

	public static void runGame(GameOfLifeBoard board, BufferedReader input, PrintStream output) throws Exception
	{
		do
		{
			board.print(output);
			board.update();
			output.println("Hit <Enter> to continue to next turn");
		}
		while(input.readLine() != null);
	}

	public static void edit(GameOfLifeBoard board, BufferedReader input, PrintStream out) throws Exception
	{
		out.println("Enter a command, or hit <enter> to start the game of life.");
		out.println("Example commands are:");
		out.println("    on 3 0");
		out.println("             -- this command turns on the cell at row 5 column 0");
		out.println("    off 0 3");
		out.println("             -- this command turns off the cell at row 0 column 3");
		out.println("    random");
		out.println("             -- this command randomly populates the board with X's");
		out.println("    glider 3 5");
		out.println("             -- this command creates a glider with top left corder at row 3 column 5");
		out.println("    read");
		out.println("             -- read the board from the input");
		out.println("    read file.txt");
		out.println("             -- read the board from file.txt");
		out.println("    save file.txt");
		out.println("             -- save the board to file.txt");
		while(true)
		{
			System.out.print("Your command? ");
			String line = input.readLine();
			if (line.length() == 0)
			{
				return;
			}
			String[] tokens = line.split(" ");
			if (tokens.length == 0)
			{
				return;
			}
			else if (tokens[0].equals("on"))
			{
				int row = Integer.parseInt(tokens[1]);
				int col = Integer.parseInt(tokens[2]);
				board.setCell(row, col, true);
			}
			else if (tokens[0].equals("off"))
			{
				int row = Integer.parseInt(tokens[1]);
				int col = Integer.parseInt(tokens[2]);
				board.setCell(row, col, false);
			}
			else if (tokens[0].equals("random"))
			{
				board.randomizeCells();
			}
			else if (tokens[0].equalsIgnoreCase("glider"))
			{
				int row = Integer.parseInt(tokens[1]);
				int col = Integer.parseInt(tokens[2]);
				createGlider(board, row, col);
			}
			else if (tokens[0].equalsIgnoreCase("read"))
			{
				if (tokens.length == 1)
				{
					board.readCells(input);
				}
				else
				{
					board.readCells(new BufferedReader(new FileReader(tokens[1])));
				}
			}
			else if (tokens[0].equalsIgnoreCase("save"))
			{
				PrintStream saveTo = new PrintStream(new FileOutputStream(tokens[1]));
				board.print(saveTo);
			}
			else
			{
				out.println("Could not parse command: \"" + line + "\"");
			}
		}
	}

	public static void createGlider(GameOfLifeBoard board, int row, int col)
	{
		board.setCell(row + 0, col + 1, true);
		board.setCell(row + 1, col + 2, true);
		board.setCell(row + 2, col + 0, true);
		board.setCell(row + 2, col + 1, true);
		board.setCell(row + 2, col + 2, true);
	}
}