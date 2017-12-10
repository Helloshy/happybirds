package cheanxin.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Jawinton on 17/1/3.
 * 用户岗位表
 */
@Entity
@Table(indexes = { @Index(name = "uk_username_post_id", columnList = "username, postId", unique = true)})
public class UserPost {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '自增id'")
    // 自增id
    private Long id;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(columnDefinition = "VARCHAR(20) COMMENT '用户名'")
    // 用户名
    private String username;

    @NotNull
    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '岗位id'")
    // 岗位id
    private Long postId;

    @NotNull
    @Column(columnDefinition = "TINYINT(1) UNSIGNED DEFAULT TRUE COMMENT '是否启用'")
    private Boolean enabled;

    @Column(columnDefinition = "INT(10) UNSIGNED COMMENT '创建时间'")
    // 创建时间
    private Long createdTime;

    public UserPost() {}

    @Override
    public String toString() {
        return "UserPost{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", postId=" + postId +
                ", enabled=" + enabled +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPost userPost = (UserPost) o;

        if (!username.equals(userPost.username)) return false;
        return postId.equals(userPost.postId);

    }

    @Override
    public int hashCode() {
        int result = username.hashCode();
        result = 31 * result + postId.hashCode();
        return result;
    }

    public UserPost(String username, Long postId, Long createdTime) {
        this.username = username;
        this.postId = postId;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }
}
