package mblog.wechat.entity.message;

/**
 * @Author 阁楼麻雀
 * @Date 2016-5-13 17:28
 * @Desc 图文消息实体类
 */

public class Article {
	// 图文消息名称  
    private String Title;  
    // 图文消息描述  
    private String Description;  
    // 图片链接，支持JPG、PNG格式
    private String PicUrl;  
    // 点击图文消息跳转链接  
    private String Url;
    //图文消息缩略图的media_id，可以在基础支持-上传多媒体文件接口中获得
    private String thumb_media_id;
    //图文消息页面的内容，支持HTML标签。具备微信支付权限的公众号，可以使用a标签，其他公众号不能使用
    private String content;
    //图文消息的作者
    private String author;
    //是否显示封面，1为显示，0为不显示
    private String show_cover_pic;
  
    public String getTitle() {  
        return Title;  
    }  
  
    public void setTitle(String title) {  
        Title = title;  
    }  
  
    public String getDescription() {  
        return null == Description ? "" : Description;  
    }  
  
    public void setDescription(String description) {  
        Description = description;  
    }  
  
    public String getPicUrl() {  
        return null == PicUrl ? "" : PicUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        PicUrl = picUrl;  
    }  
  
    public String getUrl() {  
        return null == Url ? "" : Url;  
    }  
  
    public void setUrl(String url) {  
        Url = url;  
    }

    public String getThumb_media_id() {
        return thumb_media_id;
    }
    public void setThumb_media_id(String thumb_media_id) {
        this.thumb_media_id = thumb_media_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getShow_cover_pic() {
        return show_cover_pic;
    }

    public void setShow_cover_pic(String show_cover_pic) {
        this.show_cover_pic = show_cover_pic;
    }
}
