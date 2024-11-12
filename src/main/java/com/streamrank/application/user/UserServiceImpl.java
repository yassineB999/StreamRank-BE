package com.streamrank.application.user;

import com.streamrank.application.user.record.request.UserSignInDTO;
import com.streamrank.application.user.record.request.UserSignUpDTO;
import com.streamrank.domain.user.enumeration.AccountTypeEnum;
import com.streamrank.domain.user.model.User;
import com.streamrank.domain.user.service.UserDomainService;
import com.streamrank.infrastructure.config.JWT.JwtUtil;
import com.streamrank.infrastructure.utility.Utility;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDomainService userDomainService;
    private final JwtUtil jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final Utility utility;

    @Override
    public Boolean existsByUserNameOrEmail(String userName, String email){
        return userDomainService.existsByUserNameOrEmail(userName, email);
    }

    @Override
    public Object signInUser(UserSignInDTO userToSignIn) {
        User dbUser = (userToSignIn.usernameOrEmail().contains("@") ?
                userDomainService.findByEmail(userToSignIn.usernameOrEmail()) :
                userDomainService.findByUserName(userToSignIn.usernameOrEmail())
        );

        if(dbUser == null){
            return "Invalid email or username. Please try again.";
        }

        if(!passwordEncoder.matches(userToSignIn.password(), dbUser.getPassword())){
            return "The password you entered is incorrect. Please try again or reset your password.";
        }

        Map<String, Object> data = new HashMap<>();

        data.put("token", jwtUtils.generateToken(dbUser.getUsername(), dbUser.getAuthorities()));
        data.put("rid", dbUser.getAuthorities().toString().charAt(6));
        return data;
    }

    @Override
    public Object signUpUser(UserSignUpDTO userSignUpDTO) {
        if (userDomainService.existsByUserNameOrEmail(userSignUpDTO.userName(), userSignUpDTO.email())) {
            return "Username or email already exists";
        }

        User newUser = User.builder()
                .firstName(userSignUpDTO.firstName())
                .lastName(userSignUpDTO.lastName())
                .password(passwordEncoder.encode(userSignUpDTO.password()))
                .email(userSignUpDTO.email())
                .userName(userSignUpDTO.userName())
                .dateOfBirth(userSignUpDTO.dateOfBirth())
                .accountType(AccountTypeEnum.REGULAR.toString())
                .isAdmin(false)
                .build();

        userDomainService.save(newUser);

        Map<String, Object> data = new HashMap<>();

        data.put("token", jwtUtils.generateToken(newUser.getUsername(), newUser.getAuthorities()));
        data.put("rid", newUser.getAuthorities().toString().charAt(6));
        return data;
    }
}
