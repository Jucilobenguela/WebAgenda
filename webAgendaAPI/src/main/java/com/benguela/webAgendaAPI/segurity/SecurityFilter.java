package com.benguela.webAgendaAPI.segurity;

import com.benguela.webAgendaAPI.model.User;
import com.benguela.webAgendaAPI.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

    @Component
    @Slf4j
    public class SecurityFilter extends OncePerRequestFilter {
        @Autowired
        TokenService tokenService;
        @Autowired
       UserRepository userRepository;

        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
           try {
               String token = this.recoverToken(request);
               if(token != null){
                   String info = tokenService.validateToken(token);
                   if(info != null && !info.isEmpty()){
                      UserDetails user = userRepository.findByEmail(info);

                       UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
                               null, user.getAuthorities());
                       SecurityContextHolder.getContext().setAuthentication(authentication);
                   }
               }
               filterChain.doFilter(request, response);
           } catch (ServletException e){

               response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR ,
                       "Err in server");

           }catch (IOException e){

               response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR ,
                       "A server error has occurred. Please try again later.");
           }catch (Exception e){
               response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication credentials are incorrect, please try again");


           }

        }

        private String recoverToken(HttpServletRequest request){
            String authHeader = request.getHeader("Authorization");
            if(authHeader == null) return null;
            return authHeader.replace("Bearer","");
        }
    }

