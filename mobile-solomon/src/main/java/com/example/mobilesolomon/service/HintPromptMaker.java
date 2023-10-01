package com.example.mobilesolomon.service;

import com.example.mobilesolomon.presentation.hint.HintMakerPage;

public class HintPromptMaker {

    private String prompt;


    public HintPromptMaker(String question, String option1, String option2, String option3, String option4, String answer) {

        // プロンプトを変更するのはここ！
        // 前提条件
        String dearGPT = "以下の問題は、中学生向けのプログラミングの考え方を学習する問題である。4個の選択肢があり、" +
                "その中の一つが正答である選択式の問題である。問題のヒントを三段階に分けて作成してください。なお、ヒントの三段階目は答えと解説とします。\n" +
                "フォーマットはヒント1つ目、ヒント2つ目、ヒント3つ目と表示してください。" +
                "以下に問題とそのヒントの例を示します。これを元にしてヒントを作成してください。\n" +
                "動物園の鳥小屋には以下の命令があります。\n" +
                "\n" +
                "ヒヨコを1匹飼育する: \uD83D\uDC25\n" +
                "カラスを2羽飼育する: \uD83E\uDD85\uD83E\uDD85\n" +
                "スズメを3羽飼育する: \uD83D\uDC26\uD83D\uDC26\uD83D\uDC26\n" +
                "鳥小屋で飼育されている鳥の組み合わせを選んでください。\n" +
                "ア) \uD83D\uDC25\uD83E\uDD85\uD83E\uDD85\uD83D\uDC26\uD83D\uDC26\uD83D\uDC26\n" +
                "イ) \uD83E\uDD85\uD83D\uDC25\uD83D\uDC25\uD83D\uDC25\uD83D\uDC26\uD83D\uDC26\n" +
                "ウ) \uD83D\uDC26\uD83E\uDD85\uD83D\uDC25\uD83D\uDC25\uD83D\uDC25\n" +
                "エ) \uD83D\uDC25\uD83D\uDC25\uD83D\uDC25\uD83E\uDD85\uD83D\uDC26\uD83D\uDC26\n" +
                "\n" +
                "ヒント1:\n" +
                "各命令の数を数えてみましょう。命令の数に注目して、それぞれの要素が何回出現するかを考えてみましょう。\n" +
                "\n" +
                "ヒント2:\n" +
                "各命令の数を数えると、次のようになります。\n" +
                "\n" +
                "ヒヨコ: 1匹\n" +
                "カラス: 2羽\n" +
                "スズメ: 3羽\n" +
                "ヒント3 (答え):\n" +
                "命令に書かれた要素を順番に並べると、以下の結果になります。\n" +
                "\n" +
                "\uD83D\uDC25\uD83E\uDD85\uD83E\uDD85\uD83D\uDC26\uD83D\uDC26\uD83D\uDC26\n" +
                "したがって、正しい答えは「ア、\uD83D\uDC25\uD83E\uDD85\uD83E\uDD85\uD83D\uDC26\uD83D\uDC26\uD83D\uDC26」です。";

        this.prompt = dearGPT +
                "問題：" + question +
                "\n選択肢：ア" + option1 +
                "\n選択肢：イ" + option2 +
                "\n選択肢：ウ" + option3 +
                "\n選択肢：エ" + option4 +
                "\n正解の選択肢：" + answer;

    }

    public String getPrompt() {
        return prompt;
    }
}
