package lanou.days.bean;

import java.util.List;

/**
 * Created by 贾志强 on 16/11/25.
 */

public class ReadBean {

    /**
     * list : [{"dealid":42475387,"notes":"","pic":"http://p0.meituan.net/348.348/movie/be089cc7ce775581de17466955498098129498.jpg@60q","price":38,"shortTitle":"加厚吸管杯","title":"航海王 双层加厚吸管杯","value":59},{"dealid":40184882,"notes":"","pic":"http://p0.meituan.net/348.348/movie/122bada636f8ce423939837c99380e7378478.jpg@60q","price":23.8,"shortTitle":"电影《我不是潘金莲》原著小说","title":"我不是潘金莲刘震云同名电影小说","value":29.8},{"dealid":42406194,"notes":"","pic":"http://p0.meituan.net/348.348/movie/fda3c96ab831f12d8e5f1b6feef86277292625.jpg@60q","price":19.9,"shortTitle":"限量钥匙扣","title":"正版奇异博士 限量收藏钥匙扣","value":59},{"dealid":42475679,"notes":"","pic":"http://p0.meituan.net/348.348/movie/91a39a90fe4a728f39281f6dba24da9f404694.jpg@60q","price":65,"shortTitle":"带支架手机壳","title":"航海王系列手机壳--带支架","value":98},{"dealid":41745243,"notes":"","pic":"http://p0.meituan.net/348.348/movie/a41842ccaa0f56f8b72889f1b0b7f8f8242530.jpg@60q","price":37.9,"shortTitle":"漫长的中场休息","title":"漫长的中场休息李安电影原著小说","value":45},{"dealid":42476275,"notes":"","pic":"http://p1.meituan.net/348.348/movie/d80268d857e22de707d53175ef885d3559753.jpg@60q","price":33,"shortTitle":"金属羽毛书签","title":"神奇动物在哪里 金属羽毛书签","value":48},{"dealid":42476323,"notes":"","pic":"http://p0.meituan.net/348.348/movie/63dfd901030a6e36521569701814a645135376.jpg@60q","price":35,"shortTitle":"复古便签本","title":"神奇动物在哪里 复古便签本","value":59},{"dealid":41854970,"notes":"","pic":"http://p0.meituan.net/348.348/movie/5d72d5df566c09945c8796178cf2b241172181.jpg@60q","price":59,"shortTitle":"手机壳","title":"《航海王》苹果手机壳-战斗款","value":89},{"dealid":41854825,"notes":"","pic":"http://p0.meituan.net/348.348/movie/98c915fa4f053a5431f17f5db6a3b98f572733.jpg@60q","price":59,"shortTitle":"手机壳","title":"《航海王》苹果手机壳-度假版","value":89},{"dealid":42450031,"notes":"","pic":"http://p0.meituan.net/348.348/movie/0a798122846f58993007c1dd7e20c673259926.jpg@60q","price":128,"shortTitle":"笔记本","title":"中国邮政正品《航海王》笔记本","value":158}]
     * total : 1479
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private int total;
        /**
         * dealid : 42475387
         * notes :
         * pic : http://p0.meituan.net/348.348/movie/be089cc7ce775581de17466955498098129498.jpg@60q
         * price : 38
         * shortTitle : 加厚吸管杯
         * title : 航海王 双层加厚吸管杯
         * value : 59
         */

        private List<ListBean> list;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            private int dealid;
            private String notes;
            private String pic;
            private float price;
            private String shortTitle;
            private String title;
            private float value;

            public int getDealid() {
                return dealid;
            }

            public void setDealid(int dealid) {
                this.dealid = dealid;
            }

            public String getNotes() {
                return notes;
            }

            public void setNotes(String notes) {
                this.notes = notes;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public float getPrice() {
                return price;
            }

            public void setPrice(float price) {
                this.price = price;
            }

            public float getValue() {
                return value;
            }

            public void setValue(float value) {
                this.value = value;
            }

            public String getShortTitle() {
                return shortTitle;
            }

            public void setShortTitle(String shortTitle) {
                this.shortTitle = shortTitle;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }


        }
    }
}
