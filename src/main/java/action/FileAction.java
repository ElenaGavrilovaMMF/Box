package action;

import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import entity.File;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
public class FileAction {
    private final ArrayList<File> BOX_FILES = new ArrayList();

    public void addBoxFile(File file) {
        BOX_FILES.add(file);
    }

    public ArrayList<File> getListFiles() {
        return BOX_FILES;
    }

    public void uploadFile(HttpServletRequest req, BoxFolder folder) throws IOException, ServletException {
        Part filePart = req.getPart("file");
        InputStream inputStream = filePart.getInputStream();
        String fileName = getFileName(filePart);
        folder.uploadFile(inputStream, fileName);
        inputStream.close();
    }

    public void downloadFile (BoxFile boxFile, HttpServletResponse resp) throws IOException {
        BoxFile.Info info = boxFile.getInfo();
        URL downloadURL = boxFile.getDownloadURL();
        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition","attachment; filename="+boxFile.getInfo().getName());
        resp.getWriter().write(downloadURL.getPath());
    }

    private String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
}
