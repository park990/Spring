package file.input;

import org.springframework.web.multipart.MultipartFile;

public class paramVO {
    String title, content, file_name;
    MultipartFile s_file;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getS_file() {
        return s_file;
    }

    public void setS_file(MultipartFile s_file) {
        this.s_file = s_file;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
}
