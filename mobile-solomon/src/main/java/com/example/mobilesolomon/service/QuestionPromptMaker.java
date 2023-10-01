package com.example.mobilesolomon.service;

// GPTのプロンプトを設定している

public class QuestionPromptMaker {

    private String prompt;

    public QuestionPromptMaker(
            String question, String option1, String option2, String option3,
            String option4, String answer, String hint) {

        String dearGPT = "以下の問題は中学生向けのプログラミングの考え方を学習する問題である。このような形式の問題を1問複製してください。" +
                "";

        this.prompt = dearGPT +
                "問題：" + question +
                "\n選択肢：ア" + option1 +
                "\n選択肢：イ" + option2 +
                "\n選択肢：ウ" + option3 +
                "\n選択肢：エ" + option4 +
                "\n正解の選択肢：" + answer +
                "\nヒント：" + hint;

    }

    public String getPrompt() {return prompt;}

}
