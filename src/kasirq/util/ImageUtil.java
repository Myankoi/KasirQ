package kasirq.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

public class ImageUtil {

    private static final String IMAGE_DIR =
        "src/kasirq/images/products";

    public static String saveProductImage(File sourceFile) throws IOException {

        if (sourceFile == null) return null;

        String name = sourceFile.getName();
        String ext = name.substring(name.lastIndexOf("."));

        String fileName = "product_" + UUID.randomUUID() + ext;

        Path targetDir = Paths.get(IMAGE_DIR);
        Files.createDirectories(targetDir);

        Path targetPath = targetDir.resolve(fileName);

        Files.copy(
            sourceFile.toPath(),
            targetPath,
            StandardCopyOption.REPLACE_EXISTING
        );

        return "images/products/" + fileName;
    }
}
