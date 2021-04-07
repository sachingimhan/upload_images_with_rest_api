package lk.sample.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileStoreServiceImpl implements FileStoreService {


    @Override
    public String saveFile(MultipartFile file, String rootPath) throws MalformedURLException {
        File dir = new File(rootPath + "/uploads");
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                throw new RuntimeException("Upload Dir can not be created.!");
            }
        }
        Path filePath = Paths.get(dir.getAbsolutePath(), file.getOriginalFilename().replaceAll(" ",""));
        try {
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("File can not be written.!");
        }
        return "uploads/" + filePath.getFileName().normalize().toString();
    }

    @Override
    public List<String> getAllFiles(String root) {
        File file = new File(root + "/uploads/");
        List<String> dir=new ArrayList<>();
        if (file.exists()) {
            String[] list = file.list();
            for (String s : list) {
                dir.add("/uploads/"+s);
            }
        }
        return dir;
    }

    @Override
    public String getFile(String fileName) {
        return null;
    }
}
