package Dev.UserDemo.Service.Abstract;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface S3Service {

    String saveFile(MultipartFile file);

    byte[] downloadFile(String fileName);

    String deleteFile(String fileName);

    List<String> listAllFiles();

    File convertMultiPartToFile(MultipartFile file) throws IOException;

}
