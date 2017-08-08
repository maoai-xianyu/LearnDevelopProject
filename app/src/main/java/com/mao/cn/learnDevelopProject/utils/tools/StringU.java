package com.mao.cn.learnDevelopProject.utils.tools;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringU {
    public StringU() {
    }

    public static String stripEmoji(String text) {
        StringBuilder builder = new StringBuilder();
        long count = (long) Character.codePointCount(text, 0, text.length());

        for (int index = 0; (long) index < count; ++index) {
            int codePoint = Character.codePointAt(text, index);
            if (Character.charCount(codePoint) > 1) {
                builder.appendCodePoint(codePoint);
            }
        }

        return builder.toString();
    }

    public static int length(String value) {
        if (isEmpty(value)) {
            return 0;
        } else {
            int valueLength = 0;

            for (int i = 0; i < value.length(); ++i) {
                String temp = value.substring(i, i + 1);
                if (containChinese(temp)) {
                    valueLength += 2;
                } else {
                    ++valueLength;
                }
            }

            return valueLength;
        }
    }

    public static boolean containChinese(String text) {
        if (isEmpty(text)) {
            return false;
        } else {
            String str = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";
            Pattern p = Pattern.compile(str);

            for (int i = 0; i < text.length(); ++i) {
                Matcher m = p.matcher(String.valueOf(text.charAt(i)));
                if (m.find()) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isRealName(String realName) {
        if (isEmpty(realName)) {
            return false;
        } else {
            String str = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2DA-Za-z ]+$";
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(realName);
            return m.matches();
        }
    }

    public static boolean containEmoji(String text) {
        if (isEmpty(text)) {
            return false;
        } else {
            StringBuilder builder = new StringBuilder();
            long count = (long) Character.codePointCount(text, 0, text.length());

            for (int index = 0; (long) index < count; ++index) {
                int codePoint = Character.codePointAt(text, index);
                if (Character.charCount(codePoint) > 1) {
                    return true;
                }

                builder.appendCodePoint(codePoint);
            }

            return false;
        }
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence cs) {
        return !isEmpty(cs);
    }


    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs != null && (strLen = cs.length()) != 0) {
            for (int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(cs.charAt(i))) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    public static boolean equals(CharSequence cs1, CharSequence cs2) {
        return cs1 == cs2 ? true : (cs1 != null && cs2 != null ? (cs1 instanceof String && cs2 instanceof String ? cs1.equals(cs2) : CharSequenceUtils.regionMatches(cs1, false, 0, cs2, 0, Math.max(cs1.length(), cs2.length()))) : false);
    }

    public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
        return str1 != null && str2 != null ? (str1 == str2 ? true : (str1.length() != str2.length() ? false : CharSequenceUtils.regionMatches(str1, true, 0, str2, 0, str1.length()))) : str1 == str2;
    }

    public static int indexOf(CharSequence seq, int searchChar) {
        return isEmpty(seq) ? -1 : CharSequenceUtils.indexOf(seq, searchChar, 0);
    }

    public static int indexOf(CharSequence seq, int searchChar, int startPos) {
        return isEmpty(seq) ? -1 : CharSequenceUtils.indexOf(seq, searchChar, startPos);
    }

    public static int indexOf(CharSequence seq, CharSequence searchSeq) {
        return seq != null && searchSeq != null ? CharSequenceUtils.indexOf(seq, searchSeq, 0) : -1;
    }

    public static int indexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
        return seq != null && searchSeq != null ? CharSequenceUtils.indexOf(seq, searchSeq, startPos) : -1;
    }

    public static int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal) {
        return ordinalIndexOf(str, searchStr, ordinal, false);
    }

    private static int ordinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal, boolean lastIndex) {
        if (str != null && searchStr != null && ordinal > 0) {
            if (searchStr.length() == 0) {
                return lastIndex ? str.length() : 0;
            } else {
                int found = 0;
                int index = lastIndex ? str.length() : -1;

                do {
                    if (lastIndex) {
                        index = CharSequenceUtils.lastIndexOf(str, searchStr, index - searchStr.length());
                    } else {
                        index = CharSequenceUtils.indexOf(str, searchStr, index + searchStr.length());
                    }

                    if (index < 0) {
                        return index;
                    }

                    ++found;
                } while (found < ordinal);

                return index;
            }
        } else {
            return -1;
        }
    }

    public static int indexOfIgnoreCase(CharSequence str, CharSequence searchStr) {
        return indexOfIgnoreCase(str, searchStr, 0);
    }

    public static int indexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos) {
        if (str != null && searchStr != null) {
            if (startPos < 0) {
                startPos = 0;
            }

            int endLimit = str.length() - searchStr.length() + 1;
            if (startPos > endLimit) {
                return -1;
            } else if (searchStr.length() == 0) {
                return startPos;
            } else {
                for (int i = startPos; i < endLimit; ++i) {
                    if (CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, searchStr.length())) {
                        return i;
                    }
                }

                return -1;
            }
        } else {
            return -1;
        }
    }

    public static int lastIndexOf(CharSequence seq, int searchChar) {
        return isEmpty(seq) ? -1 : CharSequenceUtils.lastIndexOf(seq, searchChar, seq.length());
    }

    public static int lastIndexOf(CharSequence seq, int searchChar, int startPos) {
        return isEmpty(seq) ? -1 : CharSequenceUtils.lastIndexOf(seq, searchChar, startPos);
    }

    public static int lastIndexOf(CharSequence seq, CharSequence searchSeq) {
        return seq != null && searchSeq != null ? CharSequenceUtils.lastIndexOf(seq, searchSeq, seq.length()) : -1;
    }

    public static int lastOrdinalIndexOf(CharSequence str, CharSequence searchStr, int ordinal) {
        return ordinalIndexOf(str, searchStr, ordinal, true);
    }

    public static int lastIndexOf(CharSequence seq, CharSequence searchSeq, int startPos) {
        return seq != null && searchSeq != null ? CharSequenceUtils.lastIndexOf(seq, searchSeq, startPos) : -1;
    }

    public static int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr) {
        return str != null && searchStr != null ? lastIndexOfIgnoreCase(str, searchStr, str.length()) : -1;
    }

    public static int lastIndexOfIgnoreCase(CharSequence str, CharSequence searchStr, int startPos) {
        if (str != null && searchStr != null) {
            if (startPos > str.length() - searchStr.length()) {
                startPos = str.length() - searchStr.length();
            }

            if (startPos < 0) {
                return -1;
            } else if (searchStr.length() == 0) {
                return startPos;
            } else {
                for (int i = startPos; i >= 0; --i) {
                    if (CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, searchStr.length())) {
                        return i;
                    }
                }

                return -1;
            }
        } else {
            return -1;
        }
    }

    public static boolean contains(CharSequence seq, int searchChar) {
        return isEmpty(seq) ? false : CharSequenceUtils.indexOf(seq, searchChar, 0) >= 0;
    }

    public static boolean contains(CharSequence seq, CharSequence searchSeq) {
        return seq != null && searchSeq != null ? CharSequenceUtils.indexOf(seq, searchSeq, 0) >= 0 : false;
    }

    public static boolean containsIgnoreCase(CharSequence str, CharSequence searchStr) {
        if (str != null && searchStr != null) {
            int len = searchStr.length();
            int max = str.length() - len;

            for (int i = 0; i <= max; ++i) {
                if (CharSequenceUtils.regionMatches(str, true, i, searchStr, 0, len)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    public static String substring(String str, int start) {
        if (str == null) {
            return null;
        } else {
            if (start < 0) {
                start += str.length();
            }

            if (start < 0) {
                start = 0;
            }

            return start > str.length() ? "" : str.substring(start);
        }
    }

    public static String substring(String str, int start, int end) {
        if (str == null) {
            return null;
        } else {
            if (end < 0) {
                end += str.length();
            }

            if (start < 0) {
                start += str.length();
            }

            if (end > str.length()) {
                end = str.length();
            }

            if (start > end) {
                return "";
            } else {
                if (start < 0) {
                    start = 0;
                }

                if (end < 0) {
                    end = 0;
                }

                return str.substring(start, end);
            }
        }
    }

    public static String[] split(String str) {
        return split(str, (String) null, -1);
    }

    public static String[] split(String str, char separatorChar) {
        return splitWorker(str, separatorChar, false);
    }

    public static String[] split(String str, String separatorChars) {
        return splitWorker(str, separatorChars, -1, false);
    }

    public static String[] split(String str, String separatorChars, int max) {
        return splitWorker(str, separatorChars, max, false);

    }

    private static String[] splitWorker(String str, char separatorChar, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        } else {
            int len = str.length();
            if (len == 0) {
                return new String[0];
            } else {
                ArrayList list = new ArrayList();
                int i = 0;
                int start = 0;
                boolean match = false;
                boolean lastMatch = false;

                while (true) {
                    while (i < len) {
                        if (str.charAt(i) == separatorChar) {
                            if (match || preserveAllTokens) {
                                list.add(str.substring(start, i));
                                match = false;
                                lastMatch = true;
                            }

                            ++i;
                            start = i;
                        } else {
                            lastMatch = false;
                            match = true;
                            ++i;
                        }
                    }

                    if (match || preserveAllTokens && lastMatch) {
                        list.add(str.substring(start, i));
                    }

                    return (String[]) list.toArray(new String[list.size()]);
                }
            }
        }
    }

    private static String[] splitWorker(String str, String separatorChars, int max, boolean preserveAllTokens) {
        if (str == null) {
            return null;
        } else {
            int len = str.length();
            if (len == 0) {
                return new String[0];
            } else {
                ArrayList list = new ArrayList();
                int sizePlus1 = 1;
                int i = 0;
                int start = 0;
                boolean match = false;
                boolean lastMatch = false;
                if (separatorChars != null) {
                    if (separatorChars.length() != 1) {
                        label87:
                        while (true) {
                            while (true) {
                                if (i >= len) {
                                    break label87;
                                }

                                if (separatorChars.indexOf(str.charAt(i)) >= 0) {
                                    if (match || preserveAllTokens) {
                                        lastMatch = true;
                                        if (sizePlus1++ == max) {
                                            i = len;
                                            lastMatch = false;
                                        }

                                        list.add(str.substring(start, i));
                                        match = false;
                                    }

                                    ++i;
                                    start = i;
                                } else {
                                    lastMatch = false;
                                    match = true;
                                    ++i;
                                }
                            }
                        }
                    } else {
                        char sep = separatorChars.charAt(0);

                        label71:
                        while (true) {
                            while (true) {
                                if (i >= len) {
                                    break label71;
                                }

                                if (str.charAt(i) == sep) {
                                    if (match || preserveAllTokens) {
                                        lastMatch = true;
                                        if (sizePlus1++ == max) {
                                            i = len;
                                            lastMatch = false;
                                        }

                                        list.add(str.substring(start, i));
                                        match = false;
                                    }

                                    ++i;
                                    start = i;
                                } else {
                                    lastMatch = false;
                                    match = true;
                                    ++i;
                                }
                            }
                        }
                    }
                } else {
                    label103:
                    while (true) {
                        while (true) {
                            if (i >= len) {
                                break label103;
                            }

                            if (Character.isWhitespace(str.charAt(i))) {
                                if (match || preserveAllTokens) {
                                    lastMatch = true;
                                    if (sizePlus1++ == max) {
                                        i = len;
                                        lastMatch = false;
                                    }

                                    list.add(str.substring(start, i));
                                    match = false;
                                }

                                ++i;
                                start = i;
                            } else {
                                lastMatch = false;
                                match = true;
                                ++i;
                            }
                        }
                    }
                }

                if (match || preserveAllTokens && lastMatch) {
                    list.add(str.substring(start, i));
                }

                return (String[]) list.toArray(new String[list.size()]);
            }
        }
    }

    public static boolean endsWith(CharSequence str, CharSequence suffix) {
        return endsWith(str, suffix, false);
    }

    public static boolean endsWithIgnoreCase(CharSequence str, CharSequence suffix) {
        return endsWith(str, suffix, true);
    }

    private static boolean endsWith(CharSequence str, CharSequence suffix, boolean ignoreCase) {
        if(str != null && suffix != null) {
            if(suffix.length() > str.length()) {
                return false;
            } else {
                int strOffset = str.length() - suffix.length();
                return CharSequenceUtils.regionMatches(str, ignoreCase, strOffset, suffix, 0, suffix.length());
            }
        } else {
            return str == null && suffix == null;
        }
    }


    public static boolean endsWithAny(CharSequence string, CharSequence... searchStrings) {
        if(!isEmpty(string) && isEmpty(searchStrings)) {
            CharSequence[] var2 = searchStrings;
            int var3 = searchStrings.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                CharSequence searchString = var2[var4];
                if(endsWith(string, searchString)) {
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    private static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }


}
