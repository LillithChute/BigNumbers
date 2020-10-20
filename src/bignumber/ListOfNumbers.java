package bignumber;

/**
 * An internal implementation of linked list operations for BigNumber.
 */
public interface ListOfNumbers extends Comparable<ListOfNumbers> {
  /**
   * This returns the number of digits in this number.
   *
   * @return The number of digits.
   */
  int length();

  /**
   * The number of shifts as an argument and shifts this number to the left by that number.
   * A negative number of left-shifts will correspond to those many right shifts.
   *
   * @param numberOfShifts The number of places that the BigNumber will move.
   */
  ListOfNumbers shiftLeft(int numberOfShifts);

  /**
   * the number of shifts as an argument and shifts this number to the right by that number.
   * The number 0 can be right-shifted any positive number of times, yielding the same number 0.
   * A negative number of right-shifts will correspond to those many left shifts.
   *
   * @param numberOfShifts The number of places that the BigNumber will move.
   */
  ListOfNumbers shiftRight(int numberOfShifts);

  /**
   * takes a single digit as an argument and adds it to this number.
   * This method throws an IllegalArgumentException if the digit passed to it is not a
   * single non-negative digit.
   *
   * @param singleDigit The single digit to add to the BigNumber.
   * @throws IllegalArgumentException if the digit passed to it is not a single
   *     non-negative digit.
   */
  ListOfNumbers addDigit(int singleDigit) throws IllegalArgumentException;

  /**
   * This will retrieve the digit that is carried in add operations.
   *
   * @return The carry digit.
   */
  int getCarryDigit();

  /**
   * Takes a position as an argument and returns the digit at that position.
   * Positions start at 0 (rightmost digit).
   *
   * @param positionOfDigit The position of the digit to be returned.
   * @return The digit at the position requested.
   * @throws IllegalArgumentException If an invalid position is passed.
   */
  int getDigitAt(int positionOfDigit) throws IllegalArgumentException;

  /**
   * Gets the value of the current node.
   *
   * @return The number.
   */
  int getValue();

  /**
   * Gets the next node.
   *
   * @return The next node.
   */
  ListOfNumbers getNextNode();

  ListOfNumbers copy();

  /**
   * This takes another BigNumber and returns the sum of these two numbers.
   * This operation does not change either number.
   *
   * @param numberToAdd A BigNumber that will be added to this number.
   * @return An independent BigNumber.
   */
  ListOfNumbers add(ListOfNumbers numberToAdd);

  /**
   * Set the next node from this one.
   *
   * @param next the next {@link ListOfNumbers} node
   * @throws IllegalArgumentException if the next node is null
   */
  void setNextNode(ListOfNumbers next);

  /**
   * Sets the value of the current node.
   *
   * @param value the new value
   * @throws IllegalArgumentException if the valid is not a single digit and non-negative
   */
  void setNodeValue(int value);

  /**
   * Set the carry of the current node, which helps number addition.
   *
   * @param value the carry value
   */
  void setCarry(int value);
}
