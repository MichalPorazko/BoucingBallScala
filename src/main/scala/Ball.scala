import scala.swing.{Color, Graphics2D}

class Ball (var position: Vector2D, var velocity: Vector2D, val radius: Float, val color: Color) {

  def update(interval: Float): Unit =
    val s = Vector2D.multiplyScalar(this.velocity, interval)
    this.position = Vector2D.addVector(this.position, s)
    
  def mass(): Float =
    this.radius

  def draw(g: Graphics2D): Unit = {
    g.setColor(color)
    g.fillOval((position.x - radius).toInt, (position.y - radius).toInt, (2 * radius).toInt, (2 * radius).toInt)
  }  

}
    
