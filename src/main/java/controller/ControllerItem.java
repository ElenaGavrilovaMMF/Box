package controller;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import entity.Folder;
import repository.FolderRepository;
import service.Redirector;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MultipartConfig
@WebServlet(value = "/page", name = "Page")
public class ControllerItem extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (FolderRepository.getInstance().getFolder(req.getParameter("id")) != null) {
            Folder folder = FolderRepository.getInstance().getFolder(req.getParameter("id"));
            BoxFolder boxFolder = new BoxFolder((BoxAPIConnection) req.getSession().getAttribute("api"), folder.getId());
            Redirector.redirectShow(req, resp, boxFolder);
        } else {
            String fileID = req.getParameter("id");
            BoxFile file = new BoxFile((BoxAPIConnection) req.getSession().getAttribute("api"), fileID);
            Redirector.redirectShow(req, resp, file);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("link") != null) {
            BoxItem.Info info;
            if (FolderRepository.getInstance().getFolder(req.getParameter("id")) != null) {
                info = new BoxFolder((BoxAPIConnection) req.getSession().getAttribute("api"), req.getParameter("id")).getInfo();
            } else {
                info = new BoxFile((BoxAPIConnection) req.getSession().getAttribute("api"), req.getParameter("id")).getInfo();
            }
            Redirector.redirectShow(req, resp, info);
        } else if (req.getParameter("searchTerm") != null) {
            Redirector.redirectShow(req, resp, req.getParameter("type"));
        } else {
            Redirector.redirectShow(req, resp);
        }
    }
}
