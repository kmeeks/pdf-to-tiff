package convertpdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PdfToTiff {
    private static final String OUTPUT_DIR = "/Users/kmacattack/fileconversion/";

    public static void main(String[] args) throws Exception{
        String filename = null;
        if(args.length > 0) {
            filename = args[0];
        }

        PDDocument document = PDDocument.load(new File(OUTPUT_DIR + String.format("%s.pdf", filename)));
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(
                    page, 300, ImageType.RGB);
            ImageIOUtil.writeImage(
                    bim, String.format("%soutput/pdf-%s-%d.%s", OUTPUT_DIR, filename, page + 1, "tiff"), 300);
        }
        document.close();

//        try (final PDDocument document = PDDocument.load(new File(OUTPUT_DIR + "conda-cheatsheet.pdf"))){
//            PDFRenderer pdfRenderer = new PDFRenderer(document);
//            for (int page = 0; page < document.getNumberOfPages(); ++page)
//            {
//                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 150, ImageType.RGB);
//                String fileName = OUTPUT_DIR + "image-" + page + ".tiff";
//                ImageIOUtil.writeImage(bim, fileName, 300);
//            }
//            document.close();
//        } catch (IOException e){
//            System.err.println("Exception while trying to create pdf document - " + e);
//        }
    }
}
