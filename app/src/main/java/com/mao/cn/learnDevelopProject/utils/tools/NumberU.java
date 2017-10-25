// +----------------------------------------------------------------------
// | Project:   boxfish-android-student  
// +----------------------------------------------------------------------
// | CreateTime: 15/12/21  下午11:00
// +----------------------------------------------------------------------
// | Author:     xab(xab@xabad.cn)
// +----------------------------------------------------------------------
// | Description:
// +----------------------------------------------------------------------
package com.mao.cn.learnDevelopProject.utils.tools;

/**
 * DESC   :
 * AUTHOR : Xabad
 */
public class NumberU {

    public static float toFloat(String dValue) {
        float defValue = 0F;
        if (StringU.isEmpty(dValue)) return defValue;
        try {
            defValue = Float.valueOf(dValue);
        } catch (Exception e) {
            return defValue;
        }
        return defValue;
    }

    public static int toInt(String dValue) {
        int defValue = 0;
        if (StringU.isEmpty(dValue)) return defValue;
        try {
            defValue = Integer.valueOf(dValue);
        } catch (Exception e) {
            return defValue;
        }
        return defValue;
    }

    public static long toLong(String dValue) {
        long defValue = 0;
        if (StringU.isEmpty(dValue)) return defValue;
        try {
            defValue = Long.valueOf(dValue);
        } catch (Exception e) {
            return defValue;
        }
        return defValue;
    }

    /**
     * 学生选课的时候 次数转成汉字
     *
     * @param dValue
     * @return
     */
    public static String toChineseCharacters(int dValue) {
        String value;
        switch (dValue) {
            case 1: {
                value = "一";
                break;
            }
            case 2: {
                value = "二";
                break;
            }
            case 3: {
                value = "三";
                break;
            }
            case 4: {
                value = "四";
                break;
            }
            default:
                value = "一";
        }
        return value;
    }

    /**
     * 是否需要去掉小数点后面的精度 7.33 和 7.00
     * 不去掉的时候 给7.33
     * 去掉的时候 给7
     *
     * @return
     */
    public static String isDoubleNum(double num) {
        if (num - (int) num > 0) {
            return String.valueOf("¥" + String.format("%.2f", num));
        } else {
            return String.valueOf("¥" + String.format("%.0f", num));
        }
    }

    /**
     * 是否需要去掉小数点后面的精度 7.33 和 7.00
     * 不去掉的时候 给7.33
     * 去掉的时候 给7
     *
     * @return
     */
    public static String isDoubleNumYuan(double num) {
        return String.valueOf(String.format("%.2f", num));
    }


    public static String isDoubleNumNoY(double num) {
        if (num - (int) num > 0) {
            return String.valueOf(String.format("%.1f", num));
        } else {
            return String.valueOf(String.format("%.0f", num));
        }
    }

    public static String isDoubleNumNo(double num) {
        return String.valueOf(String.format("%.1f", num));
    }

    /**
     * 是否需要去掉小数点后面的精度 7.3 和 7.0
     * 不去掉的时候 给7.3
     * 去掉的时候 给7
     *
     * @return
     */
    public static String isDoubleNumOne(double num) {
        if (num - (int) num > 0) {
            return String.valueOf("¥" + String.format("%.1f", num));
        } else {
            return String.valueOf("¥" + String.format("%.0f", num));
        }
    }
    public static String isDoubleNumTwo(double num) {
        if (num - (int) num > 0) {
            return String.valueOf(String.format("%.1f", num));
        } else {
            return String.valueOf(String.format("%.0f", num));
        }
    }
}
