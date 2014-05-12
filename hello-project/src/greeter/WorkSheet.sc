package greeter

import scala.collection.immutable.List

object WorkSheet {

	val list = List(1,2,4,3)                  //> list  : List[Int] = List(1, 2, 4, 3)
	list.isEmpty                              //> res0: Boolean = false
	list.max                                  //> res1: Int = 4
	list.sorted                               //> res2: List[Int] = List(1, 2, 3, 4)
	17.0%5.0                                  //> res3: Double(2.0) = 2.0

}