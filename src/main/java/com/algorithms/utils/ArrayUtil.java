package com.algorithms.utils;

public class ArrayUtil {
	private static final String UP = "UP";
	private static final String DOWN = "DOWN";
	private static final String RIGHT = "RIGHT";
	private static final String LEFT = "LEFT";
	private static int x_location = 0;
	private static int y_location = 0;
	private static int x_boundary = 0;
	private static int y_boundary = 0;
	private static int count = 0;

	public static void main(String args[]) {
		printSpiral(4, 4);
	}

	private static void printSpiral(int x, int y) {
		x_boundary = x - 1;
		y_boundary = y - 1;
		int toatal = x * y;
		while (count != toatal) {
			walk(RIGHT, toatal);
			x_location = x_location + 1;
			walk(DOWN, toatal);
			y_location = y_location - 1;
			walk(LEFT, toatal);
			x_location = x_location - 1;
			x_boundary=x_boundary-2;
			y_boundary=y_boundary-1;
			walk(UP, toatal);
			y_location = y_location + 1;
		}
	}

	private static void walk(String _command, int total) {
		boolean incerase = (_command.equals(RIGHT) || _command.equals(DOWN));
		boolean change_y = (_command.equals(RIGHT) || _command.equals(LEFT));
		boolean is_up = _command.equals(UP);
		int starting_location = change_y ? y_location : x_location;
		int ending_location = change_y ? y_boundary : x_boundary;
		int dummy = 0;
		for (int local_pointer = starting_location; isValidTermination(incerase, local_pointer, ending_location,
				is_up); dummy = incerase ? local_pointer++ : local_pointer--) {
			if (count == total) {
				break;
			} else {
				if (change_y ) {
					y_location = local_pointer;
					count = count + 1;
					System.out.print("( " + x_location + " ," + y_location + " )");
				} else {
					x_location = local_pointer;
					System.out.print("( " + x_location + " ," + y_location + " )");
					count = count + 1;
				}
			}

		}

	}

	private static boolean isValidTermination(boolean incerase, int local_pointer, int ending_location, boolean is_up) {
		return (incerase ? local_pointer <= ending_location
				: (is_up ? (local_pointer >= ending_location)
						: (local_pointer >= 0 && local_pointer <= ending_location)));
	}
}
