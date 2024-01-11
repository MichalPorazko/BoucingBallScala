case class Vector2D(x: Float, y: Float)


object Vector2D {

  def addVector(first: Vector2D, second: Vector2D): Vector2D =
    Vector2D(first.x + second.x, first.y + second.y)

  def multiplyScalar(first: Vector2D, second: Float): Vector2D =
    Vector2D(first.x * second, first.y * second)

  def invert(vector2D: Vector2D): Vector2D =
    Vector2D(-vector2D.x, -vector2D.y)

  def length(vector2D: Vector2D): Float =
    math.sqrt(vector2D.x * vector2D.x + vector2D.y * vector2D.y).toFloat

  def normalizeVector(vector2D: Vector2D): Vector2D =
    val lenght = Vector2D.length(vector2D)
    Vector2D.multiplyScalar(vector2D, 1/lenght)

  def dotProduct(vector1: Vector2D, vector2: Vector2D): Float =
    vector1.x * vector2.x + vector1.y * vector2.y


  def computeSpeed(speed: Float, angleInDegree: Float): Vector2D =
    val radian = math.toRadians(angleInDegree)
    Vector2D((speed * math.cos(radian)).toFloat, (-speed * math.sin(radian)).toFloat)
    
  

}

