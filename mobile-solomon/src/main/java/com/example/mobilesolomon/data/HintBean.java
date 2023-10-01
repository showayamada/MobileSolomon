package com.example.mobilesolomon.data;



import java.io.Serializable;


public class HintBean implements Serializable {
    private static final long serialVersionUID = 1L;

    // メンバ変数
    private int NUM;
    private String QUESTION;
    private String OPTION1;
    private String OPTION2;
    private String OPTION3;
    private String OPTION4;
    private String ANSWER;
    private String HINT;

    // 引数なしデフォルトコンストラクタ
    public HintBean(){}

    public int getNum() {
        return this.NUM;
    }

    public String getQuestion() {
        return QUESTION;
    }

    public String getOption1() {
        return OPTION1;
    }

    public String getOption2() {
        return OPTION2;
    }

    public String getOption3() {
        return OPTION3;
    }

    public String getOption4() {
        return OPTION4;
    }

    public String getAnswer() {
        return ANSWER;
    }

    public String getHint() {
        return HINT;
    }
}
