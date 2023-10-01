package com.example.mobilesolomon.service;

import com.example.mobilesolomon.data.ApiReader;
import com.example.mobilesolomon.data.ILogRepository;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HintService implements IHintService {

    private ILogRepository hintLogRepos;

    private String API_KEY;
    private String prompt; // ChatGPTに送るプロンプト
    private String hint_madeByGPT; // ChatGPTから返ってきたヒント



//　　ここでapiをたたく、レスポンスをうけとる
    @Autowired
    public HintService(ILogRepository hintLogRepos) {
        this.hintLogRepos = hintLogRepos;
    }

    @Override
    public void register(String question, String opt1, String opt2, String opt3, String opt4, String answer, String p) {
        // 現在日時を取得
        //LocalDateTime nowDate = LocalDateTime.now();
        // 表示形式を指定
        //DateTimeFormatter dtf3 =
         //       DateTimeFormatter.ofPattern("yyyy/MM/dd");
        //String formatNowDate = dtf3.format(nowDate);
        //System.out.println(formatNowDate); // 230914

        // 通し番号
        int number = hintLogRepos.selectMaxNum() + 1;

        //　APIキーを取得している
        ApiReader apiReader = new ApiReader();
        String API_KEY = apiReader.getAPI_KEY();

        // ライブラリを利用して、インスタンスを生成
        final var service = new OpenAiService(API_KEY);

        // プロンプト
        this.prompt = "The following is a conversation with an AI assistant. The assistant is helpful, creative, clever.\nHuman: " + p
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
            System.out.println("【DEBUG】chatGPTが作ったヒント： \n" + choice.getText());
            hint_madeByGPT = choice.getText();
        }

        // データベースに保存
        int n = hintLogRepos.insert(number, question, opt1, opt2, opt3, opt4, answer, hint_madeByGPT);
        System.out.println("【DEBUG】正常に記録できました");
    }

    // ここからしたプレビュー用
    // データベースからヒント取ってくる（プレビュー用）
    @Override
    public String getHint() {
        int num = hintLogRepos.selectMaxNum();
        return hintLogRepos.selectHint(num);
    }

    @Override
    public String getQ(){
        int n = hintLogRepos.selectMaxNum();
        return hintLogRepos.selectQ(n);
    }

    @Override
    public String getOpt1(){
        int n = hintLogRepos.selectMaxNum();
        return hintLogRepos.selectOpt1(n);
    }

    @Override
    public String getOpt2(){
        int n = hintLogRepos.selectMaxNum();
        return hintLogRepos.selectOpt2(n);
    }

    @Override
    public String getOpt3(){
        int n = hintLogRepos.selectMaxNum();
        return hintLogRepos.selectOpt3(n);
    }
    @Override
    public String getOpt4(){
        int n = hintLogRepos.selectMaxNum();
        return hintLogRepos.selectOpt4(n);
    }

    @Override
    public String getAns(){
        int n = hintLogRepos.selectMaxNum();
        return hintLogRepos.selectAns(n);
    }
    // ヒント更新（修正）
    @Override
    public boolean updateHint(String newHint) {
        int n = hintLogRepos.selectMaxNum();
        return hintLogRepos.update(newHint ,n);
    }

    // debug用　APIキーがゲッターで呼び出せることを確認できた
//    public static void main(String[] args) {
//        HintApiReader apiReader = new HintApiReader();
//        System.out.println(apiReader.getAPI_KEY());
//    }


}
