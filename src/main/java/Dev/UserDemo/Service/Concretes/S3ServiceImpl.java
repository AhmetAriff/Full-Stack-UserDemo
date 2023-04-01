package Dev.UserDemo.Service.Concretes;

import Dev.UserDemo.Service.Abstract.S3Service;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class S3ServiceImpl implements S3Service {

    private final AmazonS3 s3;

    @Value("${bucketName}")
    private  String bucketName;

    @Override
    public String saveFile(MultipartFile file) {
        String originalFileName = file.getOriginalFilename();
        try{
            File  file1 = convertMultiPartToFile(file);
            PutObjectResult putObjectResult = s3.putObject(bucketName,originalFileName,file1);
            return putObjectResult.getContentMd5();
        }
        catch (IOException e ){
            throw  new RuntimeException(e);
        }
    }

    @Override
    public byte[] downloadFile(String fileName) {
        S3Object object= s3.getObject(bucketName,fileName);
        S3ObjectInputStream objectContent= object.getObjectContent();
        try {
            return IOUtils.toByteArray(objectContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteFile(String fileName) {
        s3.deleteObject(bucketName,fileName);
        return "File deleted";
    }

    @Override
    public List<String> listAllFiles() {
    ListObjectsV2Result listObjectsV2Result= s3.listObjectsV2(bucketName);
    return listObjectsV2Result
            .getObjectSummaries()
            .stream()
            .map(S3ObjectSummary::getKey)
            .collect(Collectors.toList());
    }

    @Override
    public File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convertedFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convertedFile);
        fos.write(file.getBytes());
        fos.close();
        return convertedFile;
    }
}
