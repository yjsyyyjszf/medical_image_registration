package medicalimagebrowser.model;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;


public class LoginModel implements AuthenticationToken {

    private String username;

    private String password;

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginModel [username=" + username + ", password=" + password + "]";
	}
    
    
}
