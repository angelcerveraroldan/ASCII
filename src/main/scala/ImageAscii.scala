import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import java.awt.{Graphics2D, RenderingHints}

// TURN IMAGE TO ASCII
class ImageAscii(image: BufferedImage, desiredWidth: Int) {
  val ratio: Float = desiredWidth.toFloat / image.getWidth.toFloat
  val newHeight: Int = (image.getHeight.toFloat * ratio).toInt

  def refactorImage(newImageName: String, image: BufferedImage = image): BufferedImage = {
    // This is where the image is both resized and made into a black and white image vvv
    val sizedImage = new BufferedImage(desiredWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY)
    val g: Graphics2D = sizedImage.createGraphics()
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
    g.drawImage(image, 0, 0, desiredWidth, newHeight, 0, 0, image.getWidth, image.getHeight, null)
    g.dispose()

    // TODO: Remove this when tests are implemented
    ImageIO.write(sizedImage, "jpeg", new File(s"src/images/$newImageName.jpeg"))
    
    sizedImage
  }


}