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
}
