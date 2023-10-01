package com.example.mobilesolomon.presentation;

import com.example.mobilesolomon.presentation.hint.HintMakerPage;
import com.example.mobilesolomon.presentation.question.QuestionRegistrationPage;
import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.CssReferenceHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.resource.CssResourceReference;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

@WicketHomePage
@MountPath("Home")
public class HomePage extends WebPage {
    public HomePage() {
        var toHintMakerLink = new BookmarkablePageLink<>("toHintMaker", HintMakerPage.class);
        add(toHintMakerLink);
        var toQuestionRegistration = new BookmarkablePageLink<>("toQuestionRegistration", QuestionRegistrationPage.class);
        add(toQuestionRegistration);
        var toListPage = new BookmarkablePageLink<>("toListPage", ListPage.class);
        add(toListPage);
    }

    @Override
    public void renderHead(IHeaderResponse response) {
        super.renderHead(response);

        // CSSファイルを追加
        response.render(CssHeaderItem.forReference(new CssResourceReference(HomePage.class, "HomePage.css")));
    }

}
