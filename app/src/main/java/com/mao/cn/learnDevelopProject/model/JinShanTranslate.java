package com.mao.cn.learnDevelopProject.model;

import java.io.Serializable;
import java.util.List;

/**
 * author:  zhangkun .
 * date:    on 2017/10/30.
 */

public class JinShanTranslate implements Serializable{


    /**
     * word_name : work
     * is_CRI : 1
     * exchange : {"word_pl":["works"],"word_past":["worked"],"word_done":["worked"],"word_ing":["working"],"word_third":["works"],"word_er":"","word_est":""}
     * symbols : [{"ph_en":"wɜ:k","ph_am":"wɜrk","ph_other":"","ph_en_mp3":"http://res.iciba.com/resource/amp3/0/0/67/e9/67e92c8765a9bc7fb2d335c459de9eb5.mp3","ph_am_mp3":"http://res.iciba.com/resource/amp3/1/0/67/e9/67e92c8765a9bc7fb2d335c459de9eb5.mp3","ph_tts_mp3":"http://res-tts.iciba.com/6/7/e/67e92c8765a9bc7fb2d335c459de9eb5.mp3","parts":[{"part":"vt.& vi.","means":["使工作","使运作","操作","使产生效果"]},{"part":"n.","means":["工作，操作","著作","工厂","行为，事业"]},{"part":"vt.","means":["使工作","操作","经营","使缓慢前进"]}]}]
     * items : [""]
     */

    private String word_name;
    private int is_CRI;
    private ExchangeBean exchange;
    private List<SymbolsBean> symbols;
    private List<String> items;

    public String getWord_name() {
        return word_name;
    }

    public void setWord_name(String word_name) {
        this.word_name = word_name;
    }

    public int getIs_CRI() {
        return is_CRI;
    }

    public void setIs_CRI(int is_CRI) {
        this.is_CRI = is_CRI;
    }

    public ExchangeBean getExchange() {
        return exchange;
    }

    public void setExchange(ExchangeBean exchange) {
        this.exchange = exchange;
    }

    public List<SymbolsBean> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<SymbolsBean> symbols) {
        this.symbols = symbols;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public static class ExchangeBean {
        /**
         * word_pl : ["works"]
         * word_past : ["worked"]
         * word_done : ["worked"]
         * word_ing : ["working"]
         * word_third : ["works"]
         * word_er :
         * word_est :
         */

        private String word_er;
        private String word_est;
        private List<String> word_pl;
        private List<String> word_past;
        private List<String> word_done;
        private List<String> word_ing;
        private List<String> word_third;

        public String getWord_er() {
            return word_er;
        }

        public void setWord_er(String word_er) {
            this.word_er = word_er;
        }

        public String getWord_est() {
            return word_est;
        }

        public void setWord_est(String word_est) {
            this.word_est = word_est;
        }

        public List<String> getWord_pl() {
            return word_pl;
        }

        public void setWord_pl(List<String> word_pl) {
            this.word_pl = word_pl;
        }

        public List<String> getWord_past() {
            return word_past;
        }

        public void setWord_past(List<String> word_past) {
            this.word_past = word_past;
        }

        public List<String> getWord_done() {
            return word_done;
        }

        public void setWord_done(List<String> word_done) {
            this.word_done = word_done;
        }

        public List<String> getWord_ing() {
            return word_ing;
        }

        public void setWord_ing(List<String> word_ing) {
            this.word_ing = word_ing;
        }

        public List<String> getWord_third() {
            return word_third;
        }

        public void setWord_third(List<String> word_third) {
            this.word_third = word_third;
        }
    }

    public static class SymbolsBean {
        /**
         * ph_en : wɜ:k
         * ph_am : wɜrk
         * ph_other :
         * ph_en_mp3 : http://res.iciba.com/resource/amp3/0/0/67/e9/67e92c8765a9bc7fb2d335c459de9eb5.mp3
         * ph_am_mp3 : http://res.iciba.com/resource/amp3/1/0/67/e9/67e92c8765a9bc7fb2d335c459de9eb5.mp3
         * ph_tts_mp3 : http://res-tts.iciba.com/6/7/e/67e92c8765a9bc7fb2d335c459de9eb5.mp3
         * parts : [{"part":"vt.& vi.","means":["使工作","使运作","操作","使产生效果"]},{"part":"n.","means":["工作，操作","著作","工厂","行为，事业"]},{"part":"vt.","means":["使工作","操作","经营","使缓慢前进"]}]
         */

        private String ph_en;
        private String ph_am;
        private String ph_other;
        private String ph_en_mp3;
        private String ph_am_mp3;
        private String ph_tts_mp3;
        private List<PartsBean> parts;

        public String getPh_en() {
            return ph_en;
        }

        public void setPh_en(String ph_en) {
            this.ph_en = ph_en;
        }

        public String getPh_am() {
            return ph_am;
        }

        public void setPh_am(String ph_am) {
            this.ph_am = ph_am;
        }

        public String getPh_other() {
            return ph_other;
        }

        public void setPh_other(String ph_other) {
            this.ph_other = ph_other;
        }

        public String getPh_en_mp3() {
            return ph_en_mp3;
        }

        public void setPh_en_mp3(String ph_en_mp3) {
            this.ph_en_mp3 = ph_en_mp3;
        }

        public String getPh_am_mp3() {
            return ph_am_mp3;
        }

        public void setPh_am_mp3(String ph_am_mp3) {
            this.ph_am_mp3 = ph_am_mp3;
        }

        public String getPh_tts_mp3() {
            return ph_tts_mp3;
        }

        public void setPh_tts_mp3(String ph_tts_mp3) {
            this.ph_tts_mp3 = ph_tts_mp3;
        }

        public List<PartsBean> getParts() {
            return parts;
        }

        public void setParts(List<PartsBean> parts) {
            this.parts = parts;
        }

        public static class PartsBean {
            /**
             * part : vt.& vi.
             * means : ["使工作","使运作","操作","使产生效果"]
             */

            private String part;
            private List<String> means;

            public String getPart() {
                return part;
            }

            public void setPart(String part) {
                this.part = part;
            }

            public List<String> getMeans() {
                return means;
            }

            public void setMeans(List<String> means) {
                this.means = means;
            }
        }
    }

    @Override
    public String toString() {
        return "JinShanTranslate{" +
                "word_name='" + word_name + '\'' +
                ", is_CRI=" + is_CRI +
                ", exchange=" + exchange +
                ", symbols=" + symbols +
                ", items=" + items +
                '}';
    }
}
