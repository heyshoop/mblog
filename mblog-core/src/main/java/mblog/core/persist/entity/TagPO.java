/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package mblog.core.persist.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author langhsu
 * 
 */
@Entity
@Table(name = "mto_tags")
public class TagPO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name", unique = true, length = 32)
	private String name;
	
	@Column(name = "last_post_id")
	private long lastPostId;

	private int featured; // 是否推荐
	
	private int posts;   // 文章数
	private int hots;    // 热度
	private int locked;  // 是否锁定
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPosts() {
		return posts;
	}

	public void setPosts(int posts) {
		this.posts = posts;
	}

	public int getHots() {
		return hots;
	}

	public void setHots(int hots) {
		this.hots = hots;
	}

	public long getLastPostId() {
		return lastPostId;
	}

	public void setLastPostId(long lastPostId) {
		this.lastPostId = lastPostId;
	}

	public int getFeatured() {
		return featured;
	}

	public void setFeatured(int featured) {
		this.featured = featured;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
	}

}
