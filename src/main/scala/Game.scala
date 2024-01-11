import scala.swing.{Color, Graphics2D, Panel}
import scala.swing.event.UIElementResized
import scala.util.Random

class Game extends Panel{

  var simulator: Simulator = null

  def initializeBalls(numberOfBalls: Int, color: Color): IndexedSeq[Ball] =
    val rand = new Random()
    for _ <- 1 to numberOfBalls
        radius = 20
        x = rand.nextInt(size.width - radius * 2) + radius
        y = rand.nextInt(size.height - radius * 2) + radius
        speed = rand.nextInt(1)+1
        angleInDegree = rand.nextInt(360)
    yield new Ball(Vector2D(x, y), Vector2D.computeSpeed(speed, angleInDegree), radius, color)
  //simulator.list = simulator.list.appended(ball)

  reactions += {
    case _: UIElementResized =>
      simulator.rightEdge = size.width
      simulator.bottomEdge = size.height
  }

  def start(numberOfBalls: Int, color: Color) = {
    val balls = initializeBalls(numberOfBalls, color).toList
    simulator = new Simulator(balls, size.width, size.height, 0, 0, this)
    simulator.simulatorLoop()
  }


  override def paintComponent(g: Graphics2D): Unit = {
    super.paintComponent(g)
    if (simulator != null) {
      simulator.list.foreach(ball => ball.draw(g))
    }
  }


}




