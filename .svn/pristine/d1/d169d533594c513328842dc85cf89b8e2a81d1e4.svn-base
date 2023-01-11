package com.iocl.ImpactAssessmentQuiz.login;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.iocl.ImpactAssessmentQuiz.model.SessionMaster;

@SessionAttributes("sessionMaster")
@Scope("session")

@RestController
public class Getcaptcha {

	@RequestMapping(value = "/get-captcha", method = RequestMethod.GET)
	public void getCaptcha(HttpServletResponse response, @SessionAttribute("sessionMaster") SessionMaster sessionMaster)
			throws IOException {

		ServletOutputStream out = response.getOutputStream();
		BufferedImage image = new BufferedImage(350, 40, BufferedImage.TYPE_BYTE_INDEXED);

		Graphics2D graphics = image.createGraphics();
		// Set back ground of the generated image to white
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, 350, 40);
		// set gradient font of text to be converted to image
		GradientPaint gradientPaint = new GradientPaint(10, 5, Color.BLACK, 20, 10, Color.BLACK, true);
		graphics.setPaint(gradientPaint);
		String fontNames[] = { "Dialog", "KaiTi", "Comic Sans MS", "AngsanaUPC", "MV Boli", "Cooper Black", "MingLiU",
				"Microsoft YaHei Light", "Consolas", "Simplified Arabic", "Vijaya", "Lucida Bright",
				"Microsoft Yi Baiti", "FangSong", "Gill Sans Ultra Bold Condensed", "Malgun Gothic", "KodchiangUPC",
				"Algerian", "Calibri", "MingLiU-ExtB", "Centaur", "Showcard Gothic", "Agency FB", "Ebrima", "Forte",
				"Gadugi", "Levenim MT", "Microsoft Tai Le", "Calibri Light", "Perpetua", "Kokila", "Dialog",
				"Segoe Script", "Gill Sans MT", "Rockwell Condensed", "Segoe UI", "Sitka Subheading", "Century Gothic",
				"Yu Gothic", "Tahoma", "Raavi", "Aldhabi", "David", "Cordia New", "Bodoni MT Poster Compressed",
				"KaiTi", "KodchiangUPC", "Utsaah", "Franklin Gothic Demi", "Informal Roman", "Serif", "Browallia New",
				"MoolBoran", "Microsoft YaHei", "Snap ITC", "Imprint MT Shadow", "Bell MT", "SansSerif", "Gulim" };
		// String[] fontNames =
		// GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		int index = (int) (Math.random() * (fontNames.length - 1));
		Font font = new Font(fontNames[index], Font.ITALIC, 35);
		graphics.setFont(font);
		String captcha = RandomStringUtils.randomAlphabetic(6).toUpperCase();
		System.out.println("Captcha :- " + captcha);
		sessionMaster.setCaptchaAnswer(captcha);
		graphics.drawString(captcha, 10, 30);
		// java.awt.Graphics.drawLine(int x1, int y1, int x2, int y2)
		// To Generate Random Lines
		int randX1 = RandomUtils.nextInt(1, 20);
		int randY1 = RandomUtils.nextInt(1, 10);
		int randX2 = RandomUtils.nextInt(130, 150);
		int randY2 = RandomUtils.nextInt(30, 40);
		graphics.drawLine(randX1, randY1, randX2, randY2);

		randX1 = RandomUtils.nextInt(1, 20);
		randY1 = RandomUtils.nextInt(30, 40);
		randX2 = RandomUtils.nextInt(130, 150);
		randY2 = RandomUtils.nextInt(1, 10);
		graphics.drawLine(randX1, randY1, randX2, randY2);

		randX1 = RandomUtils.nextInt(1, 20);
		randY1 = RandomUtils.nextInt(1, 40);
		randX2 = RandomUtils.nextInt(130, 150);
		randY2 = RandomUtils.nextInt(1, 40);
		graphics.drawLine(randX1, randY1, randX2, randY2);

		// release resources used by graphics context
		graphics.dispose();

		// encode the image as a JPEG data stream and write the same to servlet output
		// stream
		// JPEGCodec.createJPEGEncoder(out).encode(image);
		ImageIO.write((RenderedImage) image, "jpg", out);

		// close the stream
		out.close();

	}
}