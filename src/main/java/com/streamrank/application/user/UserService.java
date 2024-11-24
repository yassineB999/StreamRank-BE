package com.streamrank.application.user;

import com.streamrank.application.user.record.request.UserSignInDTO;
import com.streamrank.application.user.record.request.UserSignUpDTO;
import com.streamrank.domain.user.model.User;

public interface UserService {
    Boolean existsByUserNameOrEmail(String userName, String email);
    Object signInUser(UserSignInDTO userToSignIn);
    Object signUpUser(UserSignUpDTO userSignUpDTO);
    User getMyInfoByUserName(String userName);
}
