package buptworker.entity;

/**
 * @author : [Xieyx]
 * @version : [v1.0]
 * @description : [商家信息简介类，由于用户首页]
 * @createTime : [2024/9/3 20:18]
 * @updateUser : [86183]
 * @updateTime : [2024/9/3 20:18]
 * @updateRemark : [说明本次修改内容]
 */


public class MchIntro {
    private Integer id;
    private String name;
    private String intro;
    private Integer label;
    private String url;

    public MchIntro() {
    }

    public MchIntro(Integer id, String name, String intro, Integer label, String url) {
        this.id = id;
        this.name = name;
        this.intro = intro;
        this.label = label;
        this.url = url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getLabel() {
        return label;
    }

    public void setLabel(Integer label) {
        this.label = label;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "MchIntro{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", label=" + label +
                ", url='" + url + '\'' +
                '}';
    }

}
