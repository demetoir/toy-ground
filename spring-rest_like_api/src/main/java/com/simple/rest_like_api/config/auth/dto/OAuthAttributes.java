package com.simple.rest_like_api.config.auth.dto;

import com.simple.rest_like_api.domain.user.Role;
import com.simple.rest_like_api.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
  private Map<String, Object> attributes;
  private String nameAttributeKey;
  private String email;
  private String name;
  private String picture;

  @Builder
  public OAuthAttributes(
      Map<String, Object> attributes,
      String nameAttributeKey,
      String name,
      String email,
      String picture) {
    this.attributes = attributes;
    this.name = name;
    this.nameAttributeKey = nameAttributeKey;
    this.email = email;
    this.picture = picture;
  }

  // registeredId 는 oauth 종류 구분하는 인자
  public static OAuthAttributes of(
      String registeredId, String userNameAttributeName, Map<String, Object> attributes) {

    if ("naver".equals(registeredId)) {
      return ofNaver("id", attributes);
    }

    return ofGoogle(userNameAttributeName, attributes);
  }

  public static OAuthAttributes ofGoogle(
      String userNameAttributeName, Map<String, Object> attributes) {
    return OAuthAttributes.builder()
        .name((String) attributes.get("name"))
        .email((String) attributes.get("email"))
        .picture((String) attributes.get("picture"))
        .attributes(attributes)
        .nameAttributeKey(userNameAttributeName)
        .build();
  }

  public static OAuthAttributes ofNaver(
      String userNameAttributeName, Map<String, Object> attributes) {

    System.out.println(attributes);
    Map<String, Object> response = (Map<String, Object>) attributes.get("response");

    return OAuthAttributes.builder()
        .name((String) response.get("name"))
        .email((String) response.get("email"))
        .picture((String) response.get("profileImage"))
        .attributes(response)
        .nameAttributeKey(userNameAttributeName)
        .build();
  }

  public User toEntity() {
    // 일단 테스트를 위해 role 을 guest 에서 user로 변경함
    return User.builder().name(name).email(email).picture(picture).role(Role.USER).build();
  }
}
