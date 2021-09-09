import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import java.awt.Color
import java.awt.Image


// TURN IMAGE TO ASCII
class ImageAscii(imageName: String) {
  val projectPath: String = new java.io.File(".").getCanonicalPath
  val image: BufferedImage = ImageIO.read(new File(s"$projectPath/src/images/$imageName"))


  def makeBlackAndWhiteImg(blackAndWhiteImageName: String): Boolean = {
    val blackAndWhiteImage: BufferedImage = new BufferedImage(image.getWidth, image.getHeight, BufferedImage.TYPE_BYTE_GRAY)
    blackAndWhiteImage.createGraphics.drawImage(image, 0, 0, Color.WHITE, null)

    ImageIO.write(blackAndWhiteImage, "jpg", new File(s"src/images/$blackAndWhiteImageName.jpg"))
  }

  def resizeImage(newWidth: Int = 1910): Image = {
    val ratio: Float = newWidth.toFloat / image.getWidth.toFloat
    val newHeight: Int = (image.getHeight.toFloat * ratio).toInt

    image.getScaledInstance(newWidth, newHeight, Image.SCALE_FAST)
  }

}