object Calculator {

  def main(args: Array[String]): Unit = {

  def parse = java.lang.Double.parseDouble _
  def evaluate(expr: String): Double  = {

  if(expr == "")
    {
      return (0)
    }
    else
  return  expr.split(' ').toList.foldLeft(List[Double]())(
    (list, token) => (list, token) match {
      case (x :: y :: zs, "*") => (y * x) :: zs
      case (x :: y :: zs, "+") => (y + x) :: zs
      case (x :: y :: zs, "-") => (y - x) :: zs
      case (x :: y :: zs, "/") => (y / x) :: zs
      case (_, _) => parse(token) :: list
    }).head
  }

  evaluate("4 2 * 8 + 2 /")
  }
}
