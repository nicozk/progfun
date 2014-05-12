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

      if (chars.isEmpty) {
        println("chars.isEmpty")
        true
      } else {
        if (chars.head == '(')
          innerBalance(chars.tail, open + 1)
        else {
          if (chars.head == ')')
            if (open > 0)
              innerBalance(chars.tail, open - 1)
            else false
          else
            innerBalance(chars.tail, open)
        }
      }
    }
   
    innerBalance(chars,0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    
    def corte (sum : Int) = {
    	(money >= sum)
    }
    
    def calc(presentValue:Int, acc:Int, allCoins: List[Int]): Int = {
      if (allCoins.isEmpty)
        acc
        else {          
           if (presentValue > money) 0
           else 
             if (presentValue==money) 
               1
               else 0
                 
                         }
    }
    
    
    def innerCountChange(remainingCoins: List[Int], acc : Int ):Int = {
		  acc + calc(remainingCoins.head, 0, coins) +  innerCountChange(remainingCoins.tail, acc)
    }
    
    //money == 0? ???
    innerCountChange(coins.sorted, 0)
  }    
    
}
