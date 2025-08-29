package spring.input;

import org.springframework.web.multipart.MultipartFile;

public class imgVO {

    private MultipartFile upload;

    public MultipartFile getUpload() {
        return upload;
    }

    public void setUpload(MultipartFile upload) {
        this.upload = upload;
    }
}
