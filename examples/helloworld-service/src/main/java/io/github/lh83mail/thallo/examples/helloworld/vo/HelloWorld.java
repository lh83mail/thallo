package io.github.lh83mail.thallo.examples.helloworld.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class HelloWorld {
    private String content;
    private LocalDateTime updateAt;
}
