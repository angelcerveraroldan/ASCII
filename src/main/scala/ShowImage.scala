import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

import ImageAscii._

object ShowImage extends App {
  val imageName: String = "eagle.jpeg"
  val projectPath: String = new java.io.File(".").getCanonicalPath

  val originalImage: BufferedImage = ImageIO.read(new File(s"$projectPath/src/images/$imageName"))
  val sizedImage: BufferedImage = prepareImage("newImg", originalImage, 300)
  ImageIO.write(sizedImage, "jpeg", new File(s"src/images/newImage.jpeg"))


  printImage(0, 0, sizedImage)
}

