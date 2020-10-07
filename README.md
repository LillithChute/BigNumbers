# Big Numbers

When we represent a number on a computer we must allocate a fixed number of bits to it. The number of bits dictates the range of numbers that can be stored. This data limitation applies to all data types, and prevents us from storing large numbers.

One way to represent a number is in terms of its digits, similar to how `int` is stored in terms of bits, or how a `String` represents a sequence of characters. In this assignment you will represent large integral numbers in a linked list representation. Instead of storing bits, we store individual digits (0-9) in a single node of such a list. We can store them in any order (corresponding to big-endian and little-endian in bit representation). For example, the number `32411` can be stored as `3 -> 2 -> 4 -> 1 -> 1` or `1 -> 1-> 4 -> 2 -> 3`.

Given an integral number, we can shift its digits to the left or right. For example 32411 can be left-shifted to get the number 324110. Thus left-shifting by one position is equivalent to multiplying the number by 10 (similar to how left-shifting by one bit in a binary representation multiplies the number by 2). Similarly, 32411 can be right-shifted to get the number 3241, which is the integer-division by 10.

We can support basic addition of a single digit to a number. For example 324115 + 7 = 324122. Shifting and adding single digits can allow us to create arbitrarily large numbers, one digit at a time. For example 32411 can be created by:

1. Start with 0.
2. Left-shift by 1 position, and add 3
3. Left-shift by 1 position (to get 30) and add 2
4. Left-shift by 1 position (to get 320) and add 4
5. Left-shift by 1 position (to get 3240) and add 1
6. Left-shift by 1 position (to get 32410) and add 1

Numbers can also be added by using simple arithmetic: start from the right-most digits and add them. Record the sum and carry, and add the carry to the next pair of digits, and so on. Note that the numbers may be of different lengths.
