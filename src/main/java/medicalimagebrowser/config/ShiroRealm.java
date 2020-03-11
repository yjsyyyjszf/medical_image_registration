package medicalimagebrowser.config;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import medicalimagebrowser.entity.Role;
import medicalimagebrowser.entity.User;
import medicalimagebrowser.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ShiroRealm extends AuthorizingRealm {

    @Lazy
    private final UserRepository userRepository;

    public ShiroRealm(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*Optional<User> optionalUser = userRepository.findByUsername((String) principals.getPrimaryPrincipal());
        if (optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(convertRoles(user.getRoles()));
        simpleAuthorizationInfo.addStringPermissions(convertPermissions(user.getRoles()));
        return simpleAuthorizationInfo;*/
        return null;
    }

    private Set<String> convertRoles(List<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }

    private Set<String> convertPermissions(List<Role> roles) {
        Set<String> permissionSet = new HashSet<>();
        roles.forEach(role -> role.getPermissions().forEach(permission -> permissionSet.add(permission.getName())));
        return permissionSet;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UnknownAccountException("用户名不存在");
        }
        User user = optionalUser.get();
        if (user.getLocked()) {
            throw new LockedAccountException("用户被锁定");
        }
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
    }

}
