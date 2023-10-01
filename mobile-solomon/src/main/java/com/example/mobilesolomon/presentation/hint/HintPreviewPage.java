package com.example.mobilesolomon.presentation.hint;

import com.example.mobilesolomon.data.ILogRepository;
import com.example.mobilesolomon.data.LogRepository;
import com.example.mobilesolomon.presentation.HomePage;
import com.example.mobilesolomon.presentation.hint.HintMakeCompPage;
import com.example.mobilesolomon.presentation.hint.HintMakerPage;
import com.example.mobilesolomon.service.HintPromptMaker;
import com.example.mobilesolomon.service.HintService;
import com.example.mobilesolomon.service.IHintService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

// 生成したヒントを見ることができ、ヒントを修正するのか、確定するのかを選択できる

@MountPath("HintPreview")
public class HintPreviewPage extends WebPage {

    // IHintServiceをIoC/DIする
    @SpringBean
    private IHintService hintService;

    // ヒント修正
    private String hint;

    public HintPreviewPage() {
        // 問題と選択肢、解答
        add(new Label("question", "問題：" + hintService.getQ()));
        add(new Label("option1", "ア：" +hintService.getOpt1()));
        add(new Label("option2", "イ：" + hintService.getOpt2()));
        add(new Label("option3", "ウ：" + hintService.getOpt3()));
        add(new Label("option4", "エ：" + hintService.getOpt4()));
        add(new Label("answer", "正答：" + hintService.getAns()));



        // ヒント
        Form<Void> form = new Form<>("textForm");
        TextArea<String> textArea = new TextArea<>("hint", Model.of(hintService.getHint()));
        form.add(textArea);

        // フォームからヒントの値を取得し、hint 変数に代入する
        hint = textArea.getModelObject();


        // 自動生成ボタン
        SubmitButton submitButton = new SubmitButton("submit");
        form.add(submitButton);
        add(form);


        var toHintMakerLink = new BookmarkablePageLink<>("toHintMaker", HintMakerPage.class);
        add(toHintMakerLink);

        var toHomeLink = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toHomeLink);

    }

    @Override
    protected void onConfigure() {
        super.onConfigure();
        // ページの再描画を有効化
        setVersioned(false);

    }

    private class SubmitButton extends org.apache.wicket.markup.html.form.Button {
        public SubmitButton(String id) {
            super(id);
        }

        @Override
        public void onSubmit() {
            super.onSubmit();

            // 修正されたヒント
            System.out.println("【DEBUG】修正後ヒント ： \n" + hint);

            // データベースへ更新
            if (hintService.updateHint(hint)) {
                System.out.println("【DEBUG】正常に更新できました。");
            }

            // HintMakeCompPageに移動
            setResponsePage(HintMakeCompPage.class);

        }
    }
}
