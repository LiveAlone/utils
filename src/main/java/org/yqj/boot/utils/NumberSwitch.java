package org.yqj.boot.utils;

import com.google.common.base.Strings;

import java.math.BigDecimal;

/**
 * Created by yaoqijun.
 * Date:2016-08-02
 * Email:yaoqijunmail@gmail.io
 * Descirbe: 对应的数字计算的方式
 */
public class NumberSwitch {

    private static final BigDecimal ratio = new BigDecimal("100");  // 元转分时的倍率

    public static Long integerToLong(Integer source){

        return new BigDecimal(source).longValue();
    }

    public static Integer longToInteger(Long source){

        return new BigDecimal(source).intValue();
    }

    /**
     * 计算平台佣金
     * @param fee 订单金额（单位：分）
     * @param rate 费率
     * @return 平台抽佣
     */
    public static Long getCommission(Integer fee,Integer rate){

        return new BigDecimal(fee).multiply(new BigDecimal(rate).divide(new BigDecimal(10000))).setScale(0, BigDecimal.ROUND_UP).longValue();//取0位小数 向上取整
    }

    /**
     * 计算代理商佣金
     * @param fee 订单金额（单位：分）
     * @param rate 费率
     * @return 平台抽佣
     */
    public static Long getAgentCommission(Long fee,Integer rate){

        return new BigDecimal(fee).multiply(new BigDecimal(rate).divide(new BigDecimal(10000))).setScale(0, BigDecimal.ROUND_UP).longValue();//取0位小数 向上取整
    }

    /*
    转换成万分之几
     */
    public static Integer getTransFormRate(String rate){
        BigDecimal bd = new BigDecimal(rate);
        Integer alipayRate = bd.multiply(new BigDecimal(10000)).intValue();

        return alipayRate;
    }

    /**
     * string类型金额转换为分为单位
     * @param price 价格
     * @return 价格
     */
    public static Long stringToFen(String price){
        if (Strings.isNullOrEmpty(price)) return 0L;
        BigDecimal money = new BigDecimal(price);
        return money.multiply(ratio).longValue();
    }

    /**
     * 将微信手续费率 转换为千分之几的字符串
     * @param price 价格
     * @return string
     */
    public static String percentToString(String price){
        BigDecimal money = new BigDecimal(price);
        return  money.divide(new BigDecimal(100)).toString();
    }

    /**
     * 微信手续费向上取整保留两位小时，并转换为以分为单位
     * @param price 价格
     * @return 价格
     */
    public static Long roundUp(String price){
        if (Strings.isNullOrEmpty(price)) return 0L;
        BigDecimal money = new BigDecimal(price);
        return money.setScale(2, BigDecimal.ROUND_UP).multiply(ratio).longValue();
    }

    /**
     * 检测第三方手续费 是否有误 （手续费大于订单原价的千分之十五即为有误）
     * @param totalFee 订单总金额
     * @param thridFee 手续费总金额
     * @param condition 手续费最高费率
     * return 是否有误
     */
    public static Boolean checkThridFee(Integer totalFee,Long thridFee,String condition){

        BigDecimal total = new BigDecimal(totalFee).multiply(new BigDecimal(condition));

        BigDecimal thrid = new BigDecimal(thridFee);

        return thrid.compareTo(total)!=1;//手续费小于等于订单原价的千分之十五为合法

    }

    /**
     * 手动计算支付宝手续费
     * @param outcome 手续费
     * @param total 总费用
     * @return 费率
     */
    public static String getAlipayRate(String outcome,String total){
        BigDecimal outcomeBg = new BigDecimal(outcome);
        BigDecimal totalBg = new BigDecimal(total);
        BigDecimal bd = outcomeBg.divide(totalBg, 2, BigDecimal.ROUND_UP);//保留小数点后面2位，向上取整！
        return bd.toString();
    }


    /**
     * 取反
     * @param source 来源
     * @return 返回值
     */
    public static Long reverse(Long source){

        return 0L-source;
    }

}
