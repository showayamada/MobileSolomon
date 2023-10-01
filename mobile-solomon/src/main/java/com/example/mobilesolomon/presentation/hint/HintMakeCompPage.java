package com.example.mobilesolomon.presentation.hint;

import com.example.mobilesolomon.presentation.HomePage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.WebPage;

@MountPath("HintMakeComp")
public class HintMakeCompPage extends WebPage {

    public HintMakeCompPage() {
        var toHintMakeLink = new BookmarkablePageLink<>("toHintMake", HintMakerPage.class);
        add(toHintMakeLink);
        var toHomeLink = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toHomeLink);
    }

}
