package com.example.mobilesolomon.presentation.question;

import com.example.mobilesolomon.data.ILogRepository;
import com.example.mobilesolomon.presentation.HomePage;
import com.example.mobilesolomon.presentation.hint.HintPreviewPage;
import com.example.mobilesolomon.service.IHintService;
import com.example.mobilesolomon.service.IQuestionService;
import com.example.mobilesolomon.service.QuestionPromptMaker;
import com.example.mobilesolomon.service.QuestionService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RadioChoice;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Arrays;
import java.util.List;

// 問題や解答、ヒントをデータベースに登録することができるページ

@MountPath("QuestionRegistration")
public class QuestionRegistrationPage extends WebPage {

    // IHintServiceをIoC/DIする
    @SpringBean
    private IQuestionService questionService;

    // 問題、解答、選択肢、ヒント
    private String question;
    private String answer;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String hint;

    public QuestionRegistrationPage() {


        // home画面に戻るためのリンク
        var toHomeLink = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toHomeLink);

        try {

            // 問題登録formを作成
            Form<QuestionRegistrationPage> form = new Form<>("questionForm", new CompoundPropertyModel<>(this));

            // 問題を入力する箇所
            TextArea<String> questionTextField = new TextArea<>("question");
            form.add(questionTextField);

            // 選択肢を入力する箇所
            TextArea<String> t1 = new TextArea<>("option1");
            form.add(t1);
            TextArea<String> t2 = new TextArea<>("option2");
            form.add(t2);
            TextArea<String> t3 = new TextArea<>("option3");
            form.add(t3);
            TextArea<String> t4 = new TextArea<>("option4");
            form.add(t4);

            // 解答を選択する箇所
            List ANS = Arrays.asList(new String[] {"ア", "イ", "ウ", "エ"});
            RadioChoice radioChoice = new RadioChoice("answer", ANS);
            form.add(radioChoice);

            // ヒントを入力する箇所
            TextArea<String> hintTextField = new TextArea<>("hint");
            form.add(hintTextField);

            // 自動生成ボタン
            SubmitButton submitButton = new SubmitButton("submit");
            form.add(submitButton);


            add(form);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    // ページの再描画を有効化
    @Override
    protected void onConfigure() {
        super.onConfigure();
        setVersioned(false);
    }

    private class SubmitButton extends org.apache.wicket.markup.html.form.Button {

        public SubmitButton (String id) {
            super(id);
        }

        @Override
        public void onSubmit() {

            super.onSubmit();

            // 問題文を出力
            System.out.println("【DEBUG】問題 ：  " + question);

            // 選択肢を出力
            System.out.println("【DEBUG】選択肢 ア ： " + option1);
            System.out.println("【DEBUG】選択肢 イ ： " + option2);
            System.out.println("【DEBUG】選択肢 ウ ： " + option3);
            System.out.println("【DEBUG】選択肢 エ ： " + option4);

            // 答えを出力
            System.out.println("【DEBUG】解答 ： " + answer);

            // ヒントを出力
            System.out.println("【DEBUG】ヒント ： " + hint);

            // データベースへ登録
            questionService.questionRegister(question, option1, option2, option3, option4, answer, hint);

            // QuestionRegistrationCompPageに移動
            setResponsePage(QuestionRegistrationCompPage.class);

        }
    }


}
