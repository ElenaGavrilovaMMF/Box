package service;

import action.FileAction;
import action.FolderAction;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import parser.ParserFolder;
import util.JspPathUtil;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Redirector {
    private static final String showRedirectPage = "profile";

    public static void redirectShow(HttpServletRequest req, HttpServletResponse resp, BoxFolder rootFolder) throws ServletException, IOException {
        for (BoxItem.Info itemInfo : rootFolder) {
            System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
            ParserFolder.parseFolder(itemInfo);
        }

        System.out.println("Папки" + new FolderAction().getListFolders().toString());
        System.out.println("Файлы" + new FileAction().getListFiles().toString());
        req.getRequestDispatcher(JspPathUtil.get(showRedirectPage))
                .forward(req, resp);
    }

}