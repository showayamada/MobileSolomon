package com.example.mobilesolomon.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LogRepository implements ILogRepository {

    // spring Frameworkのデータベース接続ライブラリを利用する
    private final JdbcTemplate jdbcTemplate;

    // Spring Frameworkのデータベース接続ライブラリを初期化する
    @Autowired
    public LogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insert(int num, String question, String opt1, String opt2, String opt3, String opt4, String answer, String hint) {
        var sql = "insert into hint values (?, ?, ?, ?, ?, ?, ?, ?)";
        var n = jdbcTemplate.update(sql, num, question, opt1, opt2, opt3, opt4, answer, hint);
        return n;
    }
    @Override
    public int selectMaxNum() {
        var sql = "select max(NUM) from hint";
        var n = jdbcTemplate.queryForObject(sql,Integer.class);
        if(n == null) {
            return 0;
        }else {
            return n;
        }
    }

    @Override
    public int delete(int num) {
        String sql = "DELETE FROM hint WHERE NUM = ?";
        int deletedRows = jdbcTemplate.update(sql, num);
        return deletedRows;
    }

    @Override
    public boolean update(String newHint, int n) {
        boolean ret = false; // 初期化
        var sql = "UPDATE HINT SET HINT = ? WHERE NUM = ?";
        var updatedRow =jdbcTemplate.update(sql,newHint, n);
        if(updatedRow>0) ret = true;
        return ret;
    }

    @Override
    public String selectHint(int n) {
        String ret = "";
        var sql = "SELECT HINT FROM HINT WHERE NUM = ?";
        ret = jdbcTemplate.queryForObject(sql, String.class, n);
        return ret;
    }

    @Override
    public String selectQ(int n) {
        String ret = "";
        var sql = "SELECT QUESTION FROM HINT WHERE NUM = ?";
        ret = jdbcTemplate.queryForObject(sql, String.class, n);
        return ret;
    }

    @Override
    public String selectOpt1(int n) {
        String ret = "";
        var sql = "SELECT OPTION1 FROM HINT WHERE NUM = ?";
        ret = jdbcTemplate.queryForObject(sql, String.class, n);
        return ret;
    }

    @Override
    public String selectOpt2(int n) {
        String ret = "";
        var sql = "SELECT OPTION2 FROM HINT WHERE NUM = ?";
        ret = jdbcTemplate.queryForObject(sql, String.class, n);
        return ret;
    }

    @Override
    public String selectOpt3(int n) {
        String ret = "";
        var sql = "SELECT OPTION3 FROM HINT WHERE NUM = ?";
        ret = jdbcTemplate.queryForObject(sql, String.class, n);
        return ret;
    }

    @Override
    public String selectOpt4(int n) {
        String ret = "";
        var sql = "SELECT OPTION4 FROM HINT WHERE NUM = ?";
        ret = jdbcTemplate.queryForObject(sql, String.class, n);
        return ret;
    }

    @Override
    public String selectAns(int n) {
        String ret = "";
        var sql = "SELECT ANSWER FROM HINT WHERE NUM = ?";
        ret = jdbcTemplate.queryForObject(sql, String.class, n);
        return ret;
    }

    @Override
    public List<HintBean> selectAllHintTable(){
        var sql = "SELECT * FROM HINT";
        List<HintBean> hints = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(HintBean.class));
        System.out.println(hints);
        System.out.println(hints.get(1).getHint());
        return hints;
    }

}
