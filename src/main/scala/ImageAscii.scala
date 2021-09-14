import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO
import java.awt.{Graphics2D, RenderingHints}
import scala.annotation.tailrec

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

  def printImage(image: BufferedImage): Any = {
    val width: Int = image.getWidth
    val height: Int = image.getHeight

    def convertEachPixel(): Unit = {
      println(width)
      println(height)

      column()

      @tailrec
      def row(x: Int = 0, y: Int): Unit = {
        print(convertPixel(x, y))

        if (x < width - 1) row(x + 1, y)
      }

      @tailrec
      def column(x: Int = 0, y: Int = 0): Unit = {
        row(0, y)
        print('\n')
        if (y < height - 1) column(x, y + 1)
      }
    }

    def convertPixel(x: Int, y: Int): Char = {
      val pixelInfo = image.getRGB(x, y)
      val darkness: Int = (pixelInfo >> 16) & 0xFF // <- This is the red value. R, G and B are the same, so it is also the darkness.

      // '@%#*+=-:. ' <- Darkest to lightest
      def darknessToASCIISimple(darkness: Int): Char = {
        if (darkness <= 25) ' '
        else if (darkness > 25 && darkness <= 51) '.'
        else if (darkness > 51 && darkness <= 76) ':'
        else if (darkness > 76 && darkness <= 102) '-'
        else if (darkness > 102 && darkness <= 127) '='
        else if (darkness > 127 && darkness <= 153) '+'
        else if (darkness > 153 && darkness <= 178) '*'
        else if (darkness > 178 && darkness <= 204) '#'
        else if (darkness > 204 && darkness <= 229) '%'
        else if (darkness > 229 && darkness <= 255) '@'
        else throw new Exception(s"No character found for darkness: $darkness")
      }

      //TODO: Add an ascii convertor with more than 10 digits for better image quality/accuracy

      darknessToASCIISimple(darkness)
    }

    convertEachPixel()
  }

  // TODO: Improve this function (it was copy pasted from java)
  // Print the RGB value of each pixel in the image
  //  def test(image: BufferedImage): Unit = {
  //    // WHEN THE IMAGE IS IN TYPE_BYTE_GRAY, EACH PIXEL HAS THE EXACT SAME RED, GREEN AND VALUE.
  //    var y = 0
  //    while ( {
  //      y < image.getHeight
  //    }) {
  //      var x = 0
  //      while ( {
  //        x < image.getWidth
  //      }) {
  //        val pixel = image.getRGB(x, y)
  //        val red = (pixel >> 16) & 0xFF
  //        val green = (pixel >> 8) & 0xFF
  //        val blue = pixel & 0xFF
  //
  //        println(s"(R,G,B) -> ($red, $green, $blue)")
  //
  //        x += 1
  //      }
  //
  //      y += 1
  //    }
  //  }
}