package com.group7.sys.common;

import com.group7.sys.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiverUser {
  private User user;

  //    private UserPat userPat;

  private List<String> roles;

  private List<String> permissions;
}
