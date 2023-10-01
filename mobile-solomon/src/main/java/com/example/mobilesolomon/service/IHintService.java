package com.example.mobilesolomon.service;

public interface IHintService {
    public void register(String question, String opt1, String opt2, String opt3, String opt4, String answer, String p);

    public String  getHint();

    public String getQ();
    public String getOpt1();
    public String getOpt2();
    public String getOpt3();
    public String getOpt4();
    public String getAns();
    public boolean updateHint(String newHint);


}
