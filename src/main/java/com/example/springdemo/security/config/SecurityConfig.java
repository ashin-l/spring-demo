package com.example.springdemo.security.config;

import com.example.springdemo.security.exception.JWTAccessDeniedHandler;
import com.example.springdemo.security.exception.JWTAuthenticationEntryPoint;
import com.example.springdemo.security.filter.JWTAuthenticationFilter;
import com.example.springdemo.security.filter.JWTAuthorizationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lqc
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception
    // {
    // // 设置自定义的userDetailsService以及密码编码器
    // auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    // }

    // ------------ 测试代码 ----------------
    // @Autowired
    // private MyAuthenticationSuccessHandler successHandler;

    // // 1、定义用户信息服务（查询用户信息）
    // @Bean
    // public UserDetailsService userDetailsService() {
    // InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    // // 角色和权限同事存在会冲突，只会保留最后调用的
    // manager.createUser(User.withUsername("aaa").password("111").roles("r1").authorities("p1").build());
    // manager.createUser(User.withUsername("bbb").password("111").authorities("p2").roles("r2").build());
    // return manager;
    // }

    // // 2、密码编码器
    // @Bean
    // public PasswordEncoder passwordEncoder() {
    // return NoOpPasswordEncoder.getInstance();
    // }

    // // 3、配置安全拦截机制
    // // 使用 spring-security 自带表单登陆
    // @Override
    // protected void configure(HttpSecurity http) throws Exception {
    // http.csrf().disable();
    // http.authorizeRequests().antMatchers("/home",
    // "/auth/login").permitAll().antMatchers("/p1/**")
    // .hasAuthority("p1").antMatchers("/p2/**").hasRole("r2").anyRequest().authenticated();
    // // http.httpBasic();
    // http.formLogin().loginProcessingUrl("/auth/login").successHandler(successHandler);

    // }

    // ------------ 生产代码使用jwt ----------------
    // 定义用户信息服务（查询用户信息）
    @Autowired
    UserDetailsService userDetailsService;

    // 密码编码器
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 配置安全拦截机制
    // 自定义登陆
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                // 禁用 CSRF
                .csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/auth/login").permitAll()
                // 指定路径下的资源需要验证了的用户才能访问
                .antMatchers("/api/**").authenticated()
                // 其他都放行了
                .anyRequest().permitAll().and()
                // 添加自定义Filter
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                // 不需要session（不创建会话）
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 授权异常处理
                .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
                .accessDeniedHandler(new JWTAccessDeniedHandler());
        // 防止H2 web 页面的Frame 被拦截
        http.headers().frameOptions().disable();
    }
}
