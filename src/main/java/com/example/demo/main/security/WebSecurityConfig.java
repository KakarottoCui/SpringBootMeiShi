package com.example.demo.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.main.service.SysUserService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private SysUserService sysUserService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(sysUserService)  	//设置用什么去查找用户,    用什么对象去查找用户
			.passwordEncoder(new BCryptPasswordEncoder()); //设置用什么去对密码加密，实例化一个密码生成器
		auth.inMemoryAuthentication().withUser("aaa").password("{noop}1234").roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
			//安全器令牌
		 	http.csrf().disable();
		 	http.headers().frameOptions().disable();    //解决iframe与安全器兼容性问题
//		 	http.logout()
//		 		.logoutSuccessUrl("/logout2")     //指定安全退出跳转页面
//		 		.invalidateHttpSession(false);		//安全退出不删除会话，
			http.formLogin()
					//登录请求被拦截
					.loginPage("/login").permitAll()
					//设置默认登录成功跳转页面
					.successForwardUrl("/main")
	                .failureUrl("/login?error");   //登录失败的页面
	        http.authorizeRequests().antMatchers("/static/**").permitAll();    //文件下的所有都了能访问
	        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
	        http.authorizeRequests().antMatchers("/home").permitAll();
	        http.authorizeRequests().antMatchers("/blog/indexBlog").permitAll();
	        http.authorizeRequests().antMatchers("/blog/fysj2").permitAll();
	        http.authorizeRequests().antMatchers("/blog/visit").permitAll();
	        http.authorizeRequests().antMatchers("/download").permitAll();
	        http.authorizeRequests().antMatchers("/sy").permitAll();
	        http.authorizeRequests().antMatchers("/gy").permitAll();
	        http.authorizeRequests().antMatchers("/blog/blogText").permitAll();
	        http.authorizeRequests().antMatchers("/system/user/save1").permitAll();
	        http.authorizeRequests().antMatchers("/wx/**").permitAll();
	        http.logout().logoutUrl("/logout").permitAll();     //退出
	        http.authorizeRequests().anyRequest().authenticated();    //除此之外的都必须通过请求验证才能访问
	}
	
}
