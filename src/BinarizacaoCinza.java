import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class BinarizacaoCinza {
    public static void main(String[] args) throws IOException {

        String imgPath = "src\\ImagensFontes\\imagem2.jpg";

        BufferedImage imagem = ImageIO.read(new File(imgPath));
        int w = imagem.getWidth();
        int h = imagem.getHeight();

        for(int lin = 0; lin < w; lin++){
            for (int col = 0; col < h; col++){
                int rgb = imagem.getRGB(lin, col);
                Color color = new Color(rgb);

                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                //Deixa a imagem cinza
                if((red+green+blue) < 128) {
                    imagem.setRGB(lin, col, Color.black.getRGB());
                } else {
                    imagem.setRGB(lin, col, Color.white.getRGB());
                }
            }
        }

//===============================================================================================

        // Geração de novo nome de arquivo
        String srcFolder = "src\\ImagensGeradas\\BinarizacaoCinza\\";
        File dir = new File(srcFolder);
        String baseName = "imagemNova";
        String extension = ".png";

        int count = 1;
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            if (file.getName().startsWith(baseName) && file.getName().endsWith(extension)) {
                String name = file.getName();
                String numberPart = name.substring(baseName.length(), name.length() - extension.length());
                try {
                    int num = Integer.parseInt(numberPart);
                    if (num >= count) {
                        count = num + 1;
                    }
                } catch (NumberFormatException ignored) {
                }
            }
        }
        String newFileName = baseName + count + extension;
        File newImageFile = new File(srcFolder + newFileName);
        ImageIO.write(imagem, "png", newImageFile);
    }
}
