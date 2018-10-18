package controller;

import action.LoginBoxAction;
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import config.ConfigBox;
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        BoxAPIConnection apiClient = getAPIClient(req, resp);
        HttpSession session = req.getSession();
        session.setAttribute("api",apiClient);
        BoxFolder rootFolder = BoxFolder.getRootFolder(apiClient);
        FolderRepository.getInstance().addFolder(new Folder(rootFolder.getInfo(), null));
        for (BoxItem.Info info : rootFolder) {
            ParserFolder.parse(info, ID_ROOT_FOLDER);
        }
        Redirector.redirectShow(req, resp, rootFolder);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        req.setCharacterEncoding("utf-8");
        res.sendRedirect(new LoginBoxAction().getBoxRedirect());
    }

    private BoxAPIConnection getAPIClient(HttpServletRequest req, HttpServletResponse resp) {
        String code = req.getParameter("code");
        return new BoxAPIConnection(ConfigBox.client_id, ConfigBox.client_secret, code);
    }
}