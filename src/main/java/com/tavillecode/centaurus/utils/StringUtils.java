package com.tavillecode.centaurus.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Interface39
 * @version 1.0
 * @description: TODO
 * @date 2025/3/29 21:06
 */
public class StringUtils {
    public static String unicodeToString(String unicode) {
        StringBuffer sb = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int index = Integer.parseInt(hex[i], 16);
            sb.append((char) index);
        }
        return sb.toString();
    }

    public static boolean isChinese(char c) {
        return String.valueOf(c).matches("[\u4E00-\u9FA5]");
    }

    public static int getSpaceLength(String line_1) {
        int length = 0;
        for (int i = 0;i < line_1.length();i++) {
            if (isChinese(line_1.charAt(i))) {
                length += 8;
            }
            else {
                length += 3;
            }
        }
        return length;
    }

    public static String getSpaceStringGreedily(String line_1) {
        int length = getSpaceLength(line_1);
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        while (length!=0) {
            if (length - arr[index] < 0) {
                index++;
                continue;
            }
            length -= arr[index];
            stringBuilder.append(space.get(arr[index]));
        }
        return stringBuilder.toString();
    }

    private static int[] arr;
    private static Map<Integer,String> space;

    static {
        arr = new int[] {
                128,64,32,8,7,6,5,4,3,2,1
        };

        space = new HashMap<>();
        space.put(128,"\uF80C");
        space.put(64,"\uF80B");
        space.put(32,"\uF80A");
        space.put(16,"\uF809");
        space.put(8,"\uF808");
        space.put(7,"\uF807");
        space.put(6,"\uF806");
        space.put(5,"\uF805");
        space.put(4,"\uF804");
        space.put(3,"\uF803");
        space.put(2,"\uF802");
        space.put(1,"\uF801");
    }
}
