import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import java.awt.{Color, Graphics2D, Image, RenderingHints}


// TURN IMAGE TO ASCII
class ImageAscii(image: BufferedImage, desiredWidth: Int) {

  def makeBlackAndWhiteImg(blackAndWhiteImageName: String): BufferedImage = {
    def resize(image: BufferedImage, newWidth: Int): BufferedImage = {
      val ratio: Float = newWidth.toFloat / image.getWidth.toFloat
      val newHeight: Int = (image.getHeight.toFloat * ratio).toInt

      // Get type will find out what type the image is
      val dimg = new BufferedImage(newWidth, newHeight, image.getType)
      val g: Graphics2D = dimg.createGraphics()
      g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
        RenderingHints.VALUE_INTERPOLATION_BILINEAR)
      g.drawImage(image, 0, 0, newWidth, newHeight, 0, 0, image.getWidth, image.getHeight, null)
      g.dispose()

      dimg
    }

    val sizedImage: BufferedImage = if (desiredWidth != image.getWidth) {
      resize(image, desiredWidth)
    } else image

    val blackAndWhiteImage: BufferedImage = new BufferedImage(sizedImage.getWidth, sizedImage.getHeight, BufferedImage.TYPE_BYTE_GRAY)
    blackAndWhiteImage.createGraphics.drawImage(sizedImage, 0, 0, Color.WHITE, null)

    // TODO: Remove this when tests are implemented
    ImageIO.write(blackAndWhiteImage, "jpeg", new File(s"src/images/$blackAndWhiteImageName.jpeg"))

    blackAndWhiteImage
  }


}