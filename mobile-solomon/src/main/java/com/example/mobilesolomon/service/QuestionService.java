package com.example.mobilesolomon.service;

import com.example.mobilesolomon.data.ApiReader;
import com.example.mobilesolomon.data.ILogRepository;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 問題と解答、ヒントを入力したら似たような問題を自動生成してくれるシステム
@Service
public class QuestionService implements IQuestionService{

    private ILogRepository logRepos;     // データベースを引っ張ってくる
    private String API_KEY;     // APIキー
    private String prompt;      // GPTに送信するprompt
    private String ReGPT;       // GPTから帰ってきたもの


    // 問題文と解答、ヒントをデータベースから引っ張り出す
    @Autowired
    public QuestionService(ILogRepository logRepos) {
        this.logRepos = logRepos;
    }

    // 問題をデータベースに登録する
    @Override
    public void questionRegister(String question, String opt1, String opt2, String opt3, String opt4, String answer, String hint) {

        // 通し番号(hintのデータベースと同じものを使う)
        int number = logRepos.selectMaxNum() + 1;

        // データベースに保存
        int n = logRepos.insert(number, question, opt1, opt2, opt3, opt4, answer, hint);
        System.out.println("【DEBUG】正常に記録できました");
    }


    // 仮
    @Override
    public void questionCopy(String question, String opt1, String opt2, String opt3, String opt4, String answer, String hint, String prom) {

        // 通し番号(hintのデータベースと同じものを使う)
        int number = logRepos.selectMaxNum() + 1;

        //　APIキーを取得している
        ApiReader apiReader = new ApiReader();
        String API_KEY = apiReader.getAPI_KEY();

        // ライブラリを利用して、インスタンスを生成
        final var service = new OpenAiService(API_KEY);

        // プロンプト
        this.prompt = "The following is a conversation with an AI assistant. The assistant is helpful, creative, clever.\nHuman: " + prom
                + "\nAI: ";

        // ここでリクエストをしている　生成された選択肢をListに格納している。
        final var completionRequest = CompletionRequest.builder()
                .model("text-davinci-003")
                .prompt(prompt)
                .maxTokens(256)
                .build();
        final var completionResult = service.createCompletion(completionRequest);
        final var choiceList = completionResult.getChoices();

        // 出力
        for (final CompletionChoice choice : choiceList) {
            System.out.println("【DEBUG】chatGPTが作った問題： \n" + choice.getText());
            ReGPT = choice.getText();
        }

        // データベースに保存
        int n = logRepos.insert(number, question, opt1, opt2, opt3, opt4, answer, hint);
        System.out.println("【DEBUG】正常に記録できました");
    }



}
