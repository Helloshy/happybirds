package cheanxin.service;

import cheanxin.domain.PostAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

/**
 * 岗位权限service类
 * 权限是和岗位绑定的，如果要给用户添加某种权限，先要给用户添加对应的岗位
 * Created by Jawinton on 16/12/21.
 */
public interface PostAuthorityService extends UserDetailsService {
    /**
     * 通过岗位id列表获取岗位权限详细信息列表
     * @param postIds
     * @return
     */
    List<PostAuthority> list(Collection<Long> postIds);
}
