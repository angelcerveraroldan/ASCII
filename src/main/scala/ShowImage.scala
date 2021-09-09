import java.awt.Image
import scala.concurrent.Future

object ShowImage extends App {
  val image = new ImageAscii(imageName = "eagle.jpeg").makeBlackAndWhiteImg("blackAndWhite")

  val blackAndWhiteImage = new ImageAscii(imageName = "blackAndWhite").resizeImage()
}
