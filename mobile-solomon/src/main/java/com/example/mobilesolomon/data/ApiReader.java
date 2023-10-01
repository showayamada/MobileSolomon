package com.example.mobilesolomon.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ApiReader {

    private String API_KEY;

    public ApiReader() {
        try {
            // Propertiesオブジェクト（キーと値のString同士をペアとして保持できる→「ChatAPI_KEY＝＝****」という形）をつくる
            Properties properties = new Properties();
            // envファイルを読み込むためのInputStream（プログラム内でバイトデータを読み込むための抽象クラス）をひらく
            InputStream inputStream = new FileInputStream(".env");
            // Propertiesに読み込んだ内容をロードする
            properties.load(inputStream);
            this.API_KEY = properties.getProperty("ChatAPI_KEY");
            //System.out.println(API_KEY); //debug
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getAPI_KEY() {
        return this.API_KEY;
    }
}
