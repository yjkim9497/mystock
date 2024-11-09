package com.stock.mystock.domain.user.service;

import com.stock.mystock.common.exception.CustomException;
import com.stock.mystock.common.response.ErrorCode;
import com.stock.mystock.domain.user.entity.User;
import com.stock.mystock.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
        User user = userRepository.findById(Long.parseLong(identifier)).orElseThrow(()->
                new CustomException(ErrorCode.USER_NOT_FOUND));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getName())      // User 엔티티의 유저명 사용
                .password(user.getPassword())      // User 엔티티의 암호 사용
                .build();
    }
}
