package controller;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import entity.Folder;
import parser.ParserFolder;
import repository.FolderRepository;
import service.Redirector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(value = "/profile", name = "Profile")
public class ControllerLogin extends HttpServlet {

    private static final String ID_ROOT_FOLDER = "0";
    private static final String TOKEN = "FsOZV9Q0HqC4Bb640lWfoNutHJKDlGRZ";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        BoxAPIConnection boxAPIConnection = new BoxAPIConnection(TOKEN);

        HttpSession session = req.getSession();
        session.setAttribute("api",boxAPIConnection);

        BoxFolder rootFolder = BoxFolder.getRootFolder(boxAPIConnection);
        FolderRepository.getInstance().addFolder(new Folder(rootFolder.getInfo(), null));
        for (BoxItem.Info info : rootFolder) {
            ParserFolder.parse(info, ID_ROOT_FOLDER);
        }
        Redirector.redirectShow(req, resp, rootFolder);
    }
}