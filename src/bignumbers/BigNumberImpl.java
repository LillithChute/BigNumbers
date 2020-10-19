package bignumbers;

/**
 * This will build a linked list of single digit numbers that will be used to
 * represent a single very large number.
 */
public class BigNumberImpl implements BigNumber {
  private ListOfNumbers number;

  /**
   * This constructor takes no arguments and creates a list with the number zero.
   */
  public BigNumberImpl() {
    this.number = new ElementNode(0);
  }

  /**
   * Create a BigNumber instance by inputting a IListOfNumber instance,
   * which is the internal implementation of BigNumber.
   *
   * @param number the IListOfNumber instance
   */
  public BigNumberImpl(ListOfNumbers number) {
    if (number == null) {
      throw new IllegalArgumentException("Invalid number.");
    }

    this.number = number;
  }

  /**
   * This will take in a single digit and add it to tje list of numbers in the list.
   *
   * @param digit A single digit to be added to the list
   */
  public BigNumberImpl(String digit) {
    this.number = new ElementNode(digit);
  }

  @Override
  public int count() {
    return this.number.count();
  }

  @Override
  public void leftShift(int numberOfShifts) {
    this.number = this.number.leftShift(numberOfShifts);
  }

  @Override
  public void rightShift(int numberOfShifts) {
    this.number = this.number.rightShift(numberOfShifts);
  }

  @Override
  public void addDigit(int singleDigit) throws IllegalArgumentException {
    this.number = this.number.addDigit(singleDigit);
  }

  @Override
  public int getDigitAt(int positionOfDigit) throws IllegalArgumentException {
    return this.number.getDigitAt(positionOfDigit);
  }

  @Override
  public BigNumber copy() {
    return new BigNumberImpl(this.number.copy());
  }

  @Override
  public BigNumber add(BigNumber numberToAdd) {
    ListOfNumbers right = new ElementNode(numberToAdd.toString());
    return new BigNumberImpl(this.number.add(right));
  }

  @Override
  public String toString() {
    return this.number.toString();
  }

}
