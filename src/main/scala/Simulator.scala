import scala.swing.Panel

class Simulator(var list: List[Ball], var rightEdge: Int, var bottomEdge: Int, var leftEdge: Int, var upperEdge: Int,
                game: Panel) {

  def simulatorLoop(): Unit = {
    val gameThread = new Thread(() => {
      val frameInterval = (1000L / 30)
      while (true) {
        update(frameInterval)
        game.repaint()
        try {
          Thread.sleep(frameInterval)
        } catch {
          case ex: InterruptedException => ()
        }
      }
    })
    gameThread.start()
  }


  def checkBallCollision(ball1: Ball, ball2: Ball): Option[Vector2D] =
    val normalVector = Vector2D.addVector(ball1.position, Vector2D.invert(ball2.position))
    val distance = Vector2D.length(normalVector)
    if (distance <= ball1.radius + ball2.radius)
      Some(Vector2D.normalizeVector(normalVector))
    else
      None

  def update(interval: Float): Unit =

    generatePairs(list).foreach( pair => checkBallCollision(pair._1, pair._2) match
      case Some(normal) => collisionBallResponse(pair,normal)
      case None => ()
    )
    
    list.foreach(collisionEdgeResponse)

    list.foreach(_.update(interval))

  def generatePairs(list: List[Ball]): List[(Ball, Ball)] =
    list match
      case head :: tail =>
        val r = generatePairs(tail)
        val newPairs = tail.map(b => (b,head))
        newPairs ++ r
      case _ => List()

  def collisionBallResponse(tuple: (Ball, Ball), normal: Vector2D): Unit =
    val b1 = tuple._1
    val b2 = tuple._2
    val v1Normal = Vector2D.dotProduct(b1.velocity, normal)
    val v2Normal = Vector2D.dotProduct(b2.velocity, normal)

    //lengths
    val v1NormalAfterCollsion = (2*b2.mass()*v2Normal + (b1.mass() - b2.mass()) * v1Normal) / (b1.mass() + b2.mass())
    val v2NormalAfterCollsion = (2*b1.mass()*v1Normal + (b2.mass() - b1.mass()) * v2Normal) / (b1.mass() + b2.mass())


    val v1X = Vector2D.addVector(b1.velocity, Vector2D.invert(Vector2D.multiplyScalar(normal, v1Normal)))
    val v2X = Vector2D.addVector(b2.velocity, Vector2D.invert(Vector2D.multiplyScalar(normal, v2Normal)))

    b1.velocity = Vector2D.addVector(v1X, Vector2D.multiplyScalar(normal, v1NormalAfterCollsion))
    b2.velocity = Vector2D.addVector(v2X, Vector2D.multiplyScalar(normal, v2NormalAfterCollsion))

  def collisionEdgeResponse(ball: Ball): Unit =
    if (ball.position.x - ball.radius <= leftEdge || ball.position.x + ball.radius >= rightEdge)
      ball.velocity = ball.velocity.copy(x = -ball.velocity.x)

    if (ball.position.y - ball.radius <= upperEdge || ball.position.y + ball.radius >= bottomEdge)
      ball.velocity = ball.velocity.copy(y = -ball.velocity.y)


}

object Simulator{

  def mftgpm(list: List[Int]): List[(Int, Int)] =
    list match
      case head :: tail =>
        val r = mftgpm(tail)
        val newPairs = tail.map(b => (head, b))
        newPairs ++ r
      case _ => List()
}