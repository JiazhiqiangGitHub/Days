package lanou.days.bean;

import java.util.List;

/**
 * Created by 贾志强 on 16/11/28.
 */

public class ReadHeadBean {

    /**
     * code : 200
     * data : {"banners":[{"ad_monitors":[],"channel":"all","id":797,"image_url":"http://img03.liwushuo.com/image/161125/6ncpziteq.jpg-w720","order":800,"status":0,"target":{"banner_image_url":"http://img02.liwushuo.com/image/161125/3x84fg5an.jpg-w300","banner_webp_url":"http://img02.liwushuo.com/image/161125/3x84fg5an.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img03.liwushuo.com/image/161125/323reusu9.jpg-w720","cover_webp_url":"http://img03.liwushuo.com/image/161125/323reusu9.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1480065793,"id":396,"posts_count":7,"status":1,"subtitle":"这才是冬天的正确打开方式","title":"就爱毛茸茸","updated_at":1480066007},"target_id":396,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=396","type":"collection","webp_url":"http://img03.liwushuo.com/image/161125/6ncpziteq.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":799,"image_url":"http://img01.liwushuo.com/image/161125/pu8lzr4py.jpg-w720","order":700,"status":0,"target":{"banner_image_url":"http://img02.liwushuo.com/image/161125/avrkchqbf.jpg-w300","banner_webp_url":"http://img02.liwushuo.com/image/161125/avrkchqbf.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img01.liwushuo.com/image/161125/nyemg5s8a.jpg-w720","cover_webp_url":"http://img01.liwushuo.com/image/161125/nyemg5s8a.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1480065762,"id":395,"posts_count":8,"status":1,"subtitle":"完美身形帮你\u201c装\u201d出来","title":"心机穿搭术","updated_at":1480067525},"target_id":395,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=395","type":"collection","webp_url":"http://img01.liwushuo.com/image/161125/pu8lzr4py.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":792,"image_url":"http://img01.liwushuo.com/image/161124/g2693c0ds.jpg-w720","order":600,"status":0,"target":{"banner_image_url":"http://img03.liwushuo.com/image/161122/tbrig8f88.jpg-w300","banner_webp_url":"http://img03.liwushuo.com/image/161122/tbrig8f88.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img02.liwushuo.com/image/161122/2zr3jhj59.jpg-w720","cover_webp_url":"http://img02.liwushuo.com/image/161122/2zr3jhj59.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1479807085,"id":394,"posts_count":12,"status":1,"subtitle":"英亚美亚来袭，亚马逊海外购prime会员无限次包邮！","title":"亚马逊海外购","updated_at":1480236866},"target_id":394,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=394","type":"collection","webp_url":"http://img01.liwushuo.com/image/161124/g2693c0ds.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":795,"image_url":"http://img03.liwushuo.com/image/161125/7u302fgrk.jpg-w720","order":500,"status":0,"target_id":1046677,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=post&post_id=1046677","type":"post","webp_url":"http://img03.liwushuo.com/image/161125/7u302fgrk.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":796,"image_url":"http://img01.liwushuo.com/image/161125/0uc37872y.jpg-w720","order":300,"status":0,"target_id":1046708,"target_type":"url","target_url":"liwushuo:///page?page_action=navigation&login=false&type=post&post_id=1046708","type":"post","webp_url":"http://img01.liwushuo.com/image/161125/0uc37872y.jpg?imageView2/2/w/720/q/85/format/webp"},{"ad_monitors":[],"channel":"all","id":730,"image_url":"http://img01.liwushuo.com/image/161114/74ozwewrz.jpg-w720","order":0,"status":0,"target_id":null,"target_url":"liwushuo:///page?type=dailylucky","type":"url","webp_url":"http://img01.liwushuo.com/image/161114/74ozwewrz.jpg?imageView2/2/w/720/q/85/format/webp"}]}
     * message : OK
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * ad_monitors : []
         * channel : all
         * id : 797
         * image_url : http://img03.liwushuo.com/image/161125/6ncpziteq.jpg-w720
         * order : 800
         * status : 0
         * target : {"banner_image_url":"http://img02.liwushuo.com/image/161125/3x84fg5an.jpg-w300","banner_webp_url":"http://img02.liwushuo.com/image/161125/3x84fg5an.jpg?imageView2/2/w/300/q/85/format/webp","cover_image_url":"http://img03.liwushuo.com/image/161125/323reusu9.jpg-w720","cover_webp_url":"http://img03.liwushuo.com/image/161125/323reusu9.jpg?imageView2/2/w/720/q/85/format/webp","created_at":1480065793,"id":396,"posts_count":7,"status":1,"subtitle":"这才是冬天的正确打开方式","title":"就爱毛茸茸","updated_at":1480066007}
         * target_id : 396
         * target_type : url
         * target_url : liwushuo:///page?page_action=navigation&login=false&type=topic&topic_id=396
         * type : collection
         * webp_url : http://img03.liwushuo.com/image/161125/6ncpziteq.jpg?imageView2/2/w/720/q/85/format/webp
         */

        private List<BannersBean> banners;

        public List<BannersBean> getBanners() {
            return banners;
        }

        public void setBanners(List<BannersBean> banners) {
            this.banners = banners;
        }

        public static class BannersBean {
            private String channel;
            private int id;
            private String image_url;
            private int order;
            private int status;
            /**
             * banner_image_url : http://img02.liwushuo.com/image/161125/3x84fg5an.jpg-w300
             * banner_webp_url : http://img02.liwushuo.com/image/161125/3x84fg5an.jpg?imageView2/2/w/300/q/85/format/webp
             * cover_image_url : http://img03.liwushuo.com/image/161125/323reusu9.jpg-w720
             * cover_webp_url : http://img03.liwushuo.com/image/161125/323reusu9.jpg?imageView2/2/w/720/q/85/format/webp
             * created_at : 1480065793
             * id : 396
             * posts_count : 7
             * status : 1
             * subtitle : 这才是冬天的正确打开方式
             * title : 就爱毛茸茸
             * updated_at : 1480066007
             */

            private TargetBean target;
            private int target_id;
            private String target_type;
            private String target_url;
            private String type;
            private String webp_url;
            private List<?> ad_monitors;

            public String getChannel() {
                return channel;
            }

            public void setChannel(String channel) {
                this.channel = channel;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public TargetBean getTarget() {
                return target;
            }

            public void setTarget(TargetBean target) {
                this.target = target;
            }

            public int getTarget_id() {
                return target_id;
            }

            public void setTarget_id(int target_id) {
                this.target_id = target_id;
            }

            public String getTarget_type() {
                return target_type;
            }

            public void setTarget_type(String target_type) {
                this.target_type = target_type;
            }

            public String getTarget_url() {
                return target_url;
            }

            public void setTarget_url(String target_url) {
                this.target_url = target_url;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getWebp_url() {
                return webp_url;
            }

            public void setWebp_url(String webp_url) {
                this.webp_url = webp_url;
            }

            public List<?> getAd_monitors() {
                return ad_monitors;
            }

            public void setAd_monitors(List<?> ad_monitors) {
                this.ad_monitors = ad_monitors;
            }

            public static class TargetBean {
                private String banner_image_url;
                private String banner_webp_url;
                private String cover_image_url;
                private String cover_webp_url;
                private int created_at;
                private int id;
                private int posts_count;
                private int status;
                private String subtitle;
                private String title;
                private int updated_at;

                public String getBanner_image_url() {
                    return banner_image_url;
                }

                public void setBanner_image_url(String banner_image_url) {
                    this.banner_image_url = banner_image_url;
                }

                public String getBanner_webp_url() {
                    return banner_webp_url;
                }

                public void setBanner_webp_url(String banner_webp_url) {
                    this.banner_webp_url = banner_webp_url;
                }

                public String getCover_image_url() {
                    return cover_image_url;
                }

                public void setCover_image_url(String cover_image_url) {
                    this.cover_image_url = cover_image_url;
                }

                public String getCover_webp_url() {
                    return cover_webp_url;
                }

                public void setCover_webp_url(String cover_webp_url) {
                    this.cover_webp_url = cover_webp_url;
                }

                public int getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(int created_at) {
                    this.created_at = created_at;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getPosts_count() {
                    return posts_count;
                }

                public void setPosts_count(int posts_count) {
                    this.posts_count = posts_count;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public String getSubtitle() {
                    return subtitle;
                }

                public void setSubtitle(String subtitle) {
                    this.subtitle = subtitle;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getUpdated_at() {
                    return updated_at;
                }

                public void setUpdated_at(int updated_at) {
                    this.updated_at = updated_at;
                }
            }
        }
    }
}
