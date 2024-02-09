package byog.Core.Operator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Created by IntelliJ IDEA. User: Sergey Karakovskiy,
 * sergey.karakovskiy@gmail.com Date: Oct 6, 2010 Time: 10:27:47 PM Package:
 * ch.idsia.tools
 */
public class Scale2x {
    private int width;
    private int height;
    private BufferedImage sourceImage;
    private int[] sourcePixels;
    private Graphics sourceGraphics;
    private BufferedImage targetImage;
    private int[] targetPixels;

    /**
     * Creates a new Scale2x object. The new object will scale images of the
     * specified size to images that are twice as large.<br>
     *
     * @param width
     *            The width of the images to be scaled
     * @param height
     *            The height of the images to be scaled
     */
    public Scale2x(int width, int height) {
        this.width = width;
        this.height = height;

        // A border of one pixel in each direction, and one down, to avoid if
        // statements in the scale loop
        sourceImage = new BufferedImage(width + 2, height + 3,
                BufferedImage.TYPE_INT_RGB);
        DataBufferInt sourceDataBuffer = (DataBufferInt) sourceImage.getRaster().getDataBuffer();
        sourcePixels = sourceDataBuffer.getData();
        sourceGraphics = sourceImage.getGraphics();

        targetImage = new BufferedImage(width * 2, height * 2,
                BufferedImage.TYPE_INT_RGB);
        DataBufferInt targetDataBuffer = (DataBufferInt) targetImage.getRaster().getDataBuffer();
        targetPixels = targetDataBuffer.getData();
    }

    /**
     * Scales an image and returns a twice as large image.<br>
     * This assumes the input image is of the dimensions specified in the
     * Scale2x constructor.<br>
     * The returned image is a reference to the internal scale target in this
     * Scale2x, so it will get changed if you call this method again, so don't
     * hold on to it for too long.<br>
     * In other words:<br>
     * <code>Image i0 = scale2x.scale(image0);<br>
     * Image i1 = scale2x.scale(image1);<br>
     * if (i0 == i1) System.exit(0); // Will always terminate</code><br>
     *
     * @param img
     *            The image to be scaled
     * @returns A scaled image. If you want that image to survive the next call
     *          to this method, make a copy of it.
     */
    public Image scale(Image img) {
        // Offset the image by one pixel so there's a border around it.
        // This lets us avoid having to check that A-I are in range of the image
        // before samping them
        sourceGraphics.drawImage(img, 1, 1, null);

        int line = width + 2;
        for (int y = 0; y < height; y++) {
            // Two lines of target pixel pointers
            int tp0 = y * width * 4 - 1;
            int tp1 = tp0 + width * 2;

            // Three lines of source pixel pointers
            int sp0 = (y) * line;
            int sp1 = (y + 1) * line;
            int sp2 = (y + 2) * line;

            // Fill the initial A-I values
            int B = sourcePixels[++sp0];
            int C = sourcePixels[++sp0];
            int D = sourcePixels[sp1];
            int E = sourcePixels[++sp1];
            int F = sourcePixels[++sp1];
            int H = sourcePixels[++sp2];
            int I = sourcePixels[++sp2];

            for (int x = 0; x < width; x++) {
                if (B != H && D != F) {
                    targetPixels[++tp0] = D == B ? D : E;
                    targetPixels[++tp0] = B == F ? F : E;
                    targetPixels[++tp1] = D == H ? D : E;
                    targetPixels[++tp1] = H == F ? F : E;
                } else {
                    targetPixels[++tp0] = E;
                    targetPixels[++tp0] = E;
                    targetPixels[++tp1] = E;
                    targetPixels[++tp1] = E;
                }

                // Scroll A-I left
                B = C;
                D = E;
                E = F;
                H = I;

                // Resample rightmost edge
                C = sourcePixels[++sp0];
                F = sourcePixels[++sp1];
                I = sourcePixels[++sp2];
            }
        }
        return targetImage;
    }
}
