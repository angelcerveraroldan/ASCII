import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import java.awt.{Graphics2D, RenderingHints}

// TURN IMAGE TO ASCII
object ImageAscii {
  def prepareImage(newImageName: String, image: BufferedImage, desiredWidth: Int): BufferedImage = {
    val ratio: Float = desiredWidth.toFloat / image.getWidth.toFloat
    val newHeight: Int = (image.getHeight.toFloat * ratio).toInt

    // This is where the image is both resized and made into a black and white image vvv
    val sizedImage = new BufferedImage(desiredWidth, newHeight, BufferedImage.TYPE_BYTE_GRAY)
    val g: Graphics2D = sizedImage.createGraphics()
    g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
    g.drawImage(image, 0, 0, desiredWidth, newHeight, 0, 0, image.getWidth, image.getHeight, null)
    g.dispose()

    sizedImage
  }

  def printImage(x: Int, y: Int, image: BufferedImage): Any = {
    test(image)
  }

  // TODO: Improve this function (it was copy pasted from java)
  // Print the RGB value of each pixel in the image
  def test(image: BufferedImage): Unit = {
    var y = 0
    while ( {
      y < image.getHeight
    }) {
      var x = 0
      while ( {
        x < image.getWidth
      }) {
        val pixel = image.getRGB(x, y)
        val alpha = (pixel >> 24) & 0xFF
        val red = (pixel >> 16) & 0xFF
        val green = (pixel >> 8) & 0xFF
        val blue = pixel & 0xFF

        println(s"(R,G,B) -> ($red, $green, $blue)")

        x += 1
      }

      y += 1
    }
  }
}