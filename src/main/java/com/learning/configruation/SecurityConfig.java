package com.learning.configruation;

import com.learning.model.CustomOAuth2User;
import com.learning.model.User;
import com.learning.service.CustomOAuth2UserService;
import com.learning.service.CustomUserDetailsService;
import com.learning.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfig {

    CustomUserDetailsService customUserDetailsService;
    UserService userService;

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler failureHandler() {
        return new SimpleUrlAuthenticationFailureHandler("/login?error");
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((request) ->
                        request.requestMatchers("/login", "/saveOrUpdate", "/oauth2/**" , "/resources/**").permitAll()
                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .requestMatchers(("/user/**")).hasAuthority("USER")
                                .anyRequest().authenticated())
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                        .loginPage("/login")
                        .loginProcessingUrl("/checklogin")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .successHandler(successHandler()) // thuc hien redirect khi login success
                        .failureHandler(failureHandler())) // thuc hien redirect khi login fail
                .logout(logoutConfigurer -> logoutConfigurer
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true))
//                .oauth2Login(
//                        oauth -> oauth
//                                .loginPage("/login")
//                                .successHandler(new AuthenticationSuccessHandler() {
//
//                                    @Override
//                                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                                                        Authentication authentication) throws IOException, ServletException {
//                                        //CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
//                                        OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();
//
//                                        //check va tao tai khoan cho user khi dang nhap with google
//                                        String email = oauthUser.getAttribute("email");
//                                        userService.processOAuthPostLogin(email);
//
//                                        //Get cac quyen
//                                        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//                                        System.out.println("Authorities: " + authorities);
//
//                                        // Tạo một danh sách mới để lưu các quyền
//                                        List<GrantedAuthority> updatedAuthorities = new ArrayList<>();
//
//                                        // Thêm quyền USER vào trong danh sách mới
//                                        updatedAuthorities.add(new SimpleGrantedAuthority("USER"));
//
//                                        // Sao chép các quyền còn lại vào danh sách mới, loại bỏ giá trị "USER" nếu đã tồn tại
//                                        for (GrantedAuthority authority : authorities) {
//                                            if (!authority.getAuthority().equals("USER")) {
//                                                updatedAuthorities.add(authority);
//                                            }
//                                        }
//
//                                        // Cập nhật authorities thành danh sách mới đã thêm quyền USER
//                                        authorities = updatedAuthorities;
//
//                                        System.out.println("Authorities: " + authorities);
//                                        System.out.println("da phan quyen user1");
//
//                                        //check quyen va redirect
//                                        if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
//                                            response.sendRedirect("/admin");
//                                        } else if (authorities.contains(new SimpleGrantedAuthority("USER"))) {
//                                            System.out.println("da phan quyen user2");
//                                            response.sendRedirect("/user/home");
//                                        }
//                                        else {
//                                            System.out.println("khong phan quyen dc");
//                                        }
//                                    }
//                                }
//
//
//                                )
//                                .userInfoEndpoint(userInfoEndpoint  -> userInfoEndpoint.userService(oauthUserService))
//
//                )
                .csrf(AbstractHttpConfigurer::disable)
                .userDetailsService(customUserDetailsService);
        return httpSecurity.build();
    }

    @Autowired
    private CustomOAuth2UserService oauthUserService;


    public AuthenticationSuccessHandler successHandler() {
        return new SimpleUrlAuthenticationSuccessHandler() {
            private final Logger logger = LoggerFactory.getLogger("AuthenticationSuccessHandler");

            @Override
            protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                HttpSession session = request.getSession();
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                session.setAttribute("userDetails", userDetails);

                // Hiển thị thông tin người dùng ra console
                logger.info("User '{}' logged in successfully.", userDetails.getUsername());

                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                System.out.println("Authorities: " + authorities);
                if (authorities.contains(new SimpleGrantedAuthority("ADMIN"))) {
                    response.sendRedirect("/admin");
                } else if (authorities.contains(new SimpleGrantedAuthority("USER"))) {
                    response.sendRedirect("/user/home");
                } else {
                    super.handle(request, response, authentication);
                }
            }
        };
    }



    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

}
