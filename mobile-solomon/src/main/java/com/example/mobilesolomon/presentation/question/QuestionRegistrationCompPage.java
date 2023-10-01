package com.example.mobilesolomon.presentation.question;

import com.example.mobilesolomon.presentation.HomePage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.wicketstuff.annotation.mount.MountPath;

// 問題を登録してボタンを押したら飛んでくるページHomeにいくボタンが出る

@MountPath("QuestionRegistrationComp")
public class QuestionRegistrationCompPage extends WebPage {

    public QuestionRegistrationCompPage() {
        var toHomeLink = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toHomeLink);
    }

}
