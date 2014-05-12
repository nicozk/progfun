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
    def isEdge(c: Int, r: Int): Boolean ={
      if (c==0) true else if (c==r) true else false
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

    def innerBalance(chars: List[Char], openFlag: Boolean): Boolean = {
      //      println("---------")
      //      println("chars " + chars)
      //      println("openFlag " + openFlag)

      if (chars.isEmpty) {
        println("chars.isEmpty")
        true
      } else {
        if (chars.head == '(')
          innerBalance(chars.tail, true)
        else {
          if (chars.head == ')')
            if (openFlag)
              innerBalance(chars.tail, false)
            else false
          else
            innerBalance(chars.tail, openFlag)
        }
      }
    }
   
    innerBalance(chars,false)
    
    
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = 0
}
