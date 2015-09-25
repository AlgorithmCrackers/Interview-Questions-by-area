/*
 * 
 * Please implement a FIFO queue which passes
 * the test below. Your solution will be judged
 * based on:
 * 
 * - Whether it passes the tests
 * - Memory usage
 * - Dynamic resizing (please do not just set the 
 *   initial size to 10,000)
 * - Performance & complexity of add() and remove()
 * - Your time spent - I expect 30-45 minutes for 
 *   this problem
 *
 * Instructions:
 * 1. Press the Run button in the upper left 
 *    corner of the screen
 * 2. Confirm that the test says "FAIL"
 * 3. Implement the "add" and "remove" functions
 *    below
 * 4. Run, tweak, run, tweak, until it works :)
 *
 * NOTE: You may not import any classes!
 */

// No imports allowed!

class Node {
  int val;
  Node next;
  
  public Node(int item) {
    val = item;
    next = null;
  }
}
/**
 * A first-in-first-out queue.
 */
class Queue {
  int n;
  Node first;
  Node last;
  public Queue() {
    n = 0;
    first = null;
    last = null;
  }
  /**
   * Adds the given value to the end of the queue.
   */
  public void add(int value) {
    Node node = new Node(value);
    if(first == null) {
      // item added for the first time
      first = last = node;
    } else {
      last.next = node;
      last = node;
    }
    n++;
  }
  
  /**
   * Removes and returns the least recently added 
   * value from this queue.
   */
  public int remove() {
    if(n == 0) {
      System.out.println("queue is empty");
      return -1;
    }
    n--;
    int item = first.val;
    first = first.next;
    return item;
  }
};

// ======== DO NOT MODIFY BELOW THIS LINE =======

class Solution {
  static int removed = 0;
  static int wrong = 0;
  
  static void printf(String s, Object... args) {
    System.out.printf(s, args);
  }
  
  static void removeAndPrint(Queue q) {
    int value = q.remove();
    if (value != removed)
      wrong++;
    removed++;
    printf("%02d ", value);
    if (removed % 20 == 0)
      printf("\n");
  }
  
  static boolean runTest(Queue q, int testNumber) {
    int valuesToTest = 100;
    removed = 0;
    wrong = 0;
    printf("Adding numbers 0 through %d while " +
           "occasionally removing,\n" +
           "then removing the rest...\n", 
           valuesToTest-1);
    printf("\n");
    for (int i = 0; i < valuesToTest; i++) {
      q.add(i);
      if (i % 3 == 0)
        removeAndPrint(q);
    }
    while (removed < valuesToTest)
      removeAndPrint(q);
    printf("\n");
    if (wrong > 0)
      printf("TEST #%d FAIL: %d of %d values returned out of order\n", 
             testNumber, wrong, valuesToTest);
    else
      printf("TEST #%d SUCCESS!\n", testNumber);
    return wrong == 0;
  }
  
  public static void main(String[] args) {
    Queue q = new Queue();
    int passed = 0;

    printf("\n");
    printf("Running test on empty queue...\n");
    printf("\n");
    if (runTest(q, 1))
      passed++;

    int bigTest = 10000;
    printf("\n");
    printf("Adding %d values to queue, then removing...\n",
           bigTest);
    for (int i = 0; i < bigTest; i++)
      q.add(1000000+i);
    for (int i = 0; i < bigTest; i++)
      q.remove();

    printf("\n");
    printf("Running test on empty queue again...\n");
    printf("\n");
    if (runTest(q, 2))
      passed++;

    if (passed == 2)
      printf("\n*** YOUR CODE PASSED ALL THE TESTS!! ***\n\n" +
             "Now just clean up your code and send me an email :)\n");
  }
}

