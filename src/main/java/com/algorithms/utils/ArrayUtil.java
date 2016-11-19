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
	private static int total=0;
	public static void main(String args[]) {
		printSpiral(1, 1);
	}

	private static void printSpiral(int x, int y) {
		x_boundary = x - 1;
		y_boundary = y - 1;
		total = x * y;
		int left_boundary = 0;
		int right_boundary = 0;
		while (count != total) {
			walk(RIGHT, y_location, y_boundary);
			if (count != total) {
				walk(DOWN, x_location + 1, x_boundary);
			}
			if (count != total) {
				walk(LEFT, y_location - 1, left_boundary);
				left_boundary = left_boundary + 1;
				right_boundary = right_boundary + 1;
			}
			if (count != total) {
				walk(UP, x_location - 1, right_boundary);
				x_boundary = x_boundary - 1;
				y_boundary = y_boundary - 1;
				y_location = y_location + 1;
			}
		}
	}

	private static void walk(String _command, int starting_location, int ending_location) {
		boolean incerase = (_command.equals(RIGHT) || _command.equals(DOWN));
		boolean change_y = (_command.equals(RIGHT) || _command.equals(LEFT));
		int dummy = 0;
		for (int local_pointer = starting_location; incerase ? local_pointer <= ending_location
				: local_pointer >= ending_location; dummy = incerase ? local_pointer++ : local_pointer--) {
			if (count == total) {
				break;
			} else {
				if (change_y) {
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
}
