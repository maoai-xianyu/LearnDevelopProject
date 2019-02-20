package com.mao.cn.learnDevelopProject.contants;

/**
 * Created by zhangkun on 2017/6/9.
 */

public class KeyMaps {

    public static final class ServerInfoConfig {
        public static final String SERVER_INFO_CONFIG = "server_info_config";
    }

    public static final class Screen {
        public final static String SCREEN_WIDTH = "SCREEN_WIDTH";
        public final static String SCREEN_HEIGHT = "SCREEN_HEIGHT";
    }

    public static final class ImagesAssetPath {

        public static final String PICASSO_DRAWABLE = "drawable://";
        public static final String PICASSO_ASSETS = "file:///android_asset/";
        public static final String PICASSO_FILE = "file://";

        public static final String FRESCO_ASSET = "asset:///"; // 需要3个/才能正常显示asset中的图片
        public static final String FRESCO_FILE = "file://";
        public static final String FRESCO_DRAWABLE = "res://";
    }

    public static final class REGEX {
        public static final String braces = "[{}]";
        public static final String multiline_regex = "(?m)";
        public static final String foot_node_inline_regex = "(?!^\\[)\\[\\^(.+?)\\]";
        public static final String foot_node_ref_regex = "^\\[\\^(.+?)\\]\\s*\\S*?\\s*(.*)$";
        public static final String special_symbol_wrap_sentence = "(//)";
        public static final String spcial_symbol_regex = "[*^>_]";
        public static final String tree_symbol_regex = "(?<!\\[)\\^|[*_>]|\\(\\(|\\)\\)";
        public static final String tree_symbol_except_underline_regex = "(?<!\\[)\\^|[*>]|\\(\\(|\\)\\)";
        public static final String punctuation_regex = "(?s)(\\.|!|\\?|\\.\"|\\?\"|!\")\\z";
        public static final String spcial_symbol_wrap_sentence = "(//)";
        public static final String is_correct_formart = "^\\s*[\\[\\{].*$";
        public static final String spcial_symbol_exceplt_underline_regex = "[*^>]";
        public static final String DoubleQuotationMarks = "[\"\"]";
        public static final String ChineseRegex = "[\u4e00-\u9fa5]";
    }


    public static final String HTML_TYPE = "html_type";

    public static final class HtmlType {
        public static final int HTML_TYPE_FOREIGN_COMMENT = 1;
        //        public static final int HTML_TYPE_BUY_MEMBER_DETAIL = 2;
        public static final int HTML_TYPE_PUBLIC_CLASS = 3;
        public static final int HTML_TYPE_ORAL_TEST = 4;
        public static final int HTML_TYPE_BUY_COMBOS = 5;
        public static final int HTML_TYPE_INTELLIGENT_CLASS = 6;
        public static final int HTML_TYPE_TRAINING_CN_CLASS = 7;
        public static final int HTML_TYPE_INVITING_FRIENDS = 8;//邀请好友
        public static final int HTML_TYPE_BUY_COURSE_REWARD_RULE = 9;//购买课程 退款须知
        public static final int HTML_TYPE_LEVEL = 10;//首页红点level查看更多
        public static final int HTML_TYPE_BOXFISH_MORE_BETTER_THAN_NEWEAST = 11;//盒子鱼比新东方领先一个时代
        public static final int HTML_TYPE_K_MEMBER_BUY_DETAIL = 12;//幼儿园的购买详情
        public static final int HTML_TYPE_K_PUBLIC_CLASS_KINDERGARTEN = 13;//幼儿园外教大讲堂的详情
        public static final int HTML_TYPE_K_PLANNING_GOAL = 14;//幼儿园的学习规划和目标网页
        public static final int HTML_TYPE_MEMBER_DETAIL = 15;//boxfish国际线上学校
        public static final int HTML_TYPE_FOREIGN_FUDAO = 16;//外教辅导
        public static final int HTML_TYPE_MEMBER_EVALUATION = 17;//vip购买页面的学员评价


        public static final int HTML_TYPE_VIP_ITEM_2 = 18;//外教课后评价
        public static final int HTML_TYPE_VIP_ITEM_6 = 19;//老师纠正错误
        public static final int HTML_TYPE_VIP_ITEM_9 = 20;//老师纠正错误
        public static final int HTML_TYPE_VIP_ITEM_10 = 21;//学习全托管


        public static final int HTML_TYPE_ALL_BUY = 22;//微课和国际班购买网页
        public static final int HTML_TYPE_MICRO_LESSON_BUY = 23;//微课购买页面
        public static final int HTML_TYPE_MICRO_MIANZE = 24;//微课购买页面免责申明

        public static final int HTML_TYPE_HTML_MICRO = 25;//外教一对一强化练习

        public static final int HTML_TYPE_MEMBER_PRODUCT_OFFLINE = 26;//外教一对一强化练习
        public static final int HTML_TYPE_MICRO_OFFLINE = 27;//微课下线
        public static final int HTML_TYPE_BUY_PREMIUM = 28;//升级到高级版
        public static final int HTML_TYPE_ONE_TO_ONE_DETAIL = 29;//一对一查看详情
        public static final int HTML_TYPE_MICRO_LESSON_DETAIL = 30;//微课详情页


        public static final int HTML_TYPE_AWARD_TEN_YUAN_TEN_TIMES = 32;//抽奖十元十次的购买
        public static final int HTML_TYPE_MASTER_CLASS = 33;//大师课课介绍网页
        public static final int HTML_TYPE_CHINESE_PUBLICE_CLASS = 34;//公益课课介绍网页
        public static final int HTML_TYPE_CHINESE_TEACHER_CLASS = 35;//中教名师课介绍网页
        public static final int HTML_TYPE_FOREIGN_TEACHER_CLASS = 36;//外教名师课介绍网页

        public static final int HTML_TYPE_FOREIGN_TEACHER_LESSON = 37;//购买外教培优课
        public static final int HTML_TYPE_INTERNATION_LESSON = 38;//购买国际班

        public static final int HTML_TYPE_FOREIGN_TEACHER_LESSON_DETAIL = 39;//购买外教培优课介绍页
        public static final int HTML_TYPE_INTERNATION_LESSON_DETAIL = 40;//购买国际班介绍页

        //了解外教学练课旋转网页：革命性的同步外教培优课
        public static final int HTML_TYPE_REVOLUTION = 41;
        public static final int HTML_TYPE_ADDRESS_LIST = 42; //地址列表


        //        public static final String HTML_TYPE_BUY_PREMIUM_URL = "https://boxfish.cn/payment/";//购买网页
        public static final String HTML_TYPE_BUY_PREMIUM_URL = "https://www.boxfish.cn/inner_app/v12.4/payment/";//购买网页
        public static final String HTML_TYPE_BUY_PREMIUM_TYPE_TERM = "compulsory";//外教综合培优班
        public static final String HTML_TYPE_BUY_PREMIUM_TYPE_ACADEMICYEAR = "international";//VIP国际班

        public static final String HTML_URL_INTELLIGENT = "https://www.boxfish.cn/inner_app/v13/payment?t=";//智能三件套购买
        public static final String HTML_URL_ADDRESS_LIST = "https://www.boxfish.cn/inner_app/address?t=";
        public static final String HTML_URL_ADDRESS_LIST_SELECT = "https://www.boxfish.cn/inner_app/address?portal=order&t=";

        //四件套的购买测试服务器网页地址打开需谨慎上线需小心
//        public static final String HTML_URL_INTELLIGENT = "https://www.boxfish.cn/inner_app/v13/payment/test?t=";//智能三件套购买
//        public static final String HTML_URL_ADDRESS_LIST = "https://www.boxfish.cn/inner_app/address/test/?t=";


    }

    public static class InternationalMember {
        public static final String MEMBER_NO = "MEMBER_NO"; //跳转成为学员
        public static final String MEMBER_B_VIP = "MEMBER_B_VIP"; //需要升级vip,弹升级页面
        public static final String MEMBER_VIP = "MEMBER_VIP"; //就是vip
        public static final String FOREIGN_COMMENT = "FOREIGN_COMMENT"; //外教点评


        public static final String BUY_MICRO_LESSON_PAGE_WEB = "BUY_MICRO_LESSON_PAGE_WEB"; //跳转到购买微课的网页
        public static final String BUY_MICRO_LESSON_PAGE = "BUY_MICRO_LESSON_PAGE"; //跳转到微课购买页面
        public static final String ALL_BUY_WEB = "ALL_BUY_WEB"; //跳转到购买课程和微课的网页
        public static final String BUY_COURSE_PAGE = "BUY_COURSE_PAGE"; //跳转到国际本课程购买页面
        public static final String BUY_MEMBER_PAGE = "BUY_MEMBER_PAGE"; //跳转到会员购买购买页面


        public static final String HTML_TYPE_FOREIGN_TEACHER_LESSONS = "HTML_TYPE_FOREIGN_TEACHER_LESSON";//购买外教培优课
        public static final String HTML_TYPE_INTERNATION_LESSONS = "HTML_TYPE_INTERNATION_LESSON";//购买国际班


    }
}
