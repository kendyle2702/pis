package lqc.com.pis.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import lqc.com.pis.service.inter.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private final BlobServiceClient blobServiceClient;
    private final BlobContainerClient containerClient;

    public FileServiceImpl(@Value("${azure.storage.connection-string}") String connectionString,
                            @Value("${azure.storage.container-name}") String containerName) {
        this.blobServiceClient = new BlobServiceClientBuilder().connectionString(connectionString).buildClient();
        this.containerClient = blobServiceClient.getBlobContainerClient(containerName);
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        // Tạo tên file duy nhất
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        // Lấy reference đến blob
        BlobClient blobClient = containerClient.getBlobClient(fileName);

        // Upload file lên Azure Blob Storage
        try (InputStream inputStream = file.getInputStream()) {
            blobClient.upload(inputStream, file.getSize(), true);
        }

        // Trả về URL của file đã upload
        return blobClient.getBlobUrl();
    }
}
