package buptworker.entity;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [一句话描述该类的功能]
 * @createTime : [2024/9/6 13:10]
 * @updateUser : [86183]
 * @updateTime : [2024/9/6 13:10]
 * @updateRemark : [说明本次修改内容]
 */


public class TopPicture {
    private Integer id;
    private String url;

    public TopPicture() {
    }

    public TopPicture(Integer id, String url) {
        this.id = id;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "TopPicture{" +
                "id=" + id +
                ", url='" + url + '\'' +
                '}';
    }
}
