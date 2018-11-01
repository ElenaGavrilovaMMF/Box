package service;

import action.FileAction;
import action.FolderAction;
import action.ItemAction;
import com.box.sdk.*;
import entity.File;
import entity.Folder;
import repository.FolderRepository;
import util.JspPathUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Redirector {
    private static final String SHOW_REDIRECT_PAGE = "profile";
    private static final String SHOW_REDIRECT_SEARCH = "search";
    private static final String ERROR = "An empty search query is set";

    public static void redirectShow(HttpServletRequest req, HttpServletResponse resp, BoxFolder rootFolder) throws ServletException, IOException {
        FolderAction folderAction = new FolderAction();
        FileAction fileAction = new FileAction();
        for (BoxItem.Info itemInfo : rootFolder) {
            if (folderAction.isFolder(itemInfo)) {
                folderAction.addBoxFolder(new Folder(itemInfo, rootFolder.getID()));
            } else {
                fileAction.addBoxFile(new File(itemInfo));
            }
        }
        req.setAttribute("types", TypeFile.getListType());
        req.setAttribute("folders", folderAction.getListFolders());
        req.setAttribute("files", fileAction.getListFiles());
        req.setAttribute("idLastFolder", FolderRepository.getInstance().getFolder(rootFolder.getID()).getIdParent());
        req.setAttribute("idCurrentFolder", rootFolder.getID());
        req.getRequestDispatcher(JspPathUtil.get(SHOW_REDIRECT_PAGE))
                .forward(req, resp);
    }

    public static void redirectShow(HttpServletRequest req, HttpServletResponse resp, BoxFile boxFile) throws ServletException, IOException {
        new FileAction().downloadFile(boxFile, resp);
        req.getRequestDispatcher(JspPathUtil.get(SHOW_REDIRECT_PAGE))
                .forward(req, resp);
    }

    public static void redirectShow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BoxFolder folder = new BoxFolder((BoxAPIConnection) req.getSession().getAttribute("api"), req.getParameter("idCurrentFolder"));
        new FileAction().uploadFile(req, folder);
        Redirector.redirectShow(req, resp, folder);
    }

    public static void redirectShow(HttpServletRequest req, HttpServletResponse resp, String type) throws ServletException, IOException {
        FileAction fileAction = new FileAction();
        PartialCollection<BoxItem.Info> searchTerm = new ItemAction().searchItem(req, req.getParameter("searchTerm"), type);
        if (searchTerm!=null) {
            for (BoxItem.Info info : searchTerm) {
                if (info.getName().contains(req.getParameter("searchTerm"))) {
                    fileAction.addBoxFile(new File(info));
                }
            }
            req.setAttribute("files", fileAction.getListFiles());
        } else {
            req.setAttribute("error", ERROR);
        }
        req.setAttribute("types", TypeFile.getListType());
        req.setAttribute("id", req.getParameter("idCurrentFolder"));
        System.out.println(fileAction.getListFiles().size());
        req.getRequestDispatcher(JspPathUtil.get(SHOW_REDIRECT_SEARCH))
                .forward(req, resp);
    }

    public static void redirectShow(HttpServletRequest req, HttpServletResponse resp, BoxItem.Info item) throws ServletException, IOException {
        String boxSharedLink = new ItemAction().getLink(item);
        req.setAttribute("link", boxSharedLink);
        redirectShow(req, resp, new BoxFolder((BoxAPIConnection) req.getSession().getAttribute("api"), req.getParameter("idCurrentFolder")));
    }

}