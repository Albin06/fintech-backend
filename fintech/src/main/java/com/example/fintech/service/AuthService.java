package com.example.fintech.service;

import com.example.fintech.entity.*;
import com.example.fintech.exception.CustomException;
import com.example.fintech.repository.*;
import com.example.fintech.dto.*;
import com.example.fintech.config.JwtUtil;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final AccountRepository accountRepository;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       OtpRepository otpRepository,
                       AccountRepository accountRepository,
                       JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.otpRepository = otpRepository;
        this.accountRepository = accountRepository;
        this.jwtUtil = jwtUtil;
    }

    public String register(RegisterRequest request) {

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setMobile(request.getMobile());
        user.setPassword(request.getPassword());
        user.setStatus("PENDING");

        userRepository.save(user);

        String otp = String.valueOf(1000 + new Random().nextInt(9000));

        otpRepository.save(new Otp(null, request.getMobile(), otp));

        return "OTP sent: " + otp;
    }

    public String verifyOtp(OtpVerifyRequest request) {

        Otp otp = otpRepository.findByMobile(request.getMobile())
                .orElseThrow(() -> new CustomException("OTP not found"));

        if (!otp.getOtp().equals(request.getOtp())) {
            throw new CustomException("Invalid OTP");
        }

        User user = userRepository.findByMobile(request.getMobile())
                .orElseThrow(() -> new CustomException("User not found"));

        user.setStatus("ACTIVE");
        userRepository.save(user);

        Account acc = new Account(null, user.getId(), 1000.0);
        accountRepository.save(acc);

        return "User verified successfully";
    }

    public String login(LoginRequest request) {

        User user = userRepository.findByEmailOrMobile(request.getUsername(), request.getUsername())
                .orElseThrow(() -> new CustomException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new CustomException("Invalid credentials");
        }

        if (!user.getStatus().equals("ACTIVE")) {
            throw new CustomException("User not verified");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}