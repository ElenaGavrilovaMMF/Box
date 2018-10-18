package service;

import com.box.sdk.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SearchItem {

    public static PartialCollection<BoxItem.Info> searchItem(HttpServletRequest req, String searchTerm, String type) {
        int limit = 10;
        int offset = 0;
        BoxSearchParameters bsp = new BoxSearchParameters();
        if (!type.equals("")) {
            bsp.setType(type);
        }
        bsp.setQuery(searchTerm);
        BoxSearch bs = new BoxSearch((BoxAPIConnection) req.getSession().getAttribute("api"));
        PartialCollection<BoxItem.Info> searchResults = bs.searchRange(offset, limit, bsp);
        return searchResults;
    }
}
