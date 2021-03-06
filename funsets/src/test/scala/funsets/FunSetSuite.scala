package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val s4 = singletonSet(4)
    val s5 = singletonSet(5)
    val s1000 = singletonSet(1000)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }

  test("intersect contains all elements that are in s and t") {
    new TestSets {
      val s = intersect(s1, union(s1, s2))
      assert(contains(s, 1), "elem 1")
      assert(!contains(s, 2), "elem 2")
      assert(!contains(s, 3), "elem 3")
    }
  }

  test("diff contains elements that are of `s` that are not in `t`") {
    new TestSets {
      val s = diff(union(s1, s2), s1)
      assert(contains(s, 2), "elem 2")
      assert(!contains(s, 1), "elem 1")
      assert(!contains(s, 3), "elem 2")
    }
  }

  test("filter") {
    new TestSets {
      val s = filter(union(s1, union(s2, s3)), (x => x > 1))
      assert(contains(s, 2), "elem 2")
      assert(contains(s, 3), "elem 3")
      assert(!contains(s, 1), "elem 1")
    }
  }

  test("forall Todo el conjunto es mayor q uno") {
    new TestSets {
      assert(forall(union(s2, s3), (x => x > 1)), "Todo el conjunto es mayor q uno")
    }
  }

  test("forall Todo el conjunto es distinto de cero") {
    new TestSets {
      assert(!forall(union(s2, s3), (x => x == 0)), "Todo el conjunto es distinto de cero")
    }
  }

  test("forall probando extremos +1000 -1000 es distinto de cero") {
    new TestSets {
      assert(!forall(union(singletonSet(-1000), singletonSet(1000)), (x => x == 0)), "Todo el conjunto es distinto de cero")
    }
  }

  test("forall solo con  +1000") {
    new TestSets {
      assert(forall(singletonSet(1000), (x => x == 1000)), "epa!")
    }
  }
  
  test("forall solo con  -1000") {
    new TestSets {
      assert(forall(singletonSet(-1000), (x => x == -1000)), "epa!")
    }
  }
  
  test("exists probando extremos +1000 -1000 0 es igual a cero") {
    new TestSets {
      assert(exists(union(singletonSet(-1000), union(singletonSet(1000), singletonSet(0))), (x => x == 0)), "Todo el conjunto es igual a cero")
    }
  }

  test("exists probando extremos +1000") {
    new TestSets {
      assert(exists(union(singletonSet(-1000), union(singletonSet(1000), singletonSet(0))), (x => x == 1000)), "Todo el conjunto es igual a cero")
    }
  }
    
  test("exists probando 1 2 es igual a cero") {
    new TestSets {
      assert(!exists(union(s1, s2), (x => x == 0)), "Todo el conjunto es igual a cero")
    }
  }

  test("map convierte un set de un elemento a incrementando en uno cada elemento") {
    new TestSets {
      val source = union(s1, s2)
      def f(x: Int): Int = x + 2
      assert(contains(map(source, f), 3), "El set deberia contener al 3")
      assert(contains(map(source, f), 4), "El set deberia contener al 4")
      assert(!contains(map(source, f), 5), "El set no debe contener al 5")
      assert(!contains(map(source, f), 2), "El set no debe contener al 2")
    }
  }

  test("map convierte {1,3,4,5,1000} a {0,2,3,4,999} ") {
    new TestSets {
      val source = union(s1, union(s3, union(s4, union(s5, s1000))))
      def f(x: Int): Int = x - 1
      assert(contains(map(source, f), 0), "El set deberia contener al 0")
      assert(contains(map(source, f), 2), "El set deberia contener al 2")
      assert(contains(map(source, f), 3), "El set deberia contener al 3")
      assert(contains(map(source, f), 4), "El set deberia contener al 4")
      assert(contains(map(source, f), 999), "El set deberia contener al 999")
      
    }
  }
  
}
