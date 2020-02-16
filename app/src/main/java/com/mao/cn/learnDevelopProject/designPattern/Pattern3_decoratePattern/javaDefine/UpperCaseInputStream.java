package com.mao.cn.learnDevelopProject.designPattern.Pattern3_decoratePattern.javaDefine;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhangkun
 * @time 2020-02-16 18:11
 */
public class UpperCaseInputStream extends FilterInputStream {

    protected UpperCaseInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        int c = super.read();
        return c == -1 ? c : Character.toUpperCase((char) c);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = super.read(b, off, len);
        for (int i = 0; i < result; i++) {
            b[i] = (byte) Character.toUpperCase((char) b[i]);
        }
        return result;
    }
}
