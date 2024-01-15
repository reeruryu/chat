package org.example.chat.dto.pushTmp;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class RequestSubscribe {
    String token;
    List<String> topicList;
}
