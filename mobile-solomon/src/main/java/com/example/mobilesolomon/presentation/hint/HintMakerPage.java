package com.example.mobilesolomon.presentation.hint;

import com.example.mobilesolomon.presentation.HomePage;
import com.example.mobilesolomon.service.HintPromptMaker;
import com.example.mobilesolomon.service.IHintService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// 問題や解答を入力して、ボタンを押すとヒントを製作できる

@MountPath("HintMaker")
public class HintMakerPage extends WebPage {
    // IHintServiceをIoC/DIする
    @SpringBean
    private IHintService hintService;

    /**入力された問題文**/
    private String question;

    /**選択された答えアイウエ**/
    private String answer;

    /**選択肢**/
    private String option1;
    private String option2;
    private String option3;
    private String option4;

    public HintMakerPage() {
        var toHomeLink = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toHomeLink);

        try {
            // ヒント入力フォーム
            Form<HintMakerPage> form = new Form<>("hintForm", new CompoundPropertyModel<>(this));
            // 問題
            TextArea<String> textField = new TextArea<>("question");
            form.add(textField);
            // 選択肢
            TextArea<String> t1 = new TextArea<>("option1");
            form.add(t1);
            TextArea<String> t2 = new TextArea<>("option2");
            form.add(t2);
            TextArea<String> t3 = new TextArea<>("option3");
            form.add(t3);
            TextArea<String> t4 = new TextArea<>("option4");
            form.add(t4);
            // 解答
            List ANS = Arrays.asList(new String[] {"ア", "イ", "ウ", "エ"});
            RadioChoice radioChoice = new RadioChoice("answer", ANS);
            form.add(radioChoice);
            // 自動生成ボタン
            SubmitButton submitButton = new SubmitButton("submit");
            form.add(submitButton);

            FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
            form.add(feedbackPanel);

            add(form);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    protected void onConfigure() {
        super.onConfigure();
        // ページの再描画を有効化
        setVersioned(false);

    }
    private class SubmitButton extends org.apache.wicket.markup.html.form.Button {
        public SubmitButton (String id) {
            super(id);
        }

        @Override
        public void onSubmit() {
            super.onSubmit();

            if(question==null||option1==null||option2==null||option3==null||option4==null||answer==null){
                error("入力情報が不足しています。内容を確認して再度「ヒント自動生成」を押してください");
                return;
            }

            // 入力された問題文
            System.out.println("【DEBUG】問題 ：  " + question);

            // 選択肢
            System.out.println("【DEBUG】選択肢 ア ： " + option1);
            System.out.println("【DEBUG】選択肢 イ ： " + option2);
            System.out.println("【DEBUG】選択肢 ウ ： " + option3);
            System.out.println("【DEBUG】選択肢 エ ： " + option4);

            // 選択された答え
            System.out.println("【DEBUG】解答 ： " + answer);

            HintPromptMaker hintPromptMaker = new HintPromptMaker(question, option1, option2, option3, option4, answer);
            // データベースへ登録
            hintService.register(question, option1, option2, option3, option4, answer, hintPromptMaker.getPrompt());

            // HintPreviewPageに移動
            setResponsePage(HintPreviewPage.class);

        }
    }

    public String getQuestion(){
        return question;
    }

    public String getAnswer() {
        return answer;
    }

}

