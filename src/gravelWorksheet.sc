
  val productions: scala.collection.mutable.Map[(Double, Double), Double]= scala.collection.mutable.Map()

  def production(f:Tuple2[Double, Double], w:Double):Unit = {
    productions += (f->w)
  }

  def order(f:Tuple2[Double, Double], w:Double):scala.collection.mutable.Map[(Double, Double), Double] = {
    val returns: scala.collection.mutable.Map[(Double, Double), Double]= scala.collection.mutable.Map()

    productions foreach(size =>
      if(size._1._1>=f._1) {
        if (size._1._2 < f._2) {
          if (size._2 >= w) {
            returns += size
            productions(size._1) -= w
            if(productions(size._1)<=0){
              productions -= size._1
            }
          }
        }
      }
    )
    return returns
  }



production((0.1, 0.2), 1)
production((0.2, 0.4), 2)

  print(productions.toString())

println(order((0, 0.4), 1).toString())
println(order((0.1, 0.2), 1).toString())

  print(productions.toString())
