package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    def isEdge(c: Int, r: Int) = {
      c == 0 || c == r
    }
    if (isEdge(c,r))
      1
      else
        pascal(c-1, r-1) + pascal(c, r-1)
  }

  /**
   * Exercise 2
   * (if (zero? x) max (/ 1 x))
   */
  def balance(chars: List[Char]): Boolean = {

    def innerBalance(chars: List[Char], open: Int): Boolean = {
      if (chars.isEmpty)
        true
      else
        chars.head match {
          case '(' => innerBalance(chars.tail, open + 1)
          case ')' => {
            if (open > 0)
              innerBalance(chars.tail, open - 1)
            else false
          }
          case _ => innerBalance(chars.tail, open)
        }
    }
   
    innerBalance(chars,0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (coins.isEmpty) 0
    else if (money < 0) 0
    else if (money == 0) 1
    else countChange(money, coins.tail) + countChange(money - coins.head, coins)
  }
  
}
